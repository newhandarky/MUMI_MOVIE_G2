<%@page import="antlr.StringUtils"%>
<%@page import="org.hibernate.Session"%>
<%@page import="core.util.HibernateUtil"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="web.expect.entity.*"%>
<%@ page import="web.movie_tag.entity.*"%>
<%@ page import="web.movie_type.entity.*"%>
<%@ page import="web.movie.entity.*"%>
<%@ page import="web.member.entity.*"%>
<%@ page import="web.satisfy.entity.*"%>
<%@ page import="web.movie_type.service.*"%>
<%@ page import="web.satisfy.service.*"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page import="java.sql.Timestamp"%>
<%@ taglib prefix="fn" 
           uri="http://java.sun.com/jsp/jstl/functions" %>

<%

MovieVO movieVO = (MovieVO) request.getAttribute("movieVO");
SatisfyService ESC = new SatisfyService(HibernateUtil.getSessionFactory());
String satisfy = ESC.findSatisyAvg(movieVO.getMovie_id());
if(satisfy == "" || satisfy == null){
	movieVO.setSatisfy("尚未評分");
}else{
 movieVO.setSatisfy(satisfy);
}
MemVO mvo;
try{mvo = (MemVO)session.getAttribute("memVO");
}catch(Exception e){
	mvo = null;
}

if(mvo != null){
	SatisfyBean sb = ESC.findMovieAndSatisfyByID(mvo.getMem_id(), movieVO.getMovie_id());
	pageContext.setAttribute("check", 1);
	if(sb != null){
		pageContext.setAttribute("check2", 1);
		pageContext.setAttribute("memVO",mvo);
	}else{
		pageContext.setAttribute("check2", 0);
		pageContext.setAttribute("memVO",mvo);
	}
		
}else{
	pageContext.setAttribute("check", 0);
	pageContext.setAttribute("check2", 0);

}







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
     <link rel="stylesheet" href="<%=request.getContextPath() %>/view/Movie_overview/movie_overview_now.css">

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
    <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</head>

<body>
    <!-- 頁首 -->
    <%@ include file="/view/index/header.jsp" %>

    <!-- Main content -->
    <div>
        <span class="return"><a href="<%=request.getContextPath() %>/view/Movie_overview/movie_overview_now.jsp"
                style="text-decoration: none;color: black; font-weight: bold;;">&nbsp&nbsp返回現正熱映&nbsp&nbsp</a>
        </span>
    </div>
    <div class="container">
        <div class="row">
            <div class="col-md-5 task_list">
                <div>

                    <img src="<%=request.getContextPath() %>/view/movie/DBGifReader?movie_id=${movieVO.movie_id}" width="500px" alt="">

                </div>

                <div class="sati">
                    <div class="sati-text">
                        滿意度
                    </div>

                    <div class="star_block">
                        <span class="star" data-star="1"><i class="fas fa-star fa-2x"></i></span>
                        <span class="star" data-star="2"><i class="fas fa-star fa-2x"></i></span>
                        <span class="star" data-star="3"><i class="fas fa-star fa-2x"></i></span>
                        <span class="star" data-star="4"><i class="fas fa-star fa-2x"></i></span>
                        <span class="star" data-star="5"><i class="fas fa-star fa-2x"></i></span>
                    </div>
                    <div class="star-submit">
                        <form method="get" action="" name="form1" class="many-star">
                            <button id="sabtn" type="button" class="btn btn-outline-dark btn-sm" >送出評分</button>
                            <input type="hidden" name="satisfy" class="satisfy">
                        </form>
                    </div>
                </div>




            </div>

            <div class="col-md-7 info">
                <span class="h1">${movieVO.movie_ch}</span>
                <span class="h2"><a href=""
                        style="text-decoration: none;color: black; font-weight: bold;">&nbsp&nbsp前往討論區&nbsp&nbsp</a>
                </span>
                <span class="h3">滿意度：${movieVO.satisfy}<img width="50px" src="<%=request.getContextPath() %>/view/Moive_satisfyview/star.jpg"></span><br>
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
            </div>
                <span style="text-align: center;font-weight:bold; font-size:40px ">電影預告</span>
            <iframe width="560" height="600" src="${str3}"  title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
        </div>
    </div>



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
            Copyright © 2022 TGA101第二組 Co. 保留所有權利。
            <a href="#">隱私政策</a>
            <a href="#">使用條款</a>
        </div>
    </footer>




    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/js/all.min.js"></script>
    <script src="<%=request.getContextPath() %>/view/Moive_satisfyview/movie_satisfy_view.js"></script>
	<script>
	var current_star;
	$("div.star_block").on("click", "span.star", function(e){
	    current_star = parseInt($(this).attr("data-star"));
	    $("form.many-star").children("input.satisfy").attr("value", current_star);
	    $(this).closest("div.star_block").find("span.star").each(function(i, item){
	        
	      if( parseInt($(this).attr("data-star")) <= current_star ){
	        $(this).addClass("-on");
	      }else{
	        $(this).removeClass("-on");
	      }

	      
	    });
	  
	  });
	
	
	
	
	var check = ${check};
	var check2 = ${check2};
	var satisfy = current_star;
	console.log(satisfy);
	$("#sabtn").on('click',function(){
        if( check == null || check == 0){
            Swal.fire({
            	  icon: 'error',
            	  title: '啊啦啦',
            	  text: '看來您還沒有登入會員!',
        })}else if( check2 != 0){
             Swal.fire({
            	  icon: 'error',
            	  title: '哎呀呀',
            	  text: '您已經評分過啦!',

        })}else{
			Swal.fire(
					  '成功送出!',
					  '您的評分已經送出!',
					  'success'
					)
			var xhr = new XMLHttpRequest();
			var jsonOBJ = {
					movie_id:`${movieVO.movie_id}`,
					mem_id:`${memVO.mem_id}`,
					satisfy: current_star
				}
			xhr.open("post", "<%=request.getContextPath()%>/SatisfyServlet", true); //post 告知後端
			xhr.setRequestHeader("Content-type", "application/json"); //告訴後端是用 JSON 格式
			var data = JSON.stringify(jsonOBJ); //將物件資料轉成字串
			xhr.send(data); //送出字串
			$(this).attr("disabled","true"); 
			
		}

    })
	
	</script>
</body>

</html>