/**
 * Created by ANRI on 11.05.2019.
 */
function createNewUser(){

    var data = {
        "name": $("#client-name").val(),
        "lastname": $("#client-lastname").val(),
        "passport": $("#client-passport-series").val() + " " + $("#client-passport-number").val(),
        "birthday": $("#birth-date").val(),
        "account": $("#client-account-1").val() + " " + $("#client-account-2").val() + " " + $("#client-account-3").val() + " " + $("#client-account-4").val(),
        "cvc": $("#client-cvc").val(),
        "password": $("#client-keyword").val(),
        "username": $("#client-phone").val(),
        "balance": $("#client-balance").val()
    };

    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "POST",
        url: "/sign-up",
        data: JSON.stringify(data),
        dataType: 'json',
        timeout: 600000,
        success: function (data) {
           $("#overlay-message-block").children().text(data.responseText);
           $(".overlay").show();
        },
        error: function (e) {
            $("#overlay-message-block").children("p").text(e.responseText);
            $(".overlay").show();
        }
    });
}

function hideOverlay(){
    $(".overlay").hide();
}