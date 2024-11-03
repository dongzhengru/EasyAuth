package top.zhengru.sso.client;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties({ClientProperties.class})
public class ClientAutoConfiguration {

}