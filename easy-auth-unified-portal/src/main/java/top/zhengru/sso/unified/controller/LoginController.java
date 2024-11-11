package top.zhengru.sso.unified.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.zhengru.sso.base.constant.BaseConstant;
import top.zhengru.sso.base.entity.AuthContent;
import top.zhengru.sso.base.entity.Result;
import top.zhengru.sso.client.service.EasyAuthService;
import top.zhengru.sso.unified.service.UserService;

@RestController
@RequestMapping("/portal-login")
public class LoginController {

    @Autowired
    private UserService userService;
    @Autowired
    private EasyAuthService easyAuthService;

    /**
     * 使用临时授权码申请应用凭证
     *
     * @param code
     * @return
     */
    @RequestMapping("/auth")
    public Result<String> auth(@RequestParam(value = BaseConstant.AUTH_CODE) String code) {
        AuthContent authContent = easyAuthService.auth(code);
        return Result.success(userService.login(authContent.getTokenUser()));
    }

    /**
     * 客户端单点退出
     *
     * @return
     */
    @RequestMapping("/logout")
    public Result<String> logout() {
        userService.logout();
        return Result.success("注销成功！");
    }
}
