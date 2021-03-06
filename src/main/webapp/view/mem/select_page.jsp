<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- <!DOCTYPE html> -->


<html>
<head>
<title>Mumi Movie: 吾映良影</title>

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
   <tr><td><h3>MUMI MOVIE Member : Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for MUMI MOVIE Member: Home</p>

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
  <li><a href='system_mem_list.jsp'>List</a> all Members.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="<c:url value="/view/member/MemServlet"/>">
    
        <b>輸入會員編號 (如12345):</b>
        <input type="text" name="mem_id">
        <input type="hidden" name="action" value="getOne_For_Update">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="memSvc" scope="page" class="web.member.service.MemService" />
   
  <li>
     <FORM METHOD="post" ACTION="<c:url value="/view/mem/MemServlet"/>">
       <b>選擇會員編號:</b>
       <select size="1" name="mem_id">
         <c:forEach var="memVO" items="${memSvc.all}" > 
         
          <option name="mem_id" value="${memVO.mem_id}">${memVO.mem_id}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Update">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="<c:url value="/view/mem/MemServlet"/>">
       <b>選擇會員姓名:</b>
       <select size="1" name="mem_id">
         <c:forEach var="memVO" items="${memSvc.all}" > 
          <option value="${memVO.mem_id}">${memVO.mem_name}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Update">
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>


<h3>會員管理</h3>

<ul>
  <li><a href='mem_register.jsp'>Add</a>register a new mumi member.</li>
</ul>

</body>
</html>