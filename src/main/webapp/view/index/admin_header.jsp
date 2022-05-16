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
                    <li><a href="system_info_list.html">會員資料列表</a></li>
                </ul>
            </li>
            <li class="has-dropdown">
                <a href="#"> 電影管理</a>
                <ul class="sidebar-dropdown list-unstyled">
                    <li><a href="#">電影上架</a></li>
                    <li><a href="#">電影下架</a></li>
                    <li><a href="#">自動排程設定</a></li>
                </ul>
            </li>
            <li class="has-dropdown">
                <a href="#"> 商品管理</a>
                <ul class="sidebar-dropdown list-unstyled">
                    <li><a href="#">商品上架</a></li>
                    <li><a href="#">商品下架</a></li>
                </ul>
            </li>
            <li class="has-dropdown">
                <a href="#"> 討論區管理</a>
                <ul class="sidebar-dropdown list-unstyled">
                    <li><a href="#">留言檢舉機制</a></li>
                </ul>
            </li>
            <li class="has-dropdown">
                <a href="#"> 票券管理</a>
                <ul class="sidebar-dropdown list-unstyled">
                    <li><a href="#">自動排程</a></li>
                    <li><a href="#">寄信提醒</a></li>
                </ul>
            </li>
            <li class="has-dropdown">
                <a href="#"> 座位管理</a>
                <ul class="sidebar-dropdown list-unstyled">
                    <li><a href="#">影廳座位設定</a></li>
                    <li><a href="#">保留位設定</a></li>
                </ul>
            </li>
            <li class="has-dropdown">
                <a href="#"> 員工管理</a>
                <ul class="sidebar-dropdown list-unstyled">
                    <li><a href="#">員工查詢</a></li>
                    <li><a href="#">新增員工</a></li>
                </ul>
            </li>
            <li class="has-dropdown">
                <a href="#"> 公告管理</a>
                <ul class="sidebar-dropdown list-unstyled">
                    <li><a href="#">公告</a></li>
                    <li><a href="#">登出</a></li>
                    <li><a href="#">回到前台首頁</a></li>
                </ul>
            </li>
            <li class="has-dropdown">
                <a href="#"> 其他</a>
                <ul class="sidebar-dropdown list-unstyled">
                    <li><a href="admin_login.html">切換帳號</a></li>
                    <li><a href="admin_login.html">登出</a></li>
                    <li><a href="index.html">回到前台首頁</a></li>
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
                    <a class="navbar-brand" href="#">影城後台管理系統</a>
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