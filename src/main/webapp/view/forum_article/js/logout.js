// 返回上一頁
$("#goback").on("click", function() {
    console.log(history.length)
    window.history.back();
});

$("#btn_primary").on("click", function() {
    Swal.fire('您已經登出，期待您下次的光臨');
    setTimeout(function(){
	    $("#logoutform").submit();
    }, 2000);
});