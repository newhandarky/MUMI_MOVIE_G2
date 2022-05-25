<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
	href="${pageContext.request.contextPath}/view/hall_seat/css/system_hall_seat.css">
</head>

<%@ include file="../index/admin_header.jsp"%>
<!-- 主要工作區 -->
<main>
	<div class="main">
		<h1>影廳管理</h1>
		<div class="hall">
			<div class="container">
				<div class="row">
					<div class="col-5">
						<input class="btn-sm btn-primary" type="submit" value="新增影廳" id="sava_data" onclick="location.href='./system_add_hall.jsp'">
					</div>
					<div class="col-3">
						<p style="float: right">查看及修改影廳座位</p>
					</div>
					<div class="col-2">
						<jsp:useBean id="hall_seatSvc" scope="page" class="web.hall_seat.service.Hall_SeatService" />
						<form id="select_hall" method="post" action="<c:url value="/view/hall_seat/Hall_SeatServlet"/>">
							<select size="1" name="hall_id" class="form-select form-select-sm" aria-label=".form-select-sm example">
								<option selected>請選擇影廳</option>
								<c:forEach var="hall_seatVO" items="${hall_seatSvc.hall_Name}">
									<option value="${hall_seatVO.hall_id}">${hall_seatVO.hall_name}
								</c:forEach>
							</select> <input type="hidden" name="action" value="getOne_For_Display">
						</form>
					</div>
					<div class="col-2">
						<button id="select_hall_btn" type="button"
							class="btn btn-primary btn-sm" style="float: left">送出</button>
					</div>
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
	src="${pageContext.request.contextPath}/view/hall_seat/js/system_hall_seat.js"></script>


</html>