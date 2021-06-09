package ru.otus.ageev.services.impl;

import ru.otus.ageev.domain.User;
import ru.otus.ageev.exeptions.ItemNotFoundException;
import ru.otus.ageev.repository.UserRepository;
import ru.otus.ageev.services.UserService;
import ru.otus.ageev.constants.Messages;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAll() {
        return userRepository.getAll();
    }

    @Override
    public User getOne(String login) {
        return Optional.ofNullable(userRepository.getOne(login))
                .orElseThrow(() -> new ItemNotFoundException(Messages.NO_USER_WITH_LOGIN + login));
    }

    @Override
    public boolean deleteUser(String login) {
        return userRepository.deleteUser(login);
    }

    @Override
    public User createUser(User user) {
        return userRepository.createUser(user);
    }

    @Override
    public boolean checkUserExistInDb(String login) {
        return userRepository.checkUserExistInDb(login);
    }
}
