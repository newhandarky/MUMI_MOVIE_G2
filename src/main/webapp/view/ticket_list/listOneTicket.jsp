<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="web.ticket_list.entity.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
  Ticket_listVO ticket_listVO = (Ticket_listVO) request.getAttribute("ticket_listVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
<title>票券資料 - listOneTicket.jsp</title>

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
	width: 600px;
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

<h4>此頁暫練習採用 Script 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>票券資料 - ListOneTicket.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>票券明細編號</th>
		<th>票券訂單編號</th>
		<th>座位編號</th>
		<th>電影時刻編號</th>
		<th>票券數量</th>
		<th>票券價格</th>

	</tr>
	<tr>
		<td><%=ticket_listVO.getTicket_list_id()%></td>
		<td><%=ticket_listVO.getTicket_orders_id()%></td>
		<td><%=ticket_listVO.getSeat_id()%></td>
		<td><%=ticket_listVO.getMovie_time_id()%></td>
		<td><%=ticket_listVO.getTicket_num()%></td>
		<td><%=ticket_listVO.getTicket_price()%></td>
	</tr>
</table>

</body>
</html>