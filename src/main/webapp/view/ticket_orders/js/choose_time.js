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

    // 電影場次按鈕停止預設行為
    $(".showtime").on("click", function(event) {
        event.preventDefault();
    });

   

    // 點選場次變色
    $(".showtime").on("click", function() {
        $(".showtime").removeClass("-on");
        $("#movie_time_id_choose").attr("id", "movie_time_id").attr("name", "");
        $(this).addClass("-on");
        $(this).next().attr("id", "movie_time_id_choose").attr("name", "movie_time_id");
    })
    
    // 場次數字轉時間
    $(document).ready(function() {
		$('button.showtime').each(function() {
			if($(this).val() == 1){
				$(this).text("8:30");
			};
			if($(this).val() == 2){
				$(this).text("11:30");
			};
			if($(this).val() == 3){
				$(this).text("14:30");
			};
			if($(this).val() == 4){
				$(this).text("17:30");
			};
			if($(this).val() == 5){
				$(this).text("20:30");
			};
		});
	});
});