<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="web.ticket_orders.dao.*"%>
<%@ page import="web.ticket_orders.entity.*"%>
<%@ page import="web.ticket_orders.service.*"%>
<%@ page import="web.member.entity.*"%>
<%@ page import="web.member.dao.*"%>
<%@ page import="web.movie.entity.*"%>
<%@ page import="web.movie.dao.*"%>
<%@ page import="web.movie.service.*"%>

<%
// 	MemVO memVO = (MemVO) request.getAttribute("memVO");
MemVO memVO = (MemVO) session.getAttribute("memVO");
Ticket_OrdersVO ticket_ordersVO = (Ticket_OrdersVO) request.getAttribute("ticket_ordersVO");
MovieService movieSvc = new MovieService();
List<MovieVO> list = movieSvc.getAll();
pageContext.setAttribute("list", list);
%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
<!-- css檔連結記得修改 -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/view/ticket_orders/css/choose_movie.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
</head>

<%@ include file="/view/index/header.jsp" %>
	<!-- 主要工作區 -->

	<main>
		<div class="main">


			<form METHOD="post" ACTION="<%=request.getContextPath()%>/view/ticket_orders/Ticket_OrdersServlet">
				<c:forEach var="movieVO" items="${list}">
					<button type="submit" class="btn btn-success choose_movie">${movieVO.movie_ch}</button>		
					<input id="movie_id" type="hidden" name="" value="${movieVO.movie_id}">
					<input type="hidden" name="action" value="getOne_For_Choose">
				</c:forEach>
				<input id="mem_id" type="hidden" name="mem_id" value="${memVO.mem_id}">
			</form>
		</div>


	</main>

	<!-- 主要工作區結束 -->


	<!-- 頁尾 -->
<%@ include file="/view/index/footer.jsp" %>


	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
		crossorigin="anonymous"></script>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<!-- JS檔連結記得修改 -->
	<script
		src="${pageContext.request.contextPath}/view/ticket_orders/js/choose_movie.js"></script>
	<script>
		$("input#movie_id").val(ticket_ordersVO.getMovie_id());
	</script>

</html>