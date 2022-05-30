<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="web.employee.entity.*"%>
<%@ page import="web.employee.service.*"%>


<%
	EmployeeService employeeSvc = new EmployeeService();
	List<EmployeeVO> list = employeeSvc.getAll();
	pageContext.setAttribute("list", list);
%>


<html lang="en">

<head>
<meta charset="UTF-8">
<title>影城後台管理系統 - 員工查詢系統</title>
<link rel='stylesheet'
	href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css'>
<link rel='stylesheet'
	href='https://unicons.iconscout.com/release/v3.0.6/css/line.css'>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/view/employee/css/system_employee_list.css">

</head>

<%@ include file="../index/admin_header.jsp" %>

		<!-- 主要工作區 -->
		<div class="main">

			<div class="showtable table-responsive">
				<h2>員工資料列表</h2>

		<c:if test="${not empty errorMsgs}">
			<font style="color:red">請修正以下錯誤:</font>
				<ul>
				    <c:forEach var="message" items="${errorMsgs}">
					<li style="color:red">${message}</li>
					</c:forEach>
				</ul>
		</c:if>


				<form METHOD="post" ACTION="<%=request.getContextPath()%>/view/employee/EmployeeServlet">
					<p class="search-p">請輸入員工編號 :</p>
					<input id="search" type="search" name="emp_id" value="${employeeVO.emp_id}"> 
					<input type="hidden" name="action" value="getOne_For_Display">
					<button type="submit" class="btn btn-primary">查詢</button>
				</form>

<table class="table">
					<tr>
						<th>員工編號</th>
						<th>員工帳號</th>
						<th>員工姓名</th>
						<th>在職/離職</th>
						<th>修改資料</th>
					</tr>
					<%@ include file="page1.file"%>
					<c:forEach var="employeeVO" items="${list}" begin="<%=pageIndex%>"
						end="<%=pageIndex+rowsPerPage-1%>">
						<tr>
							<td>${employeeVO.emp_id}</td>
							<td>${employeeVO.emp_account}</td>
							<td>${employeeVO.emp_name}</td>
							<td class="empstate${employeeVO.emp_state}"></td>
							<td>
								<FORM METHOD="post"
									ACTION="<%=request.getContextPath()%>/view/employee/EmployeeServlet" style="margin-bottom: 0px;">
									<input type="hidden" name="emp_id" value="${employeeVO.emp_id}">
									<input type="hidden" name="action" value="getOne_For_Update">
									<button type="submit" class="btn btn-primary">修改</button>
								</FORM>
							</td>
						</tr>
					</c:forEach>
				</table>

		<%@ include file="page2.file"%>
			
			</div>
			 <a href="system_employee_add.jsp">
                        <button type="button" class="btn btn btn-info btn-lg" >新增員工</button>
                    </a>			
		</div>


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
    <script src="<%=request.getContextPath()%>/view/employee/js/jquery-3.6.0.min.js"></script>
	<script src="<%=request.getContextPath()%>/view/employee/js/system_employee_list.js"></script>

</body>

</html>