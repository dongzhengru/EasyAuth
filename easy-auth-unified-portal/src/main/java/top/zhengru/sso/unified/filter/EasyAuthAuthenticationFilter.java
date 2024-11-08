package top.zhengru.sso.unified.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import top.zhengru.sso.base.entity.Result;
import top.zhengru.sso.base.util.JsonUtils;
import top.zhengru.sso.unified.config.EasyAuthAuthenticationToken;
import top.zhengru.sso.unified.service.impl.UserDetailImpl;
import top.zhengru.sso.unified.utils.JwtUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Objects;

@Component
public class EasyAuthAuthenticationFilter extends GenericFilter {
    @Autowired
    StringRedisTemplate redisTemplate;
    @Autowired
    ObjectMapper objectMapper;
    private static final String SSO_PREFIX = "easy_auth:";
    private static final String APP_TOKEN_KEY = "server_app_token:";
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String token = request.getHeader("Authorization");   // 从请求头中拿到token
        //判断token
        if (Objects.nonNull(token) && !token.trim().isEmpty()){
            Claims claims = null;
            try {
                claims = Jwts.parser().setSigningKey(JwtUtils.SECRET).parseClaimsJws(token).getBody();
            } catch (JwtException e) {
                e.printStackTrace();
                servletResponse.setCharacterEncoding("UTF-8");
                PrintWriter writer = servletResponse.getWriter();
                writer.write(objectMapper.writeValueAsString(new Result<>(9999, "尚未登录，请重新登录")));
                writer.flush();
                return;
            }
            Map<String, Object> userInfoMap = (Map<String, Object>) claims.get("userInfo");
            UserDetailImpl user = JsonUtils.parseObject(redisTemplate.opsForValue().get("LoginUser:" + userInfoMap.get("username") + "_" + token), UserDetailImpl.class);
            if (user == null) {
                servletResponse.setCharacterEncoding("UTF-8");
                PrintWriter writer = servletResponse.getWriter();
                writer.write(objectMapper.writeValueAsString(new Result<>(9999, "尚未登录，请重新登录")));
                writer.flush();
                return;
            }
            SecurityContextHolder.getContext().setAuthentication
                    (new EasyAuthAuthenticationToken(user, null, user.getAuthorities()));
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
