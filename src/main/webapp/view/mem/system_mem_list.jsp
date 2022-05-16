<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="web.member.entity.*"%>
<%@ page import="web.member.service.*"%>


<%
	MemService memSvc = new MemService();
	List<MemVO> list = memSvc.getAll();
	pageContext.setAttribute("list", list);
%>


<html lang="en">

<head>
<meta charset="UTF-8">
<title>影城後台管理系統 - 會員查詢系統</title>
<link rel='stylesheet'
	href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css'>
<link rel='stylesheet'
	href='https://unicons.iconscout.com/release/v3.0.6/css/line.css'>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/view/mem/css/system_mem_list.css">

</head>

<%@ include file="../index/admin_header.jsp" %>

		<!-- 主要工作區 -->
		<div class="main">

			<div class="showtable table-responsive">
				<h2>會員資料列表</h2>

				<form METHOD="post" ACTION="mem.do">
					<p class="search-p">搜尋會員 :</p>
					<input id="search" type="search" name="mem_id"> 
					<input type="hidden" name="action" value="getOne_For_Display">
					<button type="submit" class="btn btn-primary">查詢</button>
				</form>

				<table class="table">
					<tr>
						<th>會員編號</th>
						<th>會員帳號</th>
						<th>會員姓名</th>
						<th>帳號狀態</th>
					</tr>
					<%@ include file="page1.file" %> 
					<c:forEach var="memVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
						<tr class="memstate">

							<td>
								<form METHOD="post" ACTION="<%=request.getContextPath()%>/view/mem/MemServlet">
									<input  type="hidden" name="mem_id" value="${memVO.mem_id}"> 
									<input type="hidden" name="action" value="getOne_For_Display">
									<a href="<%=request.getContextPath()%>/view/mem/system_mem_data.jsp">
										<button type="submit" class="btn btn-success">${memVO.mem_id} </button>										
									</a>
								</form>
							</td>
							<td>${memVO.mem_account}</td>
							<td>${memVO.mem_name}</td>
							<td class="memstate${memVO.mem_state}"></td>
						</tr>
					</c:forEach>
				</table>
				<%@ include file="page2.file" %>

				<!-- 工作區結束 -->

				<footer>
					<div class="copyright">
						Copyright © 2022 MUMI MOVIE 吾映良影 Co. 保留所有權利。 <a href="#">隱私政策</a>
						<a href="#">使用條款</a>
					</div>
				</footer>
	</section>
	<script src='https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.bundle.js'></script>
	<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js'></script>
	<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <script src="<%=request.getContextPath()%>/view/mem/js/jquery-3.6.0.min.js"></script>
	<script src="<%=request.getContextPath()%>/view/mem/js/system_mem_list.js"></script>
</body>
</html>