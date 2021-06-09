package ru.otus.ageev.repository.impl;

import ru.otus.ageev.domain.User;
import ru.otus.ageev.repository.UserRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class UserRepositoryImpl implements UserRepository {
    private Map<String, User> userDb;

    public UserRepositoryImpl() {
        this.userDb = new HashMap<>();
    }

    @Override
    public List<User> getAll() {
        return (List<User>) userDb.values();
    }

    @Override
    public User getOne(String login) {
        return userDb.get(login);
    }

    @Override
    public boolean deleteUser(String login) {
        return Objects.nonNull(userDb.remove(login));
    }

    @Override
    public User createUser(User user) {
        return userDb.put(user.getLogin(), user);
    }

    @Override
    public boolean checkUserExistInDb(String login) {
        return userDb.containsKey(login);
    }
}
