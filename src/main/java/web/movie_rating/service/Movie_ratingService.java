package web.movie_rating.service;

import java.util.List;

import web.movie_rating.dao.Movie_ratingDAO;
import web.movie_rating.dao.Movie_ratingDAO_interface;
import web.movie_rating.entity.Movie_ratingVO;

public class Movie_ratingService {
	
	private Movie_ratingDAO_interface dao;
	
	public Movie_ratingService() {
		dao = new Movie_ratingDAO();
	}
	
	public Movie_ratingVO addMovie_rating(String movie_rating_ch, String movie_rating_en, byte[] movie_rating_pic) {		
		Movie_ratingVO movie_ratingVO = new Movie_ratingVO();
		
		movie_ratingVO.setMovie_rating_ch(movie_rating_ch);
		movie_ratingVO.setMovie_rating_en(movie_rating_en);
		movie_ratingVO.setMovie_rating_pic(movie_rating_pic);
		dao.insert(movie_ratingVO);
		
		return movie_ratingVO;
	}
	
	public Movie_ratingVO updateMovie_rating(Integer movie_rating_id, String movie_rating_ch, String movie_rating_en, byte[] movie_rating_pic) {
		Movie_ratingVO movie_ratingVO = new Movie_ratingVO();
		movie_ratingVO.setMovie_rating_id(movie_rating_id);
		movie_ratingVO.setMovie_rating_ch(movie_rating_ch);
		movie_ratingVO.setMovie_rating_en(movie_rating_en);
		movie_ratingVO.setMovie_rating_pic(movie_rating_pic);
		dao.update(movie_ratingVO);
		return movie_ratingVO;
	}
	
	public void deleteMovie_rating(Integer movie_rating_id) {
		dao.delete(movie_rating_id);
	}
	
	public Movie_ratingVO getOneMovie_rating(Integer movie_rating_id) {
		return dao.findByPrimaryKey(movie_rating_id);
	}

	public List<Movie_ratingVO> getAll(){
		return dao.getAll();
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
