package ru.otus.ageev.repository.impl;

import ru.otus.ageev.domain.Order;
import ru.otus.ageev.domain.ProductsInOrder;
import ru.otus.ageev.exeptions.DBException;
import ru.otus.ageev.repository.OrderRepository;
import ru.otus.ageev.repository.db.JdbcConnectionHolder;
import ru.otus.ageev.repository.utils.JdbcUtils;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import org.apache.log4j.Logger;

import static ru.otus.ageev.constants.sql.SqlQuery.SQL_INSERT_ORDER;
import static ru.otus.ageev.constants.sql.SqlQuery.SQL_INSERT_PRODUCTS_ORDER;

public class OrderRepositoryImpl implements OrderRepository {
    final static Logger LOG = Logger.getLogger(OrderRepositoryImpl.class);

    @Override
    public Order createOrder(Order order) {
        ResultSet rs = null;
        try (PreparedStatement ps = JdbcConnectionHolder.getConnection().prepareStatement(SQL_INSERT_ORDER, Statement.RETURN_GENERATED_KEYS)) {
            injectParametersToPreparedStatment(order, ps);
            if (ps.executeUpdate() > 0) {
                rs = ps.getGeneratedKeys();
                if (Objects.nonNull(rs) && rs.next()) {
                    Long id = rs.getLong(1);
                    setIdToOrder(order, id);
                }
            }
        } catch (SQLException throwables) {
            LOG.error("Cannot create user from the database. createOrder " + throwables );
            throw new DBException("Cannot create user from the database.", throwables);
        } finally {
            JdbcUtils.closeResultSet(rs);
        }
        return order;
    }

    private ProductsInOrder addProductsInOrder(ProductsInOrder productsInOrder){
        try (PreparedStatement ps = JdbcConnectionHolder.getConnection().prepareStatement(SQL_INSERT_PRODUCTS_ORDER)) {
            injectParametersToPreparedStatementProductOrder(productsInOrder, ps);
           ps.executeUpdate();
        } catch (SQLException throwables) {
            LOG.error("Cannot create user from the database. addProductsInOrder" + throwables );
            throw new DBException("Cannot create user from the database.", throwables);
        }
        return productsInOrder;
    }

    private void setIdToOrder(Order order, Long id) {
        order.setId(id);
        order.getUserOrder().forEach(e->{
            e.setOrderId(id);
            addProductsInOrder(e);
        });
    }

    private void injectParametersToPreparedStatment(Order order, PreparedStatement ps) throws SQLException {
        int index = 1;
        ps.setString(index++, order.getOrderStatus().toString());
        ps.setString(index++, order.getStatusDetails());
        ps.setTimestamp(index++, java.sql.Timestamp.valueOf(java.time.LocalDateTime.now()));
        ps.setLong(index++, order.getUserId());
    }

    private void injectParametersToPreparedStatementProductOrder(ProductsInOrder productsInOrder, PreparedStatement ps) throws SQLException {
        int index = 1;
        ps.setLong(index++,productsInOrder.getOrderId());
        ps.setLong(index++,productsInOrder.getProductId());
        ps.setBigDecimal(index++,productsInOrder.getProductPrice());
        ps.setInt(index++,productsInOrder.getNumberInOrder());
    }
}
