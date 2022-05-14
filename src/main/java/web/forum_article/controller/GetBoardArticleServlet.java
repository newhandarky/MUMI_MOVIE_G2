package web.forum_article.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.forum_article.entity.ArticleVO;
import web.forum_article.service.ArticleService;
import web.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class GetBoardArticleServlet
 */
@WebServlet("/GetBoardArticleServlet")
public class GetBoardArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		// 對Post中文參數進行解碼
		request.setCharacterEncoding("UTF-8");
		
		try {
			String article_board = request.getParameter("article_board");
			ArticleService articleSvc = new ArticleService();
			List<ArticleVO> list = articleSvc.getOneBoardArticle(article_board);
			System.out.println("這是getOneBoardArticle的list=" + list);
			
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
			String article = gson.toJson(list);
			PrintWriter out = response.getWriter();

			out.println(article);

			
		} catch (Exception e) {
			e.getMessage();
		}
		
		
		ArticleService articleSvc = new ArticleService();
		List<ArticleVO> list = articleSvc.getAll();
		
		System.out.println("有執行getAll()查詢");
		System.out.println("這是getAll()的list:" + list);
		
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		String getAllList = gson.toJson(list);
		PrintWriter out = response.getWriter();

		out.println(getAllList);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
