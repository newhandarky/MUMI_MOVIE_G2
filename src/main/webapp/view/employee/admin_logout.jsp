<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="web.employee.entity.*"%>
<%@ page import="web.employee.dao.*"%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>影城後台管理系統</title>
    <link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css'>
    <link rel='stylesheet' href='https://unicons.iconscout.com/release/v3.0.6/css/line.css'>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/view/index/css/admin_login.css">

</head>

<body>

    <!-- 主要工作區 -->
    <div class="main">
        <div class="login_area">
     <h2>登出後，您已離開後台管理系統</h2>
            <%-- 錯誤表列 --%>
			<c:if test="${not empty errorMsgs}">
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li style="list-style:none">
							<p style="color:red">${message}</p>  
						</li>
					</c:forEach>
				</ul>
			</c:if>

	            <div class="container">
	                <a href="<%=request.getContextPath()%>/view/index/index.jsp">
	                    <button type="submit" class="btn btn-primary">回到電影首頁</button>
	                </a>
	                <a href="<%=request.getContextPath()%>/view/index/admin_login.jsp">
	                	<button type="button" class="btn btn-secondary">回到登入後台首頁</button>
					</a>
	            </div>
        </div>
    </div>

    <!-- 工作區結束 -->

    <script src='https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.bundle.js'></script>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="<%=request.getContextPath()%>/view/index/js/admin_login.js"></script>

</body>


<footer>
    <div class="copyright">
        Copyright © 2022 MUMI MOVIE 吾映良影 Co. 保留所有權利。
        <a href="#">隱私政策</a>
        <a href="#">使用條款</a>
    </div>

</footer>

</html>