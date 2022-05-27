<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="web.movie_type.dao.*"%>
<%@ page import="web.movie_type.entity.*"%>
<%@ page import="web.movie_type.service.*"%>
<%
Movie_typeVO movie_typeVO = (Movie_typeVO) request.getAttribute("movie_typeVO"); //Movie_typeServlet.java (Concroller) 存入req的movie_typeVO物件 (包括幫忙取出的movie_typeVO, 也包括輸入資料錯誤時的movie_typeVO物件)
%>
<html>
<head>
<meta charset="UTF-8">
<title>影城後台管理系統</title>
<link rel='stylesheet'
	href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css'>
<link rel='stylesheet'
	href='https://unicons.iconscout.com/release/v3.0.6/css/line.css'>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/view/movie_type/css/system_movie_type_update.css">

</head>

<%@ include file="../index/admin_header.jsp"%>
<!-- 主要工作區 -->
<div class="main">
	<h2>修改電影分類</h2>

	<div class="card">
		<div class="container">
			<div class="row g-3" col="12">
				<div class="col-md-6">
					<a class="btn btn-secondary" href="system_movie_type_add.jsp">回新增頁面</a>
				</div>
				<div class="col-md-6">
					<a class="btn btn-secondary" href='system_movie_type_listAll.jsp'>所有電影分類</a>
					<jsp:useBean id="movie_typeSvc" scope="page"
						class="web.movie_type.service.Movie_typeService" />
				</div>

				<%-- 錯誤表列 --%>
				<c:if test="${not empty errorMsgs}">
					<font style="color: red">請修正以下錯誤:</font>
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color: red">${message}</li>
						</c:forEach>
					</ul>
				</c:if>


				<form method="post"
					action="<%=request.getContextPath()%>/view/movie_type/Movie_typeServlet"
					name="form1">
					<div class="mb-3 row">
						<label class="col-sm-2 col-form-label">分類編號：</label>
						<div class="col-sm-3">
							<%=movie_typeVO.getMovie_type_id()%>
						</div>
					</div>
					<div class="mb-3 row">
						<label class="col-sm-2 col-form-label">中文分類：</label>
						<div class="col-sm-3">
							<input class="form-control" type="text" name="movie_type_ch"
								value="<%=movie_typeVO.getMovie_type_ch()%>"
								aria-label="default input example">
						</div>
						<label class="col-sm-2 col-form-label">英文分類：</label>
						<div class="col-sm-3">
							<input class="form-control" type="text" name="movie_type_en"
								value="<%=movie_typeVO.getMovie_type_en()%>"
								aria-label="default input example">
						</div>
					</div>

					<br> <input type="hidden" name="action" value="update">
					<input type="hidden" name="movie_type_id"
						value="<%=movie_typeVO.getMovie_type_id()%>"> <input
						type="submit" class="btn btn-primary" value="送出">
				</form>

			</div>
		</div>
	</div>
</div>

<!-- 工作區結束 -->
<footer>
	<div class="copyright">
		Copyright © 2022 MUMI MOVIE 吾映良影 Co. 保留所有權利。 <a href="#">隱私政策</a> <a
			href="#">使用條款</a>
	</div>
</footer>

</section>
<!-- partial -->
<script
	src='https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.bundle.js'></script>
<script
	src='https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.jshttps://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js'></script>
<script
	src="<%=request.getContextPath()%>/view/movie_type/js/system_movie_type_update.js"></script>

</body>

</html>