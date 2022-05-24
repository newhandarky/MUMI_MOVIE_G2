<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="web.hall_seat.dao.*"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<title>影城後台管理系統 - 影廳管理系統</title>
<link rel='stylesheet'
	href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css'>
<link rel='stylesheet'
	href='https://unicons.iconscout.com/release/v3.0.6/css/line.css'>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/view/hall_seat/css/system_add_seat.css">

</head>

<%@ include file="../index/admin_header.jsp"%>
<!-- 主要工作區 -->
<main>
	<div class="main">
		<div class="hall">
			<div class="seat-state">
				<div class="seat-info"></div>
				<p>可選</p>
				<div class="seat-info selected"></div>
				<p>您的座位</p>
				<div class="seat-info used"></div>
				<p>已使用</p>
				<div class="seat-info hold"></div>
				<p>保留位</p>
			</div>

			<div class="screen">
				<h3>(銀幕)吾 映 良 影 只 放 好 電 影(銀幕)</h3>
			</div>

			<div>
				<jsp:useBean id="hall_seatSvc" scope="page" class="web.hall_seat.service.Hall_SeatService" />
				<form method="post" action="<%=request.getContextPath()%>/view/hall_seat/Hall_SeatServlet" name="form1">
					<input type="text" class="seat_row" placeholder="每排幾個位置" name="seat_row" value="${hall_seatVO.getSeat_row()}">
					<input type="text" class="seat_col" placeholder="總共幾排" name="seat_col" value="${hall_seatVO.getSeat_col()}">
					<button type="button" class="seat_rc_btn btn btn-primary">影廳座位預覽</button>
					<br> <br>
					<input type="text" class="seat_left" placeholder="左邊幾個位置" name="seat_left" value="${hall_seatVO.getSeat_left()}">
					<input type="text" class="seat_right" placeholder="右邊幾個位置" name="seat_right" value="${hall_seatVO.getSeat_right()}">
					<button type="button" class="seat_lr_btn btn btn-primary">左右走道預覽</button>
					<br> <br>
					<input type="text" class="seat_row_aisle1" placeholder="第幾排增加走道" name="seat_row_aisle1" value="${hall_seatVO.getseat_row_aisle1()}">
					<input type="text" class="seat_row_aisle2" placeholder="第幾排增加走道" name="seat_row_aisle2" value="${hall_seatVO.getseat_row_aisle2()}">
					<button type="button" class="seat_row_aisle_btn btn btn-primary">前後走道預覽</button>
					<br> <br>
					<c:forEach var="hall_seatVO" items="${hall_seatSvc.hall_New}">
						<input type="hidden" name="hall_id" value="${hall_seatVO.hall_id}">
					</c:forEach>
					<br> <br>
					<button type="button" class="reset_btn btn btn-secondary">重設</button>
					<input type="hidden" name="action" value="insert">
					<input class="btn btn-primary" type="submit" value="確認新增" id="sava_data">
					<!-- 走道 -->
					<div class="aisle"></div>
					<div class="seat-start"></div>
					<!-- 走道 -->
					<div class="aisle"></div>
				</form>
				<form method="post" action="<%=request.getContextPath()%>/view/hall_seat/Hall_SeatServlet" name="form1">
					<c:forEach var="hall_seatVO" items="${hall_seatSvc.hall_New}">
						<input type="hidden" name="hall_id" value="${hall_seatVO.hall_id}">
					</c:forEach>
					<input type="hidden" name="action" value="delete">
					<input class="btn btn-danger" type="submit" value="取消">
				</form>
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
	src="${pageContext.request.contextPath}/view/hall_seat/js/system_add_seat.js"></script>
<script>
	$("input#seat_name").val(hall_seatVO.getSeat_name());
</script>
<script>
	$("input#seat_state").val(hall_seatVO.getSeat_state());
</script>
<script>
	$("input#seat_no").val(hall_seatVO.getSeat_no());
</script>

</body>

</html>