package admin.config.cache.annotation;

import org.springframework.cache.annotation.Cacheable;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Cacheable(cacheNames = "tes", keyGenerator = "testKeyGenerator", cacheManager = "redisCacheManager")
public @interface Test2CacheGet {
}
