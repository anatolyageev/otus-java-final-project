package ru.otus.ageev.web.servlets;

import ru.otus.ageev.domain.Cart;
import ru.otus.ageev.domain.Product;
import ru.otus.ageev.services.ProductService;
import com.google.gson.JsonObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;


import static ru.otus.ageev.constants.WebConstant.CART;
import static ru.otus.ageev.constants.WebConstant.CART_PRODUCT_ID;
import static ru.otus.ageev.constants.WebConstant.CART_PRODUCT_PRICE;
import static ru.otus.ageev.constants.WebConstant.CART_TOTAL_PRICE;
import static ru.otus.ageev.constants.WebConstant.EQUAL;
import static ru.otus.ageev.constants.WebConstant.INT_ONE;
import static ru.otus.ageev.constants.WebConstant.PRODUCT_COUNT_CART;
import static ru.otus.ageev.constants.WebConstant.PRODUCT_ID;
import static ru.otus.ageev.constants.WebConstant.PRODUCT_NUMBER;
import static ru.otus.ageev.constants.WebConstant.PRODUCT_SERVICE;

@WebServlet("/addToCart")
public class AddToCart extends HttpServlet {
    final static Logger LOG = Logger.getLogger(AddToCart.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        Cart cart = (Cart) Optional.ofNullable(session.getAttribute(CART)).orElse(new Cart());

        ProductService productService = (ProductService) req.getServletContext().getAttribute(PRODUCT_SERVICE);
        PrintWriter pw = resp.getWriter();
        JsonObject jsonObject = new JsonObject();
        String productId = req.getParameter(PRODUCT_ID);
        String newProductNumber = req.getParameter(PRODUCT_NUMBER);
        if (Objects.isNull(productId)) {
            writerClose(pw, jsonObject);
            return;
        }

        Product addedProduct = productService.getOne(Long.parseLong(productId));

        if (cart.isProductPresent(addedProduct)) {
            if (Objects.isNull(newProductNumber)) {
                Integer currentAmount = cart.getAmountOfProduct(addedProduct);
                cart.addProductToCart(addedProduct, ++currentAmount);
            } else {
                cart.addProductToCart(addedProduct, Integer.parseInt(newProductNumber));
            }

        } else {
            cart.addProductToCart(addedProduct, INT_ONE);
        }
        fillSessionAndJson(session, cart, jsonObject, addedProduct);
        writerClose(pw, jsonObject);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Cart cart = (Cart) session.getAttribute(CART);
        ProductService productService = (ProductService) req.getServletContext().getAttribute(PRODUCT_SERVICE);
        PrintWriter pw = resp.getWriter();
        JsonObject jsonObject = new JsonObject();
        String productId;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()))) {
            String jsonObj = br.readLine();
            productId = jsonObj.split(EQUAL)[INT_ONE];
        }
        Product deletedProduct = productService.getOne(Long.parseLong(productId));
        cart.deleteProduct(deletedProduct);
        fillSessinAdnJsoneDelete(session, cart, jsonObject, deletedProduct);
        writerClose(pw, jsonObject);
    }

    private void writerClose(PrintWriter pw, JsonObject jsonObject) {
        pw.write(jsonObject.toString());
        pw.close();
    }

    private void fillSessinAdnJsoneDelete(HttpSession session, Cart cart, JsonObject jsonObject, Product deletedProduct) {
        session.setAttribute(CART, cart);
        session.setAttribute(PRODUCT_COUNT_CART, cart.getProductsNumber());
        jsonObject.addProperty(PRODUCT_COUNT_CART, cart.getProductsNumber());
        jsonObject.addProperty(CART_TOTAL_PRICE, cart.getTotalPrice());
        jsonObject.addProperty(CART_PRODUCT_ID, deletedProduct.getId());
    }

    private void fillSessionAndJson(HttpSession session, Cart cart, JsonObject jsonObject, Product product) {
        fillSessinAdnJsoneDelete(session, cart, jsonObject, product);
        jsonObject.addProperty(CART_PRODUCT_PRICE, product.getPrice().multiply(BigDecimal.valueOf(cart.getAmountOfProduct(product))));
    }
}
