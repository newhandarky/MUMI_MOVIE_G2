$(function () {


    // 頁面開關燈
    $("#btn-light").on("click", function () {
        $("#btn-light").addClass("-off");
        $("#btn-dark").removeClass("-off");
        $("main").addClass("-dark");
    });
    $("#btn-dark").on("click", function () {
        $("#btn-dark").addClass("-off");
        $("#btn-light").removeClass("-off");
        $("main").removeClass("-dark");
    });

    // 下拉選單
    $('.nav-item .nav_ul').on('click', function () {
        if ($(this).hasClass("switchDisplay") == false) {
            $('.nav-item .nav_ul,.nav-item ul').removeClass('switchDisplay');
        }
        $(this).toggleClass('switchDisplay').siblings().toggleClass('switchDisplay');
    });

    // 搜尋欄位放大鏡停止預設行為
    $("#a_loupe").on("click", function (event) {
        event.preventDefault();
    });

    // 漢堡選單按鈕停止預設行為
    $(".hamberger_menu").on("click", function (event) {
        event.preventDefault();
    })

    // 漢堡選單側邊攔位
    $(".hamberger_menu").on("click", function () {
        $(".hide_menu").toggleClass("-bye");
    })




    $('.container1').slick({
        // prevArrow: "<img class='a-left control-c prev slick-prev' src='./IMAGE/posters/咒術迴戰0(12+).jpg'>",
        // nextArrow: "<img class='a-right control-c next slick-next' src='./IMAGE/posters/咒術迴戰0(12+).jpg' width:200 height:200>",
        centerMode: true,
        centerPadding: '28px',
        slidesToShow: 3,
        arrow: true,
        responsive: [
            {
                breakpoint: 1024,
                settings: {
                    slidesToShow: 2
                }
            },
            {
                breakpoint: 768,
                settings: {
                    slidesToShow: 1
                }
            }
        ],
        // prevArrow: $(".pp2"),
        // nextArrow: $(".nn2"),
    });












});