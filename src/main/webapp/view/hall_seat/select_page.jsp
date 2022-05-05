<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>IBM hall_seat: Home</title>

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
   <tr><td><h3>IBM hall_seat: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for IBM hall_seat: Home</p>

<h3>��Ƭd��:</h3>
	
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
  <li><a href='listAllHall_Seat.jsp'>List</a> all Hall_Seat.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="<c:url value="/view/hall_seat/Hall_SeatServlet"/>">
        <b>��J�v�U�s�� (�p1):</b>
        <input type="text" name="hall_id">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="�e�X">
    </FORM>
  </li>

<%--   <jsp:useBean id="hall_seatSvc" scope="page" class="com.hall_seat.model.Hall_SeatService" /> --%>
   
<!--   <li> -->
<!--      <FORM METHOD="post" ACTION="emp.do" > -->
<!--        <b>��ܭ��u�s��:</b> -->
<!--        <select size="1" name="emp_id"> -->
<%--          <c:forEach var="empVO" items="${empSvc.all}" >  --%>
<%--           <option value="${empVO.emp_id}">${empVO.emp_id} --%>
<%--          </c:forEach>    --%>
<!--        </select> -->
<!--        <input type="hidden" name="action" value="getOne_For_Display"> -->
<!--        <input type="submit" value="�e�X"> -->
<!--     </FORM> -->
<!--   </li> -->
  
<!--   <li> -->
<!--      <FORM METHOD="post" ACTION="emp.do" > -->
<!--        <b>��ܭ��u�m�W:</b> -->
<!--        <select size="1" name="emp_id"> -->
<%--          <c:forEach var="empVO" items="${empSvc.all}" >  --%>
<%--           <option value="${empVO.emp_id}">${empVO.emp_name} --%>
<%--          </c:forEach>    --%>
<!--        </select> -->
<!--        <input type="hidden" name="action" value="getOne_For_Display"> -->
<!--        <input type="submit" value="�e�X"> -->
<!--      </FORM> -->
<!--   </li> -->
</ul>


<h3>�v�U�y��޲z</h3>

<ul>
  <li><a href='addHall_Seat.jsp'>Add</a> a new Hall_Seat.</li>
</ul>

</body>
</html>