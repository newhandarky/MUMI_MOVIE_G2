<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="web.forum_article.service.*"%>

<html>
<head>
<title>討論區: Home</title>

<style>
table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
	border: 3px ridge Gray;
	height: 80px;
	text-align: center;
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

h4 {
	color: blue;
	display: inline;
}
</style>

</head>
<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td><h3>討論區: Home</h3>
				<h4>( MVC )</h4></td>
		</tr>
	</table>

	<p>This is the Home page for 討論區: Home</p>

	<h3>資料查詢:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<ul>
		<li><a href='listAllArticle.jsp'>List</a> all Article. <br>
		<br></li>


		<li>
			<FORM METHOD="post" ACTION="<c:url value="/view/forum_article/forumArticle.do" />">
				<b>選擇文章板塊:</b> <select name="article_board" >
					<option value="綜合討論" selected>綜合討論</option>
					<option value="電影心得">電影心得</option>
					<option value="商城購物">商城購物</option>
					<option value="影城討論">影城討論</option>
				</select>
				<input type="hidden" name="action" value="getBoard_For_Display">
				<input type="submit" value="送出">
			</FORM>
		</li>

		<jsp:useBean id="ArticleSvc" scope="page" class="web.forum_article.service.ArticleService" />

		<li>
			<FORM METHOD="post" ACTION="#">
				<b>選擇文章類型:</b>
				<select name="article_type">
					<option value="心得" selected>心得</option>
					<option value="問題">問題</option>
					<option value="討論">討論</option>
					<option value="情報">情報</option>
					<option value="分享">分享</option>
				</select> 
				<input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="送出">
				
			</FORM>
		</li>

		<li>
			<FORM METHOD="post" ACTION="#">
				<b>選擇文章狀態:</b> 
				<select name="article_state">
					<option value="會員已發表" selected>會員已發表</option>
					<option value="會員已修改">會員已修改</option>
					<option value="會員已刪除">會員已刪除</option>
					<option value="管理員已鎖定">管理員已鎖定</option>
					<option value="管理員已刪除">管理員已刪除</option>
				</select>
				<input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="送出">
			</FORM>
		</li>
	</ul>


	<h3>新增文章</h3>

	<ul>
		<li><a href='postArticle.jsp'>Add</a>New Post</li>
	</ul>

</body>
</html>