<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="web.movie.dao.*"%>
<%@ page import="web.movie.entity.*"%>
<%@ page import="web.movie.service.*"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page import="java.sql.Timestamp"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
MovieService movieSvc = new MovieService();
List<MovieVO> list = movieSvc.getAll();
pageContext.setAttribute("list", list);
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
	href="<%=request.getContextPath()%>/view/movie/css/system_movie_listAll.css">

</head>

<%@ include file="../index/admin_header.jsp"%>

<!-- 主要工作區 -->
<div class="main">
	<h2>所有電影</h2>

	<div class="card">
		<div class="container">
			<div col="12">
				<div class="d-grid gap-2 d-flex justify-content-end">
					<a class="btn btn-secondary" href='system_movie_add.jsp'>新增電影</a>
				</div>
				<br>

				<%-- 錯誤表列 --%>
				<c:if test="${not empty errorMsgs}">
					<font style="color: red">請修正以下錯誤:</font>
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color: red">${message}</li>
						</c:forEach>
					</ul>
				</c:if>

				<table class="table table-striped table-bordered table-sm">
					<thead class="table-light">
						<tr>
							<th>電影編號</th>
							<th>電影中文名稱</th>
							<!-- 									<th>電影英文名稱</th> -->
							<th>電影最後修改時間</th>
							<th>片長</th>
							<!-- 									<th>演員</th> -->
							<th>導演</th>
						</tr>
					</thead>
					<%@ include file="page1.file"%>
					<c:forEach var="movieVO" items="${list}" begin="<%=pageIndex%>"
						end="<%=pageIndex+rowsPerPage-1%>">

						<tr>
							<td>${movieVO.movie_id}</td>
							<td>${movieVO.movie_ch}</td>
							<%-- 									<td>${movieVO.movie_en}</td> --%>
							<td><fmt:formatDate value="${movieVO.movie_updated_time}"
									pattern="yyyy-MM-dd HH:mm:ss" /></td>
							<td>${movieVO.movie_runtime}分鐘</td>
							<%-- 									<td>${movieVO.casts}</td> --%>
							<td>${movieVO.director}</td>

							<td>
								<FORM METHOD="post"
									ACTION="<%=request.getContextPath()%>/view/movie/MovieServlet"
									style="margin-bottom: 0px;">
									<input type="submit" value="修改" class="btn btn-success">
									<input type="hidden" name="movie_id"
										value="${movieVO.movie_id}"> <input type="hidden"
										name="action" value="getOne_For_Update">
								</FORM>
							</td>

						</tr>
					</c:forEach>
				</table>

				<%@ include file="page2.file"%>
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
	src="<%=request.getContextPath()%>/view/movie/js/system_movie_listAll.js"></script>

</body>

</html>