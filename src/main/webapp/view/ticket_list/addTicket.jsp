<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="web.ticket_list.entity.*"%>

<%
Ticket_listVO ticket_listVO = (Ticket_listVO) request.getAttribute("ticket_listVO");
%>
--<%=ticket_listVO == null%>--${ticket_listVO==null}--
<html>
<head>

<style>

table#table-1 {
	background-color: #CCCCFF;
	border: 2px solid black;
	text-align: center;
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}
h3 {
	color: white;
	display: inline;
}
h4 {
	color: blue;
	display: inline;
}

table {
	width: 550px;
	background-color: #CCCCFF;
	margin-top: 1px;
	margin-bottom: 1px;
}

table, th, td {
	border: 0px solid #CCCCFF;
}

th, td {
	padding: 1px;
}
</style>

    <meta charset="UTF-8">
    <title>影城後台管理系統</title>
    <link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css'>
    <link rel='stylesheet' href='https://unicons.iconscout.com/release/v3.0.6/css/line.css'>
    <link rel="stylesheet" href="css/system_testVer.css">

</head>

<body bgcolor='white'>
    <!-- partial:index.partial.html -->
    <aside class="sidebar position-fixed top-0 left-0 overflow-auto h-100 float-left" id="show-side-navigation1">
        <i class="uil-bars close-aside d-md-none d-lg-none" data-close="show-side-navigation1"></i>
        <div class="sidebar-header d-flex justify-content-center align-items-center px-3 py-4">

            <!-- 大頭照設定 -->
            <img class="rounded-pill img-fluid" width="80" src="./images/clapperboard.png" alt="">
            <div class="ms-2">
                <h5 class="fs-6 mb-0">
                    <a class="text-decoration-none" href="#">Tibame影城</a>
                </h5>
                <p class="mt-1 mb-0">後臺管理系統</p>
            </div>
        </div>

        <div class="search position-relative text-center px-4 py-3 mt-2">
            <input type="text" class="form-control w-100 border-0 bg-transparent" placeholder="Search here">
            <i class="fa fa-search position-absolute d-block fs-6"></i>
        </div>


        <!-- 左邊側邊欄功能列 -->
        <ul class="categories list-unstyled">
            <li class="has-dropdown">
                <i class="uil-estate fa-fw"></i><a href="#"> 會員管理</a>
                <ul class="sidebar-dropdown list-unstyled">
                    <li><a href="#">會員資料查詢</a></li>
                    <li><a href="#">修改會員資料</a></li>
                    <li><a href="#">dolor ipsum</a></li>
                    <li><a href="#">amet consectetur</a></li>
                    <li><a href="#">ipsum dolor sit</a></li>
                </ul>
            </li>
            <!-- <li class="">
                <i class="uil-folder"></i><a href="#"> File manager</a>
            </li> -->
            <li class="has-dropdown">
                <i class="uil-calendar-alt"></i><a href="#"> 電影管理</a>
                <ul class="sidebar-dropdown list-unstyled">
                    <li><a href="#">電影上架</a></li>
                    <li><a href="#">電影下架</a></li>
                    <li><a href="#">自動排程設定</a></li>
                    <li><a href="#">amet consectetur</a></li>
                    <li><a href="#">ipsum dolor sit</a></li>
                </ul>
            </li>
            <li class="has-dropdown">
                <i class="uil-envelope-download fa-fw"></i><a href="#"> 商品管理</a>
                <ul class="sidebar-dropdown list-unstyled">
                    <li><a href="#">商品上架</a></li>
                    <li><a href="#">商品下架</a></li>
                    <li><a href="#">dolor ipsum</a></li>
                    <li><a href="#">amet consectetur</a></li>
                    <li><a href="#">ipsum dolor sit</a></li>
                </ul>
            </li>
            <li class="has-dropdown">
                <i class="uil-shopping-cart-alt"></i><a href="#"> 討論區管理</a>
                <ul class="sidebar-dropdown list-unstyled">
                    <li><a href="#">留言檢舉機制</a></li>
                    <li><a href="#">ipsum dolor</a></li>
                    <li><a href="#">dolor ipsum</a></li>
                    <li><a href="#">amet consectetur</a></li>
                    <li><a href="#">ipsum dolor sit</a></li>
                </ul>
            </li>
            <li class="has-dropdown">
                <i class="uil-bag"></i><a href="#"> 票券管理</a>
                <ul class="sidebar-dropdown list-unstyled">
                    <li><a href="#">自動排程</a></li>
                    <li><a href="#">寄信提醒</a></li>
                    <li><a href="#">dolor ipsum</a></li>
                    <li><a href="#">amet consectetur</a></li>
                    <li><a href="#">ipsum dolor sit</a></li>
                </ul>
            </li>
            <li class="has-dropdown">
                <i class="uil-setting"></i><a href="#"> 座位管理</a>
                <ul class="sidebar-dropdown list-unstyled">
                    <li><a href="#">影廳座位設定</a></li>
                    <li><a href="#">保留位設定</a></li>
                    <li><a href="#">dolor ipsum</a></li>
                    <li><a href="#">amet consectetur</a></li>
                    <li><a href="#">ipsum dolor sit</a></li>
                </ul>
            </li>
            <li class="has-dropdown">
                <i class="uil-chart-pie-alt"></i><a href="#"> 員工管理</a>
                <ul class="sidebar-dropdown list-unstyled">
                    <li><a href="#">員工查詢</a></li>
                    <li><a href="#">新增員工</a></li>
                    <li><a href="#">dolor ipsum</a></li>
                    <li><a href="#">amet consectetur</a></li>
                    <li><a href="#">ipsum dolor sit</a></li>
                </ul>
            </li>
            <li class="has-dropdown">
                <i class="uil-panel-add"></i><a href="#"> 其他</a>
                <ul class="sidebar-dropdown list-unstyled">
                    <li><a href="admin_login.html">切換帳號</a></li>
                    <li><a href="admin_login.html">登出</a></li>
                    <li><a href="index.html">回到前台首頁</a></li>
                    <li><a href="#">amet consectetur</a></li>
                    <li><a href="#">ipsum dolor sit</a></li>
                </ul>
            </li>
            <!-- <li class="">
                <i class="uil-map-marker"></i><a href="#"> Maps</a>
            </li> -->
        </ul>
    </aside>

    <section id="wrapper">
        <nav class="navbar navbar-expand-md">
            <div class="container-fluid mx-2">
                <div class="navbar-header">
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                        data-bs-target="#toggle-navbar" aria-controls="toggle-navbar" aria-expanded="false"
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
                        <li class="nav-item">
                            <a class="nav-link" href="#">
                                <i data-show="show-side-navigation1" class="uil-bars show-side-btn"></i>
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>

	<!-- 主要工作區 -->



	<table id="table-1">
		<tr>
			<td>
				<h3>票券明細新增 - addTicket.jsp</h3>
			</td>
			<td>
				<h4>
					<a href="select_page.jsp"><img src="images/back.jpg"
						width="150" height="100" border="0"><br>回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>請新增以下資料:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/view/ticket_list/TicketServlet" 
					name="form1"enctype="multipart/form-data" >
		<table>
			<tr>
				<td>座位編號:</td>
				<td><input type="TEXT" name="seat_id" ></td>
			</tr>
			<tr>
				<td>電影時刻編號:</td>
				<td><input type="TEXT" name="movie_time_id" size="45" /></td>
			</tr>
			<tr>
				<td>票券價格:</td>
				<td><input type="TEXT" name="ticket_price" size="45"
					value="<%=(ticket_listVO == null) ? "1000" : ticket_listVO.getTicket_price()%>" /></td>
			</tr>
        <!-- 工作區結束 -->

		</table>
		<br>
		 <script src='https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.bundle.js'></script>
   		 <script src='https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js'></script>
    	 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    	 <script src="js/system_testVer.js"></script>
		 <input type="hidden" name="ticket_orders_id" size="45" value="<%=(ticket_listVO == null) ? "001" : ticket_listVO.getMovie_time_id()%>" /> 
		 <input type="hidden" name="action" value="insert"> 
		 <input type="submit" value="送出新增"></FORM>
</section>
		 
</body>		



</html>