<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="web.movie.dao.*"%>
<%@ page import="web.movie.entity.*"%>
<%@ page import="web.movie.service.*"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page import="java.sql.Timestamp"%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>

<%
MovieService movieSvc = new MovieService();
List<MovieVO> list = movieSvc.getAll();
pageContext.setAttribute("list", list);
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
	href="<%=request.getContextPath()%>/view/movie/css/system_movie_listAll.css">

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
			<h2>�Ҧ��q�v</h2>

			<div class="card">
				<div class="container">
					<div col="12">
						<div class="d-grid gap-2 d-flex justify-content-end">
							<a class="btn btn-secondary" href='system_movie_add.jsp'>�s�W�q�v</a>
						</div>
							<br>

						<%-- ���~��C --%>
						<c:if test="${not empty errorMsgs}">
							<font style="color: red">�Эץ��H�U���~:</font>
							<ul>
								<c:forEach var="message" items="${errorMsgs}">
									<li style="color: red">${message}</li>
								</c:forEach>
							</ul>
						</c:if>

						<table class="table table-striped table-bordered table-sm">
							<thead class="table-light">
								<tr>
									<th>�q�v�s��</th>
									<th>�q�v����W��</th>
<!-- 									<th>�q�v�^��W��</th> -->
									<th>�q�v�̫�ק�ɶ�</th>
									<th>����</th>
<!-- 									<th>�t��</th> -->
									<th>�ɺt</th>
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
									<td>${movieVO.movie_runtime}����</td>
<%-- 									<td>${movieVO.casts}</td> --%>
									<td>${movieVO.director}</td>

									<td>
										<FORM METHOD="post"
											ACTION="<%=request.getContextPath()%>/view/movie/MovieServlet"
											style="margin-bottom: 0px;">
											<input type="submit" value="�ק�" class="btn btn-success">
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

		<!-- �u�@�ϵ��� -->


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