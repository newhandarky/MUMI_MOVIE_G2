<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="web.member.entity.*"%>

<%
MemVO memVO = (MemVO) request.getAttribute("memVO");
%>
<!DOCTYPE html>

<html lang="en">

<head>
<meta charset="UTF-8">
<title>影城後台管理系統 - 會員查詢系統</title>
<link rel='stylesheet'
	href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css'>
<link rel='stylesheet'
	href='https://unicons.iconscout.com/release/v3.0.6/css/line.css'>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/view/mem/css/system_mem_data.css">

</head>

<body>
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
					<li><a href="<%=request.getContextPath()%>system_mem_list.jsp">會員資料列表</a></li>
				</ul></li>
			<!-- <li class="">
                <i class="uil-folder"></i><a href="#"> File manager</a>
            </li> -->
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
				</ul></li>
			<li class="has-dropdown"><a href="#"> 公告管理</a>
				<ul class="sidebar-dropdown list-unstyled">
					<li><a href="#">公告</a></li>
					<li><a href="#">登出</a></li>
					<li><a href="#">回到前台首頁</a></li>
				</ul></li>
			<li class="has-dropdown"><a href="#"> 其他</a>
				<ul class="sidebar-dropdown list-unstyled">
					<li><a href="admin_login.html">登出</a></li>
					<li><a href="<%=request.getContextPath()%>/view/mem/select_page.jsp">返回select Page</a></li>
					<li><a href="index.html">回到前台首頁</a></li>
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
						<li class="nav-item"><a class="nav-link" href="#"> <i
								data-show="show-side-navigation1" class="uil-bars show-side-btn"></i>
						</a></li>
					</ul>
				</div>
			</div>
		</nav>



		<!-- 主要工作區 -->
		<div class="main">
		
		<%-- 錯誤表列 --%>
		<c:if test="${not empty errorMsgs}">
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color:red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>

			<div class="mem-data">
				<h2>會員資料</h2>
				
				<table>
					<tr>
						<td>會員編號</td>
						<td>${memVO.mem_id}</td>
					</tr>

					<tr>
						<td>會員帳號</td>
						<td>${memVO.mem_account}</td>
					</tr>

					<tr>
						<td>會員姓名</td>
						<td>${memVO.mem_name}</td>
					</tr>

					<tr>
						<td>連絡電話</td>
						<td>${memVO.mem_phone}</td>
					</tr>

					<tr>
						<td>生日</td>
						<td>${memVO.mem_birthday}</td>
					</tr>

					<tr>
						<td>地址</td>
						<td>${memVO.mem_address}</td>
					</tr>

					<tr>
						<td>性別</td>
						<td id="mem_gender" class="${memVO.mem_gender}"></td>
					</tr>
					<tr>
						<td>帳號密碼</td>
						<td>${memVO.mem_password}</td>
					</tr>

					<tr>
						<td>暱稱</td>
						<td>${memVO.mem_nickname}</td>
					</tr>

					<tr>
						<td>大頭照</td>
						<td>
							<img class="userpic" src="DBGifReader4?mem_id=${memVO.mem_id}">
<!-- 							<form METHOD="get" ACTION=""> -->

<%-- 								<a href="<%=request.getContextPath()%>/mem/show_pic.jsp"> --%>
<%-- 									<button type="submit" class="btn btn-warning" name="mem_id" value="${memVO.mem_id}">另開分頁</button> --%>
<!-- 								</a> -->
<!-- 								<button type="button" class="btn btn-success">查看照片</button> -->
							
<!-- 							</form> -->
						</td>
					</tr>

					<tr>
						<td>註冊日期</td>
						<td>${memVO.mem_register}</td>
					</tr>

					<tr>
						<td>最後修改時間</td>
						<td>${memVO.mem_update}</td>
					</tr>

					<tr>
						<td>電子錢包餘額</td>
						<td>${memVO.mem_point}</td>
					</tr>


					<tr>
						<td>帳號狀態</td>
						<td id="-on" class="memstate${memVO.mem_state}" ></td>
					</tr>

				</table>
				
				<a class= href="<%=request.getContextPath()%>/mem/system_mem_list.jsp">
					<button type="button" class="btn btn-secondary">返回會員列表</button>
				</a>
				
				<form METHOD="post" ACTION="<%=request.getContextPath()%>/view/mem/MemServlet" class="changestate">
					<input type="hidden" name="mem_id" value="${memVO.mem_id}"> 
					<input type="hidden" name="mem_state" value="${memVO.mem_state}">
					<input type="hidden" name="action" value="updateState">
					<button type="submit" class="btn btn-primary" value="送出修改">修改此會員狀態</button>							
				</form>
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
		src='https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js'></script>
	<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
	<script src="<%=request.getContextPath()%>/view/mem/js/jquery-3.6.0.min.js"></script>
	<script src="<%=request.getContextPath()%>/view/mem/js/system_mem_data.js"></script>
</body>

</html>