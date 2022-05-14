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
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@WebServlet(urlPatterns = { "/view/forum_article/GetAllArticleServlet" })
public class GetAllArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		// 對Post中文參數進行解碼
		request.setCharacterEncoding("UTF-8");
		ArticleService articleSvc = new ArticleService();
		List<ArticleVO> list = articleSvc.getAll();
		
		System.out.println("有執行getAll()查詢");
		System.out.println("這是getAll()的list:" + list);
		
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		String getAllList = gson.toJson(list);
		PrintWriter out = response.getWriter();

		out.println(getAllList);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
