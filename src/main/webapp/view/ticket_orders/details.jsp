<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="web.member.entity.*"%>
<%@ page import="web.member.dao.*"%>
<%@ page import="web.movie.entity.*"%>
<%@ page import="web.movie.dao.*"%>
<%@ page import="web.movie.service.*"%>

<%
	MovieService movieSvc = new MovieService();
	MovieVO movieVO = movieSvc.getOneMovie(2);
%>   

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- 網頁標題記得修改 -->
    <title>電影場次日期選擇頁面</title>
    <!-- css檔連結記得修改 -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/view/ticket_orders/css/details.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>

<%@ include file="../index/header.jsp" %>

<!-- 主要工作區 -->

    <main>
        <div class="mumipay">
            <img src="image/others/mujilogored.png" alt="">
            <form action="">

                <div class="container">
                    <div class="row">
                        <div class="col-12">
                            <h2 class="title">您所選擇的電影是 : 咒術迴戰</h2>
                            <h3>請選擇日期與場次</h3>
                        </div>
                        <div class="col-md-6 col-sm-12">
                            <img src="image/moviestand/1825.jpg" alt="">
                        </div>

                        <div class="col-md-6 col-sm-12">
                            <div class="col-12 forspan">
                                <span class="day1">5/20</span>
                                <button class="showtime">17:30</button>
                                <button class="showtime">20:30</button>

                                <hr>

                                <span class="day2">5/21</span>
                                <button class="showtime">17:30</button>
                                <button class="showtime">20:30</button>

                                <hr>

                                <span class="day3">5/22</span>
                                <button class="showtime">08:30</button>
                                <button class="showtime">11:30</button>
                                <button class="showtime">14:30</button>
                                <button class="showtime">17:30</button>
                                <button class="showtime">20:30</button>

                                <hr>

                                <span class="day4">5/23</span>
                                <button class="showtime">08:30</button>
                                <button class="showtime">11:30</button>
                                <button class="showtime">14:30</button>
                                <button class="showtime">17:30</button>
                                <button class="showtime">20:30</button>

                                <hr>

                                <span class="day5">5/24</span>
                                <button class="showtime">08:30</button>
                                <button class="showtime">11:30</button>
                                <button class="showtime">14:30</button>
                                <button class="showtime">17:30</button>
                                <button class="showtime">20:30</button>

                                <hr>

                                <span class="day6">5/25</span>
                                <button class="showtime">08:30</button>
                                <button class="showtime">11:30</button>
                                <button class="showtime">14:30</button>
                                <button class="showtime">17:30</button>
                                <button class="showtime">20:30</button>

                                <hr>

                                <span class="day7">5/26</span>
                                <button class="showtime">08:30</button>
                                <button class="showtime">11:30</button>
                                <button class="showtime">14:30</button>

                            </div>

                        </div>
                        <div class="col-2"></div>
                        <div class="col-8">
                            <select class="form-select" aria-label="Default select example">
                                <option selected>請選擇張數</option>
                                <option value="1">1</option>
                                <option value="2">2</option>
                                <option value="3">3</option>
                                <option value="4">4</option>
                                <option value="5">5</option>
                            </select>
                        </div>

                    </div>
                </div>

                <div class="container">
                    <div class="row">
                        <div class="col-2">

                        </div>
                        <div class="col-4">

                            <button type="button" class="btn btn-primary" id="btn_primary">前往劃位</button>
                        </div>
                        <div class="col-4">
                            <a href="mem_shopping sheet.html">
                                <button type="button" class="btn btn-secondary">取消返回</button>
                            </a>
                        </div>
                    </div>
                </div>
            </form>

        </div>

    </main>

    <!-- 主要工作區結束 -->
    
    <%@ include file="../index/footer.jsp" %>
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <script src="<%=request.getContextPath()%>/view/ticket_orders/js/details.js"></script>
</body>

</html>