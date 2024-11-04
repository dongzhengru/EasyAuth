package top.zhengru.sso.unified.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import top.zhengru.sso.unified.entity.App;
import top.zhengru.sso.unified.entity.AppApplyHistory;
import top.zhengru.sso.unified.exception.UnifiedPortalServerException;
import top.zhengru.sso.unified.mapper.AppMapper;
import top.zhengru.sso.unified.param.AppApplyParam;
import top.zhengru.sso.unified.param.AppAuditParam;
import top.zhengru.sso.unified.service.AppApplyHistoryService;
import top.zhengru.sso.unified.mapper.AppApplyHistoryMapper;
import org.springframework.stereotype.Service;
import top.zhengru.sso.unified.service.AppService;
import top.zhengru.sso.unified.utils.RandomStringUtil;

/**
* @author 董政儒
* @description 针对表【sys_app_apply_history】的数据库操作Service实现
* @createDate 2024-11-03 15:13:10
*/
@Service
public class AppApplyHistoryServiceImpl extends ServiceImpl<AppApplyHistoryMapper, AppApplyHistory>
    implements AppApplyHistoryService{

    @Autowired
    AppApplyHistoryMapper appApplyHistoryMapper;
    @Autowired
    AppMapper appMapper;

    @Override
    @Transactional
    public void apply(AppApplyParam appApplyParam) {
        isAppNameExists(appApplyParam.getName());
        App insApp = new App();
        BeanUtils.copyProperties(appApplyParam, insApp);
        insApp.setAppId(generateAppId());
        insApp.setAuditStatus(0);
        insApp.setPublishStatus(0);
        insApp.setShelveStatus(0);
        insApp.setSort(99);
        // TODO 从上下文获取应用开发者
//        insApp.setDeveloper();
        // 插入后获取id
        AppApplyHistory insAppApplyHistory = new AppApplyHistory();
        BeanUtils.copyProperties(insApp, insAppApplyHistory);
        appMapper.insert(insApp);
        this.save(insAppApplyHistory);
    }

    @Override
    @Transactional
    public void audit(AppAuditParam appAuditParam) {
        LambdaQueryWrapper<App> appLambdaQueryWrapper = Wrappers.lambdaQuery();
        appLambdaQueryWrapper.eq(App::getAppId, appAuditParam.getAppId());
        App app = appMapper.selectOne(appLambdaQueryWrapper);
        app.setAuditStatus(appAuditParam.getAuditStatus());
        app.setAppSecret(RandomStringUtil.generateRandomString(64));
        appMapper.updateById(app);

        LambdaQueryWrapper<AppApplyHistory> appApplyHistoryLambdaQueryWrapper = Wrappers.lambdaQuery();
        appApplyHistoryLambdaQueryWrapper.eq(AppApplyHistory::getAppId, appAuditParam.getAppId());
        AppApplyHistory appApplyHistory = appApplyHistoryMapper.selectOne(appApplyHistoryLambdaQueryWrapper);
        appApplyHistory.setAuditStatus(appAuditParam.getAuditStatus());
        appApplyHistory.setAuditMsg(appAuditParam.getAuditMsg());
        appApplyHistoryMapper.updateById(appApplyHistory);
    }

    private void isAppNameExists(String appName) {
        LambdaQueryWrapper<App> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(App::getName, appName);
        long count = appMapper.selectCount(queryWrapper);
        if (count > 0) throw new UnifiedPortalServerException("应用名称已存在");
    }

    private Long generateAppId() {
        Long appId = RandomStringUtil.generateRandomNum(18);
        if (!isAppIdExists(appId)) return appId;
        return generateAppId();
    }

    private boolean isAppIdExists(Long appId) {
        LambdaQueryWrapper<App> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(App::getAppId, appId);
        long count = appMapper.selectCount(queryWrapper);
        return count > 0;
    }
}




