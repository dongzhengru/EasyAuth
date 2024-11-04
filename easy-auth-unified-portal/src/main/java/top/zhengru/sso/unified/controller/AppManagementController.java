package top.zhengru.sso.unified.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.zhengru.sso.base.entity.Result;
import top.zhengru.sso.unified.param.AppApplyParam;
import top.zhengru.sso.unified.param.AppAuditParam;
import top.zhengru.sso.unified.service.AppApplyHistoryService;
import top.zhengru.sso.unified.service.AppService;

/**
 * @Author: dongzhengru
 * @Blog: blog.zhengru.top
 * @Date: 2024/11/3
 * @Time: 15:16
 */
@RestController
@RequestMapping("/app-management")
public class AppManagementController {

    @Autowired
    AppService appService;
    @Autowired
    AppApplyHistoryService appApplyHistoryService;

    /**
     * 应用申请
     *
     * @return
     */
    @PostMapping("/apply")
    public Result<String> apply(@RequestBody AppApplyParam appApplyParam) {
        appApplyHistoryService.apply(appApplyParam);
        return Result.success("申请成功");
    }

    /**
     * 应用审批
     *
     * @return
     */
    @PostMapping("/audit")
    public Result<String> audit(@RequestBody AppAuditParam appAuditParam) {
        appApplyHistoryService.audit(appAuditParam);
        return Result.success("审批成功");
    }

//    /**
//     * 应用信息修改
//     *
//     * @return
//     */
//    @PostMapping("/modify")
//    public Result<String> modify() {
//        return Result.success("修改成功");
//    }
//
//    /**
//     * 应用上下架
//     *
//     * @return
//     */
//    @PostMapping("/shelve")
//    public Result<String> shelve() {
//        return Result.success("修改成功");
//    }
}
