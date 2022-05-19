<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="java.util.*"%>
<%@ page import="web.ticket_orders.dao.*"%>
<%@ page import="web.ticket_orders.entity.*"%>
<%@ page import="web.ticket_orders.service.*"%>
<%@ page import="web.member.entity.*"%>
<%@ page import="web.member.dao.*"%>

<%
pageContext.getAttribute("list");
pageContext.getAttribute("list_ticket");
MemVO memVO = (MemVO) session.getAttribute("memVO");
Ticket_OrdersVO ticket_ordersVO = (Ticket_OrdersVO) request.getAttribute("ticket_ordersVO");
%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/view/ticket_orders/css/choose_seat.css">
</head>

<%@ include file="/view/index/header.jsp"%>
    <!-- 主要工作區 -->

    <main>
        <div class="main">
            <div class="hell">
            	<div class="seat-state">
                        <div class="seat-info"></div>
                        <p>可選</p>
                        <div class="seat-info selected"></div>
                        <p>您的座位</p>
                        <div class="seat-info used"></div>
                        <p>已使用</p>
                </div>
            	<div class="screen">
                	<h3>(銀幕)吾 映 良 影 只 放 好 電 影(銀幕)</h3>
                </div>
                <div class="select_seat">
						<!-- 走道 -->
						<div class="aisle"></div>
						<div class="seat-start">
						<input id="mem_id" type="hidden" name="mem_id" value="${memVO.mem_id}">
						<c:forEach var="ticket_ordersVO" items="${list}" begin="1" end="1" >
							<div style="opacity: 0" class="hall_row" id="${ticket_ordersVO.seat_row}"> </div>
							<div style="opacity: 0" class="hall_col" id="${ticket_ordersVO.seat_col}"> </div>
							<div style="opacity: 0" class="hall_left" id="${ticket_ordersVO.seat_left}"> </div>
							<div style="opacity: 0" class="hall_right" id="${ticket_ordersVO.seat_right}"> </div>
							<div style="opacity: 0" class="hall_row_aisle1" id="${ticket_ordersVO.seat_row_aisle1}"> </div>
							<div style="opacity: 0" class="hall_row_aisle2" id="${ticket_ordersVO.seat_row_aisle2}"> </div>
						</c:forEach>
						<c:forEach var="ticket_ordersVO" items="${list_ticket}" begin="0" end="1">
						<input id="ticket_list_id" type="hidden" name="ticket_list_id" value="${ticket_ordersVO.ticket_list_id}">
						<input id="ticket_number" type="hidden" name="ticket_number" value="${ticket_ordersVO.ticket_number}">
						</c:forEach>
				 		<form method="post" action="<%=request.getContextPath()%>/view/ticket_orders/Ticket_OrdersServlet" style="margin-bottom: 0px;">
						<c:forEach var="ticket_ordersVO" items="${list}" begin="0" step="1">
							<div class="seat" name="${ticket_ordersVO.seat_no}" style="opacity: 1" id="${ticket_ordersVO.seat_state}">
							<p style="opacity: 1" id="${ticket_ordersVO.seat_id}">${ticket_ordersVO.seat_name}</p>
							<input id="seat_state" type="hidden" name="seat_state" value="${ticket_ordersVO.seat_state}">	
				     		<input id="seat_id" type="hidden" name="seat_id" value="${ticket_ordersVO.seat_id}" >
				     		<input id="movie_time_id" type="hidden" name="movie_time_id" value="${ticket_ordersVO.movie_time_id}" >
				     		<input id="seat_select_state" type="hidden" name="seat_select_state" value="${fn:substring(ticket_ordersVO.seat_select_state,(ticket_ordersVO.seat_no)-1,(ticket_ordersVO.seat_no))}" >
							</div>
						</c:forEach>
			     			<a class="btn btn-secondary" href="select_page.jsp" role="button">取消</a>
			     			<input type="hidden" name="action" value="choose_seat">
			     			<input class="btn btn-primary" type="submit" value="確定">     			
			     		</form>
						<!-- 走道 -->
						<div class="aisle"></div>
						<div class="aisle"></div>
						</div>
				</div>
            </div>
        </div>
    </main>

    <!-- 主要工作區結束 -->


    <!-- 頁尾 -->
 <%@ include file="/view/index/footer.jsp"%>


    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <!-- JS檔連結記得修改 -->
    <script src="${pageContext.request.contextPath}/view/ticket_orders/js/choose_seat.js"></script>
    <script >$("input#movie_time_id").val(ticket_ordersVO.getMovie_time_id());</script>
    <script >$("input#seat_select_state").val(ticket_ordersVO.getSeat_select_state());</script>

</html>