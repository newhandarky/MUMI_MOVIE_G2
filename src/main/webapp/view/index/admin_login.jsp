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
            <h2>歡迎來到MUMI MOVIE</h2>
            <h2>吾映良影後台管理系統</h2>
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
            
            <form id="login" action="<%=request.getContextPath()%>/view/employee/LoginHandler" method="post">

	            <div class="container">
	                <div class="row">
	                    <div class="col-1"></div>
	                    <div class="form-floating col-10">
	                        <input type="account" class="form-control" name="emp_account" id="floatingAccount" placeholder="name@example.com">
	                        <label for="floatingAccount">帳號</label>
	                    </div>
	                    <div class="col-1"></div>
	                    <div class="col-1"></div>
	                    <div class="form-floating col-10">
	                        <input type="password" class="form-control" name="emp_password" id="floatingConfirm" placeholder="Password">
	                        <label for="floatingConfirm">密碼</label>
	                    </div>
	                </div>
	                <input type="hidden" name="emp_account" value="${empVO.emp_account}">
	               	<input type="hidden" name="action" value="login">
	                <a href="<%=request.getContextPath()%>/view/index/admin_index.jsp">
	                    <button type="submit" class="btn btn-primary btn_login" id="btn_login">登入</button>
	                </a>
	            </div>
	    	</form>
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