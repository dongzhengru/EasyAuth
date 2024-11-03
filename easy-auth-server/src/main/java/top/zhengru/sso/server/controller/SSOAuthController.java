package top.zhengru.sso.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.zhengru.sso.base.constant.BaseConstant;
import top.zhengru.sso.base.entity.AuthContent;
import top.zhengru.sso.base.entity.Result;
import top.zhengru.sso.base.entity.TokenUser;
import top.zhengru.sso.server.constant.AuthMsgConstant;
import top.zhengru.sso.server.entity.CodeContent;
import top.zhengru.sso.server.entity.TokenContent;
import top.zhengru.sso.server.exception.EasyAuthServerException;
import top.zhengru.sso.server.manager.AbstractAppTokenManager;
import top.zhengru.sso.server.manager.AbstractCodeManager;
import top.zhengru.sso.server.manager.AbstractTicketGrantingTicketManager;
import top.zhengru.sso.server.service.AppService;

import java.util.Objects;

/**
 * 单点登录管理
 */
@RestController
@RequestMapping(BaseConstant.AUTH_PATH)
public class SSOAuthController {

    @Autowired
    private AbstractCodeManager codeManager;
    @Autowired
    private AbstractAppTokenManager appTokenManager;
    @Autowired
    private AbstractTicketGrantingTicketManager ticketGrantingTicketManager;
    @Autowired
    private AppService appService;

    /**
     * 使用临时授权码申请应用凭证
     *
     * @param logoutUri
     * @param appId
     * @param appSecret
     * @param code
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result<AuthContent> auth(
            @RequestParam(value = BaseConstant.LOGOUT_URI) String logoutUri,
            @RequestParam(value = BaseConstant.APP_ID) String appId,
            @RequestParam(value = BaseConstant.APP_SECRET) String appSecret,
            @RequestParam String code) {
        // 校验code和app查询user生成token返回
        CodeContent codeContent = codeManager.get(code);
        // 授权码Code使用即失效
        codeManager.remove(code);
        TokenUser tokenUser = ticketGrantingTicketManager.get(codeContent.getTgt());
        if (!Objects.equals(codeContent.getAppId(), appId)) {
            throw new EasyAuthServerException(AuthMsgConstant.ILLEGAL_APP_REQUEST);
        }
        // 校验AppSecret
        appService.authCheck(appId, appSecret);
        TokenContent tokenContent = appTokenManager.create(tokenUser, logoutUri, codeContent);
        return Result.success(new AuthContent(tokenContent.getAppToken(), tokenContent.getTokenUser()));
    }

}