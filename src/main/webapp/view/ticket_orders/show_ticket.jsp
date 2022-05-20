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
pageContext.getAttribute("list_mem_order");
MemVO memVO = (MemVO) session.getAttribute("memVO");
Ticket_OrdersVO ticket_ordersVO = (Ticket_OrdersVO) request.getAttribute("ticket_ordersVO");
%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- 網頁標題記得修改 -->
    <title>前台頁面底板Ver04</title>
    <!-- css檔連結記得修改 -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/view/ticket_orders/css/show_ticket.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>

<%@ include file="/view/index/header.jsp" %>

    <!-- 主要工作區 -->

    <main>

        <div class="showticket">
            <img src="image/others/mujilogo2.png" alt="">
            <h3>${memVO.mem_name} 您好, 這次您的訂票明細如下</h3>
            <form METHOD="post" ACTION="<%=request.getContextPath()%>/view/ticket_orders/Ticket_OrdersServlet">
			<c:forEach var="ticket_ordersVO" items="${list_mem_order}" begin="0" end="1" >
                <div class="container">
                    <div class="row">
                        <div class="col-2"></div>
                        <div class="col-8">
                            <h4 class="order_number">訂單編號 : ${ticket_ordersVO.ticket_orders_id}</h4>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-12">
                            <table class="table table-success table-striped">
                                <thead>
                                    <tr>
                                        <th scope="col">電影名稱</th>
                                        <th class="tableval" scope="col" colspan="3">${ticket_ordersVO.movie_ch}</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <th scope="row">影城影廳</th>
                                        <th class="tableval" scope="col" colspan="3">${ticket_ordersVO.hall_name}</th>
                                    </tr>
                                    <tr>
                                    	<th scope="row">播放日期</th>
                                    	<th class="tableval" scope="col" colspan="3">${ticket_ordersVO.showing_date}</th>
                                    </tr>
                                    <tr>
                                    	<th scope="row">電影場次</th>
                                    	<th class="tableval" id="movie_time" scope="col" colspan="3" value="${ticket_ordersVO.showing}"></th>
                                    </tr>
                                    <tr>
                                        <th scope="row">票券張數</th>
                                        <th class="tableval" scope="col" colspan="3">全票 ${ticket_ordersVO.ticket_number} 張</th>
                                    </tr>
                                    <tr>
                                        <th scope="row">場次座位</th>
                                        <th class="tableval" scope="col" colspan="3">${ticket_ordersVO.select_seat_name}</th>
                                    </tr>
                                    <tr>
                                        <th scope="row">合計金額</th>
                                        <th class="tableval" scope="col" colspan="3">${ticket_ordersVO.ticket_price}</th>
                                    </tr>
                                </tbody>
                            </table>
                        </div>

                    </div>
                </div>

                <div class="container">
                    <div class="row">
                        <div class="col-12">
							<a href="<%=request.getContextPath()%>/view/index/index.jsp">
                            <button type="button" class="btn btn-primary" id="btn_primary">確認</button>
                        	</a>
                        </div>
                    </div>
                </div>
            </c:forEach>    
            </form>
        </div>


    </main>

    <!-- 主要工作區結束 -->

<%@ include file="/view/index/footer.jsp" %>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <!-- JS檔連結記得修改 -->
    <script src="<%=request.getContextPath()%>/view/ticket_orders/js/show_ticket.js"></script>
</body>

</html>