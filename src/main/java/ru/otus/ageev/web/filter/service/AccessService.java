package ru.otus.ageev.web.filter.service;

import ru.otus.ageev.domain.UserRole;

public interface AccessService {
    boolean isUrlPermitted(String url);

    boolean isAccessible(UserRole userRole, String url);
}
