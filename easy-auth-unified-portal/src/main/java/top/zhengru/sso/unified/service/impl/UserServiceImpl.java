package top.zhengru.sso.unified.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.zhengru.sso.unified.entity.User;
import top.zhengru.sso.unified.mapper.UserMapper;
import top.zhengru.sso.unified.mapper.UserRoleMapper;
import top.zhengru.sso.unified.service.UserService;
import top.zhengru.sso.unified.vo.PermInfoVO;
import top.zhengru.sso.unified.vo.RoleInfoVO;

import java.util.ArrayList;
import java.util.List;

/**
* @author 董政儒
* @description 针对表【sys_user】的数据库操作Service实现
* @createDate 2024-10-27 14:09:17
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService {

    @Autowired
    UserMapper userMapper;
    @Autowired
    UserRoleMapper userRoleMapper;

    @Override
    public UserDetailImpl queryUserDetailById(Long userId) {
        User sysUser = this.getById(userId);
        /*获取账户的权限信息*/
        List<GrantedAuthorityImpl> grantedAuthorities = new ArrayList<>();
        List<RoleInfoVO> permissionList = userMapper.queryPermByUserId(userId);
        for (RoleInfoVO roleInfoVo : permissionList) {
            for (PermInfoVO permInfoVo : roleInfoVo.getPermInfoVOList()) {
                grantedAuthorities.add(new GrantedAuthorityImpl(permInfoVo.getPermission()));
            }
        }
        /*获取用户的角色信息*/
        List<String> roleList = userRoleMapper.queryRoleByUserId(userId);
        UserDetailImpl userDetail = new UserDetailImpl();
        BeanUtils.copyProperties(sysUser, userDetail);
        userDetail.setAuthorities(grantedAuthorities);
        userDetail.setRoles(roleList);
        userDetail.setPassword(null);
        userDetail.setEnabled(sysUser.getIsEnable());
        return userDetail;
    }
}




