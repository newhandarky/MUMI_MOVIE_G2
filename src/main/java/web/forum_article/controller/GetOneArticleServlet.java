package web.forum_article.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import web.forum_article.entity.ArticleVO;
import web.forum_article.service.ArticleService;


@WebServlet("/view/forum_article/GetOneArticleServlet")
public class GetOneArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		try {
			//接收資料
			String temp1 = request.getParameter("article_id");
//			Integer article_id = new Integer(request.getParameter("article_id"));
			
			
			//驗證資料
			Map<String, String> errors = new HashMap<String, String>();
			request.setAttribute("errors", errors);
			
			//轉換資料
			int article_id = 0;
			if(temp1 != null && temp1.length() != 0) {
				try {
					article_id = Integer.parseInt(temp1);
				} catch (NumberFormatException e) {
					e.printStackTrace();
					errors.put("article_id", "ArticleId must be an integer");
				}
			}
			
			ArticleService articleSvc = new ArticleService();
			
			ArticleVO articleVO = articleSvc.getOneArticle(article_id);
			System.out.println("這是GetOne的articleVO=" + articleVO);
			
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
			String detail = gson.toJson(articleVO);
			PrintWriter out = response.getWriter();
			out.println(detail);
			System.out.println("這個article=" + detail);

		} catch (Exception e) {
			e.getMessage();
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
