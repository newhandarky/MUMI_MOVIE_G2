<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="web.movie_time.dao.*"%>
<%@ page import="web.movie_time.entity.*"%>
<%@ page import="web.movie_time.service.*"%>
>
<%
Movie_timeVO movie_timeVO = (Movie_timeVO) request.getAttribute("movie_timeVO"); //Movie_timeServlet.java (Concroller) 存入req的movie_timeVO物件 (包括幫忙取出的movie_timeVO, 也包括輸入資料錯誤時的movie_timeVO物件)
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
	href="<%=request.getContextPath()%>/view/movie_time/css/system_movie_time_update.css">

</head>

<%@ include file="../index/admin_header.jsp"%>

<!-- 主要工作區 -->
<div class="main">
	<h2>修改電影時刻</h2>

	<div class="card">
		<div class="container">
			<div class="row g-3" col="12">
				<div class="col-md-6">
					<a class="btn btn-secondary" href="system_movie_time_add.jsp">回新增頁面</a>
				</div>
				<div class="col-md-6">
					<a class="btn btn-secondary" href='system_movie_time_listAll.jsp'>所有電影時刻</a>
					<jsp:useBean id="movie_timeSvc" scope="page"
						class="web.movie_time.service.Movie_timeService" />
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
					action="<%=request.getContextPath()%>/view/movie_time/Movie_timeServlet"
					name="form1">

					<div class="mb-3 row">
						<label class="col-sm-2 col-form-label">上映日期:</label>
						<div class="col-sm-3">
							<input class="form-control" type="date"
								value="<%=movie_timeVO.getShowing_date()%>" disabled
								aria-label="default input example" id="f_date1"> <input
								type="hidden" name="showing_date"
								value="<%=movie_timeVO.getShowing_date()%>">

						</div>

						<label class="col-sm-2 col-form-label">影廳:</label>
						<div class="col-sm-3">
							<input class="form-control" type="text"
								value="<%=movie_timeVO.getHall_name()%>" disabled> <input
								type="hidden" name="hall_id"
								value="<%=movie_timeVO.getHall_id()%>">
						</div>
					</div>
					<jsp:useBean id="movieSvc" scope="page"
						class="web.movie.service.MovieService" />


					<div class="mb-3 row">
						<label class="col-sm-2 col-form-label">電影名稱:</label>
						<div class="col-sm-3">
							<select size="1" name="movie_id" class="form-select">
								<c:forEach var="Movie_timeVO"
									items="${movie_timeSvc.releasingNowCh}">
									<option value="${Movie_timeVO.movie_id}">
										${Movie_timeVO.movie_ch}
								</c:forEach>
							</select>
						</div>

						<label class="col-sm-2 col-form-label">場次:</label>
						<div class="col-sm-3">

							<input type="text" class="form-control time<%=movie_timeVO.getShowing()%>"
								value="<%=movie_timeVO.getShowing()%>" disabled> <input
								type="hidden" name="showing"
								value="<%=movie_timeVO.getShowing()%>">
						</div>
					</div>

					<input type="hidden" name="action" value="update"> <input
						type="hidden" name="movie_time_id"
						value="<%=movie_timeVO.getMovie_time_id()%>"> <input
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
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script
	src="<%=request.getContextPath()%>/view/movie_time/js/system_movie_time_update.js"></script>

</body>

</html>