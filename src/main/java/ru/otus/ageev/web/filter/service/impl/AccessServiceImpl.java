package ru.otus.ageev.web.filter.service.impl;

import ru.otus.ageev.domain.UserRole;
import ru.otus.ageev.web.filter.service.AccessService;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

public class AccessServiceImpl implements AccessService {
    private Map<String, List<String>> securityMap;

    public AccessServiceImpl(Map<String, List<String>> securityMap){
        this.securityMap = securityMap;
    }

    @Override
    public boolean isUrlPermitted(String url) {
        return securityMap.entrySet().stream().anyMatch(es->url.matches(es.getKey()));
    }

    @Override
    public boolean isAccessible(UserRole userRole, String url) {
        Optional<Entry<String,List<String>>> userRoles= securityMap.entrySet()
                .stream()
                .filter(es->url.matches(es.getKey().trim()))
                .findFirst();
        if(!userRoles.isPresent()){
            return true;
        }
        List<String> roles = userRoles.get().getValue();
        return roles.contains(userRole.toString().toLowerCase());
    }
}
