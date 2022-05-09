<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="web.ticket_order.service.*"%>
<%@ page import="web.ticket_order.entity.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    TicketService ticketSvc = new TicketService();
    List<TicketVO> list = ticketSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>票券資料 - listAllTicket.jsp</title>

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
  img{
  	width: 100px;
  	height: 100px;
  }
</style>

<style>
  table {
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>

<h4>此頁練習採用 EL 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>所有票券資料 - listAllTicket.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table>
	<tr>
		<th>票券訂單編號</th>
		<th>會員編號</th>
		<th>購票日期</th>
		<th>票券QRcode</th>
		<th>票券總金額</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="ticketVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${ticketVO.ticket_orders_id}</td>
			<td>${ticketVO.mem_id}</td>
			<td>${ticketVO.buyticket_date}</td>
			<td><img src="PictureServlet?ticket_orders_id=${ticketVO.ticket_orders_id}"></td> 
			<td>${ticketVO.total_price}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/view/ticket/TicketServlet" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="ticket_orders_id"  value="${ticketVO.ticket_orders_id}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/view/ticket/TicketServlet" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="ticket_orders_id"  value="${ticketVO.ticket_orders_id}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>