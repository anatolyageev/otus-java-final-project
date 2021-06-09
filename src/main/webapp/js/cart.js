function changeProductNumber(productNumber, productId) {
    if (productNumber >= 1) {
        $.ajax({
            url: "addToCart",
            method: "POST",
            data: ({
                productId: productId,
                productNumber: productNumber
            }),
            success: responseForProductNumChange
        });
    }
}

function deleteProduct(productId) {
    $.ajax({
        url: "addToCart",
        method: "DELETE",
        data: {
            productId: productId,
        },
        success: responseForProductDelete
    });

}

function deleteAllProduct() {
    $('[id^=product-row-]').each(function () {
        var id = $(this).attr('id').split("-")[2];
        console.log(id);
        deleteProduct(id);
        console.log($(this).attr('id').split("-")[2]);
        console.log($(this).attr('id').split("-").slice(2));

    })
}

function responseForProductNumChange(data) {
    data = JSON.parse(data);
    var cartBadge = document.getElementById("cart-badge");
    var totalPrice = document.getElementById("total-price");
    var productPrice = document.getElementById("product-price-" + data.productId);

    var newAmaunt = data.countCart;
    cartBadge.innerText = cartBadge.textContent = newAmaunt;
    totalPrice.textContent = totalPrice.innerText = data.totalPrice.toFixed(2);
    productPrice.textContent = data.productPrice.toFixed(2);
}

function responseForProductDelete(data) {
    data = JSON.parse(data);

    cartBadgeChange(data);

    var totalPrice = document.getElementById("total-price");

    var productRow = document.getElementById("product-row-" + data.productId);

    totalPrice.textContent = totalPrice.innerText = data.totalPrice.toFixed(2);
    productRow.remove();
}

function cartBadgeChange(data) {
    $('#cart-badge').text(data.countCart);
}

function makeOrder(isLoginUser) {
    console.log("login: " + isLoginUser);
    console.log("=== true" + isLoginUser === true);

    if (isLoginUser === true) {

        window.location = "order";
        return;
    }
    window.location = "registration";
}

