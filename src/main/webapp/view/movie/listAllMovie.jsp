<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="web.movie.dao.*"%>
<%@ page import="web.movie.entity.*"%>
<%@ page import="web.movie.service.*"%>
<%@page import="java.text.SimpleDateFormat" %>
<%@ page import="java.sql.Timestamp"%>

<%-- �����m�߱ĥ� EL ���g�k���� --%>

<%
    MovieService movieSvc = new MovieService();
    List<MovieVO> list = movieSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>�Ҧ��q�v��� - listAllMovie.jsp</title>

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
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th{
/*    white-space: nowrap;  */
  }
  th, td {
    white-space: nowrap;
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>

<h4>�����m�߱ĥ� EL ���g�k����:</h4>
<table id="table-1">
	<tr><td>
		 <h3>�Ҧ��q�v��� - listAllMovie.jsp</h3>
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
		<th>�q�v�s��</th>
		<th>�q�v����W��</th>
		<th>�q�v�^��W��</th>
		<th>�q�v�̫�ק�ɶ�</th>
		<th>�q�v���A</th>
		<th>�q�v����</th>
		<th>����</th>
		<th>�t��</th>
		<th>�ɺt</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="movieVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${movieVO.movie_id}</td>
			<td>${movieVO.movie_ch}</td>
			<td>${movieVO.movie_en}</td>
			<td><fmt:formatDate value="${movieVO.movie_updated_time}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			<td>${movieVO.movie_state_id}</td>
			<td>${movieVO.movie_rating_id}</td>
			<td>${movieVO.movie_runtime}����</td>
			<td>${movieVO.casts}</td>
			<td>${movieVO.director}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/view/movie/MovieServlet" style="margin-bottom: 0px;">
			     <input type="submit" value="�ק�">
			     <input type="hidden" name="movie_id"  value="${movieVO.movie_id}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/view/movie/MovieServlet" style="margin-bottom: 0px;">
			     <input type="submit" value="�R��">
			     <input type="hidden" name="movie_id"  value="${movieVO.movie_id}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>