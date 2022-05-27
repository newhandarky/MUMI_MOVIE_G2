<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="web.movie_tag.dao.*"%>
<%@ page import="web.movie_tag.entity.*"%>
<%@ page import="web.movie_tag.service.*"%>

<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
Movie_tagService movie_tagSvc = new Movie_tagService();
List<Movie_tagVO> list = movie_tagSvc.getAllCh();
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
	href="<%=request.getContextPath()%>/view/movie_tag/css/system_movie_tag_listAll.css">

</head>

<%@ include file="../index/admin_header.jsp"%>
<!-- 主要工作區 -->
<div class="main">
	<h2>電影標籤</h2>


	<jsp:useBean id="movieSvc" scope="page"
		class="web.movie.service.MovieService" />

	<jsp:useBean id="movie_typeSvc" scope="page"
		class="web.movie_type.service.Movie_typeService" />

	<jsp:useBean id="movie_tagSvc2" scope="page"
		class="web.movie_tag.service.Movie_tagService" />
	<div class="card">
		<div class="container">
			<div col="12">
				<div class="mb-3 row">
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/view/movie_tag/Movie_tagServlet"
						style="margin-bottom: 0px;">
						<div class="col-sm-3 input-group">
							<select size="1" name="movie_id" class="form-select ">
								<c:forEach var="movie_tagVO" items="${movie_tagSvc2.movieCh}">
									<option value="${movie_tagVO.movie_id}">
										${movie_tagVO.movie_ch}
								</c:forEach>
							</select> <input type="submit" value="搜尋" class="btn btn-success">
							<input type="hidden" name="action" value="getType_By_Movie">
						</div>
					</FORM>
				</div>

				<!-- 						<div class="d-grid gap-2 d-flex justify-content-end"> -->
				<!-- 							<a class="btn btn-secondary" href='system_movie_tag_add.jsp'>新增電影標籤</a> -->
				<!-- 						</div> -->
				<!-- 						<br> <br> -->

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
							<th>標籤編號</th>
							<th>電影名稱</th>
							<th>分類</th>
							<!-- 									<th></th> -->
						</tr>
					</thead>
					<%@ include file="page1.file"%>
					<c:forEach var="movie_tagVO" items="${list}" begin="<%=pageIndex%>"
						end="<%=pageIndex+rowsPerPage-1%>">

						<tr>
							<td>${movie_tagVO.movie_tag_id}</td>
							<td>${movie_tagVO.movie_ch}</td>
							<td>${movie_tagVO.movie_type_ch}</td>
							<!-- 									<td> -->
							<!-- 										<FORM METHOD="post" -->
							<%-- 											ACTION="<%=request.getContextPath()%>/view/movie_tag/Movie_tagServlet" --%>
							<!-- 											style="margin-bottom: 0px;"> -->
							<!-- 											<input type="submit" value="刪除" class="btn btn-danger"> -->
							<!-- 											<input type="hidden" name="movie_tag_id" -->
							<%-- 												value="${movie_tagVO.movie_tag_id}"> <input --%>
							<!-- 												type="hidden" name="action" value="delete"> -->
							<!-- 										</FORM> -->
							<!-- 									</td> -->

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
	src="<%=request.getContextPath()%>/view/movie_tag/js/system_movie_tag_listAll.js"></script>

</body>

</html>