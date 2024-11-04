package top.zhengru.sso.unified;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("top.zhengru.sso.unified.mapper")
@SpringBootApplication
public class UnifiedPortalApplication {

    public static void main(String[] args) {
        SpringApplication.run(UnifiedPortalApplication.class, args);
    }
}
