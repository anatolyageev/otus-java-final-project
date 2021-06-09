<%@include file="/WEB-INF/jspf/head.jspf" %>

<link href="<c:url value="/styles/cart.css"/> " rel="stylesheet">
<script src="<c:url value="/js/cart.js"/>"></script>

</head>
<body>

<%@include file="/WEB-INF/jspf/navbar-bar.jspf" %>

<section class="jumbotron text-center">
    <div class="container">
        <h1 class="jumbotron-heading"><fmt:message key="order"/></h1>
    </div>
</section>

<div class="container mb-4">
    <div class="row">
        <div class="col-12">
            <div class="table-responsive">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th scope="col"><fmt:message key="product"/></th>
                        <th scope="col" class="text-center"><fmt:message key="quantity"/></th>
                        <th scope="col" class="text-right"><fmt:message key="price"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${cart.getProductsList()}" var="product">
                        <tr id="product-row-${product.id}">
                            <td>${product.name}</td>
                            <td>${cart.getAmountOfProduct(product)}</td>
                            <td class="text-right"
                                id="product-price-${product.id}">${product.price * cart.getAmountOfProduct(product)}
                            </td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td></td>
                        <td><strong><fmt:message key="total"/></strong></td>
                        <td class="text-right"><strong id="total-price">${cart.getTotalPrice()}</strong></td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="col mb-2">

            <form class="form-horizontal" method="post" action="order">
                <fieldset>

                    <!-- Form Name -->
                    <legend><fmt:message key="addition.information.for.order"/></legend>

                    <!-- Text input-->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="requisites-input">Text Input</label>
                        <div class="col-md-4">
                            <input id="requisites-input" name="requisitesInput" type="text" placeholder="requisites" class="form-control input-md">
                        </div>
                    </div>
                    <div class="row">

                        <div class="col-sm-12  col-md-4 text-center">
                            <a href="products" class="btn-lg btn-block btn-info"><fmt:message key="continue.shopping"/></a>
                        </div>

                        <div class="col-sm-12 col-md-4 text-right">
                            <button class="btn btn-lg btn-block btn-success text-uppercase" type="submit"
                                onclick="deleteAllProduct()" ><fmt:message key="make.an.order"/>
                            </button>
                        </div>
                    </div>
                </fieldset>
            </form>
        </div>
    </div>
</div>


<%@include file="/WEB-INF/jspf/footer.jspf" %>
