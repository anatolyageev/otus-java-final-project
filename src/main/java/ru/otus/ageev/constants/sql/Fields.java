package ru.otus.ageev.constants.sql;

public class Fields {
    public static final String ENTITY_ID = "id";
    public static final String ENTITY_ID_PRODUCTS = "product_id";


    //User fields
    public static final String USER_LOGIN = "login";
    public static final String USER_FIRST_NAME = "first_name";
    public static final String USER_LAST_NAME = "last_name";
    public static final String USER_EMAIL = "email";
    public static final String USER_PASSWORD = "password";

    // Products fields
    public static final String PRODUCT_NAME = "name";
    public static final String PRODUCT_CATEGORY_NAME = "category_name";
    public static final String PRODUCT_PRODUCER_NAME = "producer_name";
    public static final String PRODUCT_SHORT_DESCRIPTION = "short_description";
    public static final String PRODUCT_PRICE = "price";
    public static final String PRODUCT_IMAGE = "image";
    public static final String PRODUCT_META_TITLE = "meta_title";

    public static final String PRODUCT_COUNT = "count";
    public static final String DEFAULT_ORDER_PRODUCT_FIELD = "name";
    public static final String DEFAULT_PRODUCT_PER_PAGE = "10";
    public static final String DEFAULT_PRODUCT_OFFSET = "0";

    public static final String PRODUCER_NAME = "producer_name";
    public static final String CATEGORY_NAME = "category_name";

    //SQL operators
    public static final String LEFT_BRACKETS = " (";
    public static final String RIGHT_BRACKETS = ") ";
    public static final String SQL_EQUALS = " = ";
    public static final String SQL_LIKE = " LIKE '%";
    public static final String SQL_LIKE_ANY = "%'";
    public static final String SQL_IN = " IN ";
    public static final String SQL_WHERE = " WHERE ";
    public static final String SQL_AND = " AND ";
    public static final String SQL_BETWEEN = " BETWEEN ";
    public static final String SQL_ORDER_BY = " ORDER BY ";
    public static final String SQL_LIMIT = " LIMIT ";
    public static final String SQL_COMMA = ", ";
}
