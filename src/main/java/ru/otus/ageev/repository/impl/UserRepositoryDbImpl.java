package ru.otus.ageev.repository.impl;

import ru.otus.ageev.domain.User;
import ru.otus.ageev.domain.UserRole;
import ru.otus.ageev.exeptions.DBException;
import ru.otus.ageev.repository.UserRepository;
import ru.otus.ageev.repository.db.JdbcConnectionHolder;
import ru.otus.ageev.repository.utils.JdbcUtils;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


import static ru.otus.ageev.constants.WebConstant.USER_ROLE;
import static ru.otus.ageev.constants.sql.Fields.ENTITY_ID;
import static ru.otus.ageev.constants.sql.Fields.USER_EMAIL;
import static ru.otus.ageev.constants.sql.Fields.USER_FIRST_NAME;
import static ru.otus.ageev.constants.sql.Fields.USER_LAST_NAME;
import static ru.otus.ageev.constants.sql.Fields.USER_LOGIN;
import static ru.otus.ageev.constants.sql.Fields.USER_PASSWORD;
import static ru.otus.ageev.constants.sql.SqlQuery.SQL_DELETE_USER;
import static ru.otus.ageev.constants.sql.SqlQuery.SQL_GET_ALL;
import static ru.otus.ageev.constants.sql.SqlQuery.SQL_GET_ONE;
import static ru.otus.ageev.constants.sql.SqlQuery.SQL_INSERT_USER;


public class UserRepositoryDbImpl implements UserRepository {
    final static Logger LOG = Logger.getLogger(UserRepositoryDbImpl.class);

    @Override
    public List<User> getAll() {
        List<User> userList = new ArrayList<>();
        try (Statement st = JdbcConnectionHolder.getConnection().createStatement();
             ResultSet rs = st.executeQuery(SQL_GET_ALL)) {
            while (rs.next()) {
                userList.add(userFromResultSet(rs));
            }
        } catch (SQLException throwables) {
            LOG.error("Repository method getAll() error --> " + throwables);
            throw new DBException("Cannot obtain users from the database.", throwables);
        }
        return userList;
    }

    @Override
    public User getOne(String login) {
        ResultSet rs = null;
        try (PreparedStatement ps = JdbcConnectionHolder.getConnection().prepareStatement(SQL_GET_ONE)) {
            ps.setString(1, login);
            rs = ps.executeQuery();
            if (rs.next()) {
                return userFromResultSet(rs);
            }
        } catch (SQLException throwables) {
            LOG.error("Repository method getOne() error --> " + throwables);
            throw new DBException("Cannot obtain user from the database.", throwables);
        } finally {
            JdbcUtils.closeResultSet(rs);
        }
        return null;
    }

    @Override
    public boolean deleteUser(String login) {
        boolean result = false;
        try (PreparedStatement ps = JdbcConnectionHolder.getConnection().prepareStatement(SQL_DELETE_USER)) {
            LOG.debug("Repository method deleteUser() start --> " + login);
            if (ps.executeUpdate() > 0) {
                result = true;
            }
            LOG.debug("Repository method deleteUser() finish --> " + login);
        } catch (SQLException throwables) {
            LOG.error("Repository method deleteUser() error --> " + throwables);
            throw new DBException("Cannot delete user from the database.", throwables);
        }
        return result;
    }

    @Override
    public User createUser(User user)  {
        ResultSet rs;
        try (PreparedStatement ps = JdbcConnectionHolder.getConnection().prepareStatement(SQL_INSERT_USER, Statement.RETURN_GENERATED_KEYS)) {
            int index = 1;
            ps.setString(index++, user.getLogin());
            ps.setString(index++, user.getFirstName());
            ps.setString(index++, user.getLastName());
            ps.setString(index++, user.getEmail());
            ps.setString(index++, user.getPassword());
            ps.setString(index++, user.getUserRole().toString().toLowerCase());
            if (ps.executeUpdate() > 0) {
                rs = ps.getGeneratedKeys();
                if (Objects.nonNull(rs) && rs.next()) {
                    Long id = rs.getLong(1);
                    user.setId(id);
                }
            }
            LOG.debug("Repository method createUser() --> " + user);
        } catch (SQLException throwables) {
            LOG.error("Repository method createUser() error --> " + throwables);
            throw new DBException("Cannot create user from the database.", throwables);
        }
        return user;
    }

    @Override
    public boolean checkUserExistInDb(String login) {
        return !Objects.isNull(getOne(login));
    }

    private User userFromResultSet(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getLong(ENTITY_ID));
        user.setLogin(rs.getString(USER_LOGIN));
        user.setFirstName(rs.getString(USER_FIRST_NAME));
        user.setLastName(rs.getString(USER_LAST_NAME));
        user.setEmail(rs.getString(USER_EMAIL));
        user.setPassword(rs.getString(USER_PASSWORD));
        user.setUserRole(UserRole.valueOf(rs.getString(USER_ROLE).toUpperCase()));
        return user;
    }
}
