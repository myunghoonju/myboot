package admin.config.auth.custom;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.util.StringUtils;

@Slf4j
public class CustomCacheErrorHandler implements CacheErrorHandler {

    private static final Long RECOVERY_INTERVAL = 1L;

    @Override
    public void handleCacheGetError(RuntimeException exception, Cache cache, Object key) {
        String message = exception.getMessage();
        String cacheName = cache.getName();
        String s = key.toString();
        log.error("CustomCacheErrorHandler.handleCacheGetError {}, {}, {}", message, cacheName, s);
        handlerException(exception);
    }

    @Override
    public void handleCachePutError(RuntimeException exception, Cache cache, Object key, Object value) {
        handlerException(exception);
    }

    @Override
    public void handleCacheEvictError(RuntimeException exception, Cache cache, Object key) {
        handlerException(exception);
    }

    @Override
    public void handleCacheClearError(RuntimeException exception, Cache cache) {
        handlerException(exception);
    }


    private void handlerException(RuntimeException exception) {
        String simpleName = exception.getClass().getSimpleName();
        boolean isRedisException = StringUtils.startsWithIgnoreCase(simpleName, "Redis");
        if (isRedisException) {
            return;
        }
    }
}
