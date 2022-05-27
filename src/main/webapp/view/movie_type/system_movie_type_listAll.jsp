<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="web.movie_type.dao.*"%>
<%@ page import="web.movie_type.entity.*"%>
<%@ page import="web.movie_type.service.*"%>

<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
Movie_typeService movie_typeSvc = new Movie_typeService();
List<Movie_typeVO> list = movie_typeSvc.getAll();
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
	href="<%=request.getContextPath()%>/view/movie_type/css/system_movie_type_listAll.css">

</head>

<%@ include file="../index/admin_header.jsp"%>
<!-- 主要工作區 -->
<div class="main">
	<h2>電影分類</h2>

	<div class="card">
		<div class="container">
			<div col="12">
				<div class="d-grid gap-2 d-flex justify-content-end">
					<a class="btn btn-secondary" href='system_movie_type_add.jsp'>新增電影分類</a>
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
							<th>編號</th>
							<th>英文分類</th>
							<th>中文分類</th>
							<th></th>
						</tr>
					</thead>
					<%@ include file="page1.file"%>
					<c:forEach var="movie_typeVO" items="${list}"
						begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">

						<tr>
							<td>${movie_typeVO.movie_type_id}</td>
							<td>${movie_typeVO.movie_type_en}</td>
							<td>${movie_typeVO.movie_type_ch}</td>

							<td class="button">
								<FORM METHOD="post"
									ACTION="<%=request.getContextPath()%>/view/movie_type/Movie_typeServlet"
									style="margin-bottom: 0px;">
									<input type="submit" value="修改" class="btn btn-success">
									<input type="hidden" name="movie_type_id"
										value="${movie_typeVO.movie_type_id}"> <input
										type="hidden" name="action" value="getOne_For_Update">
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
	src="<%=request.getContextPath()%>/view/movie_type/js/system_movie_type_listAll.js"></script>

</body>

</html>