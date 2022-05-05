<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>IBN Ticket: Home</title>

<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
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

</head>
<body bgcolor='white'>

<table id="table-1">
   <tr><td><h3>IBN Ticket: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for IBN Ticket: Home</p>

<h3>�����Ƭd��:</h3>
	
<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='listAllTicket.jsp'>List</a> all Tickets.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="<c:url value="/view/ticket/TicketServlet"/>" > 
    
        <b>��J����s�� (�d��:1)</b>
        <input type="text" name="ticket_orders_id">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="�e�X">
    </FORM>
  </li>

  <jsp:useBean id="ticketSvc" scope="page" class="web.ticket_order.service.TicketService" />
   
  <li>
     <FORM METHOD="post" ACTION="<c:url value="/view/ticket/TicketServlet"/>" > 
       <b>��ܲ���s��:</b>
       <select size="1" name="ticket_orders_id">
         <c:forEach var="ticketVO" items="${ticketSvc.all}" > 
          <option value="${ticketVO.ticket_orders_id}">${ticketVO.ticket_orders_id}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="<c:url value="/view/ticket/TicketServlet"/>" > 
       <b>��ܭ��u�m�W:</b>
       <select size="1" name="ticket_orders_id">
         <c:forEach var="ticketVO" items="${ticketSvc.all}" > 
          <option value="${ticketVO.ticket_orders_id}">${ticketVO.mem_id}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
     </FORM>
  </li>
  
</ul>


<h3>����޲z</h3>

<ul>
  <li><a href='addTicket.jsp'>Add</a> a new Ticket.</li>
</ul>

</body>
</html>