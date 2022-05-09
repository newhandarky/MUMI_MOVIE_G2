<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="web.forum_article.entity.*"%>

<%
pageContext.getAttribute("list");
ArticleVO articleVO = (ArticleVO) request.getAttribute("articleVO");
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>已篩選文章資料 - listOneArticle.jsp</title>
</head>
<body>

	<div class="container">
		<div class="jumbotron">
			<h2>文章總覽</h2>
		</div>
		<table class="table table-sm table-hover table-bordered"
			style="text-align: center">
			<thead style="background-color: lightgray;">
				<tr>
					<th scope="col">編號</th>
					<th scope="col">板塊</th>
					<th scope="col">分類</th>
					<th scope="col">會員編號</th>
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
						<td scope="col">${articleVO.article_subject}</td>
						<td scope="col">${articleVO.article_contain}</td>
						<td scope="col">${articleVO.article_like_num}</td>
						<td scope="col">${articleVO.article_dislike_num}</td>
						<td scope="col"><fmt:formatDate value="${articleVO.article_publish}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						<td scope="col"><fmt:formatDate value="${articleVO.article_updated}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						<td scope="col">${articleVO.emp_id}</td>
						<td scope="col">${articleVO.article_state}</td>
						<td scope="col">${articleVO.re_article_id}</td>
						
						<td scope="col"><input type="button" value="修改"></td>
						<td scope="col"><input type="button" value="刪除"></td>
					</tr>
				</tbody>
			</c:forEach>
		</table>
	</div>

</body>
</html>