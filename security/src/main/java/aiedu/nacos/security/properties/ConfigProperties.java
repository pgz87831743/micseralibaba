package aiedu.nacos.security.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.List;

/**
 * @author pengmf
 * @since 2022/1/13
 */
@ConfigurationProperties(prefix = "pxq")
@Data
@Component
public class ConfigProperties {

    private Jwt jwt;
    private List<String> ignoreUri;


    @Data
    public static class Jwt {
        private Duration accessTokenExpires;
        private Duration refreshTokenExpires;
    }

}
