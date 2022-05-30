<%@page import="org.hibernate.Session"%>
<%@page import="core.util.HibernateUtil"%>
<%@page import="antlr.StringUtils"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="web.expect.entity.*"%>
<%@ page import="web.expect.service.*"%>
<%@ page import="web.movie_tag.entity.*"%>
<%@ page import="web.movie_type.entity.*"%>
<%@ page import="web.movie.entity.*"%>
<%@ page import="web.member.entity.*"%>
<%@ page import="web.movie_type.service.*"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page import="java.sql.Timestamp"%>

<%@ taglib prefix="fn" 
           uri="http://java.sun.com/jsp/jstl/functions" %>

<%
MemVO mvo;
MovieVO movieVO = (MovieVO) request.getAttribute("movieVO");
ExpectService ESC = new ExpectService(HibernateUtil.getSessionFactory());
try{mvo = (MemVO)session.getAttribute("memVO");
}catch(Exception e){
	mvo = null;
}

if(mvo != null){
	ExpectBean eb = ESC.findMovieAndExpectByID(mvo.getMem_id(), movieVO.getMovie_id());
	pageContext.setAttribute("check", 1);
	if(eb != null){
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
int expect = 0;
int total = ESC.findExceptTotal(movieVO.getMovie_id());
int liketotal = ESC.findLikeTotal(movieVO.getMovie_id());
if(total == 0){

	pageContext.setAttribute("expect", "尚未評分");
	
}else{
expect = Math.round(liketotal / total *100);
String str666= "期待度："+ expect + "%";
pageContext.setAttribute("expect", str666);
}
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
      <link rel="stylesheet" href="<%=request.getContextPath()%>/view/Movie_overview/movie_overview.css">
    

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

<%@ include file="/view/index/header.jsp" %>

    <div>
        <span class="return"><a href="<%=request.getContextPath() %>/view/Movie_overview/movie_overview_soon.jsp"
                style="text-decoration: none;color: black; font-weight: bold;;">&nbsp&nbsp返回即將上映&nbsp&nbsp</a>
        </span>
    </div>
    <div class="container">
        <div class="row">
            <div class="col-md-5">
                <img src="<%=request.getContextPath() %>/view/movie/DBGifReader?movie_id=${movieVO.movie_id}" width="500px" alt="">
                <form id ="likeForm" action="">
                <img width="50px" style="margin-left:140px; " src="<%=request.getContextPath()%>/view/Movie_expectview/like.png"><button  id="like" class="btn btn-success" type="button" >我覺得可以!</button>
                <input type="hidden" name="like" value="like">               
                <input type="hidden" name="mem_id" value="${memVO.mem_id}">               
                <input type="hidden" name="movie_id" value="${movieVO.movie_id}">               
                </form>
                <form id="dislikeForm" action="">
                <img width="50px" style="margin-left:140px; "  src="<%=request.getContextPath()%>/view/Movie_expectview/dislike.png" ><button id="dislike" class="btn btn-danger" type="button">我覺得不行!</button>
                <input type="hidden" name="dislike" value="dislike">               
                <input type="hidden" name="mem_id" value="${memVO.mem_id}">               
                <input type="hidden" name="movie_id" value="${movieVO.movie_id}">               
                </form>
            </div>
       
            <div class="col-md-7 info">
                <span class="h1">${movieVO.movie_ch}</span>
                <span class="h2"><a href="<%=request.getContextPath()%>/view/forum_article/ForumIndex.html"
                        style="text-decoration: none;color: black; font-weight: bold;">&nbsp&nbsp前往討論區&nbsp&nbsp</a>
                </span>
                <span class="h3"><%=pageContext.getAttribute("expect")%></span><br>
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


<%@ include file="/view/index/footer.jsp" %>
<script src="<%=request.getContextPath() %>/view/Movie_overview/movie_overview.js"></script>
	<script>
	var check = ${check};
	var check2 = ${check2};
	$("#like").on('click',function(){
		console.log("點到啦")
        if( check == null || check == 0){
            Swal.fire({
            	  icon: 'error',
            	  title: '啊啦啦',
            	  text: '看來您還沒有登入會員!',
        })}else if( check2 != 0){
             Swal.fire({
            	  icon: 'error',
            	  title: '哎呀呀',
            	  text: '您已經評價過啦!',

        })}else{
			Swal.fire(
					  '成功送出!',
					  '看來您真的覺得很可以!',
					  'success'
					)
// 			$("#likeFrom").submit();
			var xhr = new XMLHttpRequest();
			var jsonOBJ = {
					movie_id:`${movieVO.movie_id}`,
					mem_id:`${memVO.mem_id}`,
					expect:`1`
				}
			xhr.open("post", "<%=request.getContextPath()%>/ExpectServlet", true); //post 告知後端
			xhr.setRequestHeader("Content-type", "application/json"); //告訴後端是用 JSON 格式
			var data = JSON.stringify(jsonOBJ); //將物件資料轉成字串
			xhr.send(data); //送出字串
			$(this).attr("disabled","true"); 
			
		}

    })
	$("#dislike").on('click',function(){
        if( check == null || check == 0){
            Swal.fire({
            	  icon: 'error',
            	  title: '啊啦啦',
            	  text: '看來您還沒有登入會員!',

        })}
         else if( check2 != 0){
             Swal.fire({
           	  icon: 'error',
           	  title: '哎呀呀',
           	  text: '您已經評價過啦!',

        })}else{
			Swal.fire(
					  '成功送出!',
					  '看來您真的覺得不太行!',
					  'success'
					)
// 			$("#dislikeFrom").submit();
					var xhr = new XMLHttpRequest();
			var jsonOBJ = {
					movie_id:`${movieVO.movie_id}`,
					mem_id:`${memVO.mem_id}`,
					expect:`2`
				}
			xhr.open("post", "<%=request.getContextPath()%>/ExpectServlet", true); //post 告知後端
			xhr.setRequestHeader("Content-type", "application/json"); //告訴後端是用 JSON 格式
			var data = JSON.stringify(jsonOBJ); //將物件資料轉成字串
			xhr.send(data); //送出字串
			$(this).attr("disabled","true"); 
		}		
    })
	
	
	
	</script>
</body>

</html>