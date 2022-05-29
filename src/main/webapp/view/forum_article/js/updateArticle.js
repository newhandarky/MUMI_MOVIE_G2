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

//	var update_article_subject = document.querySelector("#article_subject");
//	var update_article_subject_el = document.querySelector("#article_subject_el");
//	var update_article_board = document.querySelector("#article_board_el");
//	var update_article_type = document.querySelector("#article_type_el");
	var update_article_contain = document.querySelector("#summernote");

	// 取出sessionStorage資料後填入
	var storage_data = JSON.parse(sessionStorage.getItem("update_data"));

//	update_article_subject.innerHTML = storage_data.update_article_subject_el;
//	update_article_subject_el.value = storage_data.update_article_subject_el;
//	update_article_board.value = storage_data.update_article_board;
//	update_article_board.innerHTML = storage_data.update_article_board;
//	update_article_type.value = storage_data.update_article_type;
//	update_article_type.innerHTML = storage_data.update_article_type;
	update_article_contain.innerHTML = storage_data.update_article_contain;

	var post_mem_id = document.querySelector("#post_mem_id");

	var storage_data2 = JSON.parse(sessionStorage.getItem("mem_data"));
	console.log("mem_data: " + storage_data); // 檢查用
	post_mem_id.innerHTML = storage_data2.memId;

});
