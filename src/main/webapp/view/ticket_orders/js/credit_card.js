$(function() {


    // 頁面開關燈
    $("#btn-light").on("click", function() {
        $("#btn-light").addClass("-off");
        $("#btn-dark").removeClass("-off");
        $("main").addClass("-dark");
    });
    $("#btn-dark").on("click", function() {
        $("#btn-dark").addClass("-off");
        $("#btn-light").removeClass("-off");
        $("main").removeClass("-dark");
    });

    // 下拉選單
    $('.nav-item .nav_ul').on('click', function() {
        if ($(this).hasClass("switchDisplay") == false) {
            $('.nav-item .nav_ul,.nav-item ul').removeClass('switchDisplay');
        }
        $(this).toggleClass('switchDisplay').siblings().toggleClass('switchDisplay');
    });

    // 搜尋欄位放大鏡停止預設行為
    $("#a_loupe").on("click", function(event) {
        event.preventDefault();
    });

    // 漢堡選單按鈕停止預設行為
    $(".hamberger_menu").on("click", function(event) {
        event.preventDefault();
    })

    // 漢堡選單側邊攔位
    $(".hamberger_menu").on("click", function() {
        $(".hide_menu").toggleClass("-bye");
    })

    // 信用卡卡號自動往下一格
    $('.cardnumber').keyup(function(e) {
        if ($(this).val().length == $(this).attr('maxlength'))
            $(this).parent().next(".form-floating").children().focus();
    });

    // 儲值成功sweet alert
    $("#btn_primary").on("click", function() {
        swal("感謝您的支持!!", "", "success");
    })



});