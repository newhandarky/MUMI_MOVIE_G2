package web.satisfy.controller;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import core.util.HibernateUtil;
import web.expect.service.ExpectService;
import web.satisfy.entity.SatisfyVO;
import web.satisfy.service.SatisfyService;

@WebServlet("/SatisfyServlet")
public class SatisfyServlet extends HttpServlet  {
	private static final long serialVersionUID = 1L;
       
	private ExpectService expectService;
	private SatisfyService satisfyService;
	public void init() throws ServletException{
		expectService =  new ExpectService(HibernateUtil.getSessionFactory());
		satisfyService =  new SatisfyService(HibernateUtil.getSessionFactory());
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		Gson gson = new Gson();
		
		SatisfyVO svo = gson.fromJson(request.getReader(), SatisfyVO.class);
		System.out.println(svo);
//		int total = expectService.findExceptTotal(svo.getMovie_id());
//		System.out.println("total=" + total);
//		int liketotal = expectService.findLikeTotal(svo.getMovie_id());
//		System.out.println("liketotal=" +liketotal);
		
		
		satisfyService.addSatisfy(svo.getMem_id(), svo.getMovie_id(), svo.getSatisfy());


	}

}