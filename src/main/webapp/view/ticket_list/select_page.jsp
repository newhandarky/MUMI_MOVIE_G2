<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>IBN Ticket_list: Home</title>

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
   <tr><td><h3>IBN Ticket_list: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for IBN Ticket_list: Home</p>

<h3>票券資料查詢:</h3>
	
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='listAllTicket.jsp'>List</a> all Tickets.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="<c:url value="/view/ticket_list/TicketServlet"/>" > 
    
        <b>輸入票券明細編號 (範例:1)</b>
        <input type="text" name="ticket_list_id">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="ticket_listSvc" scope="page" class="web.ticket_list.service.Ticket_listService" />
   
  <li>
     <FORM METHOD="post" ACTION="<c:url value="/view/ticket_list/TicketServlet"/>" > 
       <b>選擇票券明細編號:</b>
       <select size="1" name="ticket_list_id">
         <c:forEach var="ticket_listVO" items="${ticket_listSvc.all}" > 
          <option value="${ticket_listVO.ticket_list_id}">${ticket_listVO.ticket_list_id}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="<c:url value="/view/ticket_list/TicketServlet"/>" > 
       <b>選擇票券訂單編號:</b>
       <select size="1" name="ticket_list_id">
         <c:forEach var="ticket_listVO" items="${ticket_listSvc.all}" > 
          <option value="${ticket_listVO.ticket_list_id}">${ticket_listVO.ticket_orders_id}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
  
</ul>


<h3>票券訂單明細管理</h3>

<ul>
  <li><a href='addTicket.jsp'>Add</a> a new Ticket.</li>
</ul>

</body>
</html>