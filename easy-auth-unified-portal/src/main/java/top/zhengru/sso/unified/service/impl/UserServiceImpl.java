package top.zhengru.sso.unified.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import top.zhengru.sso.base.entity.TokenUser;
import top.zhengru.sso.base.util.JsonUtils;
import top.zhengru.sso.client.constant.RedisKeyConstant;
import top.zhengru.sso.client.service.EasyAuthService;
import top.zhengru.sso.unified.config.EasyAuthAuthenticationToken;
import top.zhengru.sso.unified.entity.User;
import top.zhengru.sso.unified.exception.UnifiedPortalServerException;
import top.zhengru.sso.unified.mapper.UserMapper;
import top.zhengru.sso.unified.mapper.UserRoleMapper;
import top.zhengru.sso.unified.service.UserService;
import top.zhengru.sso.unified.utils.JwtUtils;
import top.zhengru.sso.unified.vo.PermInfoVO;
import top.zhengru.sso.unified.vo.RoleInfoVO;

import java.util.*;
import java.util.concurrent.TimeUnit;

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
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    StringRedisTemplate redisTemplate;
    @Autowired
    EasyAuthService easyAuthService;

    @Override
    public String login(TokenUser tokenUser) {
        Authentication authenticate = null;
        EasyAuthAuthenticationToken easyAuthAuthenticationToken = new EasyAuthAuthenticationToken(tokenUser.getId(), null);
        authenticate = authenticationManager.authenticate(easyAuthAuthenticationToken);
        if (Objects.isNull(authenticate)) throw new UnifiedPortalServerException("登录失败");
        UserDetailImpl userDetails = this.queryUserDetailById(tokenUser.getId());
        Map<String, Object> claims = new HashMap<>();
        claims.put("userInfo", userDetails);
        JwtUtils jwtUtils = new JwtUtils();
        String token = jwtUtils.generateJwtToken(claims);
        redisTemplate.opsForValue()
                .set("LoginUser:" + userDetails.getUsername() + "_" + token, JsonUtils.toString(userDetails), 12, TimeUnit.HOURS);
        return token;
    }

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

    @Override
    public void logout() {
        UserDetailImpl userDetail = getUserDetail();
        String appToken = redisTemplate.opsForSet().randomMember(RedisKeyConstant.SSO_PREFIX + RedisKeyConstant.USER_APP_TOKEN_KEY + userDetail.getId());
        Set<String> keys = redisTemplate.keys("LoginUser:" + userDetail.getUsername() + "*");
        assert keys != null;
        redisTemplate.delete(keys);
        easyAuthService.logout(appToken, true);
    }

    /**
     * 获取当前用户信息
     *
     * @return
     */
    private UserDetailImpl getUserDetail() {
        return (UserDetailImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}




