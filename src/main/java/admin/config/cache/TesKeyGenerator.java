package admin.config.cache;

import org.springframework.cache.interceptor.KeyGenerator;

import java.lang.reflect.Method;

public class TesKeyGenerator implements KeyGenerator {

    @Override
    public Object generate(Object target, Method method, Object... params) {
        return "generatedKey";
    }
}
