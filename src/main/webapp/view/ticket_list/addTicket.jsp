<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="web.ticket_list.entity.*"%>

<%
Ticket_listVO ticket_listVO = (Ticket_listVO) request.getAttribute("ticket_listVO");
%>
--<%=ticket_listVO == null%>--${ticket_listVO==null}--
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>票券明細新增 - addTicket.jsp</title>

<style>
table#table-1 {
	background-color: #CCCCFF;
	border: 2px solid black;
	text-align: center;
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

h4 {
	color: blue;
	display: inline;
}
</style>

<style>
table {
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
}

table, th, td {
	border: 0px solid #CCCCFF;
}

th, td {
	padding: 1px;
}
</style>

</head>
<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td>
				<h3>票券明細新增 - addTicket.jsp</h3>
			</td>
			<td>
				<h4>
					<a href="select_page.jsp"><img src="images/tomcat.png"
						width="100" height="100" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>請新增以下資料:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/view/ticket_list/TicketServlet" 
					name="form1"enctype="multipart/form-data">
		<table>
			<tr>
				<td>電影時刻編號:</td>
				<td><input type="TEXT" name="movie_time_id" ></td>
			</tr>
			<tr>
				<td>票券數量:</td>
				<td><input type="TEXT" name="ticket_num" size="45" /></td>
			</tr>
			<tr>
				<td>票券價格:</td>
				<td><input type="TEXT" name="ticket_price" size="45"
					value="<%=(ticket_listVO == null) ? "1000" : ticket_listVO.getTicket_price()%>" /></td>
			</tr>

		</table>
		<br>
				<input type="hidden" name="mem_id" size="45" value="<%=(ticket_listVO == null) ? "001" : ticket_listVO.getMovie_time_id()%>" /> 
				<input type="hidden" name="action" value="insert"> 
				<input type="submit" value="送出新增"></FORM>
</body>		



</html>