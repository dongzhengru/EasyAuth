package top.zhengru.sso.server.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import top.zhengru.sso.base.entity.TokenUser;
import top.zhengru.sso.base.util.JsonUtils;
import top.zhengru.sso.server.entity.TokenContent;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * TGT登录凭证管理
 */
public class TicketGrantingTicketManager extends AbstractTicketGrantingTicketManager {

    protected final Logger logger = LoggerFactory.getLogger(TicketGrantingTicketManager.class);
    private static final String SSO_PREFIX = "easy_auth:";
    private static final String TGT_KEY = "server_tgt:";
    private static final String USER_TGT_KEY = "server_user_tgt:";
    private StringRedisTemplate redisTemplate;
    @Autowired
    private AbstractAppTokenManager appTokenManager;

    public TicketGrantingTicketManager(int timeout, AbstractAppTokenManager appTokenManager, StringRedisTemplate redisTemplate) {
        super(appTokenManager, timeout);
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void create(String tgt, TokenUser tokenUser) {
        redisTemplate.opsForSet().add(SSO_PREFIX + USER_TGT_KEY + tokenUser.getId(), tgt);
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
        logger.debug("根据TGT:{}查询到用户{}", tgt, tokenUser);
        if (!StringUtils.hasLength(tokenUser)) {
            return null;
        }
        return JsonUtils.parseObject(tokenUser, TokenUser.class);
    }

    @Override
    public void remove(String tgt) {
        redisTemplate.delete(SSO_PREFIX + TGT_KEY + tgt);
        logger.debug("TGT登录凭证删除成功, tgt:{}", tgt);
    }

    @Override
    public void removeByUserId(Long userId) {
        Set<String> tgtSet = redisTemplate.opsForSet().members(SSO_PREFIX + USER_TGT_KEY + userId);
        if (CollectionUtils.isEmpty(tgtSet)) {
            return;
        }
        redisTemplate.delete(SSO_PREFIX + USER_TGT_KEY + userId);
        tgtSet.forEach(tgt -> appTokenManager.removeByTgt(tgt));
    }
}