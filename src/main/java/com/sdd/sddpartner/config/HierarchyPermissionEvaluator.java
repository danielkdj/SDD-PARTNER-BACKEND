package com.sdd.sddpartner.config;

import com.sdd.sddpartner.domain.Board;
import com.sdd.sddpartner.domain.TeamCalendar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.NoSuchElementException;
import java.util.regex.Pattern;

@Component
public class HierarchyPermissionEvaluator implements PermissionEvaluator {

    private static final Logger log = LoggerFactory.getLogger(HierarchyPermissionEvaluator.class);

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {

        if (targetDomainObject instanceof TeamCalendar) {
            TeamCalendar object = (TeamCalendar) targetDomainObject;
            log.info("teamCalendar search : {}", object.getId());
            return true; // false -> denied: status 403 error
        }

        return true; // access ok
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        log.info("targetId: {}, targetType: {}, permission: {}", targetId, targetType, permission);

        if (false) {
            throw new NoSuchElementException();
        }

        return true;
    }

}
