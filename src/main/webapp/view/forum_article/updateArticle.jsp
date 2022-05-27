<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="web.forum_article.entity.*"%>
<%@ page import="java.sql.Timestamp"%>

<%
	ArticleVO articleVO = (ArticleVO) request.getAttribute("articleVO");
	pageContext.setAttribute("articleVO", articleVO);
	System.out.println("updateArticle.jsp的articleVO=" + articleVO);
	Timestamp article_updated = null;
	try {
		article_updated = articleVO.getArticle_updated();
	} catch (Exception e) {
		article_updated = new java.sql.Timestamp(System.currentTimeMillis());
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
	
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/view/forum_article/css/postArticle.css">

</head>
<body>
	<main>
		<div class="postarticle">
			<div class="logo">
				<img
					src="<%=request.getContextPath()%>/view/mem/image/others/mujilogo2.png"
					alt="">
			</div>

			<h3>文章修改頁面</h3>
			<a href="ForumIndex.html"><button type="button"
					class="btn btn-primary">回首頁</button></a>
	
		<%-- 錯誤表列 --%>
		<c:if test="${not empty errorMsgs}">
			<font style="color: red">請修正以下錯誤:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color: red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>
	
			<form action="UpdateArticleServlet" method="post" enctype="multipart/form-data">
				<div class="comtainer" style="width: 700px">
					<div>
						<p>
							會員編號: <span id="post_mem_id"><%=articleVO.getMem_id()%></span>
						</p>
					</div>
					<select size="1" name="article_board" id="choose-board" class="form-floating">
						<option value="${articleVO.article_board}" ${(articleVO.article_id == articleVO.article_id)?'selected':'' }>${articleVO.article_board}</option>
						<option value="綜合討論">綜合討論</option>
						<option value="電影心得">電影心得</option>
						<option value="影城討論">影城討論</option>
						</select>
					<select name="article_type" id="choose-type" class="form-floating">
						<option value="${articleVO.article_type}" ${(articleVO.article_type == articleVO.article_type)?'selected':'' }>${articleVO.article_type}</option>
						<option value="心得">心得</option>
						<option value="問題">問題</option>
						<option value="討論">討論</option>
						<option value="情報">情報</option>
						<option value="分享">分享</option>
					</select>

					<div class="form-floating">
						<label for="p_file">文章封面圖:</label><br>
						<input name="article_pic" type="file" id="p_file">
					</div>
					<div class="form-floating">
						<input type="text" name="article_subject" size="80" maxlength="20" value="<%=articleVO.getArticle_subject()%>">
						<p></p>
						<textarea id="summernote" name="article_contain" value="<%=articleVO.getArticle_contain()%>"></textarea>
					</div>

					<input type="hidden" name="action" value="update">
					<input type="hidden" name="article_id" value="<%=articleVO.getArticle_id()%>">			
					<input type="hidden" name="mem_id" value="<%=articleVO.getMem_id()%>">				
					<input type="hidden" name="article_updated">
					<input type="hidden" name="article_state" value="會員已修改文章">
					<button type="submit" style="float: right" class="btn btn-success">送出</button>
		<!-- 		<button style="float: right" class="btn btn-secondary">取消</button> -->
				</div>
			</form>
		</div>
	</main>
</body>

<script
	src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
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
</script>


</html>