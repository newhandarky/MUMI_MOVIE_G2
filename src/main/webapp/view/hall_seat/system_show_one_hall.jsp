<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="web.hall_seat.dao.*"%>
<%@ page import="web.hall_seat.entity.*"%>

<%
pageContext.getAttribute("list");
Hall_SeatVO hall_seatVO = (Hall_SeatVO) request.getAttribute("hall_seatVO");
%>

<html lang="en">

<head>
<meta charset="UTF-8">
<title>影城後台管理系統 - 影廳管理系統</title>
<link rel='stylesheet'
	href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css'>
<link rel='stylesheet'
	href='https://unicons.iconscout.com/release/v3.0.6/css/line.css'>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/view/hall_seat/css/system_show_one_hall.css">

</head>

<%@ include file="../index/admin_header.jsp"%>
<!-- 主要工作區 -->
<main>
	<div class="main">
		<div class="hall">
			<div class="screen">
				<h3>(銀幕)吾 映 良 影 只 放 好 電 影(銀幕)</h3>
			</div>

			<div>
				<!-- 走道 -->
				<div class="aisle"></div>
				<div class="seat-start">
					<c:forEach var="hall_seatVO" items="${list}" begin="1" end="1">
						<div style="opacity: 0" class="hall_row" id="${hall_seatVO.seat_row}"></div>
						<div style="opacity: 0" class="hall_col" id="${hall_seatVO.seat_col}"></div>
						<div style="opacity: 0" class="hall_left" id="${hall_seatVO.seat_left}"></div>
						<div style="opacity: 0" class="hall_right" id="${hall_seatVO.seat_right}"></div>
						<div style="opacity: 0" class="hall_row_aisle1" id="${hall_seatVO.seat_row_aisle1}"></div>
						<div style="opacity: 0" class="hall_row_aisle2" id="${hall_seatVO.seat_row_aisle2}"></div>
					</c:forEach>
					<form method="post" action="<%=request.getContextPath()%>/view/hall_seat/Hall_SeatServlet">
						<c:forEach var="hall_seatVO" items="${list}">
							<div class="seat" name="${hall_seatVO.seat_no}" style="opacity: 1" id="${hall_seatVO.seat_state}">
								<p style="opacity: 1" id="${hall_seatVO.seat_id}">${hall_seatVO.seat_name}</p>
								<input id="seat_state" type="hidden" name="seat_state" value="${hall_seatVO.seat_state}">
								<input id="seat_id" type="hidden" name="seat_id" value="${hall_seatVO.seat_id}">
							</div>
						</c:forEach>
						<a class="btn btn-secondary" href="system_hall_seat.jsp" role="button">取消</a>
						<input type="hidden" name="action" value="update">
						<input class="btn btn-primary" type="submit" value="修改">
					</form>
					<form method="post" action="<%=request.getContextPath()%>/view/hall_seat/Hall_SeatServlet">
						<c:forEach var="hall_seatVO" items="${list}" begin="1" end="1">
							<input id="hall_id" type="hidden" name="hall_id" value="${hall_seatVO.hall_id}">
							<input type="hidden" name="action" value="delete">
							<input class="btn btn-danger" type="submit" value="刪除影廳">
						</c:forEach>
					</form>
					<!-- 走道 -->
					<div class="aisle"></div>
					<div class="aisle"></div>
				</div>
			</div>
		</div>
	</div>
</main>

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
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script
	src="${pageContext.request.contextPath}/view/hall_seat/js/system_show_one_hall.js"></script>
<script>
	$("input#seat_state").val(hall_seatVO.getSeat_state());
</script>
<script>
	$("input#seat_id").val(hall_seatVO.getSeat_id());
</script>

</html>