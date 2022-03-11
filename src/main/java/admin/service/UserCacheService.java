package admin.service;

import admin.config.auth.custom.annotation.LoginUser;
import admin.config.auth.dto.SessionUser;
import lombok.extern.slf4j.Slf4j;
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
}
