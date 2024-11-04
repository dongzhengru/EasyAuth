package top.zhengru.sso.unified.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.zhengru.sso.unified.entity.App;
import top.zhengru.sso.unified.param.AppQueryParam;
import top.zhengru.sso.unified.vo.AppInfoVo;

import java.util.List;

/**
* @author 董政儒
* @description 针对表【sys_app】的数据库操作Mapper
* @createDate 2024-10-27 14:09:17
* @Entity top.zhengru.sso.server.entityApp
*/
@Mapper
public interface AppMapper extends BaseMapper<App> {

    List<AppInfoVo> queryApplicationInfoList(@Param("param") AppQueryParam appQueryParam);
}




