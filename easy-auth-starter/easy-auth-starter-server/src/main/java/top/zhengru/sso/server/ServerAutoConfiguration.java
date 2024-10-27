package top.zhengru.sso.server;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import top.zhengru.sso.server.manager.*;

@Configuration(proxyBeanMethods = false)
@AutoConfigureBefore({ServerAutoConfiguration.class})
@EnableConfigurationProperties({ServerProperties.class})
public class ServerAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(AbstractCodeManager.class)
    public AbstractCodeManager codeManager(ServerProperties properties, StringRedisTemplate redisTemplate) {
        return new CodeManager(properties.getCodeTimeout(), redisTemplate);
    }

    @Bean
    @ConditionalOnMissingBean(AbstractAppTokenManager.class)
    public AbstractAppTokenManager tokenManager(ServerProperties properties, StringRedisTemplate redisTemplate) {
        return new AppTokenManager(properties.getAppTokenTimeout(), redisTemplate);
    }

    @Bean
    @ConditionalOnMissingBean(AbstractTicketGrantingTicketManager.class)
    public AbstractTicketGrantingTicketManager ticketGrantingTicketManager(ServerProperties properties, AbstractAppTokenManager appTokenManager, StringRedisTemplate redisTemplate) {
        return new TicketGrantingTicketManager(properties.getTgtTimeout(), appTokenManager, redisTemplate);
    }
}