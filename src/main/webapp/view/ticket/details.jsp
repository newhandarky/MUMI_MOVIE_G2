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
    <!-- 網頁標題記得修改 -->
    <title>電影場次日期選擇頁面</title>
    <!-- css檔連結記得修改 -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/view/ticket/css/details.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>

<%@ include file="../index/header.jsp" %>

<!-- 主要工作區 -->

    <main>
        <div class="mumipay">
            <img src="<%=request.getContextPath()%>/view/ticket/images/others/mujilogo2.png" alt="">
            <form action="">

                <div class="container">
                    <div class="row">
                        <div class="col-12">
                            <h2 class="title">您所選擇的電影是 : 咒術迴戰</h2>
                            <h3>請選擇日期與場次</h3>
                        </div>
                        <div class="col-md-6 col-sm-12">
                            <img src="<%=request.getContextPath()%>/view/mem/DBGifReader4?mem_id=12346" alt="">
                        </div>

                        <div class="col-md-6 col-sm-12">
                            <div class="col-12 forspan">
                                <span class="day1 -on" value="1">5/20</span>
                                <span class="day2" value="2">5/21</span>
                                <span class="day3" value="3">5/22</span>
                                <span class="day4" value="4">5/23</span>
                                <span class="day5" value="5">5/24</span>
                                <span class="day6" value="6">5/25</span>
                                <span class="day7" value="7">5/26</span>
                            </div>

                            <hr>

                            <h4 class="hallname">A廳 4DX豪華座椅</h4>

                            <hr>

                            <h4 class="hallname">請選擇場次</h4>

                            <div class="col-12 movieall">

                                <div class="movieday -on">
                                    <button class="showtime" value="1">17:30</button>
                                    <button class="showtime" value="2">20:30</button>
                                </div>
                                <div class="movieday">
                                    <button class="showtime" value="1">08:30</button>
                                    <button class="showtime" value="2">11:30</button>
                                    <button class="showtime" value="3">14:30</button>
                                    <button class="showtime" value="4">17:30</button>
                                    <button class="showtime" value="5">20:30</button>
                                </div>
                                <div class="movieday">
                                    <button class="showtime" value="1">08:30</button>
                                    <button class="showtime" value="2">11:30</button>
                                    <button class="showtime" value="3">14:30</button>
                                    <button class="showtime" value="4">17:30</button>
                                    <button class="showtime" value="5">20:30</button>
                                </div>
                                <div class="movieday">
                                    <button class="showtime" value="1">08:30</button>
                                    <button class="showtime" value="2">11:30</button>
                                    <button class="showtime" value="3">14:30</button>
                                    <button class="showtime" value="4">17:30</button>
                                    <button class="showtime" value="5">20:30</button>
                                </div>
                                <div class="movieday">
                                    <button class="showtime" value="1">08:30</button>
                                    <button class="showtime" value="2">11:30</button>
                                    <button class="showtime" value="3">14:30</button>
                                    <button class="showtime" value="4">17:30</button>
                                    <button class="showtime" value="5">20:30</button>
                                </div>
                                <div class="movieday">
                                    <button class="showtime" value="1">08:30</button>
                                    <button class="showtime" value="2">11:30</button>
                                    <button class="showtime" value="3">14:30</button>
                                    <button class="showtime" value="4">17:30</button>
                                    <button class="showtime" value="5">20:30</button>
                                </div>
                                <div class="movieday">
                                    <button class="showtime" value="1">08:30</button>
                                    <button class="showtime" value="2">11:30</button>
                                    <button class="showtime" value="3">14:30</button>
                                </div>

                            </div>

                            <hr>

                            <select name="the_select" class="form-select" aria-label="Default select example">
                                <option value="0" selected>請選擇張數</option>
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
    <script src="<%=request.getContextPath()%>/view/ticket/js/details.js"></script>
</body>

</html>