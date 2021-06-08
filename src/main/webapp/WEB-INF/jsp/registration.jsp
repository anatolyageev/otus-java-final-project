<%@include file="/WEB-INF/jspf/format.jspf"%>
<%@include file="/WEB-INF/jspf/jspInput.jspf"%>
<%@include file="/WEB-INF/jspf/taglib.jspf"%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Login and Registr</title>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous">
    </script>
<%--    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"--%>
<%--          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">--%>
    <link rel="stylesheet" href="././styles/register/style.css">
</head>
<body>
<div class="hero">
    <div class="form-box">
        <div class="button-box">
            <div id="btn"></div>
            <button type="button" class="toggle-btn" onclick="login()">Log In</button>
            <button type="button" class="toggle-btn" onclick="register()">Register</button>
        </div>
        <div class="sosial-icons">
            <div id="error_message"><c:if test="${not empty sessionScope.error_message}">
                Error:  ${sessionScope.error_message}
            </c:if></div>
        </div>
        <form id="login" class="input-group" action="login" method="post">
            <input type="text" class="input-field" placeholder="Username" name="userId" required>
            <input type="password" class="input-field" placeholder="Enter Password" name="password" required>
            <input type="checkbox" class="check-box"><span class="span-login">Remember Password</span>
            <button type="submit" class="submit-btn">Log in</button>
            <div id="return-cart">    <c:if test="${not empty loginUser}">
                <div class="col-sm-12  col-md-4 text-center">
                    <a href="cart" class="btn-cart">Continue Purchase</a>
                </div>
        </c:if></div>

        </form>

        <form id="register" class="input-group input-group-register" action="registration" onsubmit="return validate();" method="post" enctype="multipart/form-data">
            <input id="user-id" type="text" class="input-field" name="userId" placeholder="User Id"
                   value="${userId_value}" required/>
            <input id="name" type="text" class="input-field" name="name" placeholder="Name"
                   value="${sessionScope.name_value}" required/>
            <input id="last-name" type="text" class="input-field" name="lastName" placeholder="Last Name"
                   value="${sessionScope.lastName_value}" required/>
            <input id="mail" type="email" class="input-field" name="email" placeholder="Email"
                   value="${sessionScope.email_value}" required/>
            <div>
            <input id="password" type="password" class="input-field" name="password" placeholder="Enter Password"
                   required/>
            <input id="check-box-reg" type="checkbox" class="check-box"><span class="span-reg">I agree to the terms and conditions</span>
    </div>
            <input id="mail" type="file" class="input-field" name="user_avatar" accept="image/*" />
            <tgcp:captcha/>
            <button type="submit" class="submit-btn">Register</button>
        </form>
    </div>

</div>


<%@include file="/WEB-INF/jspf/bootstrapFooter.jspf" %>
<script src="././js/registrationForm.js"></script>
<script src="././js/VanillaJSValidation.js"></script>
<script src="././js/JQueryValidation.js"></script>
</body>
</html>