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

    // 停止導覽列的標頭預設行為
    // $(".nav_ul").on("click", function(e) {
    //     e.preventDefault();
    // });

    // 漢堡選單按鈕停止預設行為
    $(".hamberger_menu").on("click", function(event) {
        event.preventDefault();
    })

    // 漢堡選單側邊攔位
    $(".hamberger_menu").on("click", function() {
        $(".hide_menu").toggleClass("-bye");
    })

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

    // 送出資料
//    $("#commit").on("click", function() {
//        swal({
//                title: "請確認資料是否填寫正確?",
//                text: "Please confirm whether the information is filled in correctly!",
//                icon: "warning",
//                buttons: true,
//                dangerMode: true,
//            })
//            .then((willDelete) => {
//                if (willDelete) {
//                    swal("資料修改完畢!!", {
//                        icon: "success",
//                    });
//                } else {
//                    swal("返回註冊頁面重新填寫");
//                }
//            });
//    })



});