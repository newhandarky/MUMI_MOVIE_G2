<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="web.employee.entity.*"%>
<%@ page import="web.employee.dao.*"%>
<%@ page import="java.sql.*"%>

<%
	EmployeeVO employeeVO = (EmployeeVO) request.getAttribute("employeeVO");	
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
	href="<%=request.getContextPath()%>/view/employee/css/system_employee_data.css">

</head>

<%@ include file="../index/admin_header.jsp" %>

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
		<form METHOD="post" ACTION="<c:url value="/view/employee/EmployeeServlet" />" enctype="multipart/form-data">
			<div class="employee-data">
				<h2>員工資料</h2>
				
				<table>
					<tr>
						<td>員工編號</td>
						<td>${employeeVO.emp_id}</td>
					</tr>

					<tr>
						<td>員工帳號</td>
						<td>${employeeVO.emp_account}</td>
					</tr>

					<tr>
						<td>員工姓名</td>
						<td>${employeeVO.emp_name}</td>
					</tr>
					
					<tr>
						<td>暱稱</td>
						<td>${employeeVO.emp_nickname}</td>
					</tr>

					<tr>
						<td>連絡電話</td>
						<td>${employeeVO.emp_phone}</td>
					</tr>

					<tr>
						<td>在職/離職</td>
						<td class="empstate${employeeVO.emp_state}"></td>
					</tr>

				</table>
				
				<a class="" href="<%=request.getContextPath()%>/view/employee/system_employee_list.jsp">
					<button type="button" class="btn btn-secondary">返回員工列表</button>
				</a>
					<input type="hidden" name="emp_id" value="<%=employeeVO.getEmp_id()%>">
					<input type="hidden" name="emp_state" value="<%=employeeVO.getEmp_state()%>">
					<input type="hidden" name="action" value="getOne_For_Update">			
					<!-- 				<button type="submit" class="btn btn-primary" value="送出修改">修改此員工狀態</button>							 -->			
				</div>
				</form>
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
	<script src="<%=request.getContextPath()%>/view/employee/js/jquery-3.6.0.min.js"></script>
	<script src="<%=request.getContextPath()%>/view/employee/js/system_employee_data.js"></script>
</body>

</html>