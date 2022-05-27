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

			<h3>文章回覆頁面</h3>
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


			<form action="<c:url value="/PostArticleServlet" />" method="post"
				enctype="multipart/form-data">

				<div class="comtainer" style="width: 700px">
					<div class="form-floating">
						<p>
							會員編號: <span id="post_mem_id"></span>
						</p>
					</div>
					<select name="article_board" id="choose-board" class="form-floating">
						<option value="" selected id="article_board_el"></option>
					</select> <select name="article_type" id="choose-type" class="form-floating">
						<option value="" selected id="article_type_el"></option>
					</select>
					<div class="form-floating">
						<label for="p_file">文章封面圖:</label> <br> <input
							name="article_pic" type="file" id="p_file">
					</div>
					<div class="form-floating">
						<h4>
							回覆: <span id="article_subject"></span>
						</h4>
						<textarea id="summernote" name="article_contain"></textarea>
					</div>
					<input type="hidden" id="re_article_id" name="re_article_id"
						value=""> <input type="hidden" id="post_mem_id_el"
						name="mem_id" value=""> <input type="hidden"
						id="article_subject_el" name="article_subject" value=""> <input
						type="hidden" name="article_publish"> <input type="hidden"
						name="article_state" value="會員已發表文章">
					<button type="submit" style="float: right" class="btn btn-success">送出</button>
					<button style="float: right" class="btn btn-secondary">取消</button>

				</div>
			</form>
		</div>
	</main>
</body>
<script
	src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
<script src="<%=request.getContextPath()%>/view/forum_article/js/replyArticle.js"></script>


</html>