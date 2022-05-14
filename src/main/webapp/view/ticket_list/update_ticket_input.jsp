<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="web.ticket_list.entity.*"%>

<%
Ticket_listVO ticket_listVO = (Ticket_listVO) request.getAttribute("ticket_listVO"); //EmpServlet.java (Concroller) �s�Jreq��empVO���� (�]�A�������X��empVO, �]�]�A��J��ƿ��~�ɪ�empVO����)
// 			System.out.println(ticket_listVO);
%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>����q����ӭק� - update_ticket_input.jsp</title>

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
	<tr><td>
		 <h3>�q����ƭק� - update_ticket_input.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

<h3>��ƭק�:</h3>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/view/ticket_list/Ticket_listServlet" name="form1" enctype="multipart/form-data">
<table>
	<tr>
		<td>������ӽs��:<font color=red><b>*</b></font></td>
		<td><%=ticket_listVO.getTicket_list_id()%></td>
	</tr>
	<tr>
		<td>����q��s��:</td>
		<td><%=ticket_listVO.getTicket_orders_id()%></td>
	</tr>
	<tr>
		<td>�y��s��:</td>
		<td><%=ticket_listVO.getTicket_orders_id()%></td>
	</tr>
	<tr>
		<td>�q�v�ɨ�s��:</td>
		<td><%=ticket_listVO.getTicket_orders_id()%></td>
	</tr>
	<tr>
		<td>�������:</td>
		<td><input type="TEXT" name="ticket_price" size="45" value="<%=ticket_listVO.getTicket_price()%>" /></td>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="ticket_orders_id" value="<%=ticket_listVO.getTicket_list_id()%>">
<input type="hidden" name="mem_id" value="<%=ticket_listVO.getTicket_orders_id()%>">
<input type="submit" value="�e�X�ק�"></FORM>
</body>



<!-- =========================================�H�U�� datetimepicker �������]�w========================================== -->

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

</html>