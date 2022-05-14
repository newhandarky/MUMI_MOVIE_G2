package web.movie_type.service;

import java.util.List;

import web.movie_type.dao.Movie_typeDAO;
import web.movie_type.dao.Movie_typeDAO_interface;
import web.movie_type.entity.Movie_typeVO;

public class Movie_typeService {

	private Movie_typeDAO_interface dao;
	
	public Movie_typeService() {
		dao = new Movie_typeDAO();
	}
	
	public Movie_typeVO addMovie_type(String movie_type_en, String movie_type_ch) {		
		Movie_typeVO movie_typeVO = new Movie_typeVO();
		movie_typeVO.setMovie_type_en(movie_type_en);
		movie_typeVO.setMovie_type_ch(movie_type_ch);
		dao.insert(movie_typeVO);
		return movie_typeVO;
	}

	public Movie_typeVO updateMovie_type(Integer movie_type_id, String movie_type_en, String movie_type_ch) {
		Movie_typeVO movie_typeVO = new Movie_typeVO();
		movie_typeVO.setMovie_type_id(movie_type_id);
		movie_typeVO.setMovie_type_en(movie_type_en);
		movie_typeVO.setMovie_type_ch(movie_type_ch);
		dao.update(movie_typeVO);
		return movie_typeVO;
	}
	
	public void deleteMovie_type(Integer movie_type_id) {
		dao.delete(movie_type_id);
	}
	
	public Movie_typeVO getOneMovie_type(Integer movie_type_id) {
		return dao.findByPrimaryKey(movie_type_id);
	}

	public List<Movie_typeVO> getAll(){
		return dao.getAll();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
