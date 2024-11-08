package top.zhengru.sso.unified.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.zhengru.sso.base.entity.Result;
import top.zhengru.sso.unified.service.impl.UserDetailImpl;

/**
 * @Author: dongzhengru
 * @Blog: blog.zhengru.top
 * @Date: 2024/11/3
 * @Time: 15:22
 */
@RestController
@RequestMapping("/user")
public class UserController {
    /**
     * 查询用户信息
     *
     * @return
     */
    @PostMapping("/info")
    public Result<UserDetailImpl> info() {
        return Result.success((UserDetailImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }
}
