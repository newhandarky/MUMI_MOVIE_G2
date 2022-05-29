<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="web.info.entity.*"%>
<%@ page import="web.info.service.*"%>
<%@ page import="java.util.*"%>

<%
InfoService infoSvc = new InfoService();
List<InfoVO> list = infoSvc.getAllPublished();
pageContext.setAttribute("list", list);
%>

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
List<MovieVO> list1 = movieSvc.getByState_id(21);
List<MovieVO> list2 = new ArrayList();

for(MovieVO mvo : list1){
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
// MovieVO vo6 = list2.get(5);



ExpectService ESC = new ExpectService(HibernateUtil.getSessionFactory());
List<MovieVO> slist = movieSvc.getByState_id(22);
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
// MovieVO svo6 = slist2.get(5);
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
                        <img src="<%=request.getContextPath() %>/view/movie/DBGifReader2?movie_id=<%=vo1.getMovie_id()%>" class="d-block w-100" alt="...">
                    </div>
                    <div class="carousel-item">
                        <img src="<%=request.getContextPath() %>/view/movie/DBGifReader2?movie_id=<%=vo2.getMovie_id()%>" class="d-block w-100" alt="...">
                    </div>
                    <div class="carousel-item">
                        <img src="<%=request.getContextPath() %>/view/movie/DBGifReader2?movie_id=<%=vo3.getMovie_id()%>" class="d-block w-100" alt="...">
                    </div>
                    <div class="carousel-item">
                        <img src="<%=request.getContextPath() %>/view/movie/DBGifReader2?movie_id=<%=vo4.getMovie_id()%>" class="d-block w-100" alt="...">
                    </div>
                    <div class="carousel-item">
                        <img src="<%=request.getContextPath() %>/view/movie/DBGifReader2?movie_id=<%=vo5.getMovie_id()%>" class="d-block w-100" alt="...">
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
                        <img src="<%=request.getContextPath() %>/view/movie/DBGifReader?movie_id=<%=vo1.getMovie_id()%>" class="card-img-top" alt="...">
                        <div class="card-body">
                            <p class="card-text"><%=vo1.getMovie_ch()%><img src="<%=request.getContextPath()%>/view/index/image/icons/mrs_15.png" class="mrs" alt=""></p>
                        </div>
                    </div>
                </div>
                <div class="col col-lg-4 col-xl-2">
                    <div class="card" style="width: 10rem;">
                        <img src="<%=request.getContextPath() %>/view/movie/DBGifReader?movie_id=<%=vo2.getMovie_id()%>" class="card-img-top" alt="...">
                        <div class="card-body">
                            <p class="card-text"><%=vo1.getMovie_ch()%><img src="<%=request.getContextPath()%>/view/index/image/icons/mrs_15.png" class="mrs" alt=""></p>
                        </div>
                    </div>
                </div>
                <div class="col col-lg-4 col-xl-2">
                    <div class="card" style="width: 10rem;">
                        <img src="<%=request.getContextPath() %>/view/movie/DBGifReader?movie_id=<%=vo3.getMovie_id()%>" class="card-img-top" alt="...">
                        <div class="card-body">
                            <p class="card-text"><%=vo1.getMovie_ch()%><img src="<%=request.getContextPath()%>/view/index/image/icons/mrs_18.png" class="mrs" alt=""></p>
                        </div>
                    </div>
                </div>
                <div class="col col-lg-4 col-xl-2">
                    <div class="card" style="width: 10rem;">
                        <img src="<%=request.getContextPath() %>/view/movie/DBGifReader?movie_id=<%=vo4.getMovie_id()%>" class="card-img-top" alt="...">
                        <div class="card-body">
                            <p class="card-text"><%=vo1.getMovie_ch()%><img src="<%=request.getContextPath()%>/view/index/image/icons/mrs_6.png" class="mrs" alt=""></p>
                        </div>
                    </div>
                </div>
                <div class="col col-lg-4 col-xl-2">
                    <div class="card" style="width: 10rem;">
                        <img src="<%=request.getContextPath() %>/view/movie/DBGifReader?movie_id=<%=vo5.getMovie_id()%>" class="card-img-top" alt="...">
                        <div class="card-body">
                            <p class="card-text"><%=vo5.getMovie_ch()%><img src="<%=request.getContextPath() %>/view/movie_rating/DBGifReader?movie_rating_id=<%=vo5.getMovie_rating_id()%>" class="mrs" alt=""></p>
                        </div>
                    </div>
                </div>
                <div class="col col-lg-4 col-xl-2">
                    <div class="card" style="width: 10rem;">
                        <img src="<%=request.getContextPath() %>/view/movie/DBGifReader?movie_id=<%=vo1.getMovie_id()%>" class="card-img-top" alt="...">
                        <div class="card-body">
                            <p class="card-text"><%=vo1.getMovie_ch()%><img src="<%=request.getContextPath() %>/view/movie_rating/DBGifReader?movie_rating_id=<%=vo1.getMovie_rating_id()%>" class="mrs" alt=""></p>
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
        

        <!-- 公告 -->
        <div class="info">

            <div id="info">
                <h1 id="info_h1">影城公告</h1>
            </div>
            <div class="container">           
                <div class="row">
             <c:forEach var="infoVO" items="${list}" >
                    <div class="col-md-5 col-sm-12">             
                    	<img class="showpic" src="<%=request.getContextPath()%>/view/info/DBGifReader4?info_id=${infoVO.info_id}">
                    </div>
                    <div class="col-6 info_p">
						<h2 class="showtitle">【${infoVO.info_title}】<br><br></h2>
                        <h3 class="showinfo">${infoVO.info_des}</h3>
                    </div>
			</c:forEach>
                </div>
            </div>
		</div> 
    </div>
    
    <div class="container">
        <div class="row">
			<img class="showinfopic" src="<%=request.getContextPath()%>/view/index/image/others/iamangry.jpg">		                
        </div>
    </div>
	        
    <h1 id="map_h1">影城位置</h1>
   	<div class="map_rwd">
        <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3614.4676870452963!2d121.54106421395167!3d25.052132843720578!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3442abe6b0446815%3A0xf006dde8c27afcc7!2z57ev6IKyVGliYU1l6ZmE6Kit5Y-w5YyX6IG36KiT5Lit5b-D!5e0!3m2!1szh-TW!2stw!4v1647743247630!5m2!1szh-TW!2stw"
            width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy"></iframe> 
    </div> 
        
        

<%@ include file="footer.jsp" %>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/vue@2.5.17/dist/vue.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="<%=request.getContextPath()%>/view/index/js/index.js"></script>
</body>

</html>