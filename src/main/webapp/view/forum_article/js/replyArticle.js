$(document).ready(function() {
	$('#summernote').summernote({
		placeholder: '請輸入文字',
		tabsize: 2,
		width: 700,
		height: 300, // set editor height
		minHeight: null, // set minimum height of editor
		maxHeight: null, // set maximum height of editor
		focus: true, // set focus to editable area after initializing summernote
		toolbar: 
			[
				['style', ['style']],
				['font', ['bold', 'underline', 'clear']],
				['color', ['color']],
				['para', ['ul', 'ol', 'paragraph']],
				['table', ['table']], 
				['insert',['link', 'picture', 'video']],
//				['view', ['fullscreen', 'codeview', 'help']]
			]
	});
});

window.addEventListener("load", function() {

	var re_article_subject = document.querySelector("#article_subject");
	var re_article_subject_el = document.querySelector("#article_subject_el");
	var re_article_board = document.querySelector("#article_board_el");
	var re_article_type = document.querySelector("#article_type_el");
	var re_article_id = document.querySelector("#re_article_id");
	var re_article_contain = document.querySelector("#summernote");

	// 取出sessionStorage資料後填入
	var storage_data = JSON.parse(sessionStorage.getItem("reply_data"));

	console.log("reply_data: " + storage_data.re_article_subject); // 檢查用
	re_article_subject.innerHTML = storage_data.re_article_subject;
	re_article_subject_el.value = storage_data.re_article_subject;
	re_article_board.value = storage_data.re_article_board;
	re_article_board.innerHTML = storage_data.re_article_board;
	re_article_type.value = storage_data.re_article_type;
	re_article_type.innerHTML = storage_data.re_article_type;
	re_article_id.value = storage_data.re_article_id;
	re_article_contain.innerHTML = storage_data.re_article_contain;

	var post_mem_id = document.querySelector("#post_mem_id");
	var post_mem_id_el = document.querySelector("#post_mem_id_el");

	var storage_data2 = JSON.parse(sessionStorage.getItem("mem_data"));
	console.log("mem_data: " + storage_data); // 檢查用
	post_mem_id.innerHTML = storage_data2.memId;
	post_mem_id_el.value = storage_data2.memId;

});