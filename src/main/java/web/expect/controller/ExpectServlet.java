package web.expect.controller;

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

import core.util.HibernateUtil;
import web.expect.entity.ExpectVO;
import web.expect.service.ExpectService;
import web.movie.dao.MovieDAO;
import web.movie.entity.MovieVO;
import web.movie.service.MovieService;
import web.satisfy.service.SatisfyService;

@WebServlet("/ExpectServlet")
public class ExpectServlet extends HttpServlet  {
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
		
		ExpectVO evo = gson.fromJson(request.getReader(), ExpectVO.class);
//		int total = expectService.findExceptTotal(evo.getMovie_id());
//		System.out.println("total=" + total);
//		int liketotal = expectService.findLikeTotal(evo.getMovie_id());
//		System.out.println("liketotal=" +liketotal);
		expectService.addExpect(evo.getMem_id(), evo.getMovie_id(), evo.getExpect());


	}

}