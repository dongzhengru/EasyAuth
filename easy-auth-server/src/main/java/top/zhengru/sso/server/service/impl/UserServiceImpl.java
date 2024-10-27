package top.zhengru.sso.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.zhengru.sso.server.entity.User;
import top.zhengru.sso.server.mapper.UserMapper;
import org.springframework.stereotype.Service;
import top.zhengru.sso.server.service.UserService;

/**
* @author 董政儒
* @description 针对表【sys_user】的数据库操作Service实现
* @createDate 2024-10-27 14:09:17
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService {

}




