package top.zhengru.sso.server.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import top.zhengru.sso.base.entity.TokenUser;
import top.zhengru.sso.base.util.JsonUtils;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * TGT登录凭证管理
 */
public class TicketGrantingTicketManager extends AbstractTicketGrantingTicketManager {

    protected final Logger logger = LoggerFactory.getLogger(TicketGrantingTicketManager.class);
    private static final String SSO_PREFIX = "easy_auth:";
    private static final String TGT_KEY = "server_tgt:";
    private StringRedisTemplate redisTemplate;

    public TicketGrantingTicketManager(int timeout, AbstractAppTokenManager appTokenManager, StringRedisTemplate redisTemplate) {
        super(appTokenManager, timeout);
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void create(String tgt, TokenUser tokenUser) {
        // 执行add操作以确保set存在
        redisTemplate.opsForSet().add(SSO_PREFIX + TGT_KEY + tgt, "__" + JsonUtils.toString(tokenUser) + "__");
        redisTemplate.expire(SSO_PREFIX + TGT_KEY + tgt, getTgtTimeout(), TimeUnit.SECONDS);
        logger.debug("TGT凭证创建成功, tgt:{}", tgt);
    }

    @Override
    public TokenUser get(String tgt) {
        Set<String> appTokenSet = redisTemplate.opsForSet().members(SSO_PREFIX + TGT_KEY + tgt);
        String tokenUser = null;
        for (String appToken : appTokenSet) {
            if (appToken.contains("__")) {
                tokenUser = appToken.replace("__", "");
                break;
            }
        }
        if (!StringUtils.hasLength(tokenUser)) {
            return null;
        }
        redisTemplate.expire(SSO_PREFIX + TGT_KEY + tgt, getTgtTimeout(), TimeUnit.SECONDS);
        return JsonUtils.parseObject(tokenUser, TokenUser.class);
    }

    @Override
    public void remove(String tgt) {
        redisTemplate.delete(SSO_PREFIX + TGT_KEY + tgt);
        logger.debug("TGT登录凭证删除成功, tgt:{}", tgt);
    }

//    @Override
//    public void refresh(String tgt) {
//        redisTemplate.expire(SSO_PREFIX + TGT_KEY + tgt, getTimeout(), TimeUnit.SECONDS);
//    }
}