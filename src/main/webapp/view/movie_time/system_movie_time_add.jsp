<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="web.movie_type.dao.*"%>
<%@ page import="web.movie_type.entity.*"%>
<html>
<head>
<meta charset="UTF-8">
<title>影城後台管理系統</title>
<link rel='stylesheet'
	href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css'>
<link rel='stylesheet'
	href='https://unicons.iconscout.com/release/v3.0.6/css/line.css'>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/view/movie_time/css/system_movie_time_add.css">

</head>

<%@ include file="../index/admin_header.jsp"%>
		<!-- 主要工作區 -->


		<div class="main">
			<h2>新增電影場次</h2>
			<div class="card">
				<div class="container">
					<div col="12">
						<div class="d-grid gap-2 d-flex justify-content-end">
							<a class="btn btn-secondary" href='system_movie_time_listAll.jsp'>所有電影時刻資料</a>
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
							action="<%=request.getContextPath()%>/view/movie_time/Movie_timeServlet"
							name="form1">

							<jsp:useBean id="movie_timeSvc" scope="page"
								class="web.movie_time.service.Movie_timeService" />
							<jsp:useBean id="movieSvc" scope="page"
								class="web.movie.service.MovieService" />


							<div class="mb-3 row">
								<label class="col-sm-2 col-form-label">播放日期:</label>
								<div class="col-sm-3">
									<input class="form-control" id="f_date1" type="date"
										name="showing_date" aria-label="default input example">
								</div>
								<label class="col-sm-2 col-form-label">影廳:</label>
								<div class="col-sm-3">
									<select size="1" name="hall_id" class="form-select">
										<c:forEach var="Movie_timeVO"
											items="${movie_timeSvc.hall_Name}">
											<option value="${Movie_timeVO.hall_id}">
												${Movie_timeVO.hall_name}
										</c:forEach>
									</select>

								</div>
							</div>


							<div class="mb-3 row">
								<label class="col-sm-2 col-form-label">電影名稱:</label>
								<div class="col-sm-3">
									<select size="1" name="movie_id" class="form-select">
										<option value="0">無</option>
										<c:forEach var="Movie_timeVO"
											items="${movie_timeSvc.releasingNowCh}">
											<option value="${Movie_timeVO.movie_id}">
												${Movie_timeVO.movie_ch}
										</c:forEach>
									</select>
								</div>
								<label class="col-sm-2 col-form-label">場次:</label>
								<div class="col-sm-3">
									<!-- 									<select size="1" name="showing" class="form-select" disabled> -->
									<!-- 										<option value="1" selected>06:00</option> -->
									<!-- 										<option value="2">09:30</option> -->
									<!-- 										<option value="3">13:00</option> -->
									<!-- 										<option value="4">16:30</option> -->
									<!-- 										<option value="5">20:00</option> -->
									<!-- 									</select> -->
									<p>06:00</p>
									<input type="hidden" name="showing" value="1">

								</div>

							</div>
					</div>
					<div class="mb-3 row">

						<label class="col-sm-2 col-form-label">電影名稱:</label>
						<div class="col-sm-3">
							<select size="1" name="movie_id" class="form-select">
								<option value="0">無</option>
								<c:forEach var="Movie_timeVO"
									items="${movie_timeSvc.releasingNowCh}">
									<option value="${Movie_timeVO.movie_id}">
										${Movie_timeVO.movie_ch}
								</c:forEach>
							</select>
						</div>
						<label class="col-sm-2 col-form-label">場次:</label>
						<div class="col-sm-3">
							<!-- 							<select size="1" name="showing" class="form-select" disabled> -->
							<!-- 								<option value="1">06:00</option> -->
							<!-- 										<option value="2" selected>09:30</option> -->
							<!-- 										<option value="3">13:00</option> -->
							<!-- 										<option value="4">16:30</option> -->
							<!-- 										<option value="5">20:00</option> -->
							<!-- 							</select> -->
							<p>09:30</p>
							<input type="hidden" name="showing" value="2">
						</div>
					</div>
					<div class="mb-3 row">

						<label class="col-sm-2 col-form-label">電影名稱:</label>
						<div class="col-sm-3">
							<select size="1" name="movie_id" class="form-select">
								<option value="0">無</option>
								<c:forEach var="Movie_timeVO"
									items="${movie_timeSvc.releasingNowCh}">
									<option value="${Movie_timeVO.movie_id}">
										${Movie_timeVO.movie_ch}
								</c:forEach>
							</select>
						</div>

						<label class="col-sm-2 col-form-label">場次:</label>
						<div class="col-sm-3">
							<!-- 							<select size="1" name="showing" class="form-select"  disabled> -->
							<!-- 								<option value="1">06:00</option> -->
							<!-- 										<option value="2">09:30</option> -->
							<!-- 										<option value="3" selected>13:00</option> -->
							<!-- 										<option value="4">16:30</option> -->
							<!-- 										<option value="5">20:00</option> -->
							<!-- 							</select> -->
							<p>13:00</p>
							<input type="hidden" name="showing" value="3">
						</div>
					</div>
					<div class="mb-3 row">

						<label class="col-sm-2 col-form-label">電影名稱:</label>
						<div class="col-sm-3">
							<select size="1" name="movie_id" class="form-select">
								<option value="0">無</option>
								<c:forEach var="Movie_timeVO"
									items="${movie_timeSvc.releasingNowCh}">
									<option value="${Movie_timeVO.movie_id}">
										${Movie_timeVO.movie_ch}
								</c:forEach>
							</select>
						</div>

						<label class="col-sm-2 col-form-label">場次:</label>
						<div class="col-sm-3">
							<!-- 							<select size="1" name="showing" class="form-select"  disabled> -->
							<!-- 								<option value="1">06:00</option> -->
							<!-- 										<option value="2">09:30</option> -->
							<!-- 										<option value="3">13:00</option> -->
							<!-- 										<option value="4" selected>16:30</option> -->
							<!-- 										<option value="5">20:00</option> -->
							<!-- 							</select> -->
							<p>16:30</p>
							<input type="hidden" name="showing" value="4">
						</div>
					</div>
					<div class="mb-3 row">

						<label class="col-sm-2 col-form-label">電影名稱:</label>
						<div class="col-sm-3">
							<select size="1" name="movie_id" class="form-select">
								<option value="0">無</option>
								<c:forEach var="Movie_timeVO"
									items="${movie_timeSvc.releasingNowCh}">
									<option value="${Movie_timeVO.movie_id}">
										${Movie_timeVO.movie_ch}
								</c:forEach>
							</select>
						</div>
						<label class="col-sm-2 col-form-label">場次:</label>
						<div class="col-sm-3">
							<!-- 							<select size="1" name="showing" class="form-select" > -->
							<!-- 							<input type="hidden" name="showing" value="5"> -->
							<!-- 								<option value="1">06:00</option> -->
							<!-- 										<option value="2">09:30</option> -->
							<!-- 										<option value="3">13:00</option> -->
							<!-- 										<option value="4">16:30</option> -->
							<!-- 										<option value="5" selected>20:00</option> -->
							<!-- 							</select> -->
							<p>20:00</p>
							<input type="hidden" name="showing" value="5">

						</div>
					</div>

					<input type="hidden" name="action" value="insert"> <input
						type="hidden" name="action2" value="ifHad"> <input
						type="submit" class="btn btn-primary" value="新增">
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
		src="<%=request.getContextPath()%>/view/movie_time/js/system_movie_time_add.js"></script>

</body>

</html>