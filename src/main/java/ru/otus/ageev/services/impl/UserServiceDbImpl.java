package ru.otus.ageev.services.impl;

import ru.otus.ageev.domain.User;
import ru.otus.ageev.exeptions.ItemNotFoundException;
import ru.otus.ageev.repository.UserRepository;
import ru.otus.ageev.repository.transaction.TransactionManager;
import ru.otus.ageev.repository.transaction.impl.TransactionManagerImpl;
import ru.otus.ageev.services.UserService;
import ru.otus.ageev.constants.Messages;

import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;

public class UserServiceDbImpl implements UserService {
    TransactionManager transactionManager;
    private final UserRepository userRepository;

    public UserServiceDbImpl(UserRepository userRepository, DataSource dataSource) {
        this.transactionManager = new TransactionManagerImpl(dataSource);
        this.userRepository = userRepository;
    }


    @Override
    public List<User> getAll() {
        return transactionManager.executeDqlTransaction(userRepository::getAll);
    }

    @Override
    public User getOne(String login) {
        return Optional.ofNullable(transactionManager.executeDqlTransaction(() -> userRepository.getOne(login)))
                .orElseThrow(() -> new ItemNotFoundException(Messages.NO_USER_WITH_LOGIN + login));
    }

    @Override
    public boolean deleteUser(String login) {
        return transactionManager.executeDmlTransaction(() -> userRepository.deleteUser(login));
    }

    @Override
    public User createUser(User user) {
        return transactionManager.executeDmlTransaction(() -> userRepository.createUser(user));
    }

    @Override
    public boolean checkUserExistInDb(String login) {
        return transactionManager.executeDqlTransaction(() -> userRepository.checkUserExistInDb(login));
    }
}
