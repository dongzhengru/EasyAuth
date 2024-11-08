package top.zhengru.sso.unified.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.zhengru.sso.unified.entity.Permission;
import top.zhengru.sso.unified.service.PermissionService;
import top.zhengru.sso.unified.mapper.PermissionMapper;
import org.springframework.stereotype.Service;

/**
* @author 董政儒
* @description 针对表【sys_permission】的数据库操作Service实现
* @createDate 2024-11-07 20:47:10
*/
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission>
    implements PermissionService{

}




