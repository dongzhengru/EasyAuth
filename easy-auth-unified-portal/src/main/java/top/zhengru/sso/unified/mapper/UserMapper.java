package top.zhengru.sso.unified.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.zhengru.sso.unified.entity.User;

/**
* @author 董政儒
* @description 针对表【sys_user】的数据库操作Mapper
* @createDate 2024-10-27 14:09:17
* @Entity top.zhengru.sso.server.entityUser
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

}




