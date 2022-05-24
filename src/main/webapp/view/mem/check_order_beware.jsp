<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="web.member.entity.*"%>
<%@ page import="web.member.dao.*"%>

<%
MemVO memVO = (MemVO) session.getAttribute("memVO");
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
	href="${pageContext.request.contextPath}/view/mem/css/check_order_beware.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
</head>

<%@ include file="/view/index/header.jsp"%>
<!-- 主要工作區 -->

<main>
	<div class="container">
		<div class="col-12">
			<div class="beware_head">
				<h2>
					請注意！吾映良影影城絕不會要求顧客至ATM操作解除分期或補繳金額 <br>
					請貴賓提高警覺避免受騙吾映良影影城與您一起防範詐騙 <br>
					<br>
					若有訂單問題請直接與本影城聯絡
				</h2>
			</div>
		</div>
		<form METHOD="post" ACTION="<%=request.getContextPath()%>/view/mem/MemServlet">
			<div class="col-12">
				<div class="row check_btn">
					<div class="col-2">
						<input id="mem_id" type="hidden" name="mem_id" value="${memVO.mem_id}">
						<input type="hidden" name="action" value="checkOrders">
						<input class="btn btn-primary" type="submit" value="確認">
					</div>
					<div class="col-2">
						<a href="<%=request.getContextPath()%>/view/index/index.jsp">
							<button type="button" class="btn btn-secondary">取消返回</button>
						</a>
					</div>
				</div>
			</div>
		</form>
		<br>
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
	src="${pageContext.request.contextPath}/view/mem/js/check_order_beware.js"></script>

</html>