package web.forum_article_report.controller;

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

import web.forum_article_report.entity.ReportArticle;
import web.forum_article_report.service.ReportArticleService;

@WebServlet(urlPatterns = { "/view/forum_article/GetDeneReportServlet" })
public class GetDoneReportServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		ReportArticleService reportArticleSvc = new ReportArticleService();
		
		//呼叫方法回傳到view
		List<ReportArticle> list = reportArticleSvc.indexGetAll();
		
		System.out.println("有執行getAll()查詢");
		System.out.println("這是getAll()的list:" + list);
		
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		String getAllList = gson.toJson(list);
		PrintWriter out = response.getWriter();

		out.println(getAllList);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
