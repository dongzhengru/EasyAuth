package top.zhengru.sso.client.service;

import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import top.zhengru.sso.base.constant.BaseConstant;
import top.zhengru.sso.base.entity.AuthContent;
import top.zhengru.sso.base.entity.Result;
import top.zhengru.sso.base.entity.TokenUser;
import top.zhengru.sso.base.util.HttpUtils;
import top.zhengru.sso.base.util.JsonUtils;
import top.zhengru.sso.client.ClientProperties;
import top.zhengru.sso.client.constant.ClientConstant;
import top.zhengru.sso.client.constant.MessageConstant;
import top.zhengru.sso.client.constant.RedisKeyConstant;
import top.zhengru.sso.client.exception.EasyAuthServerException;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @Author: dongzhengru
 * @Blog: blog.zhengru.top
 * @Date: 2024/11/11
 * @Time: 18:39
 */
@Service
public class EasyAuthService {

    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private ClientProperties clientProperties;

    /**
     * 返回拼接单点登录地址
     *
     * @param reqUrl
     * @param appId
     * @param appSecret
     * @param redirectUri
     * @return
     */
    public String getLoginUri(String reqUrl, String appId, String appSecret, String redirectUri) {
        // TODO 后续使用 Dubbo 与认证中心交互
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put(BaseConstant.APP_ID, appId);
        paramMap.put(BaseConstant.APP_SECRET, appSecret);
        String jsonStr = HttpUtils.post(reqUrl, paramMap);
        Result<String> result = JsonUtils.parseObject(jsonStr, new TypeReference<Result<String>>(){});

        if (!StringUtils.hasLength(result.getData())) {
            throw new EasyAuthServerException(MessageConstant.AUTHENTICATION_CENTER_REMOTE_SERVICE_EXCEPTION);
        }
        return new StringBuilder().append(result.getData())
                .append("?")
                .append(BaseConstant.REDIRECT_URI)
                .append("=")
                .append(redirectUri)
                .append("&")
                .append(BaseConstant.APP_ID)
                .append("=")
                .append(appId).toString();
    }

    /**
     * 使用临时授权码申请应用凭证和用户信息
     *
     * @param code
     * @return
     */
    public AuthContent auth(String code) {
        // TODO 后续使用 Dubbo 与认证中心交互
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put(BaseConstant.LOGOUT_URI, clientProperties.getLogoutUrl());
        paramMap.put(BaseConstant.APP_ID, clientProperties.getAppId());
        paramMap.put(BaseConstant.APP_SECRET, clientProperties.getAppSecret());
        paramMap.put(BaseConstant.AUTH_CODE, code);
        String jsonStr = HttpUtils.post(clientProperties.getServerUrl() + ClientConstant.AUTH_PATH, paramMap);
        if (jsonStr == null || jsonStr.isEmpty()) {
            throw new EasyAuthServerException(MessageConstant.AUTHORIZATION_FAILED);
        }
        Result<AuthContent> result = JsonUtils.parseObject(jsonStr, new TypeReference<Result<AuthContent>>() {});
        if (result == null) {
            throw new EasyAuthServerException(MessageConstant.AUTHENTICATION_CENTER_REMOTE_SERVICE_EXCEPTION);
        }
        redisTemplate.opsForValue().set(RedisKeyConstant.SSO_PREFIX + RedisKeyConstant.APP_TOKEN_KEY + result.getData().getAppToken(), JsonUtils.toString(result.getData().getTokenUser()));
        redisTemplate.opsForSet().add(RedisKeyConstant.SSO_PREFIX + RedisKeyConstant.USER_APP_TOKEN_KEY + result.getData().getTokenUser().getId(), result.getData().getAppToken());
        return result.getData();
    }

    public void logout(String appToken, boolean isNotifyAuthCenter) {
        TokenUser logoutUser = JsonUtils.parseObject(redisTemplate.opsForValue().get(RedisKeyConstant.SSO_PREFIX + RedisKeyConstant.APP_TOKEN_KEY + appToken), TokenUser.class);
        if (logoutUser != null) {
            Set<String> appTokenSet = redisTemplate.opsForSet().members(RedisKeyConstant.SSO_PREFIX + RedisKeyConstant.USER_APP_TOKEN_KEY + logoutUser.getId());
            redisTemplate.delete(RedisKeyConstant.SSO_PREFIX + RedisKeyConstant.USER_APP_TOKEN_KEY + logoutUser.getId());
            assert appTokenSet != null;
            appTokenSet.forEach(token -> redisTemplate.delete(RedisKeyConstant.SSO_PREFIX + RedisKeyConstant.APP_TOKEN_KEY + appToken));
        }
        if (isNotifyAuthCenter) notifyEasyAuthLogout(appToken);
    }

    public void notifyEasyAuthLogout(String appToken) {
        // TODO 后续使用 Dubbo 与认证中心交互
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put(BaseConstant.APP_TOKEN, appToken);
        HttpUtils.post(clientProperties.getServerUrl() + ClientConstant.LOGOUT_PATH, paramMap);
    }
}
