package ru.otus.ageev.repository;

import ru.otus.ageev.domain.User;

import java.util.List;

public interface UserRepository {

    List<User> getAll();

    User getOne(String login);

    boolean deleteUser(String login);

    User createUser(User user);

    boolean checkUserExistInDb(String login);
}
