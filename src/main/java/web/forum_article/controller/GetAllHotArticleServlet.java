package web.forum_article.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import web.forum_article.entity.ArticleVO;
import web.forum_article.service.ArticleService;

/**
 * Servlet implementation class GetAllHotArticleServlet
 */
@WebServlet(urlPatterns = {"/view/forum_article/GetAllHotArticleServlet"})
public class GetAllHotArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		ArticleService articleSvc = new ArticleService();
		
		//呼叫方法回傳到view
		List<ArticleVO> list = articleSvc.getHotAll();
		
		System.out.println("有執行getHotAll()查詢");
		System.out.println("這是getHotAll()的list:" + list);
		
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		String getHotAllList = gson.toJson(list);
		PrintWriter out = response.getWriter();

		out.println(getHotAllList);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
