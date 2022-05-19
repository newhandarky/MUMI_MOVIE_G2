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

    // 停止導覽列的標頭預設行為
    // $(".nav_ul").on("click", function(e) {
    //     e.preventDefault();
    // });

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

    // 點選日期顯示場次
    $(".forspan>span").bind("click", function() {
        let index = ($(this).index());
        $(".movieday").removeClass("-on");
        $(".forspan>span").removeClass("-on");
        $(".movieday").eq(index).addClass("-on");
        $(this).addClass("-on");
    })
	
	// 點選場次變色
    $(".showtime").on("click", function() {
        $(".showtime").removeClass("-on");
        $(this).addClass("-on");
    })
    
    // 選票完畢送出前判斷
    $("#btn_primary").on("click", function() {

        const swalWithBootstrapButtons = Swal.mixin({
            customClass: {
                confirmButton: 'btn btn-success',
                cancelButton: 'btn btn-danger'
            },
            buttonsStyling: false
        })

        swalWithBootstrapButtons.fire({
            title: '確認票券內容都無誤嗎?',
            icon: 'warning',
            showCancelButton: true,
            confirmButtonText: '前往選位頁面!',
            cancelButtonText: '返回重新選擇!',
            reverseButtons: true
        }).then((result) => {
            if (result.isConfirmed) {
                setTimeout(function() {

                }, 2000);
                swalWithBootstrapButtons.fire(
                    '請稍後',
                    '系統將為您導向選位頁面!',
                    'success'
                )
            } else if (
                result.dismiss === Swal.DismissReason.cancel
            ) {
                swalWithBootstrapButtons.fire(
                    '取消此操作',
                    '返回選取日期場次頁面',
                    'info'
                )
                $(".showtime").removeClass("-on");
                $(".movieday").removeClass("-on");
     		   	$(".forspan>span").removeClass("-on"); 
     		   	$(".day1").addClass("-on");
     		   	$(".movieday").eq(0).addClass("-on");
     		   	$("select[name='the_select']").val("0");
            }
        })
    })


});