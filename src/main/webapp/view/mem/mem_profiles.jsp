<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="web.member.entity.*"%>
<%@ page import="web.member.dao.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.text.SimpleDateFormat"%>

<%
	MemVO memVO = (MemVO) session.getAttribute("memVO");
	SimpleDateFormat sdf =   new SimpleDateFormat("yyyy年MM月dd日");
	String str = sdf.format(memVO.getMem_birthday());
%>

<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>會員資料總覽</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/view/mem/css/mem_profiles.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
</head>
<%@ include file="/view/index/header.jsp"%>
<!-- 主要工作區 -->
<main>
	<div class="register">
		<h2>會員資料總覽</h2>
		<div class="logo">
			<img src="DBGifReader4?mem_id=${memVO.mem_id}">
		</div>
		<div class="container">
			<div class="row">

				<div class="col-3">
					<p>會員帳號 :</p>
				</div>
				<div class="col-8">
					<p>${memVO.getMem_account()}</p>
				</div>
				
				<div class="col-3">
					<p>會員姓名 :</p>
				</div>
				<div class="col-8">
					<p>${memVO.getMem_name()}</p>
				</div>
				
				<div class="col-3">
					<p>會員暱稱 :</p>
				</div>
				<div class="col-8">
					<p>${memVO.getMem_nickname()}</p>
				</div>
				
				<div class="col-3">
					<p>聯絡電話 :</p>
				</div>
				<div class="col-8">
					<p>${memVO.getMem_phone()}</p>
				</div>
				
				<div class="col-3">
					<p>會員生日 :</p>
				</div>
				<div class="col-8">
					<p>${memVO.getMem_birthday()}</p>
				</div>
				
				<div class="col-3">
                    <p>會員性別 : </p>
                </div>
				<div class="col-8">
					<p class="${memVO.getMem_gender()}" id="mem_gender"></p>
				</div>

				<div class="col-3 ">
					<p>帳號密碼 :</p>
				</div>
				<div class="col-8">
					<p id="password_off" class="mem_password -on">********</p>
					<p id="password_on"class="mem_password">${memVO.getMem_password()}</p>
				</div>
				<div class="col-1 ">
					<img class="icon_eye" id="icon_eye" src="<%=request.getContextPath()%>/view/mem/image/icons/eye.png">
				</div>
				
				<div class="col-3">
					<p>會員地址 :</p>
				</div>
				<div class="col-8">
					<p>${memVO.getMem_address()}</p>
				</div>
			</div>
		</div>
		<div class="announcement">
			<a class="alist"
				href="<%=request.getContextPath()%>/view/index/index.jsp">
				<button type="button" class="btn btn-secondary" id="cancel">返回首頁</button>
			</a> 
			<a class="alist"
				href="<%=request.getContextPath()%>/view/mem/mem_revise.jsp">  
				<button type="button" class="btn btn-primary" id="change">修改資料</button>
			</a> 
		</div>
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
<script src="<%=request.getContextPath()%>/view/mem/js/mem_profiles.js"></script>
</body>
</html>