<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="web.ticket_orders.dao.*"%>
<%@ page import="web.ticket_orders.entity.*"%>
<%@ page import="web.ticket_orders.service.*"%>
<%@ page import="web.member.entity.*"%>
<%@ page import="web.member.dao.*"%>
<%@ page import="web.movie.entity.*"%>
<%@ page import="web.movie.dao.*"%>
<%@ page import="web.movie.service.*"%>

<%
MemVO memVO = (MemVO) request.getAttribute("memVO");
Ticket_OrdersVO ticket_ordersVO = (Ticket_OrdersVO) request.getAttribute("ticket_ordersVO");
// 	MovieService movieSvc = new MovieService();
// 	List<MovieVO> list = movieSvc.getAll();
//	pageContext.setAttribute("list", list);
pageContext.getAttribute("list");
%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
<!-- css檔連結記得修改 -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/view/ticket_orders/css/choose_time.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
</head>

<body>
	<!-- 頁首 -->
	<header>
		<!-- 專題LOGO -->
		<a href="#"> <img class="team_logo"
			src="./images/icons/clapperboard.png" alt="">
		</a>
		<h2>Tibame影城</h2>
		<!-- 背景亮度按鈕 -->
		<div id="lightbtn">
			<!-- 搜尋欄位 -->
			<input id="search" type="text"> <a id="a_loupe" href="#">
				<img id="loupe" src="images/icons/loupe.png" alt="">
			</a>
			<button type="button" class="btn btn-success btn-ln" id="btn-light">
				Light <span> <img id="sun" class="sunmoon"
					src="images/icons/sun.png" alt="">
				</span>
			</button>
			<button type="button" class="btn btn-dark btn-ln -off" id="btn-dark">
				Dark <span> <img id="moon" class="sunmoon -off"
					src="images/icons/crescent-moon.png" alt="">
				</span>
			</button>
		</div>

		<!-- 功能導覽列 -->
		<nav id="navi">


			<div class="nav-item">

				<ul class="nav_ul" id="movie_ul">
					<a href="#">電影資訊</a>
					<li class="nav_li"><a href="#">現正熱映</a></li>
					<li class="nav_li"><a href="#">即將上映</a></li>
					<li class="nav_li"><a href="#">二輪上映</a></li>
					<li class="nav_li"><a href="#">歷史上映</a></li>
				</ul>
			</div>

			<div class="nav-item">

				<ul class="nav_ul" id="ticket_ul">
					<a href="#">訂票系統</a>
					<li class="nav_li"><a href="#">快速購票</a></li>
					<li class="nav_li"><a href="#">預售票</a></li>
					<li class="nav_li"><a href="#">讓票</a></li>
					<li class="nav_li"><a href="#">確認劃位</a></li>
				</ul>
			</div>

			<div class="nav-item">

				<ul class="nav_ul" id="forum_ul">
					<a href="#">討論區</a>

					<!--    有需要再新增 
                        <li class="nav_li">
                        <a href="#">討論區1</a>
                    
                    -->
				</ul>
			</div>

			<div class="nav-item">

				<ul class="nav_ul" id="goods_ul">
					<a href="#">電影商城</a>
					<li class="nav_li"><a href="#">餐飲類別</a></li>
					<li class="nav_li"><a href="#">周邊商品</a></li>
				</ul>
			</div>

			<div class="nav-item">

				<ul class="nav_ul" id="member_ul">
					<a href="#">會員登入</a>
					<li class="nav_li"><a href="#">會員登入</a></li>
					<li class="nav_li"><a href="#">註冊會員</a></li>
					<li class="nav_li"><a href="#">修改資料</a></li>
					<li class="nav_li"><a href="#">歷史消費</a></li>
					<li class="nav_li"><a href="#">會員登出</a></li>
				</ul>
			</div>
		</nav>

		<!-- 漢堡選單按鈕 -->
		<div class="">
			<a href="#"><img src="images/icons/movie.png" alt=""
				class="hamberger_menu"></a>
		</div>

	</header>

	<!-- 空白top 100px -->
	<div class="underheader"></div>

	<!-- 漢堡側邊攔位 -->
	<div class="hide_menu -bye">
		<div class="accordion" id="accordionExample">
			<div class="accordion-item">
				<h2 class="accordion-header" id="headingOne">
					<button class="accordion-button collapsed" type="button"
						data-bs-toggle="collapse" data-bs-target="#collapseOne"
						aria-expanded="true" aria-controls="collapseOne">會員專區</button>
				</h2>
				<div id="collapseOne" class="accordion-collapse collapse"
					aria-labelledby="headingOne" data-bs-parent="#accordionExample">
					<div class="accordion-body">
						<ul>
							<li><a href="">會員登入</a></li>
							<li><a href="">註冊會員</a></li>
							<li><a href="">修改資料</a></li>
							<li><a href="">歷史消費</a></li>
							<li><a href="">會員登出</a></li>
						</ul>

					</div>
				</div>
			</div>
			<div class="accordion-item">
				<h2 class="accordion-header" id="headingTwo">
					<button class="accordion-button collapsed" type="button"
						data-bs-toggle="collapse" data-bs-target="#collapseTwo"
						aria-expanded="false" aria-controls="collapseTwo">電影資訊</button>
				</h2>
				<div id="collapseTwo" class="accordion-collapse collapse"
					aria-labelledby="headingTwo" data-bs-parent="#accordionExample">
					<div class="accordion-body">
						<ul>
							<li><a href="">現正熱映</a></li>
							<li><a href="">即將上映</a></li>
							<li><a href="">二輪上映</a></li>
							<li><a href="">歷史上映</a></li>
						</ul>

					</div>
				</div>
			</div>
			<div class="accordion-item">
				<h2 class="accordion-header" id="headingFour">
					<button class="accordion-button collapsed" type="button"
						data-bs-toggle="collapse" data-bs-target="#collapseFour"
						aria-expanded="false" aria-controls="collapseFour">訂票系統</button>
				</h2>
				<div id="collapseFour" class="accordion-collapse collapse"
					aria-labelledby="headingFour" data-bs-parent="#accordionExample">
					<div class="accordion-body">
						<ul>
							<li><a href="">快速購票</a></li>
							<li><a href="">預售票</a></li>
							<li><a href="">讓票</a></li>
							<li><a href="">確認劃位</a></li>
						</ul>

					</div>
				</div>
			</div>
			<div class="accordion-item">
				<h2 class="accordion-header" id="headingFive">
					<button class="accordion-button collapsed" type="button"
						data-bs-toggle="collapse" data-bs-target="#collapseFive"
						aria-expanded="false" aria-controls="collapseFive">討論區</button>
				</h2>
				<div id="collapseFive" class="accordion-collapse collapse"
					aria-labelledby="headingFive" data-bs-parent="#accordionExample">
					<div class="accordion-body">
						<ul>
							<!-- <li>
                                <a href=""></a>
                            </li>                             -->
						</ul>

					</div>
				</div>
			</div>
			<div class="accordion-item">
				<h2 class="accordion-header" id="headingThree">
					<button class="accordion-button collapsed" type="button"
						data-bs-toggle="collapse" data-bs-target="#collapseThree"
						aria-expanded="false" aria-controls="collapseThree">電影商城
					</button>
				</h2>
				<div id="collapseThree" class="accordion-collapse collapse"
					aria-labelledby="headingThree" data-bs-parent="#accordionExample">
					<div class="accordion-body">
						<ul>
							<li><a href="">餐飲類別</a></li>
							<li><a href="">周邊商品</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>



	<!-- 主要工作區 -->

	<main>
		<div class="mumipay">
			<img src="image/others/mujilogored.png" alt="">
			<form action="">

				<div class="container">
					<div class="row">
						<div class="col-12">
							<h2 class="title">您所選擇的電影是 : 咒術迴戰</h2>
							<h3>請選擇日期與場次</h3>
						</div>
						<div class="col-md-6 col-sm-12">
							<img src="image/moviestand/1825.jpg" alt="">
						</div>
						<div class="col-md-6 col-sm-12">
							<jsp:useBean id="ticket_ordersSvc" scope="page" class="web.ticket_orders.service.Ticket_OrdersService" />
							<form METHOD="post" ACTION="<%=request.getContextPath()%>/view/ticket_orders/Ticket_OrdersServlet">
								<div class="col-12 forspan">
									<c:forEach var="ticket_ordersVO" items="${list}">
										<p class="day">${ticket_ordersVO.showing_date}</p>
										<button class="showtime" value="${ticket_ordersVO.showing}">${ticket_ordersVO.showing}</button>
										<input id="movie_time_id" type="hidden" name="movie_time_id" value="${ticket_ordersVO.movie_time_id}">
									</c:forEach>
									<!-- 							迴圈外面加上票券張數input hidden標籤 -->
									<!-- 							最後再用form包起來 submit 出去 -->
									<!-- 							還要service執行 抓這個會員最新訂單主檔編號 -->
								</div>


								<h4 class="hallname">請選擇場次</h4>

								<div class="col-12 movieall"></div>

								<select name="ticket_number" class="form-select" aria-label="Default select example">
									<option selected>請選擇張數</option>
									<option id="ticket_number" value="1">1</option>
									<option id="ticket_number" value="2">2</option>
									<option id="ticket_number" value="3">3</option>
									<option id="ticket_number" value="4">4</option>
									<option id="ticket_number" value="5">5</option>
								</select>
									<input type="hidden" name="action" value="confirm_time_number">
									<input class="btn btn-primary" type="submit" value="前往劃位">
							</form>
						</div>
					</div>
				</div>

				<div class="container">
					<div class="row">
						<div class="col-2"></div>
						<div class="col-4">

							<button type="button" class="btn btn-primary" id="btn_primary">前往劃位</button>
						</div>
						<div class="col-4">
							<a href="mem_shopping sheet.html">
								<button type="button" class="btn btn-secondary">取消返回</button>
							</a>
						</div>
					</div>
				</div>
			</form>

		</div>

	</main>

	<!-- 主要工作區結束 -->


	<!-- 頁尾 -->
	<footer>
		<div class="container">
			<div class="row row-cols-2">
				<div class="col col-md-3 col-sm-6">
					<h5>服務</h5>
					<ul>
						<li><a href="#">電影導覽</a></li>
						<li><a href="#">快速訂票</a></li>
						<li><a href="#">討論區</a></li>
						<li><a href="#">周邊商城</a></li>
					</ul>
				</div>
				<div class="col col-md-3 col-sm-6">
					<h5>幫助</h5>
					<ul>
						<li><a href="#">導覽</a></li>
						<li><a href="#">FAQ</a></li>
					</ul>
				</div>
				<div class="col col-md-3 col-sm-6" id="ft_icons">
					<h5>關注我們</h5>
					<a href="#"> <span> <img src="images/icons/facebook.png"
							alt="">
					</span>
					</a> <a href="#"> <span> <img
							src="images/icons/instagram.png" alt="">
					</span>
					</a> <a href="#"> <span> <img
							src="images/icons/twitter-sign.png" alt="">
					</span>
					</a> <a href="#"> <span> <img src="images/icons/youtube.png"
							alt="">
					</span>
					</a>
				</div>
				<div class="col col-md-3 col-sm-6">
					<h5>加入好友</h5>
					<ul>
						<li><span> <img id="linerobot"
								src="images/others/robot.png" alt="">
						</span></li>
					</ul>
				</div>

			</div>
		</div>
		<div class="copyright">
			Copyright © 2022 TGA101第二組 Co. 保留所有權利。 <a href="#">隱私政策</a> <a
				href="#">使用條款</a>
		</div>
	</footer>


	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
		crossorigin="anonymous"></script>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<!-- JS檔連結記得修改 -->
	<script
		src="${pageContext.request.contextPath}/view/ticket_orders/js/choose_time.js"></script>
	<script >$("input#movie_time_id").val(ticket_ordersVO.getMovie_time_id());</script>
	<script >$("option#ticket_number").val(ticket_ordersVO.getTicket_number());</script>
</body>

</html>