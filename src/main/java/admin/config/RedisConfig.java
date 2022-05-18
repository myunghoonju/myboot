package admin.config;

import admin.config.auth.custom.CustomCacheErrorHandler;
import admin.config.cache.ListDeSerializer;
import admin.config.cache.ListTeamTwoDeSerializer;
import admin.config.cache.TesKeyGenerator;
import admin.config.cache.TestKeyGenerator;
import admin.domain.dsl.Tes;
import admin.domain.dsl.TesTwo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import io.lettuce.core.TimeoutOptions;
import io.lettuce.core.cluster.ClusterClientOptions;
import io.lettuce.core.cluster.ClusterTopologyRefreshOptions;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class RedisConfig extends CachingConfigurerSupport {

    @Bean
    public RedisTemplate<?, ?> redisTemplate() {
        RedisTemplate<?, ?> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory());

        return template;
    }

    @Bean(name = "redisCacheManager")
    public RedisCacheManager redisCacheManager() {
        return RedisCacheManager.RedisCacheManagerBuilder
                .fromConnectionFactory(redisConnectionFactory())
                .cacheDefaults(redisCacheDefaultConfiguration())
                .withInitialCacheConfigurations(getConfig())
                .build();
    }

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration();
        RedisNode redisNode = new RedisNode("127.0.0.1", 6379);
        redisClusterConfiguration.addClusterNode(redisNode);
        return new LettuceConnectionFactory(redisClusterConfiguration ,getLettuceClientConfiguration());
    }

    private static LettuceClientConfiguration getLettuceClientConfiguration() {
        return LettuceClientConfiguration.builder()
                .commandTimeout(Duration.ofMillis(1000))// timeout rule
                .shutdownTimeout(Duration.ofMillis(1000))  // shutting down time limit
                .clientOptions(ClusterClientOptions.builder()
                        .topologyRefreshOptions(ClusterTopologyRefreshOptions.builder()
                                .enablePeriodicRefresh(Duration.ofMillis(1000)) // refresh node info duration
                                .enableAllAdaptiveRefreshTriggers()
                                .adaptiveRefreshTriggersTimeout(Duration.ofMillis(10000))
                                .build())
                        .timeoutOptions(TimeoutOptions.enabled())
                        .build())
                .build();
    }

    @Override
    public CacheErrorHandler errorHandler() {
        return new CustomCacheErrorHandler();
    }

    @Bean
    public KeyGenerator tesKeyGenerator() {
        return new TesKeyGenerator();
    }

    @Bean
    public KeyGenerator testKeyGenerator() {
        return new TestKeyGenerator();
    }

    private RedisCacheConfiguration redisCacheDefaultConfiguration() {
        return RedisCacheConfiguration
                .defaultCacheConfig()
                .serializeKeysWith(RedisSerializationContext.SerializationPair
                        .fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair
                        .fromSerializer(new GenericJackson2JsonRedisSerializer()));
    }

    private Map<String, RedisCacheConfiguration> getConfig() {
        Map<String, RedisCacheConfiguration> configMap = new LinkedHashMap<>();
        configMap.put("tes", redisCacheDefaultConfiguration().serializeValuesWith(RedisSerializationContext.SerializationPair
                .fromSerializer(new Jackson2JsonRedisSerializer(Tes.class)))
        );
        configMap.put("tesTwo", redisCacheDefaultConfiguration().serializeValuesWith(RedisSerializationContext.SerializationPair
                .fromSerializer(new Jackson2JsonRedisSerializer(TesTwo.class)))
        );
        configMap.put("store", redisCacheDefaultConfiguration().serializeValuesWith(RedisSerializationContext.SerializationPair
                        .fromSerializer(new GenericJackson2JsonRedisSerializer(getObjMapper())))
        );
        configMap.put("storeTwo", redisCacheDefaultConfiguration().serializeValuesWith(RedisSerializationContext.SerializationPair
                .fromSerializer(new GenericJackson2JsonRedisSerializer(getObjMapperTwo())))
        );

        return configMap;
    }

    private ObjectMapper getObjMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addDeserializer(List.class, new ListDeSerializer());

        objectMapper.registerModule(simpleModule);
        return objectMapper;
    }


    private ObjectMapper getObjMapperTwo() {
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addDeserializer(List.class, new ListTeamTwoDeSerializer());

        objectMapper.registerModule(simpleModule);
        return objectMapper;
    }

}
