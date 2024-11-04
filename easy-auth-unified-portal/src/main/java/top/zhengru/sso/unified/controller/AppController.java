package top.zhengru.sso.unified.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.zhengru.sso.base.entity.Result;
import top.zhengru.sso.unified.entity.PageResult;
import top.zhengru.sso.unified.param.AppQueryParam;
import top.zhengru.sso.unified.service.AppService;
import top.zhengru.sso.unified.vo.AppInfoVo;

import java.util.List;

/**
 * @Author: dongzhengru
 * @Blog: blog.zhengru.top
 * @Date: 2024/11/3
 * @Time: 15:22
 */
@RestController
@RequestMapping("/app")
public class AppController {
    @Autowired
    AppService appService;

    /**
     * 查询应用列表
     * @param appQueryParam
     * @return
     */
    @PostMapping("/list")
    public Result<PageResult<AppInfoVo>> queryApplicationInfoList(@RequestBody AppQueryParam appQueryParam) {
        return Result.success(appService.queryApplicationInfoList(appQueryParam));
    }
}
