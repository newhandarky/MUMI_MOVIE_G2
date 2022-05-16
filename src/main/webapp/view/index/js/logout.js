// 返回上一頁
$("#goback").on("click", function() {
    console.log(history.length)
    window.history.back();
});