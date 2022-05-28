<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="web.info.entity.*"%>
<%@ page import="web.member.dao.*"%>
<%@ page import="java.sql.*"%>

<%
	InfoVO infoVO = (InfoVO) request.getAttribute("infoVO");
%>


<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>影城後台管理系統</title>
    <link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css'>
    <link rel='stylesheet' href='https://unicons.iconscout.com/release/v3.0.6/css/line.css'>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/view/info/css/system_editinfo.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/view/info/css/jquery.fancybox.css">

</head>

<body>
    <!-- partial:index.partial.html -->
    <aside class="sidebar position-fixed top-0 left-0 overflow-auto h-100 float-left" id="show-side-navigation1">
        <i class="uil-bars close-aside d-md-none d-lg-none" data-close="show-side-navigation1"></i>
        <div class="sidebar-header d-flex justify-content-center align-items-center px-3 py-4">

            <!-- 影城LOGO -->
            <img class="img-logo" src="<%=request.getContextPath()%>/view/info/image/others/mujilogo.png" alt="">
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
            <!-- <li class="">
                <i class="uil-folder"></i><a href="#"> File manager</a>
            </li> -->
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
            <!-- <li class="">
                <i class="uil-map-marker"></i><a href="#"> Maps</a>
            </li> -->
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



        <!-- 主要工作區 -->
        <div class="main">
        
            <h2>MUMI MOVIE 吾映良影</h2>
            <h3>影廳公告編輯頁面</h3>
        <%-- 錯誤表列 --%>
		<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color:red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>        
            <div class="setinfo container info-work">

                <form METHOD="post" action="<%=request.getContextPath()%>/view/info/InfoServlet" enctype="multipart/form-data">
                    <div class="row">

                        <div class="col-3">
                            <p>公告編號</p>
                        </div>
                        <div class="col-9">
                            <input class="form-control" type="text" placeholder="${infoVO.getInfo_id()}" disabled>
                        </div>

                        <div class="col-3">
                            <p>公告標題</p>
                        </div>
                        <div class="col-9">
                            <input type="text" class="form-control" name="info_title" value="<%= (infoVO==null)? "會員姓名" : infoVO.getInfo_title()%>">
                        </div>

                        <div class="col-3">
                            <p>公告圖片</p>
                        </div>
                        <div class="col-9">
                            <div class="row">
                                <div class="col-12">
                                    <input class="form-control" name="info_pic" type="file" id="formFile">
                                </div>
                            </div>
                        </div>

                        <div class="col-3">
                            <p>發佈人</p>
                        </div>
                        <div class="col-9">
                            <input class="form-control" type="text" name="${infoVO.getEmp_id()}" disabled>
                        </div>

                        <div class="col-3">
                            <p>公告狀態</p>
                        </div>
                        <div class="col-9">
                            <select name="info_state" >
                                <option value="0" ${ infoVO.getInfo_state() == 0 ? "selected" : ""} >已過期</option>
                                <option value="1" ${ infoVO.getInfo_state() == 1 ? "selected" : ""} >發佈中</option>
                            </select>             
                        </div>

                        <div class="col-3">
                            <p>公告內容</p>
                            <p>公告圖片預覽</p>
                            <img class="showpic" src="DBGifReader4?info_id=${infoVO.info_id}">
                             <br><br><br><br><br><br><br><br><br>
                            <img id="hidden-content"  src="DBGifReader4?info_id=${infoVO.info_id}">
                        </div>
                        <div class="col-9">
                            <textarea class="form-control" name="info_des"><%= (infoVO==null)? "" : infoVO.getInfo_des()%></textarea>
                        </div>

                    </div>
                    <a href="system_info_list.jsp">
                        <button type="button" class="btn btn-secondary">返回列表</button>
                    </a>
                    <input type="hidden" name="action" value="update">
                    <input type="hidden" name="info_id" value="${infoVO.info_id}">
                    <button type="submit" class="btn btn-primary">確認送出</button>


                </form>
            </div>

        </div>

        <!-- 工作區結束 -->

        <footer>
            <div class="copyright">
                Copyright © 2022 MUMI MOVIE 吾映良影 Co. 保留所有權利。
                <a href="#">隱私政策</a>
                <a href="#">使用條款</a>
            </div>

        </footer>

    </section>
    <!-- partial -->
    <script src='https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.bundle.js'></script>
    <script src='https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js'></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.8/dist/sweetalert2.all.min.js"></script>
	<script src="<%=request.getContextPath()%>/view/info/info/js/jquery-3.6.0.min.js"></script>
    <script src="<%=request.getContextPath()%>/view/info/js/system_editinfo.js"></script>
    <script src="https://code.jquery.com/jquery-3.0.0.js" integrity="sha256-jrPLZ+8vDxt2FnE1zvZXCkCcebI/C8Dt5xyaQBjxQIo=" crossorigin="anonymous"></script>
    <script src="<%=request.getContextPath()%>/view/info/js/jquery.fancybox.js"></script>
    
    <script>
$(document).ready(init);

function init(){
	$(".showpic").on('click', function() {	  
		$.fancybox.open({
		    src  : '#hidden-content',
		    type : 'inline',
		    opts : {
		      afterShow : function( instance, current ) {
		        console.info('done!');
	      }
	    }
	  });	  
	});
}
</script>
    
</body>

</html>