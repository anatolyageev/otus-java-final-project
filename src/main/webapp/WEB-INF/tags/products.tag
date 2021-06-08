<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="locale"/>
<div class="row">
    <c:forEach items="${productList}" var="product">
        <div class="col-lg-4 col-md-6 mb-4">
            <div class="card h-100">
                <a href="#"><img class="card-img-top" src=${product.image} alt=""></a>
                <div class="card-body">
                    <h4 class="card-title">
                        <a href="#">${product.name}</a>
                    </h4>
                    <h5>${product.price}</h5>
                    <p class="card-text">${product.shortDescription}</p>
                </div>
                <div class="card-footer">
                    <button class="rounded btn-success add-to-cart" data-id="${product.id}" ><fmt:message key="to.cart"/></button>
                </div>
            </div>
        </div>
    </c:forEach>
</div>