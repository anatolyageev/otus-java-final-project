package ru.otus.ageev.web.servlets;

import ru.otus.ageev.domain.Cart;
import ru.otus.ageev.domain.Order;
import ru.otus.ageev.domain.OrderStatus;
import ru.otus.ageev.services.OrderService;
import ru.otus.ageev.services.ProductService;
import ru.otus.ageev.services.UserService;
import java.io.IOException;
import java.util.Optional;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;


import static ru.otus.ageev.constants.WebConstant.CART;
import static ru.otus.ageev.constants.WebConstant.LOGIN_USER;
import static ru.otus.ageev.constants.WebConstant.ORDER;
import static ru.otus.ageev.constants.WebConstant.ORDER_SERVICE;
import static ru.otus.ageev.constants.WebConstant.PRODUCT_SERVICE;
import static ru.otus.ageev.constants.WebConstant.REQUISITES_INPUT;
import static ru.otus.ageev.constants.WebConstant.USER_SERVICE;

@WebServlet("/order")
public class OrderServlet extends HttpServlet {
    final static Logger LOG = Logger.getLogger(OrderServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/orderPage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Cart cart = (Cart) Optional.ofNullable(session.getAttribute(CART)).orElse(new Cart());
        ProductService productService = (ProductService) req.getServletContext().getAttribute(PRODUCT_SERVICE);
        OrderService orderService = (OrderService) req.getServletContext().getAttribute(ORDER_SERVICE);
        UserService userService = (UserService) req.getServletContext().getAttribute(USER_SERVICE);
        String userLogin = (String) session.getAttribute(LOGIN_USER);
        LOG.debug("userLogin: " + userLogin);
        Long userId = userService.getOne(userLogin).getId();
        String requisites = req.getParameter(REQUISITES_INPUT);
        Order newOrder = new Order();

        LOG.debug("requisites" + requisites);
        if (cart.getProductsNumber() <= 0) {

        }
        newOrder.setOrderStatus(OrderStatus.GENERATED);
        newOrder.setRequisites(requisites);
        newOrder.setUserOrder(cart.getProductsInOrder());
        newOrder.setUserId(userId);
        LOG.debug("newOrder" + newOrder);
        newOrder = orderService.createOrder(newOrder);
        session.removeAttribute(CART);
        session.setAttribute(ORDER, newOrder);
        req.getRequestDispatcher("/WEB-INF/jsp/orderSuccess.jsp").forward(req, resp);
    }
}
