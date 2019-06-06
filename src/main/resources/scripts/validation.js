/**
 * Created by ANRI on 09.05.2019.
 */
$(function () {
    //Text only
    $( "input[id^='client-account'], #client-cvc, #client-balance, #client-passport-number," +
        "#client-passport-series").inputfilter({
        allowNumeric: true,
        allowText: false
    });

    $( "#client-phone").inputfilter({
        allowNumeric: true,
        allowText: false,
        allowCustom: ["+"]
    });

    $( "#client-name, #client-lastname").inputfilter({
        allowNumeric: false,
        allowText: true,
        allowCustom: [" "]
    });

    $("#client-name").on("input keydown keyup drop", function() {
        $("#client-name").val($("#client-name").val().toUpperCase());
    });
    $("#client-lastname").on("input keydown keyup drop", function() {
        $("#client-lastname").val($("#client-lastname").val().toUpperCase());
    });
});