/**
 * Created by ANRI on 11.05.2019.
 */
function createNewUser(){

    var data = {
        "client_name": $("#client-name").val(),
        "client_lastname": $("#client-lastname").val(),
        "birth_date": $("#birth-date").val(),
        "client_account": $("#client-account").val(),
        "client_cvc": $("#client-cvc").val(),
        "client_keyword": $("#client-keyword").val(),
        "client_phone": $("#client-phone").val(),
        "client_balance": $("#client-balance").val()
    };

    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "POST",
        url: "/sign-up/new-client",
        data: JSON.stringify(data),
        dataType: 'json',
        timeout: 600000,
        success: function (data) {
           console.log(data);
        },
        error: function (e) {
            console.log(e);
        }
    });
}