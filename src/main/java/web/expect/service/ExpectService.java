package web.expect.service;

import java.util.List;

import org.hibernate.SessionFactory;

import core.util.HibernateUtil;
import web.expect.dao.ExpectDAO;
import web.expect.entity.ExpectBean_interface;

public class ExpectService {
	ExpectBean_interface dao;
	public ExpectService(SessionFactory sessionFactory) {
		dao = new ExpectDAO(sessionFactory) ;
	}
	
	public void addExpect(int Expect_id, int Mem_id ,int Movie_id ,int Movie_expect) {
		dao.insert(Expect_id,Mem_id,Movie_id,Movie_expect);
	}
	
	public List<Object[]> findMovieAndExpectByID(int movie_id){
		List<Object[]>list =dao.findByMovieID(movie_id);
		return list;
	}
	
}
