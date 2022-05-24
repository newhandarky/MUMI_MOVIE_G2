<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="web.member.entity.*"%>
<%@ page import="web.member.dao.*"%>
<%@ page import="web.member.service.*"%>
<%@ page import="java.sql.*"%>

<%
pageContext.getAttribute("listSheet");
MemVO memVO = (MemVO) session.getAttribute("memVO");
%>

<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
<!-- css檔連結記得修改 -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/view/mem/css/mem_orders_sheet.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
</head>

<%@ include file="/view/index/header.jsp"%>

<!-- 主要工作區 -->

<main>
	<div class="shopping_sheet">
		<form METHOD="post" ACTION="<%=request.getContextPath()%>/view/mem/MemServlet">

			<!-- <div class="">
                <h3>尚無您的交易紀錄</h3>
            </div> -->

			<h3>交易紀錄查詢</h3>
			<div class="accordion accordion-flush" id="accordionFlushExample">
				<c:forEach var="memVO" items="${listSheet}">
					<div class="accordion-item">
						<h2 class="accordion-header" id="flush-heading${memVO.ticket_orders_id}">
							<button class="accordion-button collapsed" type="button"
								data-bs-toggle="collapse" data-bs-target="#flush-collapse${memVO.ticket_orders_id}"
								aria-expanded="false" aria-controls="flush-collapse${memVO.ticket_orders_id}">
								訂單編號 :
								<p>${memVO.ticket_orders_id}</p>
							</button>
						</h2>
						<div id="flush-collapse${memVO.ticket_orders_id}" class="accordion-collapse collapse"
							aria-labelledby="flush-heading${memVO.ticket_orders_id}"
							data-bs-parent="#accordionFlushExample">
							<div class="accordion-body">
								<table class="table">
									<p>訂票日期 : <fmt:formatDate value="${memVO.buyticket_date}"
           pattern="yyyy-MM-dd HH:mm:ss" /></p>
									
									<thead>
										<tr>
											<th>放映日期</th>
											<th>時間</th>
											<th>電影名稱</th>
											<th>影廳</th>
											<th>座位</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>${memVO.showing_date}</td>
											<td id="movie_time" value="${memVO.showing}"></td>
											<td>${memVO.movie_ch}</td>
											<td>${memVO.hall_name}</td>
											<td>${memVO.select_seat_name}</td>
										</tr>
										<tr>
											<th scope="row">總額</th>
											<td colspan="3"></td>
											<td>${memVO.ticket_price} 元</td>
										</tr>
									</tbody>
								</table>

							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</form>
	</div>
</main>

<!-- 主要工作區結束 -->

<%@ include file="/view/index/footer.jsp"%>

<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
	crossorigin="anonymous"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<!-- JS檔連結記得修改 -->
<script
	src="<%=request.getContextPath()%>/view/mem/js/mem_orders_sheet.js"></script>
</body>

</html>