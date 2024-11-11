package top.zhengru.sso.unified.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import top.zhengru.sso.base.constant.BaseConstant;
import top.zhengru.sso.base.entity.TokenUser;
import top.zhengru.sso.base.util.JsonUtils;
import top.zhengru.sso.client.constant.RedisKeyConstant;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Set;

@Component
public class SSOLogoutFilter implements Filter {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        if (httpRequest.getRequestURI().equals("/sso/logout")) {
            String appToken = httpRequest.getHeader(BaseConstant.LOGOUT_PARAMETER_NAME);
            TokenUser logoutUser = JsonUtils.parseObject(redisTemplate.opsForValue().get(RedisKeyConstant.SSO_PREFIX + RedisKeyConstant.APP_TOKEN_KEY + appToken), TokenUser.class);
            // 应用注销业务代码
            Set<String> keys = redisTemplate.keys("LoginUser:" + logoutUser.getUsername() + "*");
            assert keys != null;
            redisTemplate.delete(keys);
        }
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 初始化过滤器
    }

    @Override
    public void destroy() {
        // 清理资源
    }
}
