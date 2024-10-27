package top.zhengru.sso.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.zhengru.sso.server.entity.App;
import top.zhengru.sso.server.mapper.AppMapper;
import org.springframework.stereotype.Service;
import top.zhengru.sso.server.service.AppService;

/**
* @author 董政儒
* @description 针对表【sys_app】的数据库操作Service实现
* @createDate 2024-10-27 14:09:17
*/
@Service
public class AppServiceImpl extends ServiceImpl<AppMapper, App>
    implements AppService {

}




