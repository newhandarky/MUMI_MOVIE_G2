<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="web.hall_seat.dao.*"%>

<html lang="en">

<head>
<meta charset="UTF-8">
<title>影城後台管理系統 - 影廳管理系統</title>
<link rel='stylesheet'
	href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css'>
<link rel='stylesheet'
	href='https://unicons.iconscout.com/release/v3.0.6/css/line.css'>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/view/hall_seat/css/system_add_hall.css">

</head>

<%@ include file="../index/admin_header.jsp"%>
<!-- 主要工作區 -->
<main>
	<div class="main">
		<div class="hall">
			<div class="col">
				<form method="post" action="<%=request.getContextPath()%>/view/hall_seat/Hall_SeatServlet" name="form1">
					<input type="text" class="hall_name" placeholder="輸入影廳名稱" name="hall_name" value="${hall_seatVO.getHall_name}">
					<input type="hidden" name="action" value="insert_hall">
					<input class="btn btn-primary" type="submit" value="下一步" id="sava_data">
				</form>
				<br>
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
	src="${pageContext.request.contextPath}/view/hall_seat/js/system_add_hall.js"></script>

</body>

</html>