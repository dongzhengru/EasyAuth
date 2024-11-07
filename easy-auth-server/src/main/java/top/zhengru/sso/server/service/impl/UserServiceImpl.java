package top.zhengru.sso.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.zhengru.sso.base.entity.TokenUser;
import top.zhengru.sso.server.entity.User;
import top.zhengru.sso.server.exception.EasyAuthServerException;
import top.zhengru.sso.server.mapper.UserMapper;
import org.springframework.stereotype.Service;
import top.zhengru.sso.server.service.UserService;
import top.zhengru.sso.server.util.PasswordHelper;

import java.util.Base64;
import java.util.Date;

/**
* @author 董政儒
* @description 针对表【sys_user】的数据库操作Service实现
* @createDate 2024-10-27 14:09:17
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService {

    @Override
    public TokenUser login(String username, String password) {
        User user = selectByUsername(username);
        if (user == null) {
            throw new EasyAuthServerException("账号或密码有误，请重新输入");
        } else if (!user.getPassword().equals(PasswordHelper.encrypt(new String(Base64.getDecoder().decode(password))))) {
            throw new EasyAuthServerException("账号或密码有误，请重新输入");
        } else if (!user.getIsEnable()) {
            throw new EasyAuthServerException("账号已被禁用");
        } else {
            user.setLoginCount(user.getLoginCount() + 1);
            user.setLastLoginTime(new Date());
            updateById(user);
        }
        return new TokenUser(user.getId(), user.getUsername(), user.getName(), user.getPhone(), user.getCardNo(), user.getEmail());
    }

    @Override
    public User selectByUsername(String username) {
        LambdaQueryWrapper<User> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(User::getUsername, username);
        return getOne(wrapper);
    }
}




