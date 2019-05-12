/**
 * Created by ANRI on 09.05.2019.
 */

function validateClientAccount(evt){
    var code = $("#client-account").val();

    // If backspace is press, delete space if needed
    if (evt.keyCode === 8){
        code = code[code.length - 1] === " " ? code.substr(0, code.length - 1) : code;
        $("#client-account").val(code);
        return;

        // Check that current key is numeric
    } else if (/^[a-zA-Z]*$/.test(parseInt(evt.key)) || /^[а-яА-Я]*$/.test(parseInt(evt.key)) || code.length > 19)
        code = code.substr(0, code.length - 1);

    // Set space if needed
    if (code.length == 4 || (code.length > 4 && code.length < 19 && code.lastIndexOf(" ") == code.length - 5))
        code = code + " ";

    $("#client-account").val(code);
}

function validateClientName(evt, sub_ib){
    var code = $("#client-" + sub_ib).val();
    if (!/^[a-zA-Z]*$/.test(evt.key) && evt.key != " ")
        code = code.substr(0, code.length - 1);

    code = code.toUpperCase();
    $("#client-" + sub_ib).val(code);
}

function validateClientCVC(evt){
    var code = $("#client-cvc").val();
    if (!/^\d*$/.test(parseInt(evt.key)) || code.length > 3)
        code = code.substr(0, code.length - 1);

    $("#client-cvc").val(code)
}