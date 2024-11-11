package top.zhengru.sso.unified;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@MapperScan("top.zhengru.sso.unified.mapper")
@ComponentScan(basePackages = {"top.zhengru.sso.unified", "top.zhengru.sso.client"})
@SpringBootApplication
public class UnifiedPortalApplication {

    public static void main(String[] args) {
        SpringApplication.run(UnifiedPortalApplication.class, args);
    }
}
