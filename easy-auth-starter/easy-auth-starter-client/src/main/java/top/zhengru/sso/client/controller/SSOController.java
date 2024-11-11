package top.zhengru.sso.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.zhengru.sso.base.constant.BaseConstant;
import top.zhengru.sso.base.entity.Result;
import top.zhengru.sso.client.ClientProperties;
import top.zhengru.sso.client.constant.ClientConstant;
import top.zhengru.sso.client.service.EasyAuthService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/sso")
public class SSOController {
    @Autowired
    private EasyAuthService easyAuthService;
    @Autowired
    private ClientProperties clientProperties;

    /**
     * 跳转至认证中心登录
     *
     * @param redirectUri
     * @return
     */
    @RequestMapping("/login")
    public Result<String> login(@RequestParam(value = BaseConstant.REDIRECT_URI) String redirectUri) {
        return Result.redirect(easyAuthService.getLoginUri(clientProperties.getServerUrl() + ClientConstant.LOGIN_URL_PATH,
                clientProperties.getAppId(),
                clientProperties.getAppSecret(),
                redirectUri));
    }

//    /**
//     * 使用临时授权码申请应用凭证
//     *
//     * @param code
//     * @return
//     */
//    @RequestMapping("/auth")
//    public Result<AuthContent> auth(@RequestParam(value = BaseConstant.AUTH_CODE) String code) {
//        return Result.success(easyAuthService.auth(code));
//    }

    /**
     * 客户端单点退出
     *
     * @param request
     * @return
     */
    @RequestMapping("/logout")
    public Result<String> logout(HttpServletRequest request) {
        // 接入后应用客户端通过编写过滤器实现应用注销
        String appToken = request.getHeader(BaseConstant.LOGOUT_PARAMETER_NAME);
        easyAuthService.logout(appToken, false);
        return Result.success("注销成功");
    }
}
