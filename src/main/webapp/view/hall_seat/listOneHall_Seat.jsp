<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="web.hall_seat.dao.*"%>
<%@ page import="web.hall_seat.entity.*"%>
<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<%
pageContext.getAttribute("list");
Hall_SeatVO hall_seatVO = (Hall_SeatVO) request.getAttribute("hall_seatVO");
%>

<html lang="en">

<head>
<meta charset="UTF-8">
<title>�v����x�޲z�t��</title>
<link rel='stylesheet'
	href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css'>
<link rel='stylesheet'
	href='https://unicons.iconscout.com/release/v3.0.6/css/line.css'>
<link rel="stylesheet" href="${pageContext.request.contextPath}/view/hall_seat/css/system_show_one.css">

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
				src="./images/icons/clapperboard.png" alt="">
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
		<main>
			<div class="main">
				<div class="hell">
					<div class="screen">
						<h3>(�ȹ�)�^ �M �} �v �u �� �n �q �v(�ȹ�)</h3>
					</div>

					<div>
						<!-- ���D -->
						<div class="aisle"></div>
						<div class="seat-start">
						<c:forEach var="hall_seatVO" items="${list}" begin="1" end="1" >
							<div style="opacity: 0" class="hall_row" id="${hall_seatVO.seat_row}"> </div>
							<div style="opacity: 0" class="hall_col" id="${hall_seatVO.seat_col}"> </div>
							<div style="opacity: 0" class="hall_left" id="${hall_seatVO.seat_left}"> </div>
							<div style="opacity: 0" class="hall_right" id="${hall_seatVO.seat_right}"> </div>
							<div style="opacity: 0" class="hall_row_aisle1" id="${hall_seatVO.seat_row_aisle1}"> </div>
							<div style="opacity: 0" class="hall_row_aisle2" id="${hall_seatVO.seat_row_aisle2}"> </div>
						</c:forEach>
				 		<form method="post" action="<%=request.getContextPath()%>/view/hall_seat/Hall_SeatServlet" style="margin-bottom: 0px;">
						<c:forEach var="hall_seatVO" items="${list}">
							<div class="seat" name="${hall_seatVO.seat_no}" style="opacity: 1" id="${hall_seatVO.seat_state}">
							<p style="opacity: 1" id="${hall_seatVO.seat_id}">${hall_seatVO.seat_name}</p>
							<input id="seat_state" type="hidden" name="seat_state" value="${hall_seatVO.seat_state}">	
				     		<input id="seat_id" type="hidden" name="seat_id" value="${hall_seatVO.seat_id}" >
							</div>
						</c:forEach>
			     			<a class="btn btn-secondary" href="select_page.jsp" role="button">����</a>
			     			<input type="hidden" name="action" value="update">
			     			<input class="btn btn-primary" type="submit" value="�ק�">
			     			
			     		</form>
			     		<form method="post" action="<%=request.getContextPath()%>/view/hall_seat/Hall_SeatServlet" style="margin-bottom: 0px;">
			     			<c:forEach var="hall_seatVO" items="${list}" begin="1" end="1" >
			     			<input id="hall_id" type="hidden" name="hall_id" value="${hall_seatVO.hall_id}" >
			     			<input type="hidden" name="action" value="delete">
			     			<input class="btn btn-danger" type="submit" value="�R���v�U">
			     			</c:forEach>
			     		</form>
						<!-- ���D -->
						<div class="aisle"></div>
						<div class="aisle"></div>
						</div>
					</div>
				</div>
			</div>
		</main>

		<!-- �u�@�ϵ��� -->

	</section>
	<!-- partial -->
	<script
		src='https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.bundle.js'></script>
	<script
		src='https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js'></script>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/view/hall_seat/js/system_show_one.js"></script>
	<script >$("input#seat_state").val(hall_seatVO.getSeat_state());</script>
    <script >$("input#seat_id").val(hall_seatVO.getSeat_id());</script>
    

</body>

</html>