package top.zhengru.sso.server.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import top.zhengru.sso.base.util.JsonUtils;
import top.zhengru.sso.server.entity.CodeContent;

import java.util.concurrent.TimeUnit;

/**
 * Code授权码管理
 */
public class CodeManager extends AbstractCodeManager {

    protected final Logger logger = LoggerFactory.getLogger(CodeManager.class);
    private static final String SSO_PREFIX = "easy_auth:";
    private static final String CODE_KEY = "server_code:";
    private StringRedisTemplate redisTemplate;

    public CodeManager(int timeout, StringRedisTemplate redisTemplate) {
        super(timeout);
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void create(String code, CodeContent codeContent) {
        redisTemplate.opsForValue().set(SSO_PREFIX + CODE_KEY + code, JsonUtils.toString(codeContent), getTimeout(), TimeUnit.SECONDS);
        logger.debug("Code授权码创建成功, code:{}", code);
    }

    @Override
    public CodeContent get(String code) {
        String cc = redisTemplate.opsForValue().get(SSO_PREFIX + CODE_KEY + code);
        if (!StringUtils.hasLength(cc)) {
            return null;
        }
        return JsonUtils.parseObject(cc, CodeContent.class);
    }

    @Override
    public void remove(String code) {
        redisTemplate.delete(SSO_PREFIX + CODE_KEY + code);
    }
}
