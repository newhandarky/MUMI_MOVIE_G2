<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="web.info.entity.*"%>
<%@ page import="web.info.service.*"%>
<%@ page import="java.util.*"%>

<%
InfoService infoSvc = new InfoService();
List<InfoVO> list = infoSvc.getAllPublished();
pageContext.setAttribute("list", list);
%>


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

<%@ include file="header.jsp" %>

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
             <c:forEach var="infoVO" items="${list}" >
            <div class="container">           
                <div class="row">
                    <div class="col-md-5 col-sm-12">             
                    	<img class="showpic" src="<%=request.getContextPath()%>/view/info/DBGifReader4?info_id=${infoVO.info_id}">
<%--                         <img id="infopic" src="<%=request.getContextPath()%>/view/info/DBGifReader4?info_id=1" alt="">	 --%>                        
<!--                      <h4>【禁止盜錄公告及詳情】</h4> -->
<!--                         <p class="showinfo">影廳內請勿錄影、照相、錄音。 為避免觸法，從預告片開始到片尾工作人員表結束，燈亮起之前，皆不可拍攝銀幕。 任何未經授權之攝錄行為，已觸犯著作權第91條，最高可處5年有期徒刑。</p>       -->
                    </div>
                    <div class="col-6 info_p">
						<h2 class="showtitle">【${infoVO.info_title}】<br><br></h2>
                        <h3 class="showinfo">${infoVO.info_des}</h3>
                    </div>
                </div>
            </div>
			</c:forEach>
    </div>
    <h1 id="map_h1">影城位置</h1>
    	<div class="map_rwd">
        <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3614.4676870452963!2d121.54106421395167!3d25.052132843720578!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3442abe6b0446815%3A0xf006dde8c27afcc7!2z57ev6IKyVGliYU1l6ZmE6Kit5Y-w5YyX6IG36KiT5Lit5b-D!5e0!3m2!1szh-TW!2stw!4v1647743247630!5m2!1szh-TW!2stw"
            width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy"></iframe>
    	</div>
			<img class="showpic" src="<%=request.getContextPath()%>/view/index/image/others/安心觀影-1920x1080-2.jpeg">		
        </div>
   

<%@ include file="footer.jsp" %>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/vue@2.5.17/dist/vue.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="<%=request.getContextPath()%>/view/index/js/index.js"></script>
</body>

</html>