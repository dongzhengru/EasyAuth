package top.zhengru.sso.server.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import top.zhengru.sso.base.util.JsonUtils;
import top.zhengru.sso.server.entity.TokenContent;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * AppToken调用凭证管理
 */
public class AppTokenManager extends AbstractAppTokenManager {

    private final Logger logger = LoggerFactory.getLogger(AppTokenManager.class);
    private static final String SSO_PREFIX = "easy_auth:";
    private static final String TGT_KEY = "server_tgt:";
    private static final String APP_TOKEN_KEY = "server_app_token:";
    private StringRedisTemplate redisTemplate;

    public AppTokenManager(int appTokenTimeout, StringRedisTemplate redisTemplate) {
        super(appTokenTimeout);
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void create(String appToken, TokenContent tokenContent) {
        redisTemplate.opsForValue().set(SSO_PREFIX + APP_TOKEN_KEY + appToken, JsonUtils.toString(tokenContent), getAppTokenTimeout(),
                TimeUnit.SECONDS);
        redisTemplate.opsForSet().add(SSO_PREFIX + TGT_KEY + tokenContent.getTgt(), appToken);
        logger.debug("AppToken调用凭证创建成功, appToken:{}", appToken);
    }

    @Override
    public TokenContent get(String appToken) {
        String tc = redisTemplate.opsForValue().get(SSO_PREFIX + APP_TOKEN_KEY + appToken);
        if (!StringUtils.hasLength(tc)) {
            return null;
        }
        return JsonUtils.parseObject(tc, TokenContent.class);
    }

    @Override
    public TokenContent getByAppToken(String appToken) {
        String tc = redisTemplate.opsForValue().get(SSO_PREFIX + APP_TOKEN_KEY + appToken);
        if (!StringUtils.hasLength(tc)) {
            return null;
        }
        return JsonUtils.parseObject(tc, TokenContent.class);
    }

    @Override
    public void remove(String appToken) {
        redisTemplate.delete(SSO_PREFIX + APP_TOKEN_KEY + appToken);
    }

    @Override
    public void removeByTgt(String tgt) {
        Set<String> appTokenSet = redisTemplate.opsForSet().members(SSO_PREFIX + TGT_KEY + tgt);
        if (CollectionUtils.isEmpty(appTokenSet)) {
            return;
        }
        // 删除tgt映射中的appToken集合
        redisTemplate.delete(SSO_PREFIX + TGT_KEY + tgt);

        appTokenSet.forEach(appToken -> {
            if (appToken.contains("__")) return;
            String tc = redisTemplate.opsForValue().get(SSO_PREFIX + APP_TOKEN_KEY + appToken);
            if (!StringUtils.hasLength(tc)) {
                return;
            }
            TokenContent tokenContent = JsonUtils.parseObject(tc, TokenContent.class);
            if (tokenContent == null) {
                return;
            }
            // 删除appToken
            redisTemplate.delete(SSO_PREFIX + APP_TOKEN_KEY + appToken);

            // 发起客户端退出请求
            logger.debug("发起客户端退出请求, appToken:{}, logoutUri:{}", appToken, tokenContent.getLogoutUri());
            sendLogoutRequest(tokenContent.getLogoutUri(), tokenContent.getAppToken());
        });
    }
}
