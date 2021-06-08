<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<li class="nav-item dropdown">
    <c:choose>
        <c:when test="${sessionScope.locale == 'ru_ru' }">
            <a class="nav-link dropdown-toggle" id="dropdown-locale" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><span class="flag-icon flag-icon-ru"> </span> Russian</a>
        </c:when>
        <c:otherwise>
            <a class="nav-link dropdown-toggle" id="dropdown-locale" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><span class="flag-icon flag-icon-gb"> </span> English</a>
        </c:otherwise>
    </c:choose>
    <div class="dropdown-menu" aria-labelledby="dropdown-locale">
        <a class="dropdown-item" href="${requestScope.url}?locale=en_EN"><span class="flag-icon flag-icon-gb"> </span>  English</a>
        <a class="dropdown-item" href="${requestScope.url}?locale=ru_RU"><span class="flag-icon flag-icon-ru"> </span>  Russian</a>
    </div>
</li>
