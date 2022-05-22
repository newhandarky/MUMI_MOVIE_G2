package web.movie_tag.service;

import java.util.List;

import web.movie_tag.dao.Movie_tagDAO;
import web.movie_tag.dao.Movie_tagDAO_interface;
import web.movie_tag.entity.Movie_tagVO;

public class Movie_tagService {

private Movie_tagDAO_interface dao;
	
	public Movie_tagService() {
		dao = new Movie_tagDAO();
	}
	
	public Movie_tagVO addMovie_tag(Integer movie_id, Integer movie_type_id) {		
		Movie_tagVO movie_tagVO = new Movie_tagVO();
		movie_tagVO.setMovie_id(movie_id);
		movie_tagVO.setMovie_type_id(movie_type_id);
		dao.insert(movie_tagVO);
		return movie_tagVO;
	}
	
	public Movie_tagVO updateMovie_tag(Integer movie_tag_id, Integer movie_id, Integer movie_type_id) {
		Movie_tagVO movie_tagVO = new Movie_tagVO();
		movie_tagVO.setMovie_tag_id(movie_tag_id);
		movie_tagVO.setMovie_id(movie_id);
		movie_tagVO.setMovie_type_id(movie_type_id);
		dao.update(movie_tagVO);
		return movie_tagVO;
	}
	
	public void deleteMovie_tag(Integer movie_tag_id) {
		dao.delete(movie_tag_id);
	}
	
	public Movie_tagVO getOneMovie_tag(Integer movie_tag_id) {
		return dao.findByPrimaryKey(movie_tag_id);
	}

	public List<Movie_tagVO> getAll(){
		return dao.getAll();
	}
	
	public List<Movie_tagVO> findBYMovieID(Integer movie_id){
		return dao.findByMovieID(movie_id);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
