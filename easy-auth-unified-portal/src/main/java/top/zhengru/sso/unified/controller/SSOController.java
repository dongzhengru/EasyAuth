package top.zhengru.sso.unified.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.zhengru.sso.base.constant.BaseConstant;
import top.zhengru.sso.base.entity.AuthContent;
import top.zhengru.sso.base.entity.Result;
import top.zhengru.sso.base.entity.TokenUser;
import top.zhengru.sso.base.util.HttpUtils;
import top.zhengru.sso.base.util.JsonUtils;
import top.zhengru.sso.client.ClientProperties;
import top.zhengru.sso.client.constant.ClientConstant;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/sso")
public class SSOController {

    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private ClientProperties clientProperties;
    private static final String SSO_PREFIX = "easy_auth:";
    private static final String APP_TOKEN_KEY = "server_app_token:";

    /**
     * 跳转至认证中心登录
     *
     * @param redirectUri
     * @return
     */
    @RequestMapping("/login")
    public Result<String> login(@RequestParam(value = BaseConstant.REDIRECT_URI) String redirectUri) {
        return getLoginUri(clientProperties.getServerUrl() + ClientConstant.LOGIN_URL_PATH,
                clientProperties.getAppId(),
                clientProperties.getAppSecret(),
                redirectUri);
    }

    /**
     * 使用临时授权码申请应用凭证
     *
     * @param code
     * @return
     */
    @RequestMapping("/auth")
    public Result<AuthContent> auth(@RequestParam(value = BaseConstant.AUTH_CODE) String code) {
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put(BaseConstant.LOGOUT_URI, clientProperties.getLogoutUrl());
        paramMap.put(BaseConstant.APP_ID, clientProperties.getAppId());
        paramMap.put(BaseConstant.APP_SECRET, clientProperties.getAppSecret());
        paramMap.put(BaseConstant.AUTH_CODE, code);
        String jsonStr = HttpUtils.post(clientProperties.getServerUrl() + ClientConstant.AUTH_PATH, paramMap);
        if (jsonStr == null || jsonStr.isEmpty()) {
            return Result.error("授权失败");
        }
        Result<AuthContent> result = JsonUtils.parseObject(jsonStr, new TypeReference<Result<AuthContent>>() {});
        if (result == null) {
            return Result.error("数据解析失败");
        }
        redisTemplate.opsForValue().set(SSO_PREFIX + APP_TOKEN_KEY + result.getData().getAppToken(), JsonUtils.toString(result.getData().getTokenUser()));
        return result;
    }

    /**
     * 客户端单点退出
     *
     * @param request
     * @return
     */
    @RequestMapping("/logout")
    public Result<String> logout(HttpServletRequest request) {
        // 客户端单点退出
        String token = request.getHeader("Authorization");
        if (StringUtils.hasLength(token)) {
            TokenUser logoutUser = JsonUtils.parseObject(redisTemplate.opsForValue().get(SSO_PREFIX + APP_TOKEN_KEY + token), TokenUser.class);
            redisTemplate.delete(SSO_PREFIX + APP_TOKEN_KEY + token);
            // 向认证中心发起单点退出请求
            Map<String, String> paramMap = new HashMap<>();
            paramMap.put(BaseConstant.APP_TOKEN, token);
            HttpUtils.post(clientProperties.getServerUrl() + ClientConstant.LOGOUT_PATH, paramMap);
            // 注销以后的操作
            return Result.success();
        }

        // 认证中心单点退出
        String authAppToken = request.getHeader(BaseConstant.LOGOUT_PARAMETER_NAME);
        if (StringUtils.hasLength(authAppToken)) {
            TokenUser logoutUser = JsonUtils.parseObject(redisTemplate.opsForValue().get(SSO_PREFIX + APP_TOKEN_KEY + authAppToken), TokenUser.class);
            System.out.println("退出用户信息：" + logoutUser);
            // 可以执行其他的退出操作
            redisTemplate.delete(SSO_PREFIX + APP_TOKEN_KEY + authAppToken);
        }
        return Result.success();
    }

    @RequestMapping("/ping")
    public Result<String> ping(HttpServletRequest request) {
        String token = redisTemplate.opsForValue().get(SSO_PREFIX + APP_TOKEN_KEY + request.getHeader("Authorization"));
        if (!StringUtils.hasLength(token)) {
            return getLoginUri(clientProperties.getServerUrl() + ClientConstant.LOGIN_URL_PATH,
                    clientProperties.getAppId(),
                    clientProperties.getAppSecret(),
                    clientProperties.getDefaultCallbackUrl());
        }
        return Result.success("pong");
    }

    public static Result<String> getLoginUri(String reqUrl, String appId, String appSecret, String redirectUri) {
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put(BaseConstant.APP_ID, appId);
        paramMap.put(BaseConstant.APP_SECRET, appSecret);
        String jsonStr = HttpUtils.post(reqUrl, paramMap);
        Result<String> result = JsonUtils.parseObject(jsonStr, new TypeReference<Result<String>>(){});

        if (!StringUtils.hasLength(result.getData())) {
            return Result.error("认证中心异常");
        }
        return Result.redirect(new StringBuilder().append(result.getData())
                .append("?")
                .append(BaseConstant.REDIRECT_URI)
                .append("=")
                .append(redirectUri)
                .append("&")
                .append(BaseConstant.APP_ID)
                .append("=")
                .append(appId).toString());
    }
}
