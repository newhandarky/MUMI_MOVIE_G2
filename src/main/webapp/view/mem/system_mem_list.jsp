<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="web.member.entity.*"%>
<%@ page import="web.member.service.*"%>


<%
	MemService memSvc = new MemService();
	List<MemVO> list = memSvc.getAll();
	pageContext.setAttribute("list", list);
%>


<html lang="en">

<head>
<meta charset="UTF-8">
<title>影城後台管理系統 - 會員查詢系統</title>
<link rel='stylesheet'
	href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css'>
<link rel='stylesheet'
	href='https://unicons.iconscout.com/release/v3.0.6/css/line.css'>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/view/mem/css/system_mem_list.css">

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

			<!-- 影城LOGO -->
			<img class="img-logo"
				src="<%=request.getContextPath()%>/view/mem/image/others/mujilogo.png"
				alt="">
			<div class="ms-2">
				<h5 class="fs-6 mb-0 title-h5">MUMI MOVIE</h5>
				<h5 class="fs-6 mb-0 title-h5">吾映良影</h5>
				<h5 class="fs-6 mb-0 title-h5">後臺管理系統</h5>
			</div>
		</div>

		<div class="search position-relative text-center px-4 py-3 mt-2">
			<input type="text" class="form-control w-100 border-0 bg-transparent"
				placeholder="Search here"> <i
				class="fa fa-search position-absolute d-block fs-6"></i>
		</div>


		<!-- 左邊側邊欄功能列 -->
		<ul class="categories list-unstyled">
			<li class="has-dropdown"><a href="#"> 會員管理</a>
				<ul class="sidebar-dropdown list-unstyled">
					<li><a href="system_mem_list.jsp">會員資料列表</a></li>
				</ul></li>
			<li class="has-dropdown"><a href="#"> 電影管理</a>
				<ul class="sidebar-dropdown list-unstyled">
					<li><a href="#">電影上架</a></li>
					<li><a href="#">電影下架</a></li>
					<li><a href="#">自動排程設定</a></li>
				</ul></li>
			<li class="has-dropdown"><a href="#"> 商品管理</a>
				<ul class="sidebar-dropdown list-unstyled">
					<li><a href="#">商品上架</a></li>
					<li><a href="#">商品下架</a></li>
				</ul></li>
			<li class="has-dropdown"><a href="#"> 討論區管理</a>
				<ul class="sidebar-dropdown list-unstyled">
					<li><a href="#">留言檢舉機制</a></li>
				</ul></li>
			<li class="has-dropdown"><a href="#"> 票券管理</a>
				<ul class="sidebar-dropdown list-unstyled">
					<li><a href="#">自動排程</a></li>
					<li><a href="#">寄信提醒</a></li>
				</ul></li>
			<li class="has-dropdown"><a href="#"> 座位管理</a>
				<ul class="sidebar-dropdown list-unstyled">
					<li><a href="#">影廳座位設定</a></li>
					<li><a href="#">保留位設定</a></li>
				</ul></li>
			<li class="has-dropdown"><a href="#"> 員工管理</a>
				<ul class="sidebar-dropdown list-unstyled">
					<li><a href="#">員工查詢</a></li>
					<li><a href="#">新增員工</a></li>
					<li><a href="#">dolor ipsum</a></li>
				</ul></li>
			<li class="has-dropdown"><a href="#"> 公告管理</a>
				<ul class="sidebar-dropdown list-unstyled">
					<li><a href="#">公告</a></li>
					<li><a href="#">登出</a></li>
				</ul></li>
			<li class="has-dropdown"><a href="#"> 其他</a>
				<ul class="sidebar-dropdown list-unstyled">
					<li><a href="admin_login.html">登出</a></li>
					<li><a href="<%=request.getContextPath()%>/view/mem/select_page.jsp">返回select Page</a></li>
					<li><a href="index.html">回到前台首頁</a></li>
				</ul></li>

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

						<li class="nav-item"><a class="nav-link" href="#"> <i
								data-show="show-side-navigation1" class="uil-bars show-side-btn"></i>
						</a></li>
					</ul>
				</div>
			</div>
		</nav>



		<!-- 主要工作區 -->
		<div class="main">

			<div class="showtable table-responsive">
				<h2>會員資料列表</h2>



				<form METHOD="post" ACTION="mem.do">
					<p class="search-p">搜尋會員 :</p>
					<input id="search" type="search" name="mem_id"> 
					<input type="hidden" name="action" value="getOne_For_Display">
					<button type="submit" class="btn btn-primary">查詢</button>
				</form>



				<table class="table">
					<tr>
						<th>會員編號</th>
						<th>會員帳號</th>
						<th>會員姓名</th>
						<th>帳號狀態</th>
					</tr>
					<%@ include file="page1.file" %> 
					<c:forEach var="memVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
						<tr class="memstate">

							<td>
								<form METHOD="post" ACTION="<%=request.getContextPath()%>/view/mem/MemServlet">
									<input  type="hidden" name="mem_id" value="${memVO.mem_id}"> 
									<input type="hidden" name="action" value="getOne_For_Display">
									<a href="<%=request.getContextPath()%>/view/mem/system_mem_data.jsp">
										<button type="submit" class="btn btn-success">${memVO.mem_id} </button>										
									</a>
								</form>
							</td>
							<td>${memVO.mem_account}</td>
							<td>${memVO.mem_name}</td>
							<td class="memstate${memVO.mem_state}"></td>
						</tr>
					</c:forEach>
				</table>
				<%@ include file="page2.file" %>


				<!-- 工作區結束 -->


				<footer>
					<div class="copyright">
						Copyright © 2022 MUMI MOVIE 吾映良影 Co. 保留所有權利。 <a href="#">隱私政策</a>
						<a href="#">使用條款</a>
					</div>

				</footer>
	</section>
	<!-- partial -->
	<script
		src='https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.bundle.js'></script>
	<script
		src='https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js'></script>
	<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <script src="<%=request.getContextPath()%>/view/mem/js/jquery-3.6.0.min.js"></script>
	<script src="<%=request.getContextPath()%>/view/mem/js/system_mem_list.js"></script>

</body>

</html>