<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="web.info.entity.*"%>
<%@ page import="web.info.service.*"%>

<%
	InfoService infoSvc = new InfoService();
	List<InfoVO> list = infoSvc.getAll();
	pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>影城後台管理系統</title>
    <link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css'>
    <link rel='stylesheet' href='https://unicons.iconscout.com/release/v3.0.6/css/line.css'>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/view/info/css/system_info_list.css">

</head>

<%@ include file="../index/admin_header.jsp" %>

        <!-- 主要工作區 -->
        <div class="main">

            <div class="showtable table-responsive">
                <h2>公告列表</h2>

                <table class="table">
                    <tr>
                        <th>公告編號</th>
                        <th>公告標題</th>
                        <th>發佈時間</th>
                        <th>發佈人</th>
                        <th>狀態</th>
                    </tr>
                                     
                    <c:forEach var="infoVO" items="${list}">
	                    <tr>
	                        <td>
		                    	<form METHOD="post" ACTION="<%=request.getContextPath()%>/view/info/InfoServlet">
		                        	<input  type="hidden" name="info_id" value="${infoVO.info_id}"> 
									<input type="hidden" name="action" value="getOne_For_Display">
		                            <a href="<%=request.getContextPath()%>/view/info/system_editinfo.jsp">
										<button type="submit" class="btn btn-success checkinfo">${infoVO.info_id} </button>		
									</a>
		                 	   </form>	
	                        </td>		
	                        <td>${infoVO.info_title}</td>
	                        <td>${infoVO.info_date}</td>
	                        <td>${infoVO.emp_id}</td>
	                        <td class="infostate${infoVO.info_state}"></td>
	                   </tr>
					</c:forEach>

                </table>
            </div>
            <a href="<%=request.getContextPath()%>/view/info/system_addinfo.jsp">
                <button type="button" class="btn btn-success">新增公告</button>
            </a>
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
    <script src="<%=request.getContextPath()%>/view/info/js/jquery-3.6.0.min.js"></script>
    <script src="<%=request.getContextPath()%>/view/info/js/system_info_list.js"></script>

</body>

</html>