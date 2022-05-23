<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="web.member.entity.*"%>
<%@ page import="web.member.dao.*"%>


<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MUMI MOVIE 吾映良影會員登入頁面</title>
    <!-- css檔連結記得修改 -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/view/index/css/login.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>

<%@ include file="header.jsp" %>
    <!-- 主要工作區 -->

    <main>

        <div class="loginarea">
            <div class="register">
            	<form id="login" action="<%=request.getContextPath()%>/view/member/LoginHandler" method="post">
                    <div class="logo">
                        <img src="<%=request.getContextPath()%>/view/index/image/others/mujilogo2.png" alt="">
                    </div>
                    <h2>會員登入頁面</h2>
                    
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
                        <div class="row row-cols-1 row-cols-sm-1 row-cols-md-2">
                            <div class="form-floating ">
                                <input type="account" class="form-control" name="mem_account" id="floatingAccount" placeholder="name@example.com" onkeyup="value=value.replace(/[^\a-\z\A-\Z0-9\@\.]/g,'')">
                                <label for="floatingAccount">帳號</label>
                            </div>
                            <div class="form-floating ">
                                <input type="password" class="form-control" name="mem_password" id="floatingConfirm" placeholder="Password">
                                <label for="floatingConfirm">密碼</label>
                            </div>
                        </div>
                    </div>
                    <div class="announcement">
                    	<input type="hidden" name="mem_account" value="${memVO.mem_account}">
                    	<input type="hidden" name="action" value="login">
                        <button type="button" id="btn_primary" class="btn btn-primary">登入</button>
                       	<a class="forget" href="<%=request.getContextPath()%>/view/index/forget.jsp">
                       		<button type="button" id="btn_secondary" class="btn btn-secondary">忘記密碼</button>
                       	</a>                        	
                        <p>請注意 MUMI MOVIE 吾映良影 </p>
                        <p>不會以任何理由要求您轉帳匯款</p>
                        <p>嚴防詐騙 人人有責</p>
                        <hr>
                        <a class="loginlink" href="<%=request.getContextPath()%>/view/mem/mem_register.jsp">還沒有帳號?點此前往加入會員</a>
                    </div>
                </form>
            </div>
        </div>
    </main>

    <!-- 主要工作區結束 -->

    <!-- 頁尾 -->
<%@ include file="footer.jsp" %>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <!-- JS檔連結記得修改 -->
    <script src="<%=request.getContextPath()%>/view/index/js/login.js"></script>
     <script>
     var btn_login_data = document.querySelector("#btn_primary");
     var account_data = document.querySelector("#floatingAccount");
     btn_login_data.addEventListener("click", function () {
      var data_obj = {
       login_mail: account_data.value
      };                 
      console.log(data_obj);
      sessionStorage.setItem("login_data", JSON.stringify(data_obj));
     });
    
    </script>
</body>

</html>