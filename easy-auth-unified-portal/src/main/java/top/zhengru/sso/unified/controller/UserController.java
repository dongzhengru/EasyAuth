package top.zhengru.sso.unified.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.zhengru.sso.base.entity.Result;

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
    public Result<String> info() {
        return Result.success("用户信息");
    }
}
