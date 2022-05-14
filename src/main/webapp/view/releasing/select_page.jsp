<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>IBM Movie: Home</title>

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
   <tr><td><h3>IBM Movie: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for IBM Movie: Home</p>

<h3>資料查詢:</h3>
	
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
  <li><a href='system_releasing_listAll.jsp'>List</a> 所有電影狀態資料  <br><br></li>
  
  <jsp:useBean id="releasingSvc" scope="page" class="web.releasing.service.ReleasingService" />
   
</ul>


<h3>電影管理</h3>

<ul>
  <li><a href='system_releasing_add.jsp'>Add</a> a new Movie.</li>
</ul>

</body>
</html>