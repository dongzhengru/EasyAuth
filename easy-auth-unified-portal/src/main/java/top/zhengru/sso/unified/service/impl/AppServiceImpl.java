package top.zhengru.sso.unified.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import top.zhengru.sso.unified.entity.App;
import top.zhengru.sso.unified.entity.PageResult;
import top.zhengru.sso.unified.mapper.AppMapper;
import top.zhengru.sso.unified.param.AppQueryParam;
import top.zhengru.sso.unified.service.AppService;
import top.zhengru.sso.unified.vo.AppInfoVo;

import java.util.List;

/**
* @author 董政儒
* @description 针对表【sys_app】的数据库操作Service实现
* @createDate 2024-10-27 14:09:17
*/
@Service
public class AppServiceImpl extends ServiceImpl<AppMapper, App>
    implements AppService {

    @Autowired
    AppMapper appMapper;

    @Override
    public PageResult<AppInfoVo> queryApplicationInfoList(AppQueryParam appQueryParam) {
        PageHelper.startPage(appQueryParam.getPageNo(), appQueryParam.getPageSize());
        appQueryParam.setAuditStatus(1);
        appQueryParam.setPublishStatus(1);
        appQueryParam.setShelveStatus(1);
        List<AppInfoVo> appList = appMapper.queryApplicationInfoList(appQueryParam);
        PageInfo<AppInfoVo> pageInfo = new PageInfo<>(appList);
        return new PageResult<>(pageInfo.getTotal(), pageInfo.getList());
    }
}




