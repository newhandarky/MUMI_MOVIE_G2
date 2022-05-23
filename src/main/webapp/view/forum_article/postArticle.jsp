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
<title></title>
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
				<h3>文章新增 - postArticle.jsp</h3>
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
			<h2>發表文章 - postArticle</h2>
			<div>
				<p>會員編號: <span id="post_mem_id"></span></p>
			</div>
			<select name="article_board" id="choose-board" class="form-select">
				<option value="" selected>請選擇文章板塊</option>
				<option value="綜合討論" selected>綜合討論</option>
				<option value="電影心得">電影心得</option>
				<option value="商城購物">商城購物</option>
				<option value="影城討論">影城討論</option>
			</select> <select name="article_type" id="choose-type" class="form-select">
				<option value="" selected>文章類型</option>
				<option value="心得" selected>心得</option>
				<option value="問題">問題</option>
				<option value="討論">討論</option>
				<option value="情報">情報</option>
				<option value="分享">分享</option>
			</select>
			<p></p>
			 <div>
                <label for="p_file">文章封面圖:</label>
                <input name="article_pic" type="file" id="p_file">
            </div>
            <p></p>
			<div>
				<input type="text" name="article_subject" size="80" maxlength="20">
				<p></p>
				<textarea id="summernote" name="article_contain"></textarea>
			</div>
			<p></p>			
			<input type="hidden" name="re_article_id" value="0"> 
			<input type="hidden" name="article_publish"> 
			<input type="hidden" id="post_mem_id_el" name="mem_id" value="">	
			<input type="hidden" name="article_state" value="會員已發表文章"> 
			<input type="hidden" name="action" value="insert">
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
	
	var post_mem_id = document.querySelector("#post_mem_id");
	var post_mem_id_el = document.querySelector("#post_mem_id_el");
	
    var storage_data = JSON.parse(sessionStorage.getItem("mem_data"));
    console.log("mem_data: " + storage_data); // 檢查用
    post_mem_id.innerHTML = storage_data.memId;
    post_mem_id_el.value = storage_data.memId;
	
</script>


</html>