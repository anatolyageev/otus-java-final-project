package ru.otus.ageev.repository.utils;

import ru.otus.ageev.exeptions.Messages;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcUtils {
    private static final Logger LOG = Logger.getLogger(JdbcUtils.class);
    /**
     * Closes a result set object.
     */
    public static void closeResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                LOG.error(Messages.ERR_CANNOT_CLOSE_RESULTSET, ex);
            }
        }
    }
}
