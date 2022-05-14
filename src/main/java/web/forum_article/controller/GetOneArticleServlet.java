package web.forum_article.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.forum_article.entity.ArticleVO;
import web.forum_article.service.ArticleService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


@WebServlet("/view/forum_article/GetOneArticleServlet")
public class GetOneArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		try {
			Integer article_id = new Integer(request.getParameter("article_id"));
			ArticleService articleSvc = new ArticleService();
			ArticleVO articleVO = articleSvc.getOneArticle(article_id);
			System.out.println("這是GetOne的articleVO=" + articleVO);

			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
			String article = gson.toJson(articleVO);
			PrintWriter out = response.getWriter();

			out.println(article);

		} catch (Exception e) {
			e.getMessage();
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
