<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>吾映良影網站地圖</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/view/index/css/mumi_movie_map.css">
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
		<div class="logo">
            <img src="<%=request.getContextPath()%>/view/index/image/others/mujilogo2.png" alt="">
            <h2>吾映良影網站地圖</h2>
        </div>
		<div class="ermodel">
			<img src="<%=request.getContextPath()%>/view/index/image/others/mumi_map.png">
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
<script src="<%=request.getContextPath()%>/view/index/js/mumi_movie_map.js"></script>
</body>
</html>