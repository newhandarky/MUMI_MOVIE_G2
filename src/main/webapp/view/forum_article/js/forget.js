$("#btnforget").on("click", function() {
    setTimeout(function(){
	    Swal.fire({
	        icon: 'success',
	        title: '系統已發送信件至您的信箱',
	        footer: '<a href="/MUMI_MOVIE/view/index/index.jsp">返回首頁</a>'
	    });
	    $("#forget").submit();
    }, 3000);
});
	
	
//$("#checkaccount").submit();