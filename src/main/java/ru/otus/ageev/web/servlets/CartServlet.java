package ru.otus.ageev.web.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;


import static ru.otus.ageev.constants.WebConstant.CART_PAGE;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {
    final static Logger LOG = Logger.getLogger(CartServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher(CART_PAGE).forward(req, resp);
    }
}
