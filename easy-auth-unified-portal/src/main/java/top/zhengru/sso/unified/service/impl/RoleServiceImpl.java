package top.zhengru.sso.unified.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.zhengru.sso.unified.entity.Role;
import top.zhengru.sso.unified.service.RoleService;
import top.zhengru.sso.unified.mapper.RoleMapper;
import org.springframework.stereotype.Service;

/**
* @author 董政儒
* @description 针对表【sys_role】的数据库操作Service实现
* @createDate 2024-11-07 20:47:10
*/
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
    implements RoleService{

}




