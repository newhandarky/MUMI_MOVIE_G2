<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="web.movie_time.dao.*"%>
<%@ page import="web.movie_time.entity.*"%>
<%@ page import="web.movie_time.service.*"%>
>
<%
Movie_timeVO movie_timeVO = (Movie_timeVO) request.getAttribute("movie_timeVO"); //Movie_timeServlet.java (Concroller) �s�Jreq��movie_timeVO���� (�]�A�������X��movie_timeVO, �]�]�A��J��ƿ��~�ɪ�movie_timeVO����)
%>
<html>
<head>
<meta charset="UTF-8">
<title>�v����x�޲z�t��</title>
<link rel='stylesheet'
	href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css'>
<link rel='stylesheet'
	href='https://unicons.iconscout.com/release/v3.0.6/css/line.css'>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/view/movie_time/css/system_movie_time_update.css">

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

			<!-- �j�Y�ӳ]�w -->
			<img class="rounded-pill img-fluid" width="80"
				src="./IMAGE/icons/clapperboard.png" alt="">
			<div class="ms-2">
				<h5 class="fs-6 mb-0">
					<a class="text-decoration-none" href="#">Tibame�v��</a>
				</h5>
				<p class="mt-1 mb-0">��O�޲z�t��</p>
			</div>
		</div>

		<div class="search position-relative text-center px-4 py-3 mt-2">
			<input type="text" class="form-control w-100 border-0 bg-transparent"
				placeholder="Search here"> <i
				class="fa fa-search position-absolute d-block fs-6"></i>
		</div>


		<!-- ���䰼����\��C -->
		<ul class="categories list-unstyled">
			<li class="has-dropdown"><i class="uil-estate fa-fw"></i><a
				href="#"> �|���޲z</a>
				<ul class="sidebar-dropdown list-unstyled">
					<li><a href="#">�|����Ƭd��</a></li>
					<li><a href="#">�ק�|�����</a></li>
					<li><a href="#">dolor ipsum</a></li>
					<li><a href="#">amet consectetur</a></li>
					<li><a href="#">ipsum dolor sit</a></li>
				</ul></li>
			<!-- <li class="">
                <i class="uil-folder"></i><a href="#"> File manager</a>
            </li> -->
			<li class="has-dropdown"><i class="uil-calendar-alt"></i><a
				href="#"> �q�v�޲z</a>
				<ul class="sidebar-dropdown list-unstyled">
					<li><a href="#">�q�v�W�[</a></li>
					<li><a href="#">�q�v�U�[</a></li>
					<li><a href="#">�۰ʱƵ{�]�w</a></li>
					<li><a href="#">amet consectetur</a></li>
					<li><a href="#">ipsum dolor sit</a></li>
				</ul></li>
			<li class="has-dropdown"><i class="uil-envelope-download fa-fw"></i><a
				href="#"> �ӫ~�޲z</a>
				<ul class="sidebar-dropdown list-unstyled">
					<li><a href="#">�ӫ~�W�[</a></li>
					<li><a href="#">�ӫ~�U�[</a></li>
					<li><a href="#">dolor ipsum</a></li>
					<li><a href="#">amet consectetur</a></li>
					<li><a href="#">ipsum dolor sit</a></li>
				</ul></li>
			<li class="has-dropdown"><i class="uil-shopping-cart-alt"></i><a
				href="#"> �Q�װϺ޲z</a>
				<ul class="sidebar-dropdown list-unstyled">
					<li><a href="#">�d�����|����</a></li>
					<li><a href="#">ipsum dolor</a></li>
					<li><a href="#">dolor ipsum</a></li>
					<li><a href="#">amet consectetur</a></li>
					<li><a href="#">ipsum dolor sit</a></li>
				</ul></li>
			<li class="has-dropdown"><i class="uil-bag"></i><a href="#">
					����޲z</a>
				<ul class="sidebar-dropdown list-unstyled">
					<li><a href="#">�۰ʱƵ{</a></li>
					<li><a href="#">�H�H����</a></li>
					<li><a href="#">dolor ipsum</a></li>
					<li><a href="#">amet consectetur</a></li>
					<li><a href="#">ipsum dolor sit</a></li>
				</ul></li>
			<li class="has-dropdown"><i class="uil-setting"></i><a href="#">
					�y��޲z</a>
				<ul class="sidebar-dropdown list-unstyled">
					<li><a href="#">�v�U�y��]�w</a></li>
					<li><a href="#">�O�d��]�w</a></li>
					<li><a href="#">dolor ipsum</a></li>
					<li><a href="#">amet consectetur</a></li>
					<li><a href="#">ipsum dolor sit</a></li>
				</ul></li>
			<li class="has-dropdown"><i class="uil-chart-pie-alt"></i><a
				href="#"> ���u�޲z</a>
				<ul class="sidebar-dropdown list-unstyled">
					<li><a href="#">���u�d��</a></li>
					<li><a href="#">�s�W���u</a></li>
					<li><a href="#">dolor ipsum</a></li>
					<li><a href="#">amet consectetur</a></li>
					<li><a href="#">ipsum dolor sit</a></li>
				</ul></li>
			<li class="has-dropdown"><i class="uil-panel-add"></i><a
				href="#"> ��L</a>
				<ul class="sidebar-dropdown list-unstyled">
					<li><a href="admin_login.html">�����b��</a></li>
					<li><a href="admin_login.html">�n�X</a></li>
					<li><a href="index.html">�^��e�x����</a></li>
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
					<a class="navbar-brand" href="#">�v����x�޲z�t��</a>
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



		<!-- �D�n�u�@�� -->
		<div class="main">
			<h2>�ק�q�v�ɨ�</h2>

			<div class="card">
				<div class="container">
					<div class="row g-3" col="12">
						<div class="col-md-6">
							<a class="btn btn-secondary" href="system_movie_time_add.jsp">�^�s�W����</a>
						</div>
						<div class="col-md-6">
							<a class="btn btn-secondary" href='system_movie_time_listAll.jsp'>�Ҧ��q�v�ɨ�</a>
							<jsp:useBean id="movie_timeSvc" scope="page"
								class="web.movie_time.service.Movie_timeService" />
						</div>

						<%-- ���~���C --%>
						<c:if test="${not empty errorMsgs}">
							<font style="color: red">�Эץ��H�U���~:</font>
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
								<label class="col-sm-2 col-form-label">�W�M���:</label>
								<div class="col-sm-3">
									<input class="form-control" type="date"
										value="<%=movie_timeVO.getShowing_date()%>" disabled
										aria-label="default input example" id="f_date1"> <input
										type="hidden" name="showing_date"
										value="<%=movie_timeVO.getShowing_date()%>">

								</div>

								<label class="col-sm-2 col-form-label">�v�U:</label>
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
								<label class="col-sm-2 col-form-label">�q�v�W��:</label>
								<div class="col-sm-3">
									<select size="1" name="movie_id" class="form-select">
										<c:forEach var="Movie_timeVO"
											items="${movie_timeSvc.releasingNowCh}">
											<option value="${Movie_timeVO.movie_id}">
												${Movie_timeVO.movie_ch}
										</c:forEach>
									</select>
								</div>

								<label class="col-sm-2 col-form-label">����:</label>
								<div class="col-sm-3">

									<input type="text" class="form-control"
										value="<%=movie_timeVO.getShowing()%>" disabled> <input
										type="hidden" name="showing"
										value="<%=movie_timeVO.getShowing()%>">
								</div>
							</div>

							<input type="hidden" name="action" value="update"> <input
								type="hidden" name="movie_time_id"
								value="<%=movie_timeVO.getMovie_time_id()%>"> <input
								type="submit" class="btn btn-primary" value="�e�X">




						</form>

					</div>
				</div>
			</div>






















		</div>

		<!-- �u�@�ϵ��� -->


	</section>
	<!-- partial -->
	<script
		src='https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.bundle.js'></script>
	<script
		src='https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.jshttps://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js'></script>
	<script
		src="<%=request.getContextPath()%>/view/movie_time/js/system_movie_time_update.js"></script>

</body>

</html>