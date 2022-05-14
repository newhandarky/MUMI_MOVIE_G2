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
    <title>MUMI MOVIE 吾映良影會員登入頁面</title>
    <!-- css檔連結記得修改 -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/view/index/css/login.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>

<body>
    <!-- 頁首 -->
    <header>
        <!-- 專題LOGO -->
        <a class="link_index" href="<%=request.getContextPath()%>/view/index/index.jsp">
            <img class="team_logo" src="<%=request.getContextPath()%>/view/index/image/others/mujilogo.png" alt="">
        </a>
        <p>${memVO.mem_id}</p>
        
        
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
                        <a href="hell_seat.html">確認劃位</a>
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
                    <a href="#">會員專區</a>
                    <li class="nav_li">
                        <a href="#">會員登入</a>
                    </li>
                    <li class="nav_li">
                        <a href="#">修改資料</a>
                    </li>
                    <li class="nav_li">
                        <a href="mem_shopping_sheet.html">歷史消費</a>
                    </li>
                    <li class="nav_li">
                        <a href="mem_mumipay.html" class="mumipay">MUMIPAY</a>
                    </li>
                    <li class="nav_li">
<!--                 		<a href="/member/Logout">會員登出</a>   -->
                    
                    	<form action="<%=request.getContextPath()%>/view/member/LoginHandler" method="post">                 
<!-- 	                    	<input type="hidden" name="session" value="session"> -->
	                    	<input type="hidden" name="action" value="logout">
	                    	<input type="submit" value="會員登出">
<!-- 		                        <a href="javascript:document.form1.submit();">會員登出</a>   -->
                        </form>   
                        
                    </li>                
                </ul>
            </div>
        </nav>

        <!-- 漢堡選單按鈕 -->
        <div class="">
            <a href="#"><img src="<%=request.getContextPath()%>/view/mem/image/icons/movie.png" alt="" class="hamberger_menu"></a>
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
                                <a href="login.html">會員登入</a>
                            </li>
                            <li>
                                <a href="#">註冊會員</a>
                            </li>
                            <li>
                                <a href="mem_revise.html">修改資料</a>
                            </li>
                            <li>
                                <a href="mem_shopping_sheet.html">歷史消費</a>
                            </li>
                            <li class="nav_li">
                                <a href="mem_mumipay.html">MUMIPAY</a>
                            </li>
                            <li>
                                <a href="login.html">會員登出</a>
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
                                <a href="">確認劃位</a>
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
                            <!-- <li>
                                <a href=""></a>
                            </li>                             -->
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
	


    <!-- 主要工作區 -->

    <main>

        <div class="loginarea">
            <div class="register">
            	<form action="<%=request.getContextPath()%>/view/member/LoginHandler" method="post">
                    <div class="logo">
                        <img src="<%=request.getContextPath()%>/view/index/image/others/mujilogo2.png" alt="">
                    </div>
                    <h2>會員登入頁面</h2>
                    
                    <%-- 錯誤表列 --%>
					<c:if test="${not empty errorMsgs}">
						<font style="color:red">請修正以下錯誤:</font>
						<ul>
							<c:forEach var="message" items="${errorMsgs}">
								<li style="list-style:none">
									<p style="color:red">${message}</p>  
								</li>
							</c:forEach>
						</ul>
					</c:if>
                    
                    <div class="container">
                        <div class="row row-cols-1 row-cols-sm-1 row-cols-md-2">
                            <div class="form-floating ">
                                <input type="account" class="form-control" name="mem_account" id="floatingAccount" placeholder="name@example.com" onkeyup="value=value.replace(/[^\a-\z\A-\Z0-9\@\.]/g,'')">
                                <label for="floatingAccount">帳號</label>
                            </div>
                            <div class="form-floating ">
                                <input type="password" class="form-control" name="mem_password" id="floatingConfirm" placeholder="Password">
                                <label for="floatingConfirm">密碼</label>
                            </div>
                        </div>
                    </div>
                    <div class="announcement">
                    	<input type="hidden" name="mem_account" value="${memVO.mem_account}">
                    	<input type="hidden" name="action" value="login">
                        <button type="submit" id="btn_primary" class="btn btn-primary">登入</button>
                        <button type="button" id="btn_secondary" class="btn btn-secondary">忘記密碼</button>
                        <p>請注意 MUMI MOVIE 吾映良影 </p>
                        <p>不會以任何理由要求您轉帳匯款</p>
                        <p>嚴防詐騙 人人有責</p>
                        <hr>
                        <a class="loginlink" href="<%=request.getContextPath()%>/view/mem/mem_register.jsp">還沒有帳號?點此前往加入會員</a>
                    </div>
                </form>
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
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <!-- JS檔連結記得修改 -->
    <script src="<%=request.getContextPath()%>/view/index/js/login.js"></script>
</body>

</html>