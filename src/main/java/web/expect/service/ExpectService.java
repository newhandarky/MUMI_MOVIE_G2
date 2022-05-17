package web.expect.service;

import java.util.List;

import web.expect.dao.ExpectDAO;
import web.expect.entity.ExpectBean_interface;

public class ExpectService {
	ExpectBean_interface dao;
	public ExpectService() {
		dao = new ExpectDAO() ;
	}
	
	public void addExpect(int Expect_id, int Mem_id ,int Movie_id ,int Movie_expect) {
		dao.insert(Expect_id,Mem_id,Movie_id,Movie_expect);
	}
	
	public List<Object[]> findMovieImgByID(int movie_id){
		List<Object[]>list =dao.findByMovieID(movie_id);
		return list;
	}
	
}
