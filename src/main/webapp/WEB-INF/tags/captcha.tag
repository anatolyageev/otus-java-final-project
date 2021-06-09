<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<div class="captcha">
    <img src="img/captcha?hidden_field=${hidden_field}">
    <c:if test = "${applicationScope.captchaType == 'field'}">
        <input type='hidden' name='hidden_field' value="${hidden_field}">
    </c:if>
    <input type='text' name='user_captcha' placeholder='Enter those numbers'>
</div>
<br>