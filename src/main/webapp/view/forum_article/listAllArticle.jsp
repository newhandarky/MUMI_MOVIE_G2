<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="web.forum_article.service.*"%>
<%@ page import="web.forum_article.entity.*"%>

<%
ArticleService articleSvc = new ArticleService();
List<ArticleVO> list = articleSvc.getAll();
pageContext.setAttribute("list", list);
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>所有文章資料 - listAllArticle.jsp</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<script
	src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.minjs"></script>

</head>
<body>

	<div class="container">
		<div class="jumbotron">
			<table id="table-1">
				<tr><td>
		 				<h3>所有文章資料 - listAllArticle.jsp</h3>
		 				<h4><a href="select_page.jsp">回首頁</a></h4>
				</td></tr>
			</table>
		</div>
		<table class="table table-sm table-hover table-bordered"
			style="text-align: center">
			<thead style="background-color: lightgray;">
				<tr>
					<th scope="col">編號</th>
					<th scope="col">板塊</th>
					<th scope="col">分類</th>
					<th scope="col">會員編號</th>
					<th scope="col">文章首圖</th>
					<th scope="col">標題</th>
					<th scope="col">內文</th>
					<th scope="col">按讚數</th>
					<th scope="col">倒讚數</th>
					<th scope="col">發表時間</th>
					<th scope="col">更新時間</th>
					<th scope="col">員工編號</th>
					<th scope="col">狀態</th>
					<th scope="col">參考編號</th>
					<th scope="col">修改</th>
					<th scope="col">刪除</th>
				</tr>
			</thead>

			<c:forEach var="articleVO" items="${list}">

				<tbody>
					<tr>
						<td scope="row">${articleVO.article_id}</td>
						<td scope="col">${articleVO.article_board}</td>
						<td scope="col">${articleVO.article_type}</td>
						<td scope="col">${articleVO.mem_id}</td>
						<td scope="col"><img src="ShowPicServlet?article_id=${articleVO.article_id}" width="120"></td>						
						<td scope="col">${articleVO.article_subject}</td>
						<td scope="col">${articleVO.article_contain}</td>
						<td scope="col">${articleVO.article_like_num}</td>
						<td scope="col">${articleVO.article_dislike_num}</td>
						<td scope="col"><fmt:formatDate value="${articleVO.article_publish}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						<td scope="col"><fmt:formatDate value="${articleVO.article_updated}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						<td scope="col">${articleVO.emp_id}</td>
						<td scope="col">${articleVO.article_state}</td>
						<td scope="col">${articleVO.re_article_id}</td>
						
						<td scope="col">
							<form method="post" action="<%=request.getContextPath()%>/view/forum_article/forumArticle.do">
								<input type="submit" value="修改">
								<input type="hidden" name="article_id" value="${articleVO.article_id}">								
								<input type="hidden" name="action" value="getOne_For_Update">								
							</form>
						</td>
						<td scope="col">
							<form method="post" action="<%=request.getContextPath()%>/view/forum_article/forumArticle.do">
								<input type="submit" value="刪除">
								<input type="hidden" name="article_id" value="${articleVO.article_id}">								
								<input type="hidden" name="action" value="delete">								
							</form>
						</td>
					</tr>
				</tbody>
			</c:forEach>
		</table>
	</div>


</body>
</html>