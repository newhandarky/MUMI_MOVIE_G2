<%@page import="antlr.StringUtils"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="web.expect.entity.*"%>
<%@ page import="web.movie_tag.entity.*"%>
<%@ page import="web.movie_type.entity.*"%>
<%@ page import="web.movie.entity.*"%>
<%@ page import="web.movie_type.service.*"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page import="java.sql.Timestamp"%>
<%@ taglib prefix="fn" 
           uri="http://java.sun.com/jsp/jstl/functions" %>

<%

MovieVO movieVO = (MovieVO) request.getAttribute("movieVO");
List<String> typelist = (List<String>) request.getAttribute("typelist");
String str = movieVO.getTrailer();
String str2 = str.substring(32);
String str3 = "https://www.youtube.com/embed/"+ str2;

pageContext.setAttribute("typelist", typelist);
pageContext.setAttribute("movieVO", movieVO);
pageContext.setAttribute("str3", str3);
%>




<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
        integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/view/Movie_expectview/movie_expectview.css">

    <style>
        body {
            background-image: url(<%=request.getContextPath()%>/view/Movie_expectview/expectBGI.png);
        }

        a:hover {
            color: red;
            text-decoration: none;
        }

        .container {
            padding-top: 30px;
            margin-bottom: 10px;
        }

        .h1 {
            font-weight: bold;
            font-size: 30px;
            display: inline-block;
            padding-top: 30px;
        }

        .h2 {
            background-color: #537895;
            margin-left: 40px;
            font-size: 25px;
            border-radius: 30px;
            display: inline-block;
        }

        .h3 {
            margin-left: 50px;
            font-size: 25px;
            font-weight: bold;
            border-radius: 30px;
            display: inline-block;

        }

        .h4 {
            font-size: 20px;
            counter-reset: lightgray;
        }

        .return {
            background-color: #537895;
            margin-left: 80%;
            margin-top: 110px;
            font-size: 25px;
            border-radius: 30px;
            display: inline-block;

        }
    </style>

    <script src="https://apis.google.com/js/client.js?onload=onClientLoad"></script>
    <script src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
</head>

<body>
     <!-- 頁首 -->
    <header>
        <!-- 背景亮度按鈕 -->
        <div id="lightbtn">
            <!-- 搜尋欄位 -->
            <input id="search" type="text">
            <a id="a_loupe" href="#">
                <img id="loupe" src="./IMAGE/icons/loupe.png" alt="">
            </a>
            <button type="button" class="btn btn-success btn-ln" id="btn-light">Light
                <span>
                    <img id="sun" class="sunmoon" src="./IMAGE/icons/sun.png" alt="">
                </span>
            </button>
            <button type="button" class="btn btn-dark btn-ln -off" id="btn-dark">Dark
                <span>
                    <img id="moon" class="sunmoon -off" src="./IMAGE/icons/crescent-moon.png" alt="">
                </span>
            </button>
        </div>

        <!-- 功能導覽列 -->
        <nav id="navi">


            <div class="nav-item">

                <ul class="nav_ul" id="movie_ul">
                    <a href="#">電影資訊</a>
                    <li class="nav_li">
                        <a href="#">現正熱映</a>
                    </li>
                    <li class="nav_li">
                        <a href="#">即將上映</a>
                    </li>
                    <li class="nav_li">
                        <a href="#">二輪上映</a>
                    </li>
                    <li class="nav_li">
                        <a href="#">歷史上映</a>
                    </li>
                </ul>
            </div>

            <div class="nav-item">

                <ul class="nav_ul" id="ticket_ul">
                    <a href="#">訂票系統</a>
                    <li class="nav_li">
                        <a href="#">快速購票</a>
                    </li>
                    <li class="nav_li">
                        <a href="#">預售票</a>
                    </li>
                    <li class="nav_li">
                        <a href="#">讓票</a>
                    </li>
                    <li class="nav_li">
                        <a href="#">確認劃位</a>
                    </li>
                </ul>
            </div>

            <div class="nav-item">

                <ul class="nav_ul" id="forum_ul">
                    <a href="#">討論區</a>

                    <!--    有需要再新增 
                        <li class="nav_li">
                        <a href="#">討論區1</a>
                    
                    -->
                </ul>
            </div>

            <div class="nav-item">

                <ul class="nav_ul" id="goods_ul">
                    <a href="#">電影商城</a>
                    <li class="nav_li">
                        <a href="#">餐飲類別</a>
                    </li>
                    <li class="nav_li">
                        <a href="#">周邊商品</a>
                    </li>
                </ul>
            </div>

            <div class="nav-item">

                <ul class="nav_ul" id="member_ul">
                    <a href="#">會員登入</a>
                    <li class="nav_li">
                        <a href="#">會員登入</a>
                    </li>
                    <li class="nav_li">
                        <a href="#">註冊會員</a>
                    </li>
                    <li class="nav_li">
                        <a href="#">修改資料</a>
                    </li>
                    <li class="nav_li">
                        <a href="#">歷史消費</a>
                    </li>
                    <li class="nav_li">
                        <a href="#">會員登出</a>
                    </li>
                </ul>
            </div>
        </nav>

    </header>

    Main content
    <div>
        <span class="return"><a href="<%=request.getContextPath() %>/view/Movie_overview/movie_overview_soon.jsp"
                style="text-decoration: none;color: black; font-weight: bold;;">&nbsp&nbsp返回即將上映&nbsp&nbsp</a>
        </span>
    </div>
    <div class="container">
        <div class="row">
            <div class="col-md-2">
                <img src="<%=request.getContextPath() %>/view/movie/DBGifReader?movie_id=${movieVO.movie_id}" width="500px" alt="">
            </div>
            <div class="col-md-3">

            </div>
            <div class="col-md-6 info">
                <span class="h1">${movieVO.movie_ch}</span>
                <span class="h2"><a href=""
                        style="text-decoration: none;color: black; font-weight: bold;">&nbsp&nbsp前往討論區&nbsp&nbsp</a>
                </span>
                <span class="h3">期待度87%</span><br>
                <span class="h4">${movieVO.movie_en}</span><br><br>

                <span style="font-weight: bold;font-size: 25px;margin-right: 40px;">級別</span><img src="<%=request.getContextPath() %>/view/movie_rating/DBGifReader?movie_rating_id=${movieVO.movie_rating_id}"
                    width="45px" alt=""><br><br>
                <span style="font-weight: bold;font-size: 25px;margin-right: 30px;">片長</span><span
                    style="font-size: 25px;font-weight: bold;color:gray;">${movieVO.movie_runtime}分</span><br><br>
                <span style="font-weight: bold;font-size: 25px;margin-right: 30px;">上映日</span><span
                    style="font-size: 25px;font-weight: bold;color:gray;">${movieVO.release_date}</span><br><br>
                <span style="font-weight: bold;font-size: 25px;margin-right: 30px;">類型</span><span
                    style="font-size: 25px;font-weight: bold;color:gray;">${typelist}</span><br><br>
                <span style="font-weight: bold;font-size: 25px;margin-right: 30px;">演員</span><span
                    style="font-size: 25px;font-weight: bold;color:gray;">${movieVO.casts}</span><br><br>
                <span style="font-weight: bold;font-size: 25px;margin-right: 30px;">導演</span><span
                    style="font-size: 25px;font-weight: bold;color:gray;">${movieVO.director}</span><br><br>
                <span style="font-weight: bold;font-size: 25px;margin-right: 30px;">簡介</span>
                <div style="font-size: 25px;font-weight: bold;color:gray;">${movieVO.movie_intro}</div><br><br><br>
                <span style="font-weight:bold; font-size:40px ">電影預告</span>
            </div>
                <iframe width="560" height="600" src="${str3}"  title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>

        </div>
    </div>



    <!-- 頁尾 -->
    <footer>
        <div class="container">
            <div class="row row-cols-2">
                <div class="col col-md-3 col-sm-6">
                    <h5>服務</h5>
                    <ul>
                        <li>
                            <a href="#">電影導覽</a>
                        </li>
                        <li>
                            <a href="#">快速訂票</a>
                        </li>
                        <li>
                            <a href="#">討論區</a>
                        </li>
                        <li>
                            <a href="#">周邊商城</a>
                        </li>
                    </ul>
                </div>
                <div class="col col-md-3 col-sm-6">
                    <h5>幫助</h5>
                    <ul>
                        <li>
                            <a href="#">導覽</a>
                        </li>
                        <li>
                            <a href="#">FAQ</a>
                        </li>
                    </ul>
                </div>
                <div class="col col-md-3 col-sm-6" id="ft_icons">
                    <h5>關注我們</h5>
                    <a href="#">
                        <span>
                            <img src="./IMAGE/icons/facebook.png" alt="">
                        </span>
                    </a>
                    <a href="#">
                        <span>
                            <img src="./IMAGE/icons/instagram.png" alt="">
                        </span>
                    </a>
                    <a href="#">
                        <span>
                            <img src="./IMAGE/icons/twitter-sign.png" alt="">
                        </span>
                    </a>
                    <a href="#">
                        <span>
                            <img src="./IMAGE/icons/youtube.png" alt="">
                        </span>
                    </a>
                </div>
                <div class="col col-md-3 col-sm-6">
                    <h5>加入好友</h5>
                    <ul>
                        <li>
                            <span>
                                <img id="linerobot" src="./IMAGE/others/robot.png" alt="">
                            </span>
                        </li>
                    </ul>
                </div>

            </div>
        </div>
        <div class="copyright">
            Copyright © 2022 TGA101第二組 Co. 保留所有權利。
            <a href="#">隱私政策</a>
            <a href="#">使用條款</a>
        </div>
    </footer>
	
</body>

</html>