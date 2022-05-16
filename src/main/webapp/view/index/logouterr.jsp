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
    <link rel="stylesheet" href="<%=request.getContextPath()%>/view/index/css/logouterr.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>

<body>
    <main>
        <div class="loginarea">
            <div class="register">
                <div class="logo">
                    <img src="<%=request.getContextPath()%>/view/index/image/others/mujilogo2.png" alt="">
                </div>
                <h2>系統訊息</h2>
                <div class="container">
                    <p class="topp">您好您尚未登入喔</p>
                    <p>
                        <a href="<%=request.getContextPath()%>/view/index/login.jsp">
                            點此進入登入頁面
                        </a>
                    </p>
                    <p class="underp">
                        或是
                        <a href="#" id="goback">返回</a> 先前頁面
                    </p>

                </div>
            </div>
        </div>

    </main>

    <!-- 主要工作區結束 -->

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <!-- JS檔連結記得修改 -->
    <script src="<%=request.getContextPath()%>/view/index/js/logouterr.js"></script>
</body>

</html>