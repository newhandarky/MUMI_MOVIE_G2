<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="web.releasing.dao.*"%>
<%@ page import="web.releasing.entity.*"%>
<html>
<head>
<meta charset="UTF-8">
<title>影城後台管理系統</title>
<link rel='stylesheet'
	href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css'>
<link rel='stylesheet'
	href='https://unicons.iconscout.com/release/v3.0.6/css/line.css'>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/view/releasing/css/system_releasing_add.css">

</head>

<%@ include file="../index/admin_header.jsp"%>
<!-- 主要工作區 -->


<div class="main">
	<h2>新增電影狀態</h2>
	<div class="card">
		<div class="container">
			<div col="12">
				<div class="d-grid gap-2 d-flex justify-content-end">
					<a class="btn btn-secondary" href='system_releasing_listAll.jsp'>所有電影狀態資料</a>
					<jsp:useBean id="releasingSvc" scope="page"
						class="web.releasing.service.ReleasingService" />
				</div>
				<br>

				<c:if test="${not empty errorMsgs}">
					<font style="color: red">請修正以下錯誤:</font>
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color: red">${message}</li>
						</c:forEach>
					</ul>
				</c:if>

				<form method="post"
					action="<%=request.getContextPath()%>/view/releasing/ReleasingServlet"
					name="form1">
					<div class="mb-3 row">
						<label class="col-sm-2 col-form-label">電影狀態：</label>
						<div class="col-sm-3">
							<input class="form-control" type="text" name="movie_state"
								aria-label="default input example">
						</div>
					</div>
					<input type="hidden" name="action" value="insert"> <input
						type="submit" class="btn btn-primary" value="送出新增">
				</form>


			</div>
		</div>
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
	src='https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.jshttps://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js'></script>
<script
	src="<%=request.getContextPath()%>/view/releasing/js/system_releasing_add.js"></script>

</body>

</html>