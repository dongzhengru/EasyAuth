package top.zhengru.sso.unified.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import top.zhengru.sso.base.entity.Result;

import java.sql.SQLException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SQLException.class)
    public Result<String> sqlException(SQLException e){
        e.printStackTrace();
        return Result.error("数据库异常，操作失败！");
    }

    @ExceptionHandler(UnifiedPortalServerException.class)
    public Result<String> unifiedPortalServerException(UnifiedPortalServerException e){
        e.printStackTrace();
        return Result.error(e.getMessage());
    }
}