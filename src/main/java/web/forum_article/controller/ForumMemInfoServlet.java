package web.forum_article.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import web.member.entity.MemVO;
import web.member.service.MemService;


@WebServlet("/view/forum_article/ForumMemInfoServlet")
public class ForumMemInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
	    
	    String account = request.getParameter("mem_account");
	    
		MemService memSvc = new MemService();
		MemVO memVO = memSvc.getOneMemByAccount(account);
		
		System.out.println("這是getOneMemByAccount的articleVO=" + memVO);
		
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		String memInfo = gson.toJson(memVO);
		PrintWriter out = response.getWriter();
		out.println(memInfo);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
