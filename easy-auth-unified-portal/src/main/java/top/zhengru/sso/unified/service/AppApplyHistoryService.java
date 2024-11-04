package top.zhengru.sso.unified.service;

import top.zhengru.sso.unified.entity.AppApplyHistory;
import com.baomidou.mybatisplus.extension.service.IService;
import top.zhengru.sso.unified.param.AppApplyParam;
import top.zhengru.sso.unified.param.AppAuditParam;

/**
* @author 董政儒
* @description 针对表【sys_app_apply_history】的数据库操作Service
* @createDate 2024-11-03 15:13:10
*/
public interface AppApplyHistoryService extends IService<AppApplyHistory> {

    void apply(AppApplyParam appApplyParam);

    void audit(AppAuditParam appAuditParam);
}
