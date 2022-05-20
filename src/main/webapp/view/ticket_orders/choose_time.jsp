<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="web.ticket_orders.dao.*"%>
<%@ page import="web.ticket_orders.entity.*"%>
<%@ page import="web.ticket_orders.service.*"%>
<%@ page import="web.member.entity.*"%>
<%@ page import="web.member.dao.*"%>

<%
MemVO memVO = (MemVO) request.getAttribute("memVO");
Ticket_OrdersVO ticket_ordersVO = (Ticket_OrdersVO) request.getAttribute("ticket_ordersVO");
pageContext.getAttribute("list");
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
	href="${pageContext.request.contextPath}/view/ticket_orders/css/choose_time.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
</head>

<%@ include file="/view/index/header.jsp"%>
<!-- 主要工作區 -->

<main>
	<div class="mumipay">
		<img src="image/others/mujilogored.png" alt="">

		<jsp:useBean id="ticket_ordersSvc" scope="page"
			class="web.ticket_orders.service.Ticket_OrdersService" />
		<form METHOD="post"
			ACTION="<%=request.getContextPath()%>/view/ticket_orders/Ticket_OrdersServlet">
			<div class="container">
				<div class="row">
					<div class="col-12">
						<h2 class="title">您所選擇的電影是 : 咒術迴戰</h2>
						<h3>請選擇日期與場次</h3>
					</div>
					<div class="col-md-6 col-sm-12">
						<img src="image/moviestand/1825.jpg" alt="">
					</div>
					<div class="col-md-6 col-sm-12">
						<div class="col-12 forspan">
							<c:forEach var="ticket_ordersVO" items="${list}" varStatus="status">
        						<c:set var="timeAlreadyExists" value="${false}" />
        						<c:if test="${(status.index - 1) >= 0}">
            						<c:forEach var="previousDate" items="${list}" begin="0" end="${status.index - 1}" varStatus="inner">
                						<c:if test="${ticket_ordersVO.showing_date == previousDate.showing_date}">
                    						<c:set var="timeAlreadyExists" value="${true}" />
                						</c:if>
            						</c:forEach>
        						</c:if>
        						<c:if test="${not timeAlreadyExists}">
        							<hr>
            						<span class="day">${ticket_ordersVO.showing_date}</span>
       							</c:if>
            						<button class="showtime" value="${ticket_ordersVO.showing}"></button>
            						<input id="movie_time_id" type="hidden" name="" value="${ticket_ordersVO.movie_time_id}">
    						</c:forEach>
    						<hr>
						</div>
						<input id="mem_id" type="hidden" name="mem_id" value="${memVO.mem_id}">
					</div>
					<div class="col-2"></div>
					<div class="col-8">
						<select name="ticket_number" class="form-select" aria-label="Default select example">
							<option selected>請選擇張數</option>
							<option id="ticket_number" value="1">1</option>
							<option id="ticket_number" value="2">2</option>
							<option id="ticket_number" value="3">3</option>
							<option id="ticket_number" value="4">4</option>
							<option id="ticket_number" value="5">5</option>
						</select>
					</div>
				</div>
			</div>

			<div class="container">
				<div class="row">
					<div class="col-2"></div>
					<div class="col-4">
						<input type="hidden" name="action" value="confirm_time_number">
						<input class="btn btn-primary" type="submit" value="前往劃位">
					</div>
					<div class="col-4">
						<a href="mem_shopping sheet.html">
							<button type="button" class="btn btn-secondary">取消返回</button>
						</a>
					</div>
				</div>
			</div>
		</form>
	</div>

</main>

<!-- 主要工作區結束 -->


<!-- 頁尾 -->
<%@ include file="/view/index/footer.jsp"%>


<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
	crossorigin="anonymous"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<!-- JS檔連結記得修改 -->
<script
	src="${pageContext.request.contextPath}/view/ticket_orders/js/choose_time.js"></script>
<script>
	$("input#movie_time_id_choose").val(ticket_ordersVO.getMovie_time_id());
</script>
<script>
	$("option#ticket_number").val(ticket_ordersVO.getTicket_number());
</script>

</html>