/**
 * Created by ANRI on 11.05.2019.
 */
function createNewUser(){

    var data = {
        "clientName": $("#client-name").val(),
        "clientLastname": $("#client-lastname").val(),
        "clientPassport": $("#client-passport-series").val() + " " + $("#client-passport-number").val(),
        "birthday": $("#birth-date").val(),
        "clientAccount": $("#client-account-1").val() + " " + $("#client-account-2").val() + " " + $("#client-account-3").val() + " " + $("#client-account-4").val(),
        "clientCvc": $("#client-cvc").val(),
        "clientKeyword": $("#client-keyword").val(),
        "clientPhone": $("#client-phone").val(),
        "clientBalance": $("#client-balance").val()
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
           console.log(data);
           $("#overlay-message-block").children().text(data.responseText);
           $(".overlay").show();
        },
        error: function (e) {
            console.log(e);
            $("#overlay-message-block").children("p").text(e.responseText);
            $(".overlay").show();
        }
    });
}

function hideOverlay(){
    $(".overlay").hide();
}