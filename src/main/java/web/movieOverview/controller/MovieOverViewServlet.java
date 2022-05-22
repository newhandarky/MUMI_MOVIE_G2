package web.movieOverview.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import core.util.HibernateUtil;
import web.expect.dao.ExpectDAO;
import web.expect.entity.ExpectBean;
import web.expect.entity.MovieBean;
import web.expect.service.ExpectService;
import web.movie.entity.MovieVO;
import web.movie.service.MovieService;
import web.movie_tag.entity.Movie_tagVO;
import web.movie_tag.service.Movie_tagService;
import web.movie_type.entity.Movie_typeVO;
import web.movie_type.service.Movie_typeService;
import web.satisfy.entity.SatisfyBean;
import web.satisfy.service.SatisfyService;

@WebServlet("/MovieOverViewServlet")
public class MovieOverViewServlet extends HttpServlet { 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ExpectService expectService;
	private SatisfyService satisfyService;
	public void init() throws ServletException{
		expectService =  new ExpectService(HibernateUtil.getSessionFactory());
		satisfyService =  new SatisfyService(HibernateUtil.getSessionFactory());
	}
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		if("now".equals(req.getParameter("now"))){
			String movie_id = req.getParameter("movie_id");
			Integer.valueOf(movie_id);
			Movie_tagService MTSvc = new Movie_tagService();
			Movie_typeService typeSvc = new Movie_typeService();
			MovieService MS = new MovieService();
			MovieVO MVO = MS.getOneMovie(Integer.valueOf(movie_id));

			List<Movie_tagVO> taglist = MTSvc.findBYMovieID(Integer.valueOf(movie_id)); //該MovieVO的類型Tag
			List<String> typelist = new ArrayList();
			for(Movie_tagVO tagVO : taglist) {
				Movie_typeVO mtvo = typeSvc.getOneMovie_type(tagVO.getMovie_type_id());
				String type = mtvo.getMovie_type_ch();
				typelist.add(type);				
			}
		
			req.setAttribute("movieVO", MVO); // 資料庫取出的VO物件,存入req
			req.setAttribute("typelist", typelist);

			String url = "/view/Moive_satisfyview/Moive_satisfyview.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交給單獨頁面
			successView.forward(req, res);				
		}
	
		if("soon".equals(req.getParameter("soon"))){
			String movie_id = req.getParameter("movie_id");
			Integer.valueOf(movie_id);
			Movie_tagService MTSvc = new Movie_tagService();
			Movie_typeService typeSvc = new Movie_typeService();
			MovieService MS = new MovieService();
			MovieVO MVO = MS.getOneMovie(Integer.valueOf(movie_id));

			List<Movie_tagVO> taglist = MTSvc.findBYMovieID(Integer.valueOf(movie_id)); //該MovieVO的類型Tag
			List<String> typelist = new ArrayList();
			for(Movie_tagVO tagVO : taglist) {
				Movie_typeVO mtvo = typeSvc.getOneMovie_type(tagVO.getMovie_type_id());
				String type = mtvo.getMovie_type_ch();
				typelist.add(type);				
			}
		
			req.setAttribute("movieVO", MVO); // 資料庫取出的VO物件,存入req
			req.setAttribute("typelist", typelist);

			String url = "/view/Movie_expectview/movie_expectview.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交給單獨頁面
			successView.forward(req, res);				
		}
	}
}
