<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="web.ticket_list.service.*"%>
<%@ page import="web.ticket_list.entity.*"%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>

<%
    Ticket_listService ticket_listSvc = new Ticket_listService();
    List<Ticket_listVO> list = ticket_listSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>����q����� - listAllTicket.jsp</title>

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

<h4>�����m�߱ĥ� EL ���g�k����:</h4>
<table id="table-1">
	<tr><td>
		 <h3>�Ҧ�������Ӹ�� - listAllTicket.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table>
	<tr>
		<th>������ӽs��</th>
		<th>����q��s��</th>
		<th>�y��s��</th>
		<th>�q�v�ɨ�s��</th>
		<th>����ƶq</th>
		<th>�������</th>
		<th>�ק�</th>
		<th>�R��</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="ticket_listVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${ticket_listVO.ticket_list_id}</td>
			<td>${ticket_listVO.ticket_orders_id}</td>
			<td>${ticket_listVO.seat_id}</td>
			<td>${ticket_listVO.movie_time_id}</td>
			<td>${ticket_listVO.ticket_num}</td>
			<td>${ticket_listVO.ticket_price}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/view/ticket_list/TicketServlet" style="margin-bottom: 0px;">
			     <input type="submit" value="�ק�">
			     <input type="hidden" name="ticket_list_id"  value="${ticket_listVO.ticket_list_id}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/view/ticket_list/TicketServlet" style="margin-bottom: 0px;">
			     <input type="submit" value="�R��">
			     <input type="hidden" name="ticket_list_id"  value="${ticket_listVO.ticket_list_id}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>