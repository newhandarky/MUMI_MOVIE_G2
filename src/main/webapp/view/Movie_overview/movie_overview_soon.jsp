<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="web.movie.dao.*"%>
<%@ page import="web.movie.entity.*"%>
<%@ page import="web.movie.service.*"%>
<%@ page import="web.expect.service.*"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page import="java.sql.Timestamp"%>
<%@page import="org.hibernate.Session"%>
<%@page import="core.util.HibernateUtil"%>


<%


ExpectService ESC = new ExpectService(HibernateUtil.getSessionFactory());
MovieService movieSvc = new MovieService();
List<MovieVO> list = movieSvc.getByState_id(2);
List<MovieVO> list2 = new ArrayList();
for(MovieVO movieVO :list){
	int expect = 0;
	int total = ESC.findExceptTotal(movieVO.getMovie_id());
	int liketotal = ESC.findLikeTotal(movieVO.getMovie_id());
	if(total == 0){
		movieVO.setExpect("尚未評分");
		list2.add(movieVO);
	}else{
		expect = Math.round(liketotal / total *100);
		String str666= "期待度："+ expect + "%";
		movieVO.setExpect(str666);
		list2.add(movieVO);

	}
}
pageContext.setAttribute("list2", list2);


%>


<!DOCTYPE html>
<html lang="zh">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <!-- css檔連結記得修改 -->
    <link rel="stylesheet" href="<%=request.getContextPath() %>/view/Movie_overview/movie_overview_now.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.9.0/slick.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.9.0/slick-theme.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css">
     <style>
	
	body{
	background-image: url("<%=request.getContextPath() %>/view/Movie_overview/MUMI.png");

	}
    </style>
</head>

<%@ include file="/view/index/header.jsp" %>

    <!-- 主要工作區 -->

    <main>




        <div class="container">

            <div class="row">
                <div class="col-10 text-left ">
                    <div class="input-group mb-3">

                        <button id="returnAll"class="btn btn-outline-secondary" type="button">返回電影資訊</button>
                    </div>
                </div>
            </div>
        </div>
        



        <div class="jumbotron">
            <div class="container">
                <h1 class="title">即將上映</h1>
            </div>
        </div>

        <hr>

        <div class="row row-cols-1 row-cols-md-3 g-4">
        	<c:forEach var="movieVO" items="${list2}">
            <div class="col">
                <div class="card h-100">
                    <img src="<%=request.getContextPath() %>/view/movie/DBGifReader?movie_id=${movieVO.movie_id}" class="card-img-top">
                    <div class="card-body">
                        <div class="title_info">
                            <h5 class="card-title title_name">${movieVO.movie_ch}</h5>
                            <img src="./IMAGE/posters/12+.png" alt="" class="level">
                        </div>
                        <h5 class="card-title">${movieVO.movie_en}</h5>
                       
                    </div>
                    <div class="card-footer">
                        <div class="stars">
                            <p class="star">${movieVO.expect}</p>
<!--                             <i class="fa fa-star" aria-hidden="true"></i> -->
                            <form method="post" action="<%=request.getContextPath() %>/MovieOverViewServlet">
                            	<button type="submit" name="movie_id" value="${movieVO.movie_id}" class="btn_info">電影資訊介紹</button>
                            	<input type="hidden" name="soon" value="soon">
                            </form>
                        </div>
                       
                    </div>
                </div>
            </div>
            </c:forEach>

        </div>

    </main>

    <!-- 主要工作區結束 -->


    <!-- 頁尾 -->
    <%@ include file="/view/index/footer.jsp" %>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <!-- JS檔連結記得修改 -->
    <script src="<%=request.getContextPath() %>/view/Movie_overview/movie_overview_now.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.9.0/slick.js"></script>
        <script>
    $("#returnAll").on('click',function(){
        location.href='<%=request.getContextPath()%>/view/Movie_overview/movie_overview_all.jsp';

    })
    </script>
</body>

</html>