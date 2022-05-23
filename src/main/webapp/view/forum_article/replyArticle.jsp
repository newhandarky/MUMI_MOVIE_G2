<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="web.forum_article.entity.*"%>
<%@ page import="java.sql.Timestamp"%>

<%
ArticleVO articleVO = (ArticleVO) request.getAttribute("articleVO");

Timestamp article_publish = null;
try {
	article_publish = articleVO.getArticle_publish();
} catch (Exception e) {
	article_publish = new java.sql.Timestamp(System.currentTimeMillis());
}
%>


<html>
<head>
<meta charset="UTF-8">
<title>回覆文章</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<script
	src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.js"></script>
<!-- 上線時要記得改回jquery.slim.min.js -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.js"></script>
<!-- 上線時要記得改回bootstrap.bundle.min.js -->
<!-- include summernote css/js -->
<link
	href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>


</head>
<body>

	<table id="table-1">
		<tr>
			<td>
				<h3>文章回覆 - replyArticle.jsp</h3>
			</td>
			<td>
				<h4>
					<a href="ForumIndex.html">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>


	<form action="<c:url value="/PostArticleServlet" />" method="post" enctype="multipart/form-data">

		<div class="comtainer" style="width: 700px">
			<h2>回覆文章 - ReplyArticle</h2>
			<br>
			<div>
				<p>會員編號: <span id="post_mem_id"></span></p>
				<select name="article_board" id="choose-board" class="form-select">
					<option value="" selected id="article_board_el"></option>
				</select>
				<select name="article_type" id="choose-type" class="form-select">
					<option value="" selected id="article_type_el"></option>
				</select>
			</div>
			<div>
                <label for="p_file">文章封面圖:</label>
                <input name="article_pic" type="file" id="p_file">
            </div>
			<p></p>
			<div>
				<h4>回覆: <span id="article_subject"></span></h4>
				<textarea id="summernote" name="article_contain"></textarea>
			</div>
			<p></p>			
			<input type="hidden" id="re_article_id" name="re_article_id" value="">
			<input type="hidden" id="post_mem_id_el" name="mem_id" value="">
			<input type="hidden" id="article_subject_el" name="article_subject" value="">
			<input type="hidden" name="article_publish"> 
			<input type="hidden" name="article_state" value="會員已發表文章">
			<button type="submit" style="float: right" class="btn btn-success">送出</button>
			<button style="float: right" class="btn btn-secondary">取消</button>


		</div>

	</form>
</body>

<script>
	$(document).ready(function() {
		$('#summernote').summernote({
			placeholder : '請輸入文字',
			tabsize : 2,
			width : 700,
			height : 300, // set editor height
			minHeight : null, // set minimum height of editor
			maxHeight : null, // set maximum height of editor
			focus : true, // set focus to editable area after initializing summernote
			toolbar : [
				['style', ['style']],
				['font', ['bold', 'underline', 'clear']],
				['color', ['color']],
				['para', ['ul', 'ol', 'paragraph']],
				['table', ['table']],
				['insert', ['link', 'picture', 'video']],
				// ['view', ['fullscreen', 'codeview', 'help']]
			]								
		});
	});
	
	window.addEventListener("load", function () {
		
		var re_article_subject = document.querySelector("#article_subject");
		var re_article_subject_el = document.querySelector("#article_subject_el");
		var re_article_board = document.querySelector("#article_board_el");
		var re_article_type = document.querySelector("#article_type_el");
		var re_article_id = document.querySelector("#re_article_id");
		
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
		
    	var post_mem_id = document.querySelector("#post_mem_id");
    	var post_mem_id_el = document.querySelector("#post_mem_id_el");
    	
        var storage_data2 = JSON.parse(sessionStorage.getItem("mem_data"));
        console.log("mem_data: " + storage_data); // 檢查用
        post_mem_id.innerHTML = storage_data2.memId;
        post_mem_id_el.value = storage_data2.memId;
        
	});
	
</script>


</html>