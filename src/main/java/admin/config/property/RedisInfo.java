package admin.config.property;

import org.springframework.context.annotation.Configuration;

@Configuration
public class RedisInfo {

    public static String host2;
    public static int port2;

    private final PropertyConfig propertyConfig;

    public RedisInfo(PropertyConfig propertyConfig) {
        this.propertyConfig = propertyConfig;
        host2 = propertyConfig.getHost();
        port2 = propertyConfig.getPort();
    }
}
