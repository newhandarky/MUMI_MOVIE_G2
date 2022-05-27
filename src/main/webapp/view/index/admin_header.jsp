<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<body>
    <aside class="sidebar position-fixed top-0 left-0 overflow-auto h-100 float-left" id="show-side-navigation1">
        <i class="uil-bars close-aside d-md-none d-lg-none" data-close="show-side-navigation1"></i>
        <div class="sidebar-header d-flex justify-content-center align-items-center px-3 py-4">
            <!-- 影城LOGO -->
            <img class="img-logo" src="<%=request.getContextPath()%>/view/index/image/others/mujilogo.png" alt="">
            <div class="ms-2">
                <h5 class="fs-6 mb-0 title-h5">
                    MUMI MOVIE
                </h5>
                <h5 class="fs-6 mb-0 title-h5">
                    吾映良影
                </h5>
                <h5 class="fs-6 mb-0 title-h5">
                    後臺管理系統
                </h5>
            </div>
        </div>

        <div class="search position-relative text-center px-4 py-3 mt-2">
            <input type="text" class="form-control w-100 border-0 bg-transparent" placeholder="Search here">
            <i class="fa fa-search position-absolute d-block fs-6"></i>
        </div>

        <!-- 左邊側邊欄功能列 -->
        <ul class="categories list-unstyled">
            <li class="has-dropdown">
                <a href="#"> 會員管理</a>
                <ul class="sidebar-dropdown list-unstyled">
                    <li><a href="<%=request.getContextPath()%>/view/mem/system_info_list.jsp">會員資料列表</a></li>
                </ul>
            </li>
            <li class="has-dropdown">
                <a href="#"> 電影管理</a>
                <ul class="sidebar-dropdown list-unstyled">
                    <li><a href="<%=request.getContextPath()%>/view/movie/system_movie_add.jsp">編輯電影</a></li>
                    <li><a href="<%=request.getContextPath()%>/view/movie_time/system_movie_time_add.jsp">編輯電影場次</a></li>
                    <li><a href="<%=request.getContextPath()%>/view/releasing/system_releasing_add.jsp">編輯電影狀態</a></li>
                	<li><a href="<%=request.getContextPath()%>/view/movie_rating/system_movie_rating_add.jsp">編輯電影分級</a></li>
                	<li><a href="<%=request.getContextPath()%>view/movie_type/system_movie_type_add.jsp">編輯電影分類</a></li>
                	<li><a href="<%=request.getContextPath()%>/view/movie_tag/system_movie_tag_listAll.jsp">編輯電影標籤</a></li>
                </ul>
            </li>
            <li class="has-dropdown">
                <a href="#"> 討論區管理</a>
                <ul class="sidebar-dropdown list-unstyled">
                    <li><a href="<%=request.getContextPath()%>/view/forum_article/AdminForumIndex.html">討論區管理</a></li>
                </ul>
            </li>
            <li class="has-dropdown">
                <a href="#"> 座位管理</a>
                <ul class="sidebar-dropdown list-unstyled">
                    <li><a href="<%=request.getContextPath()%>/view/hall_seat/system_hall_seat.jsp">影廳座位設定</a></li>
                </ul>
            </li>
            <li class="has-dropdown">
                <a href="#"> 員工管理</a>
                <ul class="sidebar-dropdown list-unstyled">
                    <li><a href="<%=request.getContextPath()%>/view/employee/system_employee_list.jsp">員工管理</a></li>
                </ul>
            </li>
            <li class="has-dropdown">
                <a href="#"> 公告管理</a>
                <ul class="sidebar-dropdown list-unstyled">
                    <li><a href="<%=request.getContextPath()%>/view/info/system_info_list.jsp">公告管理</a></li>
                </ul>
            </li>
            <li class="has-dropdown">
                <a href="#"> 其他</a>
                <ul class="sidebar-dropdown list-unstyled">
                    <li><a href="">登出</a></li>
                    <li><a href="<%=request.getContextPath()%>/view/index/index.jsp">回到前台首頁</a></li>
                </ul>
            </li>
        </ul>
    </aside>

    <section id="wrapper">
        <nav class="navbar navbar-expand-md">
            <div class="container-fluid mx-2">
                <div class="navbar-header">
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#toggle-navbar" aria-controls="toggle-navbar" aria-expanded="false" aria-label="Toggle navigation">
            <i class="uil-bars text-white"></i>
        </button>
                    <a class="navbar-brand" href="<%=request.getContextPath()%>/view/index/admin_index.jsp">影城後台管理系統</a>
                </div>
                <div class="collapse navbar-collapse" id="toggle-navbar">
                    <ul class="navbar-nav ms-auto">
                        <li class="nav-item">
                            <a class="nav-link" href="#">
                                <i data-show="show-side-navigation1" class="uil-bars show-side-btn"></i>
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>