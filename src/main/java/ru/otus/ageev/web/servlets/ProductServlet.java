package ru.otus.ageev.web.servlets;

import ru.otus.ageev.bean.ProductFilterBean;
import ru.otus.ageev.domain.Product;
import ru.otus.ageev.services.ProductService;
import ru.otus.ageev.web.utils.ProductRequestUtils;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;


import static ru.otus.ageev.constants.WebConstant.CATEGORIES_LIST;
import static ru.otus.ageev.constants.WebConstant.CURRENT_PAGE;
import static ru.otus.ageev.constants.WebConstant.HREF_PAGINATION;
import static ru.otus.ageev.constants.WebConstant.MAIN_PAGE;
import static ru.otus.ageev.constants.WebConstant.NUMBER_OF_PAGES;
import static ru.otus.ageev.constants.WebConstant.PRODUCERS_LIST;
import static ru.otus.ageev.constants.WebConstant.PRODUCT_FILTER_BEEN;
import static ru.otus.ageev.constants.WebConstant.PRODUCT_LIST;
import static ru.otus.ageev.constants.WebConstant.PRODUCT_SERVICE;
import static ru.otus.ageev.web.utils.ProductRequestUtils.hrefFromFilter;

@WebServlet("/products")
public class ProductServlet extends HttpServlet {
    final static Logger LOG = Logger.getLogger(ProductServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ProductFilterBean productFilterBean = ProductRequestUtils.filterFromRequest(req);
        LOG.debug("Filter been: " + productFilterBean);
        String currentPage = req.getParameter(CURRENT_PAGE);
        String hrefForPagination = hrefFromFilter(productFilterBean);
        ProductService productService = (ProductService) req.getServletContext().getAttribute(PRODUCT_SERVICE);
        List<Product> productList = productService.getByFilter(productFilterBean);
        List<String> categoriesList = productService.getAllCategories();
        List<String> producersList = productService.getAllProducers();
        Long noOfPages = productService.getCountByFilter(productFilterBean) / productFilterBean.getProductsPerPage();
        LOG.debug(hrefForPagination);
        LOG.debug("No of pages: " + noOfPages);
        req.setAttribute(PRODUCT_LIST, productList);
        req.setAttribute(PRODUCT_FILTER_BEEN, productFilterBean);
        req.setAttribute(NUMBER_OF_PAGES, noOfPages);
        req.setAttribute(CURRENT_PAGE, currentPage);
        req.setAttribute(CATEGORIES_LIST, categoriesList);
        req.setAttribute(PRODUCERS_LIST, producersList);
        req.setAttribute(HREF_PAGINATION, hrefForPagination);
        req.getRequestDispatcher(MAIN_PAGE).forward(req, resp);
    }
}
