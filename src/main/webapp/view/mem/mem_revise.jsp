<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="web.member.entity.*"%>
<%@ page import="web.member.dao.*"%>
<%@ page import="java.sql.*"%>

<%

  MemVO memVO = (MemVO) request.getAttribute("memVO");

%>   

<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <!-- css檔連結記得修改 -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/view/mem/css/mem_revise.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>

<body>
    <!-- 頁首 -->
    <header>
    
    	<%-- 錯誤表列 --%>
		<c:if test="${not empty errorMsgs}">
			<font style="color:red">請修正以下錯誤:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color:red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>	
    
        <!-- 專題LOGO -->
        <a class="link_index" href="<%=request.getContextPath()%>/view/mem/select_page.jsp">
            <img class="team_logo" src="<%=request.getContextPath()%>/view/mem/image/others/mujilogo.png" alt="">
        </a>
        <!-- 背景亮度按鈕 -->
        <div id="lightbtn">
            <!-- 搜尋欄位 -->
            <input id="search" type="text">
            <a id="a_loupe" href="#">
                <img id="loupe" src="<%=request.getContextPath()%>/view/mem/image/icons/loupe.png" alt="">
            </a>
            <button type="button" class="btn btn-success btn-ln" id="btn-light">Light
                <span>
                    <img id="sun" class="sunmoon" src="<%=request.getContextPath()%>/view/mem/image/icons/sun.png" alt="">
                </span>
            </button>
            <button type="button" class="btn btn-dark btn-ln -off" id="btn-dark">Dark
                <span>
                    <img id="moon" class="sunmoon -off" src="<%=request.getContextPath()%>/view/mem/image/icons/crescent-moon.png" alt="">
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
                        <a href="hell_seat_4dxA.html">確認劃位</a>
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
                        <a href="login.html">會員登出</a>
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
                                <a href="#">會員登入</a>
                            </li>
                            <li>
                                <a href="#">註冊會員</a>
                            </li>
                            <li>
                                <a href="">修改資料</a>
                            </li>
                            <li>
                                <a href="mem_shopping_sheet.html">歷史消費</a>
                            </li>
                            <li>
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
        <div class="register">
        	
            <form METHOD="post" ACTION="<c:url value="/view/mem/MemServlet" />" enctype="multipart/form-data">
                                    
                <h2>會員資料修改</h2>
				
                <div class="logo">
                    <img src="DBGifReader4?mem_id=${memVO.mem_id}">
                </div>
                <div class="container">
                    <div class="row row-cols-sm-1 row-cols-md-2">

                        <div class="col-md-3">
                            <p>會員帳號 : </p>
                        </div>
                        <div class="col-md-9">
                            <input class="form-control" type="text" placeholder="${memVO.getMem_account()} " aria-label="Disabled input example" disabled>
                        </div>

                        <div class="col-md-3">
                            <p>會員姓名 : </p>
                        </div>
                        <div class="col-md-9">
                            <input class="form-control" type="text" name="mem_name" value="<%= (memVO==null)? "會員姓名" : memVO.getMem_name()%>" aria-label="default input example">
                        </div>

                        <div class="col-md-3">
                            <p>會員暱稱 : </p>
                        </div>
                        <div class="col-md-9">
                            <input class="form-control" type="text" name="mem_nickname" value="<%= (memVO==null)? "會員暱稱" : memVO.getMem_nickname()%>" aria-label="default input example">
                        </div>

                        <div class="col-md-3">
                            <p>聯絡電話 : </p>
                        </div>
                        <div class="col-md-9">
                            <input class="form-control" type="text" name="mem_phone" value="<%= (memVO==null)? "聯絡電話" : memVO.getMem_phone()%>" aria-label="default input example" onkeyup="value=value.replace(/[^0-9]/g,'')" maxlength="10">
                        </div>

                        <div class="col-md-3">
                            <p>會員生日 : </p>
                        </div>
                        <div class="col-md-9">
                            <input class="form-control" type="date" name="mem_birthday" value="<%= (memVO==null)? "會員生日" : memVO.getMem_birthday()%>">
                        </div>
                        <div class="col-md-3">
                            <p>會員照片 : </p>
                        </div>
                        <div class="col-md-9">
                        	<input class="form-control" name="mem_pic" type="file">
                        </div>

                        <div class="col-md-3">
                            <p>會員性別 : </p>
                        </div>
                        <div class="col-md-9">
                            <div class="row">
                                <div class="form-check col-6">
                                    <input class="form-check-input radio" type="radio" id="flexRadioDefault1" name="mem_gender" value="0" <%=(memVO.getMem_gender()==0)? "checked": ""%> >
                                    <label class="form-check-label" for="flexRadioDefault1">
                                        男
                                    </label>
                                </div>
                                <div class="form-check col-6">
                                    <input class="form-check-input radio" type="radio" id="flexRadioDefault2" name="mem_gender" value="1" <%=(memVO.getMem_gender()==1)? "checked": ""%> >
                                    <label class="form-check-label" for="flexRadioDefault2">
                                        女
                                    </label>
                                </div>
                            </div>
                        </div>

                        <div class="col-md-3 ">
                            <p>帳號密碼 : </p>
                        </div>
                        <div class="col-md-9">
                            <input class="form-control" type="password"  name="mem_password" value="<%= (memVO==null)? "帳號密碼" : memVO.getMem_password()%>" aria-label="default input example">
                        </div>

                        <div class="col-md-3">
                            <p>會員地址 : </p>
                        </div>
                        <div class="col-md-9">
                            <input class="form-control" type="text" name="mem_address" value="<%= (memVO==null)? "會員地址" : memVO.getMem_address()%>" aria-label="default input example">
                        </div>

                        <div class="col-md-3 mem-e-money">
                            <p>電子錢包餘額 : </p>
                        </div>
                        <div class="col-md-5 col-sm-7 mem-e-money">
                            <input class="form-control" type="text" value="${memVO.getMem_point()}" aria-label="Disabled input example" disabled readonly>
                        </div>
                        <div class="col-md-4 col-sm-5 mem-e-money">
                            <a href="mem_mumipay.html">
                                <button type="button" class="btn btn-success btn-charge">點我儲值</button>
                            </a>
                        </div>

                      

                    </div>
                </div>
                <div class="announcement">
                    <a class="alist" href="<%=request.getContextPath()%>/view/mem/select_page.jsp">
                        <button type="button" class="btn btn-secondary" id="cancel">取消變更</button>
                    </a>
                    
					<input type="hidden" name="mem_id" value="<%=memVO.getMem_id()%>">
					<input type="hidden" name="action" value="update">
                    <button type="submit" class="btn btn-primary" id="commit" value="送出修改">確認送出</button>

                    <hr>
                    <p>提醒您！ MUMI MOVIE 吾映良影不會以電話通知更改付款方式或要求改以ATM重新轉帳。 </p>
                    <p>亦不會委託廠商以電話通知變更付款方式或要求提供ATM匯款帳號。 </p>
                </div>
            </form>
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
                        <a href="#"> 張志鵬</a>
                        <p>會員系統</p>
                    </span>
                    <span class="focus_us">
                        <a href="#">郭家榮</a>
                        <p>討論區</p>
                    </span>
                    <span class="focus_us">
                        <a href="#">蕭仲威</a>
                        <p>電影系統</p>
                    </span>
                    <span class="focus_us">
                        <a href="#">吳宗哲</a>
                        <p>商城購物</p>
                    </span>
                    <span class="focus_us">
                        <a href="#">徐浩鈞</a>
                        <p>訂票系統</p>
                    </span>
                </div>
                <div class="col col-md-3 col-sm-6">
                    <h5>加入好友</h5>
                    <ul>
                        <li>
                            <span>
                                <img id="linerobot" src="<%=request.getContextPath()%>/view/mem/image/others/robot.png" alt="">
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
    <script src="<%=request.getContextPath()%>/view/mem/js/mem_revise.js"></script>
</body>

</html>