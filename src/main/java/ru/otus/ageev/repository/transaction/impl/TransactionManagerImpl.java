package ru.otus.ageev.repository.transaction.impl;

import ru.otus.ageev.exeptions.DBException;
import ru.otus.ageev.repository.db.JdbcConnectionHolder;
import ru.otus.ageev.repository.transaction.RepositoryExecutor;
import ru.otus.ageev.repository.transaction.TransactionManager;
import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.apache.log4j.Logger;

public class TransactionManagerImpl implements TransactionManager {
    private static final Logger LOG = Logger.getLogger(TransactionManagerImpl.class);
    DataSource dataSource;

    public TransactionManagerImpl(DataSource dataSource) {
        LOG.debug("TransactionManagerImpl constructor");
        this.dataSource = dataSource;
    }

    @Override
    public <T> T executeDmlTransaction(RepositoryExecutor<T> repositoryExecutor) {
        Connection connection = null;
        T result = null;
        try {
            connection = dataSource.getConnection();
            JdbcConnectionHolder.setConnection(connection);
            result = repositoryExecutor.execute();
            connection.commit();
        } catch (SQLException | DBException throwables) {
            rollback(connection);
            LOG.error("TransactionManagerImpl method executeDmlTransaction() error --> " + throwables);
        } finally {
            closeConnection(connection);
        }
        return result;
    }

    @Override
    public <T> T executeDqlTransaction(RepositoryExecutor<T> repositoryExecutor) {
        Connection connection = null;
        T result = null;
        try {
            connection = dataSource.getConnection();
            JdbcConnectionHolder.setConnection(connection);
            result = (T) repositoryExecutor.execute();
        } catch (SQLException | DBException throwables) {
            LOG.error("TransactionManagerImpl method executeDqlTransaction() error --> " + throwables);
        } finally {
            closeConnection(connection);
        }
        return result;
    }

    /**
     * Rollbacks a connection.
     *
     * @param con Connection to be rollbacked.
     */
    private void rollback(Connection con) {
        if (con != null) {
            try {
                con.rollback();
            } catch (SQLException ex) {
                LOG.error("Cannot rollback transaction", ex);
            }
        }
    }

    /**
     * Closes a connection and remove it from ThreadLocal
     *
     * @param connection Connection to be closed.
     */
    private void closeConnection(Connection connection) {
        JdbcConnectionHolder.removeConnection();
        try {
            connection.close();
        } catch (SQLException throwables) {
            LOG.error("Cannot close connection", throwables);
        }
    }
}
