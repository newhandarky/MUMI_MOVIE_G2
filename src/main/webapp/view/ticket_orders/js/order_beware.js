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
    
    
	
	
	// 進入畫面設定
	$(document).ready(function() {
		$('.seat').each(function() {
			//取消座位參數設定
			if($(this).attr('id') == 0){
				$(this).attr('class', 'seat close');
				$(this).css('opacity', '0');
				$(this).css('cursor', 'not-allowed');
				$(this).css('pointer-events', 'none');
				$(this).find("input#seat_select_state").attr("value", "3");
			};
			//身障座位參數設定
			if($(this).attr('id') == 2){
				$(this).attr('class', 'seat disabled');
				$(this).find("p").attr('style', 'display:none');
				$(this).append("<img src='./images/icons/disabled.png' alt='' class='disabled'>");
				$(this).css('cursor', 'not-allowed');
				$(this).css('pointer-events', 'none');
				$(this).find("input#seat_select_state").attr("value", "3");
			};
			//已選座位禁止點擊
			if($(this).find('input#seat_select_state').attr('value') == 2){
				$(this).css('cursor', 'not-allowed');
				$(this).css('pointer-events', 'none');
				$(this).attr('class', 'seat used');
			};
			//初次選票預設可選位置為0
			if($(this).find("input#seat_select_state").attr('value') <= 0){
				$(this).find("input#seat_select_state").attr("value", "0");
			};		
		});
	});
	
	//進入畫面 走道設置
	$(document).ready(function() {
		let row = parseInt($(".hall_row").attr("id"))
		let left = parseInt($(".hall_left").attr("id"))
		let right = parseInt($(".hall_right").attr("id"))
		let row_aisle1 = parseInt($(".hall_row_aisle1").attr("id"))
		let row_aisle2 = parseInt($(".hall_row_aisle2").attr("id"))
		$('.seat').each(function() {
			if (($(this).attr('name')) % row == 0) {
				$(this).after("</br>");
			};
			if (($(this).attr('name')) % row == left) {
				$(this).css("margin-right", " 50px");
			};
			if (($(this).attr('name')) % row == (row-right)) {
				$(this).css("margin-right", " 50px");
			};
			if (($(this).attr('name')) == row * row_aisle1) {
				$(this).after("<div class='aisle'>");
			};
			if (($(this).attr('name')) == row * row_aisle2) {
				$(this).after("<div class='aisle'>");
			};
		});
	});
		
		//使用者選座位
	$(document).on("click", ".seat", function() {
			if ($(this).find('input#seat_select_state').attr('value') == 0) {
				$(this).find('input#seat_select_state').attr('value', '1');
				$(this).attr('class', 'seat selected');
			} else if ($(this).find('input#seat_select_state').attr('value') == 1) {
				$(this).find('input#seat_select_state').attr('value', '0');
				$(this).attr('class', 'seat');
			} 
		})
	});