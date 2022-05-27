<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="web.movie.dao.*"%>
<%@ page import="web.movie.entity.*"%>
<%@ page import="web.movie.service.*"%>
<%@ page import="web.expect.service.*"%>
<%@ page import="web.satisfy.service.*"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page import="java.sql.Timestamp"%>
<%@page import="org.hibernate.Session"%>
<%@page import="core.util.HibernateUtil"%>


<%
MovieService movieSvc = new MovieService();
SatisfyService SSC = new SatisfyService(HibernateUtil.getSessionFactory());
List<MovieVO> list = movieSvc.getByState_id(1);
List<MovieVO> list2 = new ArrayList();

for(MovieVO mvo : list){
	String satisfy = SSC.findSatisyAvg(mvo.getMovie_id());
	if(satisfy == "" || satisfy == null){
		mvo.setSatisfy("尚未評分");
		list2.add(mvo);
	}else{
		mvo.setSatisfy(satisfy);
		list2.add(mvo);
	}
}


MovieVO vo1 = list2.get(0);
MovieVO vo2 = list2.get(1);
MovieVO vo3 = list2.get(2);
MovieVO vo4 = list2.get(3);
MovieVO vo5 = list2.get(4);



ExpectService ESC = new ExpectService(HibernateUtil.getSessionFactory());
List<MovieVO> slist = movieSvc.getByState_id(2);
List<MovieVO> slist2 = new ArrayList();
for(MovieVO movieVO : slist){
	int expect = 0;
	int total = ESC.findExceptTotal(movieVO.getMovie_id());
	int liketotal = ESC.findLikeTotal(movieVO.getMovie_id());
	if(total == 0){
		movieVO.setExpect("尚未評分");
		slist2.add(movieVO);
	}else{
		expect = Math.round(liketotal / total *100);
		String str666= "期待度："+ expect + "%";
		movieVO.setExpect(str666);
		slist2.add(movieVO);

	}
}
MovieVO svo1 = slist2.get(0);
MovieVO svo2 = slist2.get(1);
MovieVO svo3 = slist2.get(2);
MovieVO svo4 = slist2.get(3);
MovieVO svo5 = slist2.get(4);
%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <!-- css檔連結記得修改 -->
    <link rel="stylesheet" href="movie_overview.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.9.0/slick.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.9.0/slick-theme.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css">
    <style>
    @import url(https://fonts.googleapis.com/earlyaccess/cwtexyen.css);
    * {
	font-family: "cwTeXYen", sans-serif;
	font-weight: bold;
	}
	
	body{
	background-image: url("<%=request.getContextPath() %>/view/Movie_overview/MUMI.png");

	}
    </style>
</head>

<body>
    <!-- 頁首 -->
    <header>
        <!-- 專題LOGO -->
        <a href="#">
            <img class="team_logo" src="IMAGE/icons/clapperboard.png" alt="">
        </a>
        <h2>Tibame影城</h2>
        <!-- 背景亮度按鈕 -->
        <div id="lightbtn">
            <!-- 搜尋欄位 -->
            <input id="search" type="text">
            <a id="a_loupe" href="#">
                <img id="loupe" src="IMAGE/icons/loupe.png" alt="">
            </a>
            <button type="button" class="btn btn-success btn-ln" id="btn-light">Light
                <span>
                    <img id="sun" class="sunmoon" src="IMAGE/icons/sun.png" alt="">
                </span>
            </button>
            <button type="button" class="btn btn-dark btn-ln -off" id="btn-dark">Dark
                <span>
                    <img id="moon" class="sunmoon -off" src="IMAGE/icons/crescent-moon.png" alt="">
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
                    <a href="#">討論區</a>

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

        <!-- 漢堡選單按鈕 -->
        <div class="">
            <a href="#"><img src="IMAGE/icons/movie.png" alt="" class="hamberger_menu"></a>
        </div>

    </header>

    <!-- 空白top 100px -->
    <div class="underheader"></div>

    <!-- 漢堡側邊攔位 -->
    <div class="hide_menu -bye">
        <div class="accordion" id="accordionExample">
            <div class="accordion-item">
                <h2 class="accordion-header" id="headingOne">
                    <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                        data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                        會員專區
                    </button>
                </h2>
                <div id="collapseOne" class="accordion-collapse collapse" aria-labelledby="headingOne"
                    data-bs-parent="#accordionExample">
                    <div class="accordion-body">
                        <ul>
                            <li>
                                <a href="">會員登入</a>
                            </li>
                            <li>
                                <a href="">註冊會員</a>
                            </li>
                            <li>
                                <a href="">修改資料</a>
                            </li>
                            <li>
                                <a href="">歷史消費</a>
                            </li>
                            <li>
                                <a href="">會員登出</a>
                            </li>
                        </ul>

                    </div>
                </div>
            </div>
            <div class="accordion-item">
                <h2 class="accordion-header" id="headingTwo">
                    <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                        data-bs-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                        電影資訊
                    </button>
                </h2>
                <div id="collapseTwo" class="accordion-collapse collapse" aria-labelledby="headingTwo"
                    data-bs-parent="#accordionExample">
                    <div class="accordion-body">
                        <ul>
                            <li>
                                <a href="">現正熱映</a>
                            </li>
                            <li>
                                <a href="">即將上映</a>
                            </li>
                            <li>
                                <a href="">二輪上映</a>
                            </li>
                            <li>
                                <a href="">歷史上映</a>
                            </li>
                        </ul>

                    </div>
                </div>
            </div>
            <div class="accordion-item">
                <h2 class="accordion-header" id="headingFour">
                    <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                        data-bs-target="#collapseFour" aria-expanded="false" aria-controls="collapseFour">
                        訂票系統
                    </button>
                </h2>
                <div id="collapseFour" class="accordion-collapse collapse" aria-labelledby="headingFour"
                    data-bs-parent="#accordionExample">
                    <div class="accordion-body">
                        <ul>
                            <li>
                                <a href="">快速購票</a>
                            </li>
                            <li>
                                <a href="">預售票</a>
                            </li>
                            <li>
                                <a href="">讓票</a>
                            </li>
                            <li>
                                <a href="">確認劃位</a>
                            </li>
                        </ul>

                    </div>
                </div>
            </div>
            <div class="accordion-item">
                <h2 class="accordion-header" id="headingFive">
                    <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                        data-bs-target="#collapseFive" aria-expanded="false" aria-controls="collapseFive">
                        討論區
                    </button>
                </h2>
                <div id="collapseFive" class="accordion-collapse collapse" aria-labelledby="headingFive"
                    data-bs-parent="#accordionExample">
                    <div class="accordion-body">
                        <ul>
                            <!-- <li>
                                <a href=""></a>
                            </li>                             -->
                        </ul>

                    </div>
                </div>
            </div>
            <div class="accordion-item">
                <h2 class="accordion-header" id="headingThree">
                    <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                        data-bs-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                        電影商城
                    </button>
                </h2>
                <div id="collapseThree" class="accordion-collapse collapse" aria-labelledby="headingThree"
                    data-bs-parent="#accordionExample">
                    <div class="accordion-body">
                        <ul>
                            <li>
                                <a href="">餐飲類別</a>
                            </li>
                            <li>
                                <a href="">周邊商品</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>



    <!-- 主要工作區 -->

    <main>






        <div class="jumbotron">
            <div class="container">
                <h1 class="title">現正熱映</h1>
                <div class="d-grid gap-2 d-flex justify-content-end">
                    <button id="moreNow" type="button" class="btn btn-outline-primary">更多現正熱映電影</button>
                </div>
            </div>
        </div>

        <hr>

        <div class="jumbotron">

            <div class="container">
                <div class="container1">
                    <div class="movie_info">
                        <img src="<%=request.getContextPath() %>/view/movie/DBGifReader?movie_id=<%=vo1.getMovie_id()%>" alt="" class="img_poster">
                        <div class="title_info">
                            <h5 class="title_name"><%=vo1.getMovie_ch()%></h5>
                            <img src="<%=request.getContextPath() %>/view/movie_rating/DBGifReader?movie_rating_id=<%=vo1.getMovie_rating_id()%>"  class="level">
                        </div>
                        <p class="en_title"><%=vo1.getMovie_en()%></p>
                        <div class="stars">
                            <p class="star"><%=vo1.getSatisfy()%></p>
                            <i class="fa fa-star" aria-hidden="true"></i>
                            <form method="post" action="<%=request.getContextPath() %>/MovieOverViewServlet">
                            	<button type="submit" name="movie_id" value="<%=vo1.getMovie_id()%>" class="btn_info">電影資訊介紹</button>
                            	<input type="hidden" name="now" value="now">
                            </form>
                        </div>

                    </div>
                    <div class="movie_info">
                        <img src="<%=request.getContextPath() %>/view/movie/DBGifReader?movie_id=<%=vo2.getMovie_id()%>" alt="" class="img_poster">
                        <div class="title_info">
                            <h5 class="title_name"><%=vo2.getMovie_ch()%></h5>
                            <img src="<%=request.getContextPath() %>/view/movie_rating/DBGifReader?movie_rating_id=<%=vo2.getMovie_rating_id()%>" alt="" class="level">
                        </div>
                        <p class="en_title"><%=vo2.getMovie_en()%></p>
                        <div class="stars">
                            <p class="star"><%=vo2.getSatisfy()%></p>
                            <i class="fa fa-star" aria-hidden="true"></i>
                             <form method="post" action="<%=request.getContextPath() %>/MovieOverViewServlet">
                            	<button type="submit" name="movie_id" value="<%=vo2.getMovie_id()%>" class="btn_info">電影資訊介紹</button>
                            	<input type="hidden" name="now" value="now">
                            </form>
                        </div>
                    </div>
                    <div class="movie_info">
                        <img src="<%=request.getContextPath() %>/view/movie/DBGifReader?movie_id=<%=vo3.getMovie_id()%>" alt="" class="img_poster">
                        <div class="title_info">
                            <h5 class="title_name"><%=vo3.getMovie_ch()%></h5>
                            <img src="<%=request.getContextPath() %>/view/movie_rating/DBGifReader?movie_rating_id=<%=vo3.getMovie_rating_id()%>" alt="" class="level">
                        </div>
                        <p class="en_title"><%=vo3.getMovie_en()%></p>
                        <div class="stars">
                            <p class="star"><%=vo3.getSatisfy()%></p>
                            <i class="fa fa-star" aria-hidden="true"></i>
                             <form method="post" action="<%=request.getContextPath() %>/MovieOverViewServlet">
                            	<button type="submit" name="movie_id" value="<%=vo3.getMovie_id()%>" class="btn_info">電影資訊介紹</button>
                            	<input type="hidden" name="now" value="now">
                            </form>
                        </div>
                    </div>
                    <div class="movie_info">
                        <img src="<%=request.getContextPath() %>/view/movie/DBGifReader?movie_id=<%=vo4.getMovie_id()%>" alt="" class="img_poster">
                        <div class="title_info">
                            <h5 class="title_name"><%=vo4.getMovie_ch()%></h5>
                            <img src="<%=request.getContextPath() %>/view/movie_rating/DBGifReader?movie_rating_id=<%=vo4.getMovie_rating_id()%>" alt="" class="level">
                        </div>
                        <p class="en_title"><%=vo4.getMovie_en()%></p>
                        <div class="stars">
                            <p class="star"><%=vo4.getSatisfy()%></p>
                            <i class="fa fa-star" aria-hidden="true"></i>
                             <form method="post" action="<%=request.getContextPath() %>/MovieOverViewServlet">
                            	<button type="submit" name="movie_id" value="<%=vo4.getMovie_id()%>" class="btn_info">電影資訊介紹</button>
                            	<input type="hidden" name="now" value="now">
                            </form>
                        </div>
                    </div>
                    <div class="movie_info">
                        <img src="<%=request.getContextPath() %>/view/movie/DBGifReader?movie_id=<%=vo5.getMovie_id()%>" alt="" class="img_poster">
                        <div class="title_info">
                            <h5 class="title_name"><%=vo5.getMovie_ch()%></h5>
                            <img src="<%=request.getContextPath() %>/view/movie_rating/DBGifReader?movie_rating_id=<%=vo5.getMovie_rating_id()%>" alt="" class="level">
                        </div>
                        <p class="en_title"><%=vo5.getMovie_en()%></p>
                        <div class="stars">
                            <p class="star"><%=vo5.getSatisfy()%></p>
                            <i class="fa fa-star" aria-hidden="true"></i>
                             <form method="post" action="<%=request.getContextPath() %>/MovieOverViewServlet">
                            	<button type="submit" name="movie_id" value="<%=vo5.getMovie_id()%>" class="btn_info">電影資訊介紹</button>
                            	<input type="hidden" name="now" value="now">
                            </form>
                        </div>
                    </div>
                </div>



            </div>
        </div>




        <div class="jumbotron">
            <div class="container">
                <h1 class="title">即將上映</h1>
                <div class="d-grid gap-2 d-flex justify-content-end">
                    <button id="moreHistory" type="button" class="btn btn-outline-primary">更多即將上映電影</button>
                </div>
            </div>
        </div>

        <hr>


        <div class="jumbotron">

            <div class="container">
                <div class="container1">
                    <div class="movie_info">
                        <img src="<%=request.getContextPath() %>/view/movie/DBGifReader?movie_id=<%=svo1.getMovie_id()%>" alt="" class="img_poster">
                        <div class="title_info">
                            <h5 class="title_name"><%=svo1.getMovie_ch()%></h5>
                            <img src="<%=request.getContextPath() %>/view/movie_rating/DBGifReader?movie_rating_id=<%=svo1.getMovie_rating_id()%>" alt="" class="level">
                        </div>
                        <p class="en_title"><%=svo1.getMovie_en()%></p>
                        <div class="stars">
                            <p class="star"><%=svo1.getExpect()%></p>
<!--                             <i class="fa fa-star" aria-hidden="true"></i> -->
                            <form method="post" action="<%=request.getContextPath() %>/MovieOverViewServlet">
                            	<button type="submit" name="movie_id" value="<%=svo1.getMovie_id()%>" class="btn_info">電影資訊介紹</button>
                            	<input type="hidden" name="soon" value="soon">
                            </form>
                        </div>

                    </div>
                    <div class="movie_info">
                        <img src="<%=request.getContextPath() %>/view/movie/DBGifReader?movie_id=<%=svo2.getMovie_id()%>" alt="" class="img_poster">
                        <div class="title_info">
                            <h5 class="title_name"><%=svo2.getMovie_ch()%></h5>
                            <img src="<%=request.getContextPath() %>/view/movie_rating/DBGifReader?movie_rating_id=<%=svo2.getMovie_rating_id()%>" alt="" class="level">
                        </div>
                        <p class="en_title"><%=svo2.getMovie_en()%></p>
                        <div class="stars">
                            <p class="star"><%=svo2.getExpect()%></p>
<!--                             <i class="fa fa-star" aria-hidden="true"></i> -->
                            <form method="post" action="<%=request.getContextPath() %>/MovieOverViewServlet">
                            	<button type="submit" name="movie_id" value="<%=svo2.getMovie_id()%>" class="btn_info">電影資訊介紹</button>
                            	<input type="hidden" name="soon" value="soon">
                            </form>
                        </div>
                    </div>
                    <div class="movie_info">
                        <img src="<%=request.getContextPath() %>/view/movie/DBGifReader?movie_id=<%=svo3.getMovie_id()%>" alt="" class="img_poster">
                        <div class="title_info">
                            <h5 class="title_name"><%=svo3.getMovie_ch()%></h5>
                            <img src="<%=request.getContextPath() %>/view/movie_rating/DBGifReader?movie_rating_id=<%=svo3.getMovie_rating_id()%>" alt="" class="level">
                        </div>
                        <p class="en_title"><%=svo3.getMovie_en()%></p>
                        <div class="stars">
                            <p class="star"><%=svo3.getExpect()%></p>
<!--                             <i class="fa fa-star" aria-hidden="true"></i> -->
                            <form method="post" action="<%=request.getContextPath() %>/MovieOverViewServlet">
                            	<button type="submit" name="movie_id" value="<%=svo3.getMovie_id()%>" class="btn_info">電影資訊介紹</button>
                            	<input type="hidden" name="soon" value="soon">
                            </form>
                        </div>
                    </div>
                    <div class="movie_info">
                        <img src="<%=request.getContextPath() %>/view/movie/DBGifReader?movie_id=<%=svo4.getMovie_id()%>" alt="" class="img_poster">
                        <div class="title_info">
                            <h5 class="title_name"><%=svo4.getMovie_ch()%></h5>
                            <img src="<%=request.getContextPath() %>/view/movie_rating/DBGifReader?movie_rating_id=<%=svo4.getMovie_rating_id()%>" alt="" class="level">
                        </div>
                        <p class="en_title"><%=svo4.getMovie_en()%></p>
                        <div class="stars">
                            <p class="star"><%=svo4.getExpect()%></p>
<!--                             <i class="fa fa-star" aria-hidden="true"></i> -->
                            <form method="post" action="<%=request.getContextPath() %>/MovieOverViewServlet">
                            	<button type="submit" name="movie_id" value="<%=svo4.getMovie_id()%>" class="btn_info">電影資訊介紹</button>
                            	<input type="hidden" name="soon" value="soon">
                            </form>
                        </div>
                    </div>
                    <div class="movie_info">
                        <img src="<%=request.getContextPath() %>/view/movie/DBGifReader?movie_id=<%=svo5.getMovie_id()%>" alt="" class="img_poster">
                        <div class="title_info">
                            <h5 class="title_name"><%=svo5.getMovie_ch()%></h5>
                            <img src="./IMAGE/posters/12+.png" alt="" class="level">
                        </div>
                        <p class="en_title"><%=svo5.getMovie_en()%></p>
                        <div class="stars">
                            <p class="star"><%=svo5.getExpect()%></p>
<!--                             <i class="fa fa-star" aria-hidden="true"></i> -->
                            <form method="post" action="<%=request.getContextPath() %>/MovieOverViewServlet">
                            	<button type="submit" name="movie_id" value="<%=svo5.getMovie_id()%>" class="btn_info">電影資訊介紹</button>
                            	<input type="hidden" name="soon" value="soon">
                            </form>
                        </div>
                    </div>
                </div>



            </div>
        </div>
























    </main>

    <!-- 主要工作區結束 -->


    <!-- 頁尾 -->
    <footer>
        <div class="container">
            <div class="row row-cols-2">
                <div class="col col-md-3 col-sm-6">
                    <h5>服務</h5>
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
                            <a href="#">周邊商城</a>
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
                    <h5>關注我們</h5>
                    <a href="#">
                        <span>
                            <img src="IMAGE/icons/facebook.png" alt="">
                        </span>
                    </a>
                    <a href="#">
                        <span>
                            <img src="IMAGE/icons/instagram.png" alt="">
                        </span>
                    </a>
                    <a href="#">
                        <span>
                            <img src="IMAGE/icons/twitter-sign.png" alt="">
                        </span>
                    </a>
                    <a href="#">
                        <span>
                            <img src="IMAGE/icons/youtube.png" alt="">
                        </span>
                    </a>
                </div>
                <div class="col col-md-3 col-sm-6">
                    <h5>加入好友</h5>
                    <ul>
                        <li>
                            <span>
                                <img id="linerobot" src="IMAGE/others/robot.png" alt="">
                            </span>
                        </li>
                    </ul>
                </div>

            </div>
        </div>
        <div class="copyright">
            Copyright © 2022 TGA101第二組 Co. 保留所有權利。
            <a href="#">隱私政策</a>
            <a href="#">使用條款</a>
        </div>
    </footer>


    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <!-- JS檔連結記得修改 -->
    <script src="<%=request.getContextPath() %>/view/Movie_overview/movie_overview.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.9.0/slick.js"></script>
    <script>
    $("#moreNow").on('click',function(){
        location.href='<%=request.getContextPath() %>/view/Movie_overview/movie_overview_now.jsp';

    })
    
    $("#moreHistory").on('click',function(){
        location.href='<%=request.getContextPath() %>/view/Movie_overview/movie_overview_soon.jsp';

    })
    
    
    </script>
</body>

</html>