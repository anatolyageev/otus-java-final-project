package ru.otus.ageev.repository.impl;

import ru.otus.ageev.bean.ProductFilterBean;
import ru.otus.ageev.domain.Product;
import ru.otus.ageev.exeptions.DBException;
import ru.otus.ageev.repository.ProductRepository;
import ru.otus.ageev.repository.db.JdbcConnectionHolder;
import ru.otus.ageev.repository.utils.JdbcUtils;
import ru.otus.ageev.repository.utils.ProductFilterUtil;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import static ru.otus.ageev.constants.sql.Fields.CATEGORY_NAME;
import static ru.otus.ageev.constants.sql.Fields.ENTITY_ID_PRODUCTS;
import static ru.otus.ageev.constants.sql.Fields.PRODUCER_NAME;
import static ru.otus.ageev.constants.sql.Fields.PRODUCT_CATEGORY_NAME;
import static ru.otus.ageev.constants.sql.Fields.PRODUCT_COUNT;
import static ru.otus.ageev.constants.sql.Fields.PRODUCT_IMAGE;
import static ru.otus.ageev.constants.sql.Fields.PRODUCT_META_TITLE;
import static ru.otus.ageev.constants.sql.Fields.PRODUCT_NAME;
import static ru.otus.ageev.constants.sql.Fields.PRODUCT_PRICE;
import static ru.otus.ageev.constants.sql.Fields.PRODUCT_PRODUCER_NAME;
import static ru.otus.ageev.constants.sql.Fields.PRODUCT_SHORT_DESCRIPTION;
import static ru.otus.ageev.constants.sql.SqlQuery.SQL_GET_ALL_CATEGORIES;
import static ru.otus.ageev.constants.sql.SqlQuery.SQL_GET_ALL_PRODUCERS;
import static ru.otus.ageev.constants.sql.SqlQuery.SQL_GET_ONE_PRODUCT;
import static ru.otus.ageev.constants.sql.SqlQuery.SQL_PRODUCT_GET_ALL;
import static ru.otus.ageev.constants.sql.SqlQuery.SQL_PRODUCT_GET_COUNT;

public class ProductRepositoryImpl implements ProductRepository {
    final static Logger LOG = Logger.getLogger(ProductRepositoryImpl.class);


    @Override
    public List<Product> getByFilter(ProductFilterBean productFilterBean) {
        List<Product> productList = new ArrayList<>();
        String sqlQuery = buildSqlFromFilter(SQL_PRODUCT_GET_ALL, productFilterBean, true);
        try (Statement st = JdbcConnectionHolder.getConnection().createStatement();
             ResultSet rs = st.executeQuery(sqlQuery)) {
            while (rs.next()) {
                productList.add(productFromResultSet(rs));
            }
        } catch (SQLException throwables) {
            LOG.error("Repository method getByFilter() error --> " + throwables);
            throw new DBException("Cannot obtain users from the database.", throwables);
        }
        return productList;
    }


    public Long getCountByFilter(ProductFilterBean productFilterBean) {
        String sqlQuery = buildSqlFromFilter(SQL_PRODUCT_GET_COUNT, productFilterBean, false);
        long result = 0L;
        try (Statement st = JdbcConnectionHolder.getConnection().createStatement();
             ResultSet rs = st.executeQuery(sqlQuery)) {
            if (rs.next()) {
                result = rs.getLong(PRODUCT_COUNT);
            }
        } catch (SQLException throwables) {
            LOG.error("Repository method getCountByFilter() error --> " + throwables);
            throw new DBException("Cannot obtain users from the database.", throwables);
        }
        return result;
    }


    @Override
    public Product getOne(Long id) {
        ResultSet rs = null;
        try (PreparedStatement ps = JdbcConnectionHolder.getConnection().prepareStatement(SQL_GET_ONE_PRODUCT)) {
            ps.setLong(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                return productFromResultSet(rs);
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
    public List<String> getAllProducers() {
        List<String> producerList = new ArrayList<>();
        try (Statement st = JdbcConnectionHolder.getConnection().createStatement();
             ResultSet rs = st.executeQuery(SQL_GET_ALL_PRODUCERS)) {
            while (rs.next()) {
                producerList.add(rs.getString(PRODUCER_NAME));
            }
        } catch (SQLException throwables) {
            LOG.error("Repository method getAllProducers() error --> " + throwables);
            throw new DBException("Cannot obtain users from the database.", throwables);
        }
        return producerList;
    }

    @Override
    public List<String> getAllCategories() {
        List<String> categoriesList = new ArrayList<>();
        try (Statement st = JdbcConnectionHolder.getConnection().createStatement();
             ResultSet rs = st.executeQuery(SQL_GET_ALL_CATEGORIES)) {
            while (rs.next()) {
                categoriesList.add(rs.getString(CATEGORY_NAME));
            }
        } catch (SQLException throwables) {
            LOG.error("Repository method getAllCategories() error --> " + throwables);
            throw new DBException("Cannot obtain users from the database.", throwables);
        }
        return categoriesList;
    }

    private Product productFromResultSet(ResultSet rs) throws SQLException {
        Product product = new Product();

        product.setId(rs.getLong(ENTITY_ID_PRODUCTS));
        product.setName(rs.getString(PRODUCT_NAME));
        product.setCategory(rs.getString(PRODUCT_CATEGORY_NAME));
        product.setProducer(rs.getString(PRODUCT_PRODUCER_NAME));
        product.setShortDescription(rs.getString(PRODUCT_SHORT_DESCRIPTION));
        product.setPrice(rs.getBigDecimal(PRODUCT_PRICE));
        product.setImage(rs.getString(PRODUCT_IMAGE));
        product.setMetaTitle(rs.getString(PRODUCT_META_TITLE));
        return product;
    }

    private String buildSqlFromFilter(String sqlQuery, ProductFilterBean productFilterBean, boolean limitNeeded) {
        StringBuilder sb = new StringBuilder().append(sqlQuery);
        ProductFilterUtil productFilterUtil = new ProductFilterUtil(productFilterBean);
        sb.append(productFilterUtil.addFilter(limitNeeded));
        LOG.debug("Result query: " + sb.toString());
        return sb.toString();
    }


}
