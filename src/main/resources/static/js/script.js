$(document).ready(function () {
    $("#user_register").submit(function (event) {
        event.preventDefault();
        console.log($("#username").val() + $("#email").val() + $("#password").val())
        var formData = {
            username : $("#username").val(),
            email : $("#email").val(),
            password : $("#password").val()
        };

        // Obtain the CSRF token from the server's response cookies
        var csrfToken = $("meta[name='_csrf']").attr("content");
        var csrfHeader = $("meta[name='_csrf_header']").attr("content");

        // Include the CSRF token in AJAX requests
        $.ajaxSetup({
            beforeSend: function (xhr) {
                xhr.setRequestHeader(csrfHeader, csrfToken);
            }
        });

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/register",
            data: JSON.stringify(formData),
            dataType: "json",
            success: function (response) {
                $(".toast-body").text("Registered successfully.");
                $("#toast").toast(show);
            },
            error: function (error) {
                displayCustomError(error.responseJSON);
            }
        });
    });

    function displayCustomError(error) {
        $("#nameError").text(error.name);
        $("#emailError").text(error.email);
        $("#passwordError").text(error.password);
    }
});