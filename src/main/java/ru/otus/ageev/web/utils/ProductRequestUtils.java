package ru.otus.ageev.web.utils;

import ru.otus.ageev.bean.ProductFilterBean;
import ru.otus.ageev.bean.SortDirection;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;


import static ru.otus.ageev.constants.WebConstant.PRODUCT_FILTER_FORM_CATEGORY;
import static ru.otus.ageev.constants.WebConstant.PRODUCT_FILTER_FORM_FIELD_SORT;
import static ru.otus.ageev.constants.WebConstant.PRODUCT_FILTER_FORM_MAX_PRICE;
import static ru.otus.ageev.constants.WebConstant.PRODUCT_FILTER_FORM_MIN_PRICE;
import static ru.otus.ageev.constants.WebConstant.PRODUCT_FILTER_FORM_NAME;
import static ru.otus.ageev.constants.WebConstant.PRODUCT_FILTER_FORM_PRODUCER;
import static ru.otus.ageev.constants.WebConstant.PRODUCT_FILTER_FORM_PRODUCTS_DEFAULT_OFFSET;
import static ru.otus.ageev.constants.WebConstant.PRODUCT_FILTER_FORM_PRODUCTS_OFFSET;
import static ru.otus.ageev.constants.WebConstant.PRODUCT_FILTER_FORM_PRODUCTS_PER_PAGE;
import static ru.otus.ageev.constants.WebConstant.PRODUCT_FILTER_FORM_PRODUCTS_PER_PAGE_DEFAULT;
import static ru.otus.ageev.constants.WebConstant.PRODUCT_FILTER_FORM_SORT_DIRECTION;

public class ProductRequestUtils {
    final static Logger LOG = Logger.getLogger(ProductRequestUtils.class);

    public static ProductFilterBean filterFromRequest(HttpServletRequest request) {
        ProductFilterBean productFilterBean = new ProductFilterBean();
        productFilterBean.setProductName(request.getParameter(PRODUCT_FILTER_FORM_NAME));
        LOG.debug("ProductFilterBean : " + productFilterBean);
        setMinMaxPrice(request, productFilterBean);
        LOG.debug("ProductFilterBean : " + productFilterBean);
        productFilterBean.setOffset(Optional.ofNullable(request.getParameter(PRODUCT_FILTER_FORM_PRODUCTS_OFFSET))
                .map(Integer::parseInt)
                .orElse(PRODUCT_FILTER_FORM_PRODUCTS_DEFAULT_OFFSET));
        LOG.debug("ProductFilterBean : " + productFilterBean);
        productFilterBean.setProductsPerPage(Optional.ofNullable(request.getParameter(PRODUCT_FILTER_FORM_PRODUCTS_PER_PAGE))
                .map(Integer::parseInt)
                .orElse(PRODUCT_FILTER_FORM_PRODUCTS_PER_PAGE_DEFAULT));
        LOG.debug("ProductFilterBean : " + productFilterBean);
        productFilterBean.setSortDirection(Optional.ofNullable(request.getParameter(PRODUCT_FILTER_FORM_SORT_DIRECTION))
                .map(SortDirection::valueOf)
                .orElse(SortDirection.ASC));
        LOG.debug("ProductFilterBean : " + productFilterBean);
        productFilterBean.setSortByField(request.getParameter(PRODUCT_FILTER_FORM_FIELD_SORT));
        LOG.debug("ProductFilterBean : " + productFilterBean);
        productFilterBean.setCategoryNames(getCategoryNames(request, PRODUCT_FILTER_FORM_CATEGORY));
        LOG.debug("ProductFilterBean : " + productFilterBean);
        productFilterBean.setProducerNames(getCategoryNames(request, PRODUCT_FILTER_FORM_PRODUCER));
        LOG.debug("ProductFilterBean : " + productFilterBean);
        return productFilterBean;
    }

    public static String hrefFromFilter(ProductFilterBean productFilterBean) {
        StringBuilder sb = new StringBuilder();
        sb.append("products").append("?");
        productFilterBean.getCategoryNames().
                forEach(category -> sb.append(PRODUCT_FILTER_FORM_CATEGORY).append("=").append(category).append("&"));
        productFilterBean.getProducerNames().
                forEach(producer -> sb.append(PRODUCT_FILTER_FORM_PRODUCER).append("=").append(producer).append("&"));
        sb.append(PRODUCT_FILTER_FORM_NAME).append("=").append(productFilterBean.getProductName()).append("&")
                .append(PRODUCT_FILTER_FORM_MIN_PRICE).append("=").append(productFilterBean.getPriceMin()).append("&")
                .append(PRODUCT_FILTER_FORM_MAX_PRICE).append("=").append(productFilterBean.getPriceMax()).append("&")
                .append(PRODUCT_FILTER_FORM_PRODUCTS_PER_PAGE).append("=").append(productFilterBean.getProductsPerPage()).append("&")
                .append(PRODUCT_FILTER_FORM_SORT_DIRECTION).append("=").append(productFilterBean.getSortDirection()).append("&")
                .append(PRODUCT_FILTER_FORM_FIELD_SORT).append("=").append(productFilterBean.getSortByField()).append("&");

        return sb.toString();
    }

    private static List<String> getCategoryNames(HttpServletRequest request, String productFilterFormCategory) {
        String[] parameters = request.getParameterValues(productFilterFormCategory);
        if (Objects.nonNull(parameters)) {
            return Arrays.asList(parameters);
        }
        return Collections.emptyList();
    }

    private static void setMinMaxPrice(HttpServletRequest request, ProductFilterBean productFilterBean) {
        int max = 0;
        int min = 0;
        try {
            max = Integer.parseInt(request.getParameter(PRODUCT_FILTER_FORM_MAX_PRICE));
            min = Integer.parseInt(request.getParameter(PRODUCT_FILTER_FORM_MIN_PRICE));
        } catch (NumberFormatException ex) {
            LOG.debug("Incorrect data");
        }
        if (max == 0) {
            return;
        }
        LOG.debug("Incorrect data: " + min + "  " + max);
        if (min <= max) {
            productFilterBean.setPriceMin(min);
            productFilterBean.setPriceMax(max);
        }
    }
}
