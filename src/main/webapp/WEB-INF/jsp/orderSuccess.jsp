<%@include file="/WEB-INF/jspf/head.jspf" %>

<link href="<c:url value="/styles/cart.css"/> " rel="stylesheet">
<script src="<c:url value="/js/cart.js"/>"></script>

</head>
<body>

<%@include file="/WEB-INF/jspf/navbar-bar.jspf" %>

<div class="col-lg-9">

<%--    <script>deleteAllProduct();</script>--%>

Order ${order.id} id status ${order.orderStatus}

</div>


<%@include file="/WEB-INF/jspf/footer.jspf" %>