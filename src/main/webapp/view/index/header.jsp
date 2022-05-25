<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<body>
    <header>
        <!-- 專題LOGO -->
        <a class="link_index" href="<%=request.getContextPath()%>/view/index/index.jsp">
            <img class="team_logo" src="<%=request.getContextPath()%>/view/index/image/others/mujilogo.png" alt="">
        </a>
        
        <!-- 背景亮度按鈕 -->
        <div id="lightbtn">
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
                        <a href="#">電影總覽</a>
                    </li>
                    <li class="nav_li">
                        <a href="#">現正熱映</a>
                    </li>
                    <li class="nav_li">
                        <a href="#">即將上映</a>
                    </li>
                </ul>
            </div>

            <div class="nav-item">
                <ul class="nav_ul" id="ticket_ul">
                    <a href="#">訂票系統</a>
                    <li class="nav_li">
                        <a href="<%=request.getContextPath()%>/view/ticket_orders/order_beware.jsp">確認劃位</a>
                    </li>
                </ul>
            </div>

            <div class="nav-item">
                <ul class="nav_ul" id="forum_ul">
                    <a href="#">討論區</a>
                    <li class="nav_li">
                        <a href="http://localhost:8080/MUMI_MOVIE_G2/view/forum_article/ForumIndex.html">保留區</a>
                    </li>
                </ul>
            </div>

            <div class="nav-item">
                <ul class="nav_ul" id="member_ul">
                    <a href="#">會員專區</a>
                    <li class="nav_li">
                        <a href="<%=request.getContextPath()%>/view/mem/mem_register.jsp">註冊會員</a>
                    </li>
                    <li class="nav_li">
                        <a href="<%=request.getContextPath()%>/view/mem/mem_profiles.jsp">會員資料</a>
                    </li>
                    <li class="nav_li">
                        <a href="<%=request.getContextPath()%>/view/mem/mem_revise.jsp">修改資料</a>
                    </li>
                    <li class="nav_li">
                        <a href="<%=request.getContextPath()%>/view/mem/check_order_beware.jsp">歷史消費</a>
                    </li>
                    <li class="nav_li">
                        <a style="font-size: 14px" href="#" class="checksession">登入/登出</a>
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
                                <a href="<%=request.getContextPath()%>/view/mem/mem_register.jsp">註冊會員</a>
                            </li>
                            <li>
                                <a href="<%=request.getContextPath()%>/view/mem/mem_profiles.jsp">會員資料</a>
                            </li>
                            <li>
                                <a href="<%=request.getContextPath()%>/view/mem/mem_revise.jsp">修改資料</a>
                            </li>
                            <li>
                                <a href="<%=request.getContextPath()%>/view/mem/check_order_beware.jsp">歷史消費</a>
                            </li>
                            <li>
								<a href="#" class="checksession" >登入/登出</a>
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
                        	<li class="nav_li">
		                        <a href="#">電影總攬</a>
		                    </li>
                            <li>
                                <a href="">現正熱映</a>
                            </li>
                            <li>
                                <a href="">即將上映</a>
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
                                <a href="<%=request.getContextPath()%>/view/ticket_orders/order_beware.jsp">確認劃位</a>
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
                        	<li>
                                <a href="http://localhost:8080/MUMI_MOVIE_G2/view/forum_article/ForumIndex.html">保留區</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <div class="hiddenform" style="display:none;">
    	<form id="submitcheck" action="<%=request.getContextPath()%>/member/Logout" method="post">
        	<input type="hidden" name="action" value="logout">
            <button type="submit" id="btn-primary" class="btn btn-primary">確定登出</button>
        </form>
    </div>
    
    <script>
	    window.onload=function(){
	        $(".checksession").on("click", function() {
	            $("#submitcheck").submit();
	        });
	    }
    </script>
    