package web.forum_article.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
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


@WebServlet("/view/forum_article/GetBoardArticleServlet")
public class GetBoardArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		try {
			//接收資料
			Integer page = new Integer(request.getParameter("page"));
			
			//驗證資料
			Map<String, String> errors = new HashMap<String, String>();
			request.setAttribute("errors", errors);
			

			System.out.println(page);
			
			String article_board = null;
			switch (page) {
			case 0:
				article_board = "全部文章";
				break;
			case 1:
				article_board = "綜合討論";
				break;
			case 2:
				article_board = "電影心得";
				break;
			case 4:
				article_board = "影城討論";
				break;
			}
						
			ArticleService articleSvc = new ArticleService();
			List<ArticleVO> list = articleSvc.getOneBoardArticle(article_board);
		
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
			String BoardArticle = gson.toJson(list);
			PrintWriter out = response.getWriter();

			out.println(BoardArticle);

			
		} catch (Exception e) {
			e.getMessage();
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
