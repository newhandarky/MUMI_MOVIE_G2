package web.satisfy.service;

import java.util.List;

import web.satisfy.dao.SatisfyDAO;
import web.satisfy.entity.SatisfyBean_interface;



public class SatisfyService {
	SatisfyBean_interface dao;
	public SatisfyService() {
		dao = new SatisfyDAO() ;
	}
	
	public void addSatisfy(int Satisfy_id, int Mem_id, int Movie_id, int Movie_sati) {
		dao.insert(Satisfy_id,Mem_id,Movie_id,Movie_sati);
	}
	
	public List<Object[]> findMovieImgByID(int movie_id){
		List<Object[]>list =dao.findByMovieID(movie_id);
		return list;
	}
	
}
