package top.zhengru.sso.unified.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.zhengru.sso.unified.entity.User;
import top.zhengru.sso.unified.service.impl.UserDetailImpl;

/**
* @author 董政儒
* @description 针对表【sys_user】的数据库操作Service
* @createDate 2024-10-27 14:09:17
*/
public interface UserService extends IService<User> {

    UserDetailImpl queryUserDetailById(Long userId);
}
