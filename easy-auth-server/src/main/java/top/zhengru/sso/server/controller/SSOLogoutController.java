package top.zhengru.sso.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.zhengru.sso.base.constant.BaseConstant;
import top.zhengru.sso.base.entity.Result;
import top.zhengru.sso.server.entity.TokenContent;
import top.zhengru.sso.server.manager.AbstractAppTokenManager;
import top.zhengru.sso.server.manager.AbstractTicketGrantingTicketManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 单点退出
 */
@RestController
@RequestMapping(BaseConstant.LOGOUT_PATH)
public class SSOLogoutController {

    @Autowired
    AbstractAppTokenManager appTokenManager;
    @Autowired
    AbstractTicketGrantingTicketManager ticketGrantingTicketManager;

    /**
     * 认证中心统一注销
     *
     * @param appToken
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result<String> logout(@RequestParam String appToken) {
        // 拿到App-Token，查询对应的TGT
        TokenContent tc = appTokenManager.getByAppToken(appToken);
        // 注销TGT下所有App-Token
        ticketGrantingTicketManager.invalidate(tc.getTgt());
        return Result.success("ok");
    }
}