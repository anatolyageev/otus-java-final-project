package ru.otus.ageev.repository.transaction;

import java.sql.SQLException;

@FunctionalInterface
public interface RepositoryExecutor<T> {
    T execute() throws SQLException;
}
