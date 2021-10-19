package ir.bigz.rediscache.global;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ConfigurationProperties("spring.redis")
public class RedisProperties {

    private String host;
    private int port;

}