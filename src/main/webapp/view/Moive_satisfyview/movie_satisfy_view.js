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














});

$("div.star_block").on("click", "span.star", function(e){

    let current_star = parseInt($(this).attr("data-star"));
    $("form.many-star").children("input.satisfy").attr("value", current_star);
    $(this).closest("div.star_block").find("span.star").each(function(i, item){
        
      if( parseInt($(this).attr("data-star")) <= current_star ){
        $(this).addClass("-on");
      }else{
        $(this).removeClass("-on");
      }

      
    });
  
  });