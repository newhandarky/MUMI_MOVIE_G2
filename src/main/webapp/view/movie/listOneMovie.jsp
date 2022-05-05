<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="web.movie.dao.*"%>
<%@ page import="web.movie.entity.*"%>
<%@ page import="web.movie.service.*"%>
<%@page import="java.text.SimpleDateFormat" %>
<%@ page import="java.sql.Timestamp"%>
<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<%
MovieVO movieVO = (MovieVO) request.getAttribute("movieVO"); //MovieServlet.java(Concroller), �s�Jreq��movieVO����
%>

<html>
<head>
<title>���u��� - listOneMovie.jsp</title>

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
	white-space: nowrap;
	padding: 5px;
	text-align: center;
}
img{
	max-width: 200px;
	height: auto;
}
</style>

</head>
<body bgcolor='white'>

	<h4>�����Ƚm�߱ĥ� Script ���g�k����:</h4>
	<table id="table-1">
		<tr>
			<td>
				<h3>�q�v��� - ListOneMovie.jsp</h3>
				<h4>
					<a href="select_page.jsp"><img src="images/back1.gif"
						width="100" height="32" border="0">�^����</a>
				</h4>
			</td>
		</tr>
	</table>

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
			<th>����</th>
		</tr>
		<tr>
			<td><%=movieVO.getMovie_id()%></td>
			<td><%=movieVO.getMovie_ch()%></td>
			<td><%=movieVO.getMovie_en()%></td>
			<td><fmt:formatDate value="${movieVO.movie_updated_time}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
			<td><%=movieVO.getMovie_state_id()%></td>
			<td><%=movieVO.getMovie_rating_id()%></td>
			<td><%=movieVO.getMovie_runtime()%>����</td>
			<td><%=movieVO.getCasts()%></td>
			<td><%=movieVO.getDirector()%></td>
			<td><img src="DBGifReader?movie_id=${movieVO.movie_id}"></td>
		</tr>
	</table>

</body>
</html>