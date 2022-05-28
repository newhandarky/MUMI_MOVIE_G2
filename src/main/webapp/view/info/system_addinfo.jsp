<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="web.info.entity.*"%>
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
    <link rel="stylesheet" href="<%=request.getContextPath()%>/view/info/css/system_addinfo.css">

</head>

<%@ include file="../index/admin_header.jsp" %>

        <!-- 主要工作區 -->
        <div class="main">
            <h2>MUMI MOVIE 吾映良影</h2>
            <h3>影廳公告新增頁面</h3>
        
            <div class="setinfo container info-work">
            
            <c:if test="${not empty errorMsgs}">
				<font style="color:red">請修正以下錯誤:</font>
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color:red">${message}</li>
					</c:forEach>
				</ul>
			</c:if>

                <form METHOD="post" action="<%=request.getContextPath()%>/view/info/InfoServlet" enctype="multipart/form-data">
                    <div class="row">

                        <div class="col-3">
                            <p>公告標題</p>
                        </div>
                        <div class="col-9">
                            <input type="text" class="form-control" name="info_title" value="<%= (infoVO==null)? "" : infoVO.getInfo_title()%>">
                        </div>

                        <div class="col-3">
                            <p>公告圖片</p>
                        </div>
                        <div class="col-9">
                            <input class="form-control" name="info_pic" type="file" id="formFile">
                        </div>

                        <div class="col-3">
                            <p>公告內文</p>
                        </div>
                        <div class="col-9">
                        	<input type="hidden" class="form-control">
                            <textarea class="form-control" name="info_des" value="<%= (infoVO==null)? "" : infoVO.getInfo_des()%>"></textarea>
                        </div>

                    </div>
                    <a href="system_info_list.jsp">
                        <button type="button" class="btn btn-secondary">返回列表</button>
                    </a>
                    <input type="hidden" name="action" value="insert">
                    <input type="hidden" name="info_id" size="45" value="<%=(infoVO == null) ? "001" : infoVO.getInfo_id()%>" /> 
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
    <script src="<%=request.getContextPath()%>/view/info/info/js/jquery-3.6.0.min.js"></script>
    <script src="<%=request.getContextPath()%>/view/info/js/system_addinfo.js"></script>
</body>

</html>