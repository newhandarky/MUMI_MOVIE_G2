<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
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

<body>
	<!-- partial:index.partial.html -->
	<aside
		class="sidebar position-fixed top-0 left-0 overflow-auto h-100 float-left"
		id="show-side-navigation1">
		<i class="uil-bars close-aside d-md-none d-lg-none"
			data-close="show-side-navigation1"></i>
		<div
			class="sidebar-header d-flex justify-content-center align-items-center px-3 py-4">

			<!-- 大頭照設定 -->
			<img class="rounded-pill img-fluid" width="80"
				src="./IMAGE/icons/clapperboard.png" alt="">
			<div class="ms-2">
				<h5 class="fs-6 mb-0">
					<a class="text-decoration-none" href="#">Tibame影城</a>
				</h5>
				<p class="mt-1 mb-0">後臺管理系統</p>
			</div>
		</div>

		<div class="search position-relative text-center px-4 py-3 mt-2">
			<input type="text" class="form-control w-100 border-0 bg-transparent"
				placeholder="Search here"> <i
				class="fa fa-search position-absolute d-block fs-6"></i>
		</div>


		<!-- 左邊側邊欄功能列 -->
		<ul class="categories list-unstyled">
			<li class="has-dropdown"><i class="uil-estate fa-fw"></i><a
				href="#"> 會員管理</a>
				<ul class="sidebar-dropdown list-unstyled">
					<li><a href="#">會員資料查詢</a></li>
					<li><a href="#">修改會員資料</a></li>
					<li><a href="#">dolor ipsum</a></li>
					<li><a href="#">amet consectetur</a></li>
					<li><a href="#">ipsum dolor sit</a></li>
				</ul></li>
			<!-- <li class="">
                <i class="uil-folder"></i><a href="#"> File manager</a>
            </li> -->
			<li class="has-dropdown"><i class="uil-calendar-alt"></i><a
				href="#"> 電影管理</a>
				<ul class="sidebar-dropdown list-unstyled">
					<li><a href="#">電影上架</a></li>
					<li><a href="#">電影下架</a></li>
					<li><a href="#">自動排程設定</a></li>
					<li><a href="#">amet consectetur</a></li>
					<li><a href="#">ipsum dolor sit</a></li>
				</ul></li>
			<li class="has-dropdown"><i class="uil-envelope-download fa-fw"></i><a
				href="#"> 商品管理</a>
				<ul class="sidebar-dropdown list-unstyled">
					<li><a href="#">商品上架</a></li>
					<li><a href="#">商品下架</a></li>
					<li><a href="#">dolor ipsum</a></li>
					<li><a href="#">amet consectetur</a></li>
					<li><a href="#">ipsum dolor sit</a></li>
				</ul></li>
			<li class="has-dropdown"><i class="uil-shopping-cart-alt"></i><a
				href="#"> 討論區管理</a>
				<ul class="sidebar-dropdown list-unstyled">
					<li><a href="#">留言檢舉機制</a></li>
					<li><a href="#">ipsum dolor</a></li>
					<li><a href="#">dolor ipsum</a></li>
					<li><a href="#">amet consectetur</a></li>
					<li><a href="#">ipsum dolor sit</a></li>
				</ul></li>
			<li class="has-dropdown"><i class="uil-bag"></i><a href="#">
					票券管理</a>
				<ul class="sidebar-dropdown list-unstyled">
					<li><a href="#">自動排程</a></li>
					<li><a href="#">寄信提醒</a></li>
					<li><a href="#">dolor ipsum</a></li>
					<li><a href="#">amet consectetur</a></li>
					<li><a href="#">ipsum dolor sit</a></li>
				</ul></li>
			<li class="has-dropdown"><i class="uil-setting"></i><a href="#">
					座位管理</a>
				<ul class="sidebar-dropdown list-unstyled">
					<li><a href="#">影廳座位設定</a></li>
					<li><a href="#">保留位設定</a></li>
					<li><a href="#">dolor ipsum</a></li>
					<li><a href="#">amet consectetur</a></li>
					<li><a href="#">ipsum dolor sit</a></li>
				</ul></li>
			<li class="has-dropdown"><i class="uil-chart-pie-alt"></i><a
				href="#"> 員工管理</a>
				<ul class="sidebar-dropdown list-unstyled">
					<li><a href="#">員工查詢</a></li>
					<li><a href="#">新增員工</a></li>
					<li><a href="#">dolor ipsum</a></li>
					<li><a href="#">amet consectetur</a></li>
					<li><a href="#">ipsum dolor sit</a></li>
				</ul></li>
			<li class="has-dropdown"><i class="uil-panel-add"></i><a
				href="#"> 其他</a>
				<ul class="sidebar-dropdown list-unstyled">
					<li><a href="admin_login.html">切換帳號</a></li>
					<li><a href="admin_login.html">登出</a></li>
					<li><a href="index.html">回到前台首頁</a></li>
					<li><a href="#">amet consectetur</a></li>
					<li><a href="#">ipsum dolor sit</a></li>
				</ul></li>
			<!-- <li class="">
                <i class="uil-map-marker"></i><a href="#"> Maps</a>
            </li> -->
		</ul>
	</aside>

	<section id="wrapper">
		<nav class="navbar navbar-expand-md">
			<div class="container-fluid mx-2">
				<div class="navbar-header">
					<button class="navbar-toggler" type="button"
						data-bs-toggle="collapse" data-bs-target="#toggle-navbar"
						aria-controls="toggle-navbar" aria-expanded="false"
						aria-label="Toggle navigation">
						<i class="uil-bars text-white"></i>
					</button>
					<a class="navbar-brand" href="#">影城後台管理系統</a>
				</div>
				<div class="collapse navbar-collapse" id="toggle-navbar">
					<ul class="navbar-nav ms-auto">
						<!-- <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                Settings
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <li>
                                    <a class="dropdown-item" href="#">My account</a>
                                </li>
                                <li><a class="dropdown-item" href="#">My inbox</a>
                                </li>
                                <li><a class="dropdown-item" href="#">Help</a>
                                </li>
                                <li>
                                    <hr class="dropdown-divider">
                                </li>
                                <li><a class="dropdown-item" href="#">Log out</a></li>
                            </ul>
                        </li> -->
						<!-- <li class="nav-item">
                            <a class="nav-link" href="#"><i class="uil-comments-alt"></i><span>23</span></a>
                        </li> -->
						<!-- <li class="nav-item">
                            <a class="nav-link" href="#"><i class="uil-bell"></i><span>98</span></a>
                        </li> -->
						<li class="nav-item"><a class="nav-link" href="#"> <i
								data-show="show-side-navigation1" class="uil-bars show-side-btn"></i>
						</a></li>
					</ul>
				</div>
			</div>
		</nav>



		<!-- 主要工作區 -->


		<div class="main">
			<h2>新增電影場次</h2>
			<div class="card">
				<div class="container">
					<div col="12">
						<div class="col-6">
							<a class="btn btn-secondary" href='system_movie_time_listAll.jsp'>所有電影時刻資料</a>
							<br> <br>

						</div>

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
								<label class="col-sm-2 col-form-label">上映日期:</label>
								<div class="col-sm-3">
									<input class="form-control" id="f_date1" type="date"
										name="showing_date" aria-label="default input example">
								</div>
								<label class="col-sm-2 col-form-label">影廳:</label>
								<div class="col-sm-3">
									<select size="1" name="hall_id" class="form-select">

										<option value="1">A廳</option>
										<option value="2">B廳</option>
										<option value="3">C廳</option>
										<option value="4">D廳</option>
										<option value="5">E廳</option>

									</select>

									<!-- 									<select size="1" name="hall_id" class="form-select"> -->
									<%-- 										<c:forEach var="Movie_timeVO" items="${movie_timeSvc.all}"> --%>
									<%-- 											<option value="${Movie_timeVO.hall_id}"> --%>
									<%-- 												${Movie_timeVO.hall_id} --%>
									<%-- 										</c:forEach> --%>
									<!-- 									</select> -->
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