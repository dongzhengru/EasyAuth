package top.zhengru.sso.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.zhengru.sso.base.constant.BaseConstant;
import top.zhengru.sso.base.entity.Result;
import top.zhengru.sso.base.entity.TokenUser;
import top.zhengru.sso.server.ServerProperties;
import top.zhengru.sso.server.manager.AbstractCodeManager;
import top.zhengru.sso.server.manager.AbstractTicketGrantingTicketManager;
import top.zhengru.sso.server.service.AppService;
import top.zhengru.sso.server.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Objects;

/**
 * 单点登录管理
 */
@RestController
@RequestMapping(BaseConstant.LOGIN_PATH)
public class SSOLoginController {

    @Autowired
    private AbstractCodeManager codeManager;
    @Autowired
    private AbstractTicketGrantingTicketManager ticketGrantingTicketManager;
    @Autowired
    private UserService userService;
    @Autowired
    private AppService appService;
    @Autowired
    private ServerProperties serverProperties;

    /**
     * 获取认证中心登录地址
     *
     * @param appId
     * @param appSecret
     * @return
     */
    @PostMapping("/loginUrl")
    public Result<String> loginUrl(
            @RequestParam(value = BaseConstant.APP_ID) String appId,
            @RequestParam(value = BaseConstant.APP_SECRET) String appSecret) {
        appService.authCheck(appId, appSecret);
        return Result.success(serverProperties.getServerLoginUrl());
    }


    /**
     * 认证中心登录提交
     *
     * @param redirectUri
     * @param appId
     * @param username
     * @param password
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result<String> login(
            @RequestParam(value = BaseConstant.LOGIN_TYPE) String loginType,
            @RequestParam(value = BaseConstant.REDIRECT_URI) String redirectUri,
            @RequestParam(value = BaseConstant.APP_ID) String appId,
            @RequestParam String username,
            @RequestParam String password,
            HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        TokenUser tokenUser = new TokenUser();

        // 校验appId是否存在、redirectUri是否合法
        appService.loginCheck(appId, redirectUri);

        // 判断Header是否存在有效的TGT
        String tgt = ticketGrantingTicketManager.get(request);

        if (tgt != null) {
            // 存在有效TGT则直接生成授权码code并返回重定向地址
            return Result.redirect(generateCodeAndRedirect(tgt, appId, redirectUri));
        }

        // 不存在TGT或无效TGT
        // 指定loginType登录校验
        if (Objects.equals(loginType, "account")) {
            tokenUser = userService.login(username, password);
        }
        tgt = ticketGrantingTicketManager.getOrCreate(tokenUser, request, response);
        // 生成授权码code并返回重定向地址
        return Result.redirect(generateCodeAndRedirect(tgt, appId, redirectUri));
    }

    /**
     * 创建授权码code，拼接到redirectUri
     *
     * @param tgt
     * @param appId
     * @param redirectUri
     * @return
     */
    private String generateCodeAndRedirect(String tgt, String appId, String redirectUri) throws UnsupportedEncodingException {
        // 创建授权码
        String code = codeManager.create(tgt, appId);
        return authRedirectUri(redirectUri, code);
    }

    /**
     * 将授权码拼接到回调redirectUri中
     *
     * @param redirectUri
     * @param code
     * @return
     */
    private String authRedirectUri(String redirectUri, String code) throws UnsupportedEncodingException {
        StringBuilder sbf = new StringBuilder(redirectUri);
        if (redirectUri.indexOf("?") > -1) {
            sbf.append("&");
        } else {
            sbf.append("?");
        }
        sbf.append(BaseConstant.AUTH_CODE).append("=").append(code);
        return URLDecoder.decode(sbf.toString(), "utf-8");
    }

}