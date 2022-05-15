package admin.config.property;

import lombok.Getter;

@Getter
public enum ConfigEnum {

    REDIS_INFO(RedisInfo.host2, RedisInfo.port2);

    private String host;
    private int port;

    ConfigEnum(String host, int port) {
        this.host = host;
        this.port = port;
    }
}
