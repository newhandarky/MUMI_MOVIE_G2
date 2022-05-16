<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="web.member.entity.*"%>
<%@ page import="java.sql.*"%>

<%
	MemVO memVO = (MemVO) request.getAttribute("memVO");	
%>   

<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>會員註冊頁面</title>
    <!-- css檔連結記得修改 -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/view/mem/css/mem_register.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>

<%@ include file="/view/index/header.jsp" %>
    <!-- 主要工作區 -->

    <main>
        <div class="register">
             <div class="logo">
                 <img src="<%=request.getContextPath()%>/view/mem/image/others/mujilogo2.png" alt="">
             </div>
             
             <h2>會員註冊頁面</h2>
             
            <%-- 錯誤表列 --%>
			<c:if test="${not empty errorMsgs}">
				<font style="color:red">請修正以下錯誤:</font>
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li style="list-style:none">
							<p style="color:red">${message}</p>  
						</li>
					</c:forEach>
				</ul>
			</c:if>
             
             
       	  	 <form METHOD="post" ACTION="<%=request.getContextPath()%>/view/mem/MemServlet">
	              <div class="container">
	                  <div class="row row-cols-1 row-cols-sm-1 row-cols-md-2">
	                      <div class="form-floating ">
	                          <input type="email" class="form-control" id="floatingMail" name="mem_account" placeholder="電子信箱" value="<%= (memVO==null)? "" : memVO.getMem_account()%>">
	                          <label for="floatingMail">電子信箱(帳號)</label>
	                      </div>
	                      <div class="form-floating ">
	                          <input type="text" class="form-control" id="floatingUserName" name="mem_name" placeholder="姓名" value="<%= (memVO==null)? "" : memVO.getMem_name()%>">
	                          <label for="floatingUserName">姓名</label>
	                      </div>
	                      <div class="form-floating ">
	                          <input type="tel" class="form-control" id="floatingPhone" name="mem_phone" placeholder="連絡電話" onkeyup="value=value.replace(/[^0-9]/g,'')" maxlength="10" value="<%= (memVO==null)? "" : memVO.getMem_phone()%>">
	                          <label for="floatingPhone">連絡電話</label>
	                      </div>
	                      <div class="form-floating ">
	                          <input type="text" class="form-control" id="floatingNickName" name="mem_nickname" placeholder="暱稱" value="<%= (memVO==null)? "" : memVO.getMem_nickname()%>">
	                          <label for="floatingNickName">暱稱</label>
	                      </div>
	                      <div class="form-floating ">
	                          <input type="password" class="form-control" id="floatingPassword" name="mem_password" placeholder="Password" value="<%= (memVO==null)? "" : memVO.getMem_password()%>">
	                          <label for="floatingPassword">帳號密碼</label>
	                      </div>
	                      <div class="form-floating ">
	                          <input type="password" class="form-control" id="floatingConfirmPassword" placeholder="ConfirmPassword" name="ConfirmPassword">
	                          <label for="floatingConfirmPassword">確認密碼</label>
	                          <input type="hidden" name="mem_register"></input>
	                      </div>
	                  </div>
	              </div>
	              <div class="announcement">
	              	  <input type="hidden" name="action" value="insert">
	                  <button type="submit" class="btn btn-primary">註冊會員</button>
	
	
	                  <p>請注意 MUMI MOVIE 吾映良影 </p>
	                  <p>不會以任何理由要求您轉帳匯款</p>
	                  <p>嚴防詐騙 人人有責</p>
	                  <hr>
	                  <a class="loginlink" href="login.html">已經擁有帳號? 點擊這邊回登入頁面</a>
	              </div>
         	</form>
     	</div>


    </main>

    <!-- 主要工作區結束 -->


    <!-- 頁尾 -->
    <%@ include file="/view/index/footer.jsp" %>
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <!-- JS檔連結記得修改 -->
    <script src="<%=request.getContextPath()%>/view/mem/js/mem_register.js"></script>
</body>

</html>