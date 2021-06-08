<%@include file="/WEB-INF/jspf/head.jspf" %>

<link href="<c:url value="/styles/cart.css"/> " rel="stylesheet">
<script src="<c:url value="/js/cart.js"/>"></script>

</head>
<body>


<%@include file="/WEB-INF/jspf/navbar-bar.jspf" %>

<%--<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">--%>

<section class="jumbotron text-center">
    <div class="container">
        <h1 class="jumbotron-heading"><fmt:message key="your.cart"/></h1>
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
                        <th></th>
                    </tr>
                    </thead>

                    <tbody>
                    <c:forEach items="${cart.getProductsList()}" var="product">
                        <tr id="product-row-${product.id}">
                            <td>${product.name}</td>


                            <td>
                                <input class="form-control" id="product-amount"
                                       onchange="changeProductNumber(this.value, ${product.id})" type="number" min="1"
                                       name="productNumber" value="${cart.getAmountOfProduct(product)}"/>
                            </td>


                            <td class="text-right"
                                id="product-price-${product.id}">${product.price * cart.getAmountOfProduct(product)}</td>
                            <td class="text-right">
                                <button onclick="deleteProduct(${product.id})" class="btn btn-sm btn-danger"><i
                                        class="fa fa-trash"></i></button>
                            </td>
                        </tr>
                    </c:forEach>

                    <tr>

                        <td></td>

                        <td></td>
                        <td><strong><fmt:message key="total"/></strong></td>
                        <td class="text-right"><strong id="total-price">${cart.getTotalPrice()}</strong></td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="col mb-2">
            <div class="row">
                <div class="col-sm-12  col-md-4 text-center">
                    <a href="products" class="btn-lg btn-block btn-info"><fmt:message key="continue.shopping"/></a>
                </div>
                <div class="col-sm-12  col-md-4 text-center">
                    <button onclick="deleteAllProduct()" class="btn-lg btn-block btn-danger"><fmt:message key="clear.cart"/></button>
                </div>
                <div class="col-sm-12 col-md-4 text-right">
                    <button class="btn btn-lg btn-block btn-success text-uppercase" onclick="makeOrder(${not empty loginUser})"><fmt:message key="checkout"/></button>
                </div>
            </div>
        </div>
    </div>
</div>


<%@include file="/WEB-INF/jspf/footer.jspf" %>
