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

var post_mem_id = document.querySelector("#post_mem_id");
var post_mem_id_el = document.querySelector("#post_mem_id_el");

var storage_data = JSON.parse(sessionStorage.getItem("mem_data"));
console.log("mem_data: " + storage_data); // 檢查用
post_mem_id.innerHTML = storage_data.memId;
post_mem_id_el.value = storage_data.memId;