package top.zhengru.sso.unified.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import top.zhengru.sso.unified.filter.EasyAuthAuthenticationFilter;
import top.zhengru.sso.unified.handler.AccessDeniedHandlerImpl;
import top.zhengru.sso.unified.handler.AuthenticationEntryPointImpl;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    EasyAuthAuthenticationProvider easyAuthAuthenticationProvider;
    @Autowired
    EasyAuthAuthenticationFilter easyAuthAuthenticationFilter;
    @Autowired
    AccessDeniedHandlerImpl accessDeniedHandler;
    @Autowired
    AuthenticationEntryPointImpl authenticationEntryPoint;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.
                // EasyAuth登录验证方式
                authenticationProvider(easyAuthAuthenticationProvider);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                //不通过Session获取SecurityContext
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/captcha/get").permitAll()
                .antMatchers("/captcha/check").permitAll()
                .antMatchers("/sso/login").permitAll()
                .antMatchers("/sso/auth").permitAll()
                // 除上面外的所有请求全部需要鉴权认证
                .anyRequest().authenticated()
                .and()
                .logout()
                .logoutUrl("/sso/logout")
                .and()
                .cors();
//                .and()
                // 设置无session
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        //添加过滤器
        http.addFilterBefore(easyAuthAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        //配置异常处理器
        http.exceptionHandling()
                //配置认证失败处理器
                .authenticationEntryPoint(authenticationEntryPoint)
                .accessDeniedHandler(accessDeniedHandler);
    }


    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
