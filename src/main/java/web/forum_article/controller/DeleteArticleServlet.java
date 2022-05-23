package web.forum_article.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.forum_article.service.ArticleService;


@WebServlet(urlPatterns = {"/view/forum_article/DeleteArticleServlet"})
public class DeleteArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Integer article_id = new Integer(request.getParameter("article_id"));
		
		//驗證資料
		Map<String, String> errors = new HashMap<String, String>();
		request.setAttribute("errors", errors);
		
		ArticleService articleSvc = new ArticleService();
		articleSvc.deleteArticle(article_id);
		
		String url = request.getContextPath() + "/view/forum_article/AdminForumIndex.html";
		response.sendRedirect(url);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
