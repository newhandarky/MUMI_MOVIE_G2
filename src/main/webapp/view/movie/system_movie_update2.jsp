<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="web.movie.dao.*"%>
<%@ page import="web.movie.entity.*"%>
<%
MovieVO movieVO = (MovieVO) request.getAttribute("movieVO");
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
	href="<%=request.getContextPath()%>/view/movie/css/system_movie_update2.css">

</head>

<%@ include file="../index/admin_header.jsp"%>

<!-- 主要工作區 -->


<div class="main">
	<h2>修改電影類別</h2>
	<div class="card">
		<div class="container">
			<div col="12">
				<div class="d-grid gap-2 d-flex justify-content-end">
<!-- 					<a class="btn btn-secondary" href='system_movie_listAll.jsp'>所有電影</a> -->
					<jsp:useBean id="movieSvc" scope="page"
						class="web.movie.service.MovieService" />
				</div>
				<br>

				<c:if test="${not empty errorMsgs}">
					<font style="color: red">請修正以下錯誤:</font>
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color: red">${message}</li>
						</c:forEach>
					</ul>
				</c:if>

				<form method="post"
					action="<%=request.getContextPath()%>/view/movie/MovieServlet"
					name="form1">

					<div class="mb-3 row">
						<label class="col-sm-2 col-form-label">選擇類型：</label>
						<jsp:useBean id="movie_typeSvc" scope="page"
							class="web.movie_type.service.Movie_typeService" />

						<div class="col-sm-9">
							<c:forEach var="Movie_typeVO" items="${movie_typeSvc.all}">
								<div class="form-check form-check-inline" id="type_num">
									<input class="form-check-input" type="checkbox"
										value="${Movie_typeVO.movie_type_id}" id="flexCheckDefault"
										name="movie_type_id"> ${Movie_typeVO.movie_type_ch}
								</div>
							</c:forEach>
						</div>
					</div>
<%-- 					<c:forEach var="movieVO" items="${movieSvc.all}"> --%>
						<input type="hidden" name="movie_id" value="${movieVO.movie_id}">
<%-- 					</c:forEach> --%>
					<br> <input type="hidden" name="action" value="type_insert">
					<input type="submit" class="btn btn-primary" value="修改">
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
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script
	src="<%=request.getContextPath()%>/view/movie/js/system_movie_update2.js"></script>

</body>

</html>