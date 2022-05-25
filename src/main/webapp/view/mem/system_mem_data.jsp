<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="web.member.entity.*"%>

<%
MemVO memVO = (MemVO) request.getAttribute("memVO");
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
	href="<%=request.getContextPath()%>/view/mem/css/system_mem_data.css">

</head>

<%@ include file="../index/admin_header.jsp" %>

		<!-- 主要工作區 -->
		<div class="main">
		
		<%-- 錯誤表列 --%>
		<c:if test="${not empty errorMsgs}">
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color:red; list-style: none;">${message}</li>
				</c:forEach>
			</ul>
		</c:if>

			<div class="mem-data">
				<h2>會員資料</h2>
				
				<table>
					<tr>
						<td>會員編號</td>
						<td>${memVO.mem_id}</td>
					</tr>

					<tr>
						<td>會員帳號</td>
						<td>${memVO.mem_account}</td>
					</tr>

					<tr>
						<td>會員姓名</td>
						<td>${memVO.mem_name}</td>
					</tr>

					<tr>
						<td>連絡電話</td>
						<td>${memVO.mem_phone}</td>
					</tr>

					<tr>
						<td>生日</td>
						<td>${memVO.mem_birthday}</td>
					</tr>

					<tr>
						<td>地址</td>
						<td>${memVO.mem_address}</td>
					</tr>

					<tr>
						<td>性別</td>
						<td id="mem_gender" class="${memVO.mem_gender}"></td>
					</tr>
					<tr>
						<td>帳號密碼</td>
						<td>${memVO.mem_password}</td>
					</tr>

					<tr>
						<td>暱稱</td>
						<td>${memVO.mem_nickname}</td>
					</tr>

					<tr>
						<td>大頭照</td>
						<td>
							<img class="userpic" src="DBGifReader4?mem_id=${memVO.mem_id}">
						</td>
					</tr>

					<tr>
						<td>註冊日期</td>
						<td>${memVO.mem_register}</td>
					</tr>

					<tr>
						<td>最後修改時間</td>
						<td>${memVO.mem_update}</td>
					</tr>

					<tr>
						<td>帳號狀態</td>
						<td id="-on" class="memstate${memVO.mem_state}" ></td>
					</tr>

				</table>
				
				<a class="" href="system_mem_list.jsp">
					<button type="button" class="btn btn-secondary">返回會員列表</button>
				</a>
				
				<form id="updateState" METHOD="post" ACTION="<%=request.getContextPath()%>/view/mem/MemServlet" class="changestate">
					<input type="hidden" name="mem_id" value="${memVO.mem_id}"> 
					<input type="hidden" name="mem_state" value="${memVO.mem_state}">
					<input type="hidden" name="action" value="updateState">
					<button type="button" class="btn btn-primary" value="送出修改">修改此會員狀態</button>							
				</form>
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
		src='https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js'></script>
	<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
	<script src="<%=request.getContextPath()%>/view/mem/js/jquery-3.6.0.min.js"></script>
	<script src="<%=request.getContextPath()%>/view/mem/js/system_mem_data.js"></script>
</body>

</html>