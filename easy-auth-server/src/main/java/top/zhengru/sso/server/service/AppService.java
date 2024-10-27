package top.zhengru.sso.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.zhengru.sso.server.entity.App;

/**
* @author 董政儒
* @description 针对表【sys_app】的数据库操作Service
* @createDate 2024-10-27 14:09:17
*/
public interface AppService extends IService<App> {

    void loginCheck(String appId, String redirectUri);

    void authCheck(String appId, String appSecret);
}
