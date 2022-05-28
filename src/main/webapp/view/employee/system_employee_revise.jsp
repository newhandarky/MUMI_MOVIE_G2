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
				<h2>員工資料修改</h2>
				
				<table>
					<tr>
						<td>員工編號</td>
						<td><%=employeeVO.getEmp_id()%></td>
					</tr>

					<tr>
						<td>員工帳號</td>
						<td>
							<input class="form-control" type="text" name="emp_account" value="<%= (employeeVO==null)? "員工帳號" :employeeVO.getEmp_account()%>" aria-label="default input example" >
						</td>
					</tr>

					<tr>
						<td>員工姓名</td>
						<td>
							<input class="form-control" type="text" name="emp_name" value="<%= (employeeVO==null)? "員工姓名" : employeeVO.getEmp_name()%>" aria-label="default input example">
						</td>
					</tr>
					
					<tr>
						<td>暱稱</td>
						<td>
							<input class="form-control" type="text" name="emp_nickname" value="<%= (employeeVO==null)? "員工暱稱" : employeeVO.getEmp_nickname()%>" aria-label="default input example">
						</td>
					</tr>

					<tr>
						<td>連絡電話</td>
						<td>
							<input class="form-control" type="text" name="emp_phone" value="<%= (employeeVO==null)? "聯絡電話" : employeeVO.getEmp_phone()%>" aria-label="default input example" maxlength="10">
						</td>
					</tr>

					<tr>
						<td>帳號密碼</td>
						<td>
							<input class="form-control" type="text"  name="emp_password" value="<%= (employeeVO==null)? "帳號密碼" : employeeVO.getEmp_password()%>" aria-label="default input example">
						</td>
					</tr>

					<tr>
						<td>雇用日期</td>
						<td>
							<input class="form-control" type="date" name="emp_hiredate" value="<%= (employeeVO==null)? "雇用日期" : employeeVO.getEmp_hiredate()%>">
						</td>
					</tr>
					<tr>	
						<td>在職/離職</td>
						<td>
                            <select name="emp_state" >
                                <option value="0" ${ employeeVO.getEmp_state() == 0 ? "selected" : ""} >已離職</option>
                                <option value="1" ${ employeeVO.getEmp_state() == 1 ? "selected" : ""} >在職中</option>
                            </select>             
                        </td>
					</tr>

				</table>
				
				<a class="" href="<%=request.getContextPath()%>/view/employee/system_employee_list.jsp">
					<button type="button" class="btn btn-secondary" id="cancel">取消變更</button>
				</a>
					<input type="hidden" name="emp_id" value="<%=employeeVO.getEmp_id()%>">
					<input type="hidden" name="action" value="update">
                    <button type="submit" class="btn btn-primary" id="commit" value="送出修改">確認送出</button>							
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
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>

<script>
        $.datetimepicker.setLocale('zh');
        $('#updateHiredate').datetimepicker({
           theme: '',              //theme: 'dark',
 	       timepicker:false,       //timepicker:true,
 	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
 	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
 		   value: '<%=employeeVO.getEmp_hiredate()%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
</script>

</html>