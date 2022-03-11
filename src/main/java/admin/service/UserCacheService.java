package admin.service;

import admin.config.auth.custom.annotation.LoginUser;
import admin.config.auth.dto.SessionUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserCacheService {

    @Cacheable(value = "name", key = "#user.getName()")
    public String getUserName(@LoginUser SessionUser user) {
        log.info("UserCacheService.getUserName()");
        return user.getName();
    }

    @CacheEvict(value="name", allEntries=true)
    public String getUserNameCacheEvict(@LoginUser SessionUser user) {
        log.info("UserCacheService.getUserNameCacheEvict()");
        return user.getName();
    }

    @CachePut(value = "name", key = "#user.getName()")
    public String putUserName(@LoginUser SessionUser user) {
        log.info("UserCacheService.putUserName()");
        return user.getName();
    }
}
