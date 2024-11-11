package top.zhengru.sso.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.zhengru.sso.base.constant.BaseConstant;
import top.zhengru.sso.base.entity.AuthContent;
import top.zhengru.sso.base.entity.Result;
import top.zhengru.sso.base.entity.TokenUser;
import top.zhengru.sso.client.ClientProperties;
import top.zhengru.sso.client.constant.ClientConstant;
import top.zhengru.sso.client.constant.RedisKeyConstant;
import top.zhengru.sso.client.service.EasyAuthService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @Autowired
    EasyAuthService easyAuthService;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    ClientProperties clientProperties;

    @GetMapping("/ping")
    public Result<String> ping(HttpServletRequest request) {
        String token = redisTemplate.opsForValue().get(RedisKeyConstant.SSO_PREFIX + RedisKeyConstant.APP_TOKEN_KEY + request.getHeader("Authorization"));
        if (!StringUtils.hasLength(token)) {
            return Result.redirect(easyAuthService.getLoginUri(clientProperties.getServerUrl() + ClientConstant.LOGIN_URL_PATH,
                    clientProperties.getAppId(),
                    clientProperties.getAppSecret(),
                    clientProperties.getDefaultCallbackUrl()));
        }
        return Result.success("pong");
    }

    @RequestMapping("/auth")
    public Result<AuthContent> auth(@RequestParam(value = BaseConstant.AUTH_CODE) String code) {
        AuthContent authContent = easyAuthService.auth(code);
        return Result.success(authContent);
    }

    @GetMapping("/logout")
    public Result<String> logout(String userId) {
        String appToken = redisTemplate.opsForSet().randomMember(RedisKeyConstant.SSO_PREFIX + RedisKeyConstant.USER_APP_TOKEN_KEY + userId);
        easyAuthService.logout(appToken, true);
        return Result.success("注销成功！");
    }
}
