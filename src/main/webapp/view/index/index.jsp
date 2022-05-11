<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MUMI MOVIE 吾映良影影城首頁</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/view/index/css/index.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" 
    	  rel="stylesheet" 
    	  integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" 
    	  crossorigin="anonymous">
</head>

<body>
    <header>
        <!-- 專題LOGO -->
        <a class="link_index" href="#">
            <img class="team_logo" src="<%=request.getContextPath()%>/view/index/image/others/mujilogo.png" alt="">
        </a>
        <!-- 背景亮度按鈕 -->
        <div id="lightbtn">
            <!-- 搜尋欄位 -->
            <input id="search" type="text">
            <a id="a_loupe" href="#">
                <img id="loupe" src="<%=request.getContextPath()%>/view/index/image/icons/loupe.png" alt="">
            </a>
            <button type="button" class="btn btn-success btn-ln" id="btn-light">Light
                <span>
                    <img id="sun" class="sunmoon" src="<%=request.getContextPath()%>/view/index/image/icons/sun.png" alt="">
                </span>
            </button>
            <button type="button" class="btn btn-dark btn-ln -off" id="btn-dark">Dark
                <span>
                    <img id="moon" class="sunmoon -off" src="<%=request.getContextPath()%>/view/index/image/icons/crescent-moon.png" alt="">
                </span>
            </button>
        </div>
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
                        <a href="hell_seat_4dxA.html">確認劃位</a>
                    </li>
                    <li>
                        <a href="">${memVO.getMem_nickname()}</a>
                    </li>  
                </ul>
            </div>

            <div class="nav-item">
                <ul class="nav_ul" id="forum_ul">
                    <a href="#">討論區</a>
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
                    <a href="#">會員專區</a>
                    <li class="nav_li">
                        <a href="<%=request.getContextPath()%>/view/index/login.jsp">會員登入</a>
                    </li>
                    <li class="nav_li">
                        <a href="<%=request.getContextPath()%>/view/mem/mem_register.jsp">註冊會員</a>
                    </li>
                    <li class="nav_li">
                        <a href="<%=request.getContextPath()%>/view/mem/mem_revise.jsp">修改資料</a>
                    </li>
                    <li class="nav_li">
                        <a href="mem_shopping_sheet.html">歷史消費</a>
                    </li>
                    <li class="nav_li">
                        <a href="mem_mumipay.html" class="mumipay">MUMIPAY</a>
                    </li>
                    <li class="nav_li">
                        <a href="#">會員登出</a>
                    </li>
                </ul>
            </div>
        </nav>

        <!-- 漢堡選單按鈕 -->
        <div class="">
            <a href="#"><img src="<%=request.getContextPath()%>/view/index/image/icons/movie.png" alt="" class="hamberger_menu"></a>
        </div>

    </header>
    <!-- 空白top 100px -->
    <div class="underheader"></div>

    <!-- 漢堡側邊攔位 -->
    <div class="hide_menu -bye">
        <div class="accordion" id="accordionExample">
            <div class="accordion-item">
                <h2 class="accordion-header" id="headingOne">
                    <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                        會員專區
                    </button>
                </h2>
                <div id="collapseOne" class="accordion-collapse collapse" aria-labelledby="headingOne" data-bs-parent="#accordionExample">
                    <div class="accordion-body">
                        <ul>
                            <li>
                                <a href="<%=request.getContextPath()%>/view/index/login.jsp">會員登入</a>
                            </li>
                            <li>
                                <a href="<%=request.getContextPath()%>/view/mem/mem_register.jsp">註冊會員</a>
                            </li>
                            <li>
                                <a href="<%=request.getContextPath()%>/view/mem/mem_revise.jsp">修改資料</a>
                            </li>
                            <li>
                                <a href="mem_shopping_sheet.html">歷史消費</a>
                            </li>
                            <li class="nav_li">
                                <a href="mem_mumipay.html">MUMIPAY</a>
                            </li>
                            <li>
                                <a href="#">會員登出</a>
                            </li>
                        </ul>

                    </div>
                </div>
            </div>
            <div class="accordion-item">
                <h2 class="accordion-header" id="headingTwo">
                    <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                        電影資訊
                    </button>
                </h2>
                <div id="collapseTwo" class="accordion-collapse collapse" aria-labelledby="headingTwo" data-bs-parent="#accordionExample">
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
                    <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseFour" aria-expanded="false" aria-controls="collapseFour">
                        訂票系統
                    </button>
                </h2>
                <div id="collapseFour" class="accordion-collapse collapse" aria-labelledby="headingFour" data-bs-parent="#accordionExample">
                    <div class="accordion-body">
                        <ul>
                            <li>
                                <a href="">快速購票</a>
                            </li>
                            <li>
                                <a href="">預售票</a>
                            </li>
                            <li>
                                <a href="hell_seat_4dxA.html">確認劃位</a>
                            </li>
                        </ul>

                    </div>
                </div>
            </div>
            <div class="accordion-item">
                <h2 class="accordion-header" id="headingFive">
                    <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseFive" aria-expanded="false" aria-controls="collapseFive">
                        討論區
                    </button>
                </h2>
                <div id="collapseFive" class="accordion-collapse collapse" aria-labelledby="headingFive" data-bs-parent="#accordionExample">
                    <div class="accordion-body">
                        <ul>
<!--                             <li> -->
<%--                                 <a href="">${memVO.getMem_nickname()}</a> --%>
<!--                             </li>                              -->
                        </ul>

                    </div>
                </div>
            </div>
            <div class="accordion-item">
                <h2 class="accordion-header" id="headingThree">
                    <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                        電影商城
                    </button>
                </h2>
                <div id="collapseThree" class="accordion-collapse collapse" aria-labelledby="headingThree" data-bs-parent="#accordionExample">
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

    <!-- 電影輪播 -->
    <div id="picouter">
        <div id="app">

            <div id="carouselExampleIndicators" class="carousel slide" data-bs-ride="carousel">
                <div class="carousel-indicators">
                    <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
                    <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1" aria-label="Slide 2"></button>
                    <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2" aria-label="Slide 3"></button>
                    <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="3" aria-label="Slide 4"></button>
                    <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="4" aria-label="Slide 5"></button>
                </div>
                <div class="carousel-inner">
                    <div class="carousel-item active">
                        <img src="<%=request.getContextPath()%>/view/index/image/carousel/JUJUTSU KAISEN ZERO_Poster_TW_1450x608.jpg" class="d-block w-100" alt="...">
                    </div>
                    <div class="carousel-item">
                        <img src="<%=request.getContextPath()%>/view/index/image/carousel/60fe5d79c7bfb570c4b41fffb1468e3c.jpg" class="d-block w-100" alt="...">
                    </div>
                    <div class="carousel-item">
                        <img src="<%=request.getContextPath()%>/view/index/image/carousel/3d24f9cb36fb47d89b4be3bc1614ea2a.jpg" class="d-block w-100" alt="...">
                    </div>
                    <div class="carousel-item">
                        <img src="<%=request.getContextPath()%>/view/index/image/carousel/8ad709b813e49f42da3809a494f1f0fc.jpg" class="d-block w-100" alt="...">
                    </div>
                    <div class="carousel-item">
                        <img src="<%=request.getContextPath()%>/view/index/image/carousel/27ca24800b7dbfbbe67f6279deb4bb89.jpg" class="d-block w-100" alt="...">
                    </div>
                </div>
                <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Previous</span>
                </button>
                <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Next</span>
                </button>
            </div>
        </div>
    </div>

    <div class="picouter">

        <!-- 現正熱映 / 即將上映 -->

        <div id="movies" class="container">
            <button type="button" class="btn btn-secondary" id="btn_now">現正熱映</button>
            <button type="button" class="btn btn-secondary" id="btn_cs">即將上映</button>
            <div class="row" id="div_now">
                <div class="col col-lg-4 col-xl-2">
                    <div class="card" style="width: 10rem;">
                        <img src="<%=request.getContextPath()%>/view/index/image/moviestand/1825.jpg" class="card-img-top" alt="...">
                        <div class="card-body">
                            <p class="card-text">咒術迴戰<img src="<%=request.getContextPath()%>/view/index/image/icons/mrs_15.png" class="mrs" alt=""></p>
                        </div>
                    </div>
                </div>
                <div class="col col-lg-4 col-xl-2">
                    <div class="card" style="width: 10rem;">
                        <img src="<%=request.getContextPath()%>/view/index/image/moviestand/film_20220214007.jpg" class="card-img-top" alt="...">
                        <div class="card-body">
                            <p class="card-text">蝙蝠俠<img src="<%=request.getContextPath()%>/view/index/image/icons/mrs_15.png" class="mrs" alt=""></p>
                        </div>
                    </div>
                </div>
                <div class="col col-lg-4 col-xl-2">
                    <div class="card" style="width: 10rem;">
                        <img src="<%=request.getContextPath()%>/view/index/image/moviestand/GoddamnedAsura_180x270_Poster.jpg" class="card-img-top" alt="...">
                        <div class="card-body">
                            <p class="card-text">阿修羅<img src="<%=request.getContextPath()%>/view/index/image/icons/mrs_18.png" class="mrs" alt=""></p>
                        </div>
                    </div>
                </div>
                <div class="col col-lg-4 col-xl-2">
                    <div class="card" style="width: 10rem;">
                        <img src="<%=request.getContextPath()%>/view/index/image/moviestand/5JoLmuOv0wzXt2DbER0f-1080x1538.jpg" class="card-img-top" alt="...">
                        <div class="card-body">
                            <p class="card-text">月球殞落<img src="<%=request.getContextPath()%>/view/index/image/icons/mrs_6.png" class="mrs" alt=""></p>
                        </div>
                    </div>
                </div>
                <div class="col col-lg-4 col-xl-2">
                    <div class="card" style="width: 10rem;">
                        <img src="<%=request.getContextPath()%>/view/index/image/moviestand/Blacklight_180x270_Poster.jpg" class="card-img-top" alt="...">
                        <div class="card-body">
                            <p class="card-text">黑光行動<img src="<%=request.getContextPath()%>/view/index/image/icons/mrs_15.png" class="mrs" alt=""></p>
                        </div>
                    </div>
                </div>
                <div class="col col-lg-4 col-xl-2">
                    <div class="card" style="width: 10rem;">
                        <img src="<%=request.getContextPath()%>/view/index/image/moviestand/Ambulance_180x270_Poster.jpg" class="card-img-top" alt="...">
                        <div class="card-body">
                            <p class="card-text">劫命救護<img src="<%=request.getContextPath()%>/view/index/image/icons/mrs_18.png" class="mrs" alt=""></p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row -off" id="div_cs">
                <div class="col col-lg-4 col-xl-2">
                    <div class="card" style="width: 10rem;">
                        <img src="<%=request.getContextPath()%>/view/index/image/moviestand/Fortress_Poster_TW (1).jpg" class="card-img-top" alt="...">
                        <div class="card-body">
                            <p class="card-text">終極堡壘<img src="<%=request.getContextPath()%>/view/index/image/icons/mrs_15.png" class="mrs" alt=""></p>
                        </div>
                    </div>
                </div>
                <div class="col col-lg-4 col-xl-2">
                    <div class="card" style="width: 10rem;">
                        <img src="<%=request.getContextPath()%>/view/index/image/moviestand/hideandseekPOSTER.jpg" class="card-img-top" alt="...">
                        <div class="card-body">
                            <p class="card-text">詭迷藏<img src="<%=request.getContextPath()%>/view/index/image/icons/mrs_18.png" class="mrs" alt=""></p>
                        </div>
                    </div>
                </div>
                <div class="col col-lg-4 col-xl-2">
                    <div class="card" style="width: 10rem;">
                        <img src="<%=request.getContextPath()%>/view/index/image/moviestand/IdaRed_180x270_Poster.jpg" class="card-img-top" alt="...">
                        <div class="card-body">
                            <p class="card-text">劫獄救援<img src="<%=request.getContextPath()%>/view/index/image/icons/mrs_15.png" class="mrs" alt=""></p>
                        </div>
                    </div>
                </div>
                <div class="col col-lg-4 col-xl-2">
                    <div class="card" style="width: 10rem;">
                        <img src="<%=request.getContextPath()%>/view/index/image/moviestand/KBW5d0AM3mVAOKbP0PJh-1280x1827.jpg" class="card-img-top" alt="...">
                        <div class="card-body">
                            <p class="card-text">秘境探險<img src="<%=request.getContextPath()%>/view/index/image/icons/mrs_0.png" class="mrs" alt=""></p>
                        </div>
                    </div>
                </div>
                <div class="col col-lg-4 col-xl-2">
                    <div class="card" style="width: 10rem;">
                        <img src="<%=request.getContextPath()%>/view/index/image/moviestand/LostIllusions_180x270_Poster.jpg" class="card-img-top" alt="...">
                        <div class="card-body">
                            <p class="card-text">巴黎夢想家<img src="<%=request.getContextPath()%>/view/index/image/icons/mrs_12.png" class="mrs" alt=""></p>
                        </div>
                    </div>
                </div>
                <div class="col col-lg-4 col-xl-2">
                    <div class="card" style="width: 10rem;">
                        <img src="<%=request.getContextPath()%>/view/index/image/moviestand/sonic.jpg" class="card-img-top" alt="...">
                        <div class="card-body">
                            <p class="card-text">音速小子2<img src="<%=request.getContextPath()%>/view/index/image/icons/mrs_6.png" class="mrs" alt=""></p>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>


    <!-- 主要內容區 -->
    <div id="mainouter" class="mainouter">
        <main class="container" id="mainapp">
            <div class="row" id="app2">

                <div class="col-lg-5 col-md-12">
                    <div class="row">

                        <div id="fastbuy">
                            <h3>快速購票</h3>
                            <div>
                                <img id="movieicon" src="<%=request.getContextPath()%>/view/index/image/icons/movie-camera.png" alt="">
                                <select name="fastmovie" id="moviebar">
                                    <option class="moviebar" value="0" name="fastmovie">請選擇影片</option>
                                    <option class="moviebar" value="1" name="fastmovie">咒術迴戰</option>
                                    <option class="moviebar" value="2" name="fastmovie">蝙蝠俠</option>
                                    <option class="moviebar" value="3" name="fastmovie">阿修羅</option>
                                    <option class="moviebar" value="4" name="fastmovie">超吉任務</option>
                                    <option class="moviebar" value="5" name="fastmovie">黑光行動</option>
                                </select>
                            </div>

                            <div>
                                <img id="dateicon" src="<%=request.getContextPath()%>/view/index/image/icons/deadline.png" alt="">
                                <input id="moviedate" type="date">
                            </div>

                            <div>
                                <img id="timeicon" src="<%=request.getContextPath()%>/view/index/image/icons/back-in-time.png" alt="">
                                <select name="fastmovietime" id="movietimebar">
                                    <option value="1" name="fastmovie">請選擇場次</option>
                                    <option value="2" name="fastmovie">09:30 - 11:30</option>
                                    <option value="3" name="fastmovie">12:00 - 14:00</option>
                                    <option value="4" name="fastmovie">14:30 - 16:30</option>
                                    <option value="5" name="fastmovie">17:00 - 19:00</option>
                                    <option value="6" name="fastmovie">19:30 - 21:30</option>
                                    <option value="6" name="fastmovie">22:00 - 00:00</option>
                                </select>
                            </div>

                            <div>
                                <img id="peopleicon" src="<%=request.getContextPath()%>/view/index/image/icons/group.png" alt="">
                                <select name="fastmoviepeople" id="moviepeoplebar">
                                    <option value="1" name="fastmovie">請選擇人數</option>
                                    <option value="2" name="fastmovie">1</option>
                                    <option value="3" name="fastmovie">2</option>
                                    <option value="4" name="fastmovie">3</option>
                                    <option value="5" name="fastmovie">4</option>
                                    <option value="6" name="fastmovie">5</option>
                                </select>
                            </div>
                            <div id="fastbtn">
                                <span>
                                    <a href="#">前往訂票</a>
                                </span>
                                <span>
                                    <a href="#">查看資訊</a>
                                </span>
                            </div>
                        </div>
                    </div>
                </div>

                <div class='embed-container col-lg-7' id="youtube_in">
                    <div class="embed-in">
                        <iframe src='https://www.youtube.com/embed//39qGk7E6OuY' frameborder='0' allowfullscreen></iframe>
                    </div>
                </div>

            </div>
        </main>

        <!-- 公告 -->
        <div class="info">

            <div id="info">
                <h1 id="info_h1">影城公告</h1>
            </div>
            <div class="container">
                <div class="row">
                    <div class="col-md-5 col-sm-12">
                        <img id="infopic" src="<%=request.getContextPath()%>/view/index/image/others/f_26003004_1.jpg" alt="">
                    </div>
                    <div class="col-6 info_p">
                        <h4>【禁止盜錄公告及詳情】</h4>
                        <p class="showinfo">影廳內請勿錄影、照相、錄音。 為避免觸法，從預告片開始到片尾工作人員表結束，燈亮起之前，皆不可拍攝銀幕。 任何未經授權之攝錄行為，已觸犯著作權第91條，最高可處5年有期徒刑。
                        </p>
                    </div>
                </div>
            </div>

            <div class="container">
                <div class="row">
                    <div class="col-6 info_p">
                        <h5 class="firstinfo showinfo">MUMI MOVIE 吾映良影絕對不會主動致電要求貴賓操作ATM</h5>
                        <h4 class="redinfo showinfo">「解除分期 / 補繳金額」</h4>
                        <h4 class="redinfo showinfo">或其他任何事項</h4>
                        <h3 class="showinfo">請貴賓提高警覺避免受騙!!</h3>
                    </div>
                    <div class="col-md-5 col-sm-12">
                        <img id="infopic2" src="<%=request.getContextPath()%>/view/index/image/others/sagi2.png" alt="">
                    </div>
                </div>
            </div>
        </div>
    </div>
    <h1 id="map_h1">影城位置</h1>
    <div class="map_rwd">
        <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3614.4676870452963!2d121.54106421395167!3d25.052132843720578!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3442abe6b0446815%3A0xf006dde8c27afcc7!2z57ev6IKyVGliYU1l6ZmE6Kit5Y-w5YyX6IG36KiT5Lit5b-D!5e0!3m2!1szh-TW!2stw!4v1647743247630!5m2!1szh-TW!2stw"
            width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy"></iframe>
    </div>


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
                <div class="col col-md-3 col-sm-6">
                    <h5>關注我們</h5>
                    <span class="focus_us">
                        <a href="https://drive.google.com/drive/folders/1FvkVquqoKRgwpjlaPAfuDpjvcCElwwgF?usp=sharing"> 張志鵬</a>
                        <p>會員系統</p>
                    </span>
                    <span class="focus_us">
                        <a href="https://drive.google.com/drive/folders/1Hu3MXljw2mPs8y4uqIq2coDHt4OiEwLV?usp=sharing">郭家榮</a>
                        <p>討論區</p>
                    </span>
                    <span class="focus_us">
                        <a href="https://drive.google.com/drive/folders/143UhfMZr6X_sGmmouBIsc_2DskIW7vHH">蕭仲威</a>
                        <p>電影系統</p>
                    </span>
                    <span class="focus_us">
                        <a href="https://drive.google.com/drive/folders/1sixqrgkXZrUoEDbod4Wi2mygZyp2dl0i?usp=sharing">吳宗哲</a>
                        <p>影廳系統</p>
                    </span>
                    <span class="focus_us">
                        <a href="https://drive.google.com/drive/folders/14u0F868uYrXT8pDRCgaNoy9M3Rep6ji6?usp=sharing">徐浩鈞</a>
                        <p>訂票系統</p>
                    </span>
                    <span class="focus_us">
                        <a href="https://drive.google.com/drive/folders/1dJ3kpYDJpT8bTvJQEKBN8G5JJ5Vr8Xh5">陳鏡</a>
                        <p>電影時刻表</p>
                    </span>
                </div>
                <div class="col col-md-3 col-sm-6">
                    <h5>加入好友</h5>
                    <ul>
                        <li>
                            <span>
                                <img id="linerobot" src="<%=request.getContextPath()%>/view/index/image/others/robot.png" alt="">
                            </span>
                        </li>
                    </ul>
                </div>

            </div>
        </div>
        <div class="copyright">
            Copyright © 2022 MUMI MOVIE 吾映良影 Co. 保留所有權利。
            <a href="#">隱私政策</a>
            <a href="#">使用條款</a>
        </div>
    </footer>


    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/vue@2.5.17/dist/vue.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="<%=request.getContextPath()%>/view/index/js/index.js"></script>
</body>

</html>