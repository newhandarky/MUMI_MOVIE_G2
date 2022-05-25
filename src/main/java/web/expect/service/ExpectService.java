package web.expect.service;

import java.util.List;

import org.hibernate.SessionFactory;

import core.util.HibernateUtil;
import web.expect.dao.ExpectDAO;
import web.expect.entity.ExpectBean;
import web.expect.entity.ExpectBean_interface;

public class ExpectService {
	ExpectBean_interface dao;
	public ExpectService(SessionFactory sessionFactory) {
		dao = new ExpectDAO(sessionFactory) ;
	}
	
	public void addExpect(int Expect_id, int Mem_id ,int Movie_id ,int Movie_expect) {
		dao.insert(Expect_id,Mem_id,Movie_id,Movie_expect);
	}
	
	public ExpectBean findMovieAndExpectByID(int mem_id ,int movie_id){
		return dao.findByID(mem_id, movie_id);
	}
	
	public int findExceptTotal(int movie_id) {	
		return dao.findExpectTotal(movie_id);
	}
	
}
