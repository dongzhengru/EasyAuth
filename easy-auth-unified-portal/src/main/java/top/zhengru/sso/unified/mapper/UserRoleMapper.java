package top.zhengru.sso.unified.mapper;

import top.zhengru.sso.unified.entity.UserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author 董政儒
* @description 针对表【sys_user_role】的数据库操作Mapper
* @createDate 2024-11-07 20:47:10
* @Entity top.zhengru.sso.unified.entity.UserRole
*/
public interface UserRoleMapper extends BaseMapper<UserRole> {

    List<String> queryRoleByUserId(Long userId);
}




