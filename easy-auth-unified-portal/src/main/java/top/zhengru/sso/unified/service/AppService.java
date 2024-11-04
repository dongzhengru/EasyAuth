package top.zhengru.sso.unified.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import top.zhengru.sso.unified.entity.App;
import top.zhengru.sso.unified.entity.PageResult;
import top.zhengru.sso.unified.param.AppQueryParam;
import top.zhengru.sso.unified.vo.AppInfoVo;

import java.util.List;

/**
* @author 董政儒
* @description 针对表【sys_app】的数据库操作Service
* @createDate 2024-10-27 14:09:17
*/
public interface AppService extends IService<App> {

    PageResult<AppInfoVo> queryApplicationInfoList(AppQueryParam appQueryParam);
}
