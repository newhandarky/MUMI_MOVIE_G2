<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
	href="<%=request.getContextPath()%>/view/movie/css/system_movie_add.css">

</head>

<%@ include file="../index/admin_header.jsp"%>

		<!-- 主要工作區 -->


		<div class="main">
			<h2>新增電影</h2>
			<div class="card">
				<div class="container">
					<div col="12">
						<div class="d-grid gap-2 d-flex justify-content-end">
							<a class="btn btn-secondary" href='system_movie_listAll.jsp'>所有電影</a>
						</div>
						<br>
						<jsp:useBean id="movieSvc" scope="page"
							class="web.movie.service.MovieService" />

						<c:if test="${not empty errorMsgs}">
							<font style="color: red">請修正以下錯誤:</font>
							<ul>
								<c:forEach var="message" items="${errorMsgs}">
									<li style="color: red">${message}</li>
								</c:forEach>
							</ul>
						</c:if>

						<jsp:useBean id="releasingSvc" scope="page"
							class="web.releasing.service.ReleasingService" />
						<jsp:useBean id="movie_ratingSvc" scope="page"
							class="web.movie_rating.service.Movie_ratingService" />
						
						<form method="post"
							action="<%=request.getContextPath()%>/view/movie/MovieServlet"
							name="form1" ENCTYPE="multipart/form-data">
							<div class="mb-3 row">
								<label class="col-sm-2 col-form-label">電影中文名稱:</label>
								<div class="col-sm-3">
									<input class="form-control" type="text" name="movie_ch"
										aria-label="default input example"
										value="<%=(movieVO == null) ? "哈比人" : movieVO.getMovie_ch()%>">
								</div>
								<label class="col-sm-2 col-form-label">電影英文名稱:</label>
								<div class="col-sm-3">
									<input class="form-control" type="text" name="movie_en"
										aria-label="default input example"
										value="<%=(movieVO == null) ? "HAPPY MAN" : movieVO.getMovie_en()%>">
								</div>
							</div>
							<div class="mb-3 row">

								<label class="col-sm-2 col-form-label">電影狀態:</label>
								<div class="col-sm-3">
									<select size="1" name="movie_state_id" class="form-select">
										<c:forEach var="ReleasingVO" items="${releasingSvc.all}">
											<option value="${ReleasingVO.movie_state_id}">
												${ReleasingVO.movie_state}
										</c:forEach>
									</select>
								</div>


								<label class="col-sm-2 col-form-label">電影分級:</label>
								<div class="col-sm-3">
									<select size="1" name="movie_rating_id" class="form-select">
										<c:forEach var="Movie_ratingVO" items="${movie_ratingSvc.all}">
											<option value="${Movie_ratingVO.movie_rating_id}">
												${Movie_ratingVO.movie_rating_ch}
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="mb-3 row">
								<label class="col-sm-2 col-form-label">長度(分鐘)：</label>
								<div class="col-sm-3">
									<input class="form-control" type="text" name="movie_runtime"
										aria-label="default input example"
										value="<%=(movieVO == null) ? "120" : movieVO.getMovie_runtime()%>">
								</div>
								<label class="col-sm-2 col-form-label">上映日期:</label>
								<div class="col-sm-3">
									<input class="form-control" id="f_date1" type="date"
										name="release_date" aria-label="default input example">
								</div>
							</div>
							<div class="mb-3 row">
								<label class="col-sm-2 col-form-label">簡介：</label>
								<div class="col-sm-3">
									<input class="form-control" type="text" name="movie_intro"
										aria-label="default input example"
										value="<%=(movieVO == null) ? "很多哈比人" : movieVO.getMovie_intro()%>">
								</div>
								<label class="col-sm-2 col-form-label">演員：</label>
								<div class="col-sm-3">
									<input class="form-control" type="text" name="casts"
										aria-label="default input example"
										value="<%=(movieVO == null) ? "七個小矮人" : movieVO.getCasts()%>">
								</div>
							</div>

							<div class="mb-3 row">
								<label class="col-sm-2 col-form-label">導演：</label>
								<div class="col-sm-3">
									<input class="form-control" type="text" name="director"
										aria-label="default input example"
										value="<%=(movieVO == null) ? "彼得傑克森" : movieVO.getDirector()%>">
								</div>
								<label class="col-sm-2 col-form-label">預告：</label>
								<div class="col-sm-3">
									<input class="form-control" type="text" name="trailer"
										aria-label="default input example"
										value="<%=(movieVO == null) ? "http://35.221.208.108:8080/MUMI_MOVIE/view/movie/system_movie_listAll.jsp" : movieVO.getTrailer()%>">
								</div>
							</div>
							<div class="mb-3 row">
								<label class="col-sm-2 col-form-label">海報：</label>
								<div class="col-sm-3">
									<input class="form-control" type="file" name="movie_poster"
										aria-label="default input example">
								</div>
								<label class="col-sm-2 col-form-label">輪播海報：</label>
								<div class="col-sm-3">
									<input class="form-control" type="file"
										name="movie_slide_poster" aria-label="default input example">
								</div>
							</div>

							<!-- 								<div class="mb-3 row"> -->
							<!-- 									<label class="col-sm-2 col-form-label">選擇類型：</label> -->
							<%-- 									<jsp:useBean id="movie_typeSvc" scope="page" --%>
							<%-- 										class="web.movie_type.service.Movie_typeService" /> --%>

							<!-- 									<div class="col-sm-9"> -->
							<%-- 										<c:forEach var="Movie_typeVO" items="${movie_typeSvc.all}"> --%>
							<!-- 											<div class="form-check form-check-inline" id="type_num"> -->
							<!-- 												<input class="form-check-input" type="checkbox" -->
							<%-- 													value="${Movie_typeVO.movie_type_id}" id="flexCheckDefault" --%>
							<%-- 													name="movie_type_id"> ${Movie_typeVO.movie_type_ch} --%>
							<!-- 											</div> -->

							<%-- 										</c:forEach> --%>
							<!-- 									</div> -->
							<!-- 								</div> -->




							<div class="mb-3 row"></div>


							<br> <input type="hidden" name="action" value="insert">


							<input type="submit" class="btn btn-primary" value="下一步">
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
		src="<%=request.getContextPath()%>/view/movie/js/system_movie_add.js"></script>

</body>

<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<%
java.sql.Date release_date = null;
try {
	release_date = movieVO.getRelease_date();
} catch (Exception e) {
	release_date = new java.sql.Date(System.currentTimeMillis());
}
%>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script
	src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}
</style>

<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: '<%=release_date%>
	', // value:   new Date(),
	//disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
	//startDate:	            '2017/07/10',  // 起始日
	//minDate:               '-1970-01-01', // 去除今日(不含)之前
	//maxDate:               '+1970-01-01'  // 去除今日(不含)之後
	});

	// ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

	//      1.以下為某一天之前的日期無法選擇
	//      var somedate1 = new Date('2017-06-15');
	//      $('#f_date1').datetimepicker({
	//          beforeShowDay: function(date) {
	//        	  if (  date.getYear() <  somedate1.getYear() || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
	//              ) {
	//                   return [false, ""]
	//              }
	//              return [true, ""];
	//      }});

	//      2.以下為某一天之後的日期無法選擇
	//      var somedate2 = new Date('2017-06-15');
	//      $('#f_date1').datetimepicker({
	//          beforeShowDay: function(date) {
	//        	  if (  date.getYear() >  somedate2.getYear() || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
	//              ) {
	//                   return [false, ""]
	//              }
	//              return [true, ""];
	//      }});

	//      3.以下為兩個日期之外的日期無法選擇 (也可按需要換成其他日期)
	//      var somedate1 = new Date('2017-06-15');
	//      var somedate2 = new Date('2017-06-25');
	//      $('#f_date1').datetimepicker({
	//          beforeShowDay: function(date) {
	//        	  if (  date.getYear() <  somedate1.getYear() || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
	//		             ||
	//		            date.getYear() >  somedate2.getYear() || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
	//              ) {
	//                   return [false, ""]
	//              }
	//              return [true, ""];
	//      }});
</script>

</html>