const regexName = /^([А-Я]{1}[а-яё]{1,23}|[A-Z]{1}[a-z]{1,23})$/;
const regexUserId = /^(?!\d+$)\w{8,20}/;
const regexMail = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})/;
const regexPassword = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{8,}/;

$(document).ready(function () {
    $('#user-id').on('input', function () {
        var input = $(this);
        var is_userId = regexUserId.test(input.val());
        if (is_userId) {
            input.removeClass("invalid").addClass("valid");
        } else {
            input.removeClass("valid").addClass("invalid");
        }
    });

    $('#name').on('input', function () {
        var input = $(this);
        var is_name = regexName.test(input.val());
        changeStyleInput(is_name, input);
    });

    $('#last-name').on('input', function () {
        var input = $(this);
        var is_last_name = regexName.test(input.val());
        changeStyleInput(is_last_name, input);
    });

    $('#mail').on('input', function () {
        var input = $(this);
        var is_email = regexMail.test(input.val());
        if (is_email) {
            input.removeClass("invalid").addClass("valid");
        } else {
            input.removeClass("valid").addClass("invalid");
        }
    });

    $('#password').on('input', function () {
        var input = $(this);
        var is_password = regexPassword.test(input.val());
        changeStyleInput(is_password, input);
    });

});

let changeStyleInput = function (is_correct, input) {
    if (is_correct) {
        input.removeClass("invalid").addClass("valid");
    } else {
        input.removeClass("valid").addClass("invalid");
    }
};