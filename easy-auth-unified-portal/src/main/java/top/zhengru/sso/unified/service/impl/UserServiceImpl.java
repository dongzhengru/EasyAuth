package top.zhengru.sso.unified.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.zhengru.sso.unified.entity.User;
import top.zhengru.sso.unified.mapper.UserMapper;
import top.zhengru.sso.unified.service.UserService;

/**
* @author 董政儒
* @description 针对表【sys_user】的数据库操作Service实现
* @createDate 2024-10-27 14:09:17
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService {

}




