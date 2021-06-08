function validate() {
    var userId = document.getElementById("user-id").value;
    var name = document.getElementById("name").value;
    var lastName = document.getElementById("last-name").value;
    var email = document.getElementById("mail").value;
    var pass = document.getElementById("password").value;
    var error_message = document.getElementById("error_message");
    var checBox = document.getElementById("check-box-reg");

    error_message.style.padding = "10px";

    var text;

    if (!regexUserId.test(userId)) {
        text = "Please Enter valid User Id. Min length 8 symbols";
        error_message.innerHTML = text;
        return false;
    }

    if (!regexName.test(name)) {
        text = "Please Enter valid Name";
        error_message.innerHTML = text;
        return false;
    }

    if (!regexName.test(lastName)) {
        text = "Please Enter valid Last Name";
        error_message.innerHTML = text;
        return false;
    }

    if (!regexMail.test(email)) {
        text = "Please Enter valid Email";
        error_message.innerHTML = text;
        return false;
    }

    if (!regexPassword.test(pass)) {
        text = "Please Enter valid password. Min length 8, digit and at lest one capital letter";
        error_message.innerHTML = text;
        return false;
    }

    if (!checBox.checked) {
        console.log(checBox);
        console.log(checBox.checked);
        text = "You should agree to the terms and conditions og cancel the registration";
        error_message.innerHTML = text;
        return false;
    }
    return true;
}