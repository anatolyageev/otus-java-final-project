<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="locale"/>
<%--<%@ page import="WebConstant" %>--%>
<c:choose>
    <c:when test="${not empty sessionScope.loginUser}">
        <li>
            <img src=getAvatar class="avatar-img">
        </li>
        <li>
            <a class="nav-link" href="#"> ${sessionScope.loginUser} </a>
        </li>
        <li>
            <a href="logout" class="btn btn-primary "><fmt:message key="sign.out"/></a>
        </li>
    </c:when>

    <c:otherwise>
        <li class="dropdown">
            <a data-toggle="dropdown" class="dropdown-toggle" href="#"><i class="fa fa-user-o"></i> <fmt:message key="login"/></a>
            <ul class="dropdown-menu">
                <li>
                    <form class="form-inline login-form" action="login" method="post">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-user"></i></span>
                            <input type="text" class="form-control" name="userId" placeholder="Username" required>
                        </div>
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-lock"></i></span>
                            <input type="password" class="form-control" name="password" placeholder="Password" required>
                        </div>
                        <button type="submit" class="btn btn-primary"><fmt:message key="login"/></button>
                    </form>
                </li>
            </ul>
        </li>
    </c:otherwise>
</c:choose>
