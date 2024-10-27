package top.zhengru.sso.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import top.zhengru.sso.server.constant.AuthMsgConstant;
import top.zhengru.sso.server.entity.App;
import top.zhengru.sso.server.entity.User;
import top.zhengru.sso.server.exception.EasyAuthServerException;
import top.zhengru.sso.server.mapper.AppMapper;
import org.springframework.stereotype.Service;
import top.zhengru.sso.server.service.AppService;

/**
* @author 董政儒
* @description 针对表【sys_app】的数据库操作Service实现
* @createDate 2024-10-27 14:09:17
*/
@Service
public class AppServiceImpl extends ServiceImpl<AppMapper, App>
    implements AppService {

    @Override
    public void loginCheck(String appId, String redirectUri) {
        LambdaQueryWrapper<App> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(App::getAppId, appId);
        App appInfo = getOne(wrapper);
        if (appInfo == null) {
            throw new EasyAuthServerException(AuthMsgConstant.APP_NOT_FOUND);
        }
        // TODO 校验redirectUri是否合法
    }

    @Override
    public void authCheck(String appId, String appSecret) {
        LambdaQueryWrapper<App> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(App::getAppId, appId);
        wrapper.eq(App::getAppSecret, appSecret);
        App appInfo = getOne(wrapper);
        if (appInfo == null) {
            throw new EasyAuthServerException(AuthMsgConstant.APP_SECRET_INVALID);
        }
    }
}




