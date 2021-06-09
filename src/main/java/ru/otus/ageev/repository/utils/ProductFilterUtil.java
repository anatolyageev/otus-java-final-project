package ru.otus.ageev.repository.utils;

import ru.otus.ageev.bean.ProductFilterBean;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.apache.log4j.Logger;

import static ru.otus.ageev.constants.sql.Fields.DEFAULT_ORDER_PRODUCT_FIELD;
import static ru.otus.ageev.constants.sql.Fields.DEFAULT_PRODUCT_OFFSET;
import static ru.otus.ageev.constants.sql.Fields.DEFAULT_PRODUCT_PER_PAGE;
import static ru.otus.ageev.constants.sql.Fields.LEFT_BRACKETS;
import static ru.otus.ageev.constants.sql.Fields.PRODUCT_CATEGORY_NAME;
import static ru.otus.ageev.constants.sql.Fields.PRODUCT_NAME;
import static ru.otus.ageev.constants.sql.Fields.PRODUCT_PRICE;
import static ru.otus.ageev.constants.sql.Fields.PRODUCT_PRODUCER_NAME;
import static ru.otus.ageev.constants.sql.Fields.RIGHT_BRACKETS;
import static ru.otus.ageev.constants.sql.Fields.SQL_AND;
import static ru.otus.ageev.constants.sql.Fields.SQL_BETWEEN;
import static ru.otus.ageev.constants.sql.Fields.SQL_COMMA;
import static ru.otus.ageev.constants.sql.Fields.SQL_EQUALS;
import static ru.otus.ageev.constants.sql.Fields.SQL_IN;
import static ru.otus.ageev.constants.sql.Fields.SQL_LIKE;
import static ru.otus.ageev.constants.sql.Fields.SQL_LIKE_ANY;
import static ru.otus.ageev.constants.sql.Fields.SQL_LIMIT;
import static ru.otus.ageev.constants.sql.Fields.SQL_ORDER_BY;
import static ru.otus.ageev.constants.sql.Fields.SQL_WHERE;

public class ProductFilterUtil {
    private final static Logger LOG = Logger.getLogger(ProductFilterUtil.class);
    private boolean filterIsEmpty;
    private final ProductFilterBean productFilterBean;

    public ProductFilterUtil(ProductFilterBean productFilterBean) {
        this.filterIsEmpty = true;
        this.productFilterBean = productFilterBean;
    }

    public String addFilter(boolean limit) {
        StringBuilder sb = new StringBuilder();

        sb.append(Optional.of(addNameFilter()).orElse(""));
        sb.append(Optional.of(addNames(PRODUCT_CATEGORY_NAME, productFilterBean.getCategoryNames())).orElse(""));
        sb.append(Optional.of(addNames(PRODUCT_PRODUCER_NAME, productFilterBean.getProducerNames())).orElse(""));
        sb.append(Optional.of(addPriceFilter()).orElse(""));
        sb.append(addOrder());
        if (limit) {
            sb.append(addLimit());
        }
        LOG.debug("Method addFilter: " + sb.toString());
        return sb.toString();
    }

    private String addNameFilter() {
        StringBuilder sb = new StringBuilder();
        String nameFilter = productFilterBean.getProductName();
        if (Objects.nonNull(nameFilter) && nameFilter.length() > 0) {
            sb.append(checkFilterStatus());
            filterIsEmpty = false;
            sb.append(PRODUCT_NAME).append(SQL_LIKE).append(nameFilter).append(SQL_LIKE_ANY);
        }
        LOG.debug("Method addNameFilter: " + sb.toString());
        return sb.toString();
    }

    private String addNames(String nameOfParameters, List<String> categories) {
        StringBuilder sb = new StringBuilder();
        if (categories.size() > 0) {
            sb.append(checkFilterStatus());
            sb.append(nameOfParameters).append(SQL_IN).append(LEFT_BRACKETS);
            sb.append("'");
            sb.append(String.join("', '", categories));
            sb.append("'");
            sb.append(RIGHT_BRACKETS);
            filterIsEmpty = false;
        }
        LOG.debug("Method addProducerNames: " + sb.toString());
        return sb.toString();
    }

    private String addPriceFilter() {
        StringBuilder sb = new StringBuilder();
        if (Objects.nonNull(productFilterBean.getPriceMin()) && Objects.nonNull(productFilterBean.getPriceMax())) {
            sb.append(checkFilterStatus());

            if (productFilterBean.getPriceMin() < productFilterBean.getPriceMax()) {
                sb.append(PRODUCT_PRICE).append(SQL_BETWEEN).append(productFilterBean.getPriceMin())
                        .append(SQL_AND)
                        .append(productFilterBean.getPriceMax());
                filterIsEmpty = false;
            }

            if (productFilterBean.getPriceMin().equals(productFilterBean.getPriceMax())) {
                sb.append(PRODUCT_PRICE).append(SQL_EQUALS).append(productFilterBean.getPriceMax());
                filterIsEmpty = false;
            }
        }
        LOG.debug("Method addPriceFilter: " + sb.toString());
        return sb.toString();
    }

    private String addOrder() {
        StringBuilder sb = new StringBuilder();
        if (Objects.nonNull(productFilterBean.getSortByField()) && Objects.nonNull(productFilterBean.getSortDirection())) {
            sb.append(SQL_ORDER_BY).append(productFilterBean.getSortByField()).append(" ").append(productFilterBean.getSortDirection().toString());
        } else {
            sb.append(SQL_ORDER_BY).append(DEFAULT_ORDER_PRODUCT_FIELD);
        }
        LOG.debug("Method addOrder: " + sb.toString());
        return sb.toString();
    }

    private String addLimit() {
        StringBuilder sb = new StringBuilder();
        if (Objects.nonNull(productFilterBean.getProductsPerPage()) && Objects.nonNull(productFilterBean.getOffset())) {
            sb.append(SQL_LIMIT).append(productFilterBean.getOffset())
                    .append(SQL_COMMA)
                    .append(productFilterBean.getProductsPerPage());
        } else {
            sb.append(SQL_LIMIT)
                    .append(DEFAULT_PRODUCT_OFFSET)
                    .append(SQL_COMMA)
                    .append(DEFAULT_PRODUCT_PER_PAGE);
        }
        LOG.debug("Method addLimit: " + sb.toString());
        return sb.toString();
    }

    private String checkFilterStatus() {
        return filterIsEmpty ? SQL_WHERE : SQL_AND;
    }
}
