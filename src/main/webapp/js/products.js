$(document).ready(function () {
    $('#productName').on('input', function () {
        var input = $(this);
        var is_userId = input.val().length < 255;
        if (is_userId) {
            input.removeClass("invalid").addClass("valid");
        } else {
            input.removeClass("valid").addClass("invalid");
        }
    });

});

$(document).ready(function () {
    if (history.pushState) {
        var res = '';
        var d = location.href.split("#")[0].split("?");
        var base = d[0];
        var query = d[1];
        if (query) {
            var params = query.split("&");
            for (var i = 0; i < params.length; i++) {
                var keyval = params[i].split("=");
                if (keyval[1] !== "") {
                    res += params[i] + '&';
                }
            }
        }
        console.log("res --> " + res);
        var newUrl = base + '?' + res;
        history.pushState(null, null, newUrl.slice(0, -1)
        );
    } else {
        console.warn('History API не поддерживается');
    }
});


$(document).ready(function () {
    let add_to_cart = $(".add-to-cart");

    add_to_cart.click(function () {
        var id = $(this).data('id');
        $.ajax({
            url: "addToCart",
            method: "POST",
            data: ({productId: id}),
            success: response
        });
    });

    function response(data) {
        data = JSON.parse(data);
        var cartBadge = document.getElementById("cart-badge");
        var newAmaunt = data.countCart;
        cartBadge.innerText = cartBadge.textContent = newAmaunt;
    }
});


