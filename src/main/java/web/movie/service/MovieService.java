package web.movie.service;

import java.sql.Timestamp;
import java.util.List;

import web.movie.dao.MovieDAO;
import web.movie.dao.MovieDAO_interface;
import web.movie.entity.MovieVO;

public class MovieService {

	private MovieDAO_interface dao;

	public MovieService() {
		dao = new MovieDAO();
	}

//movie_state_id, movie_rating_id, movie_ch, movie_en, 
//movie_runtime, release_date, movie_poster, 
//movie_slide_poster, movie_intro, casts, director, trailer
	public MovieVO addMovie(Integer movie_state_id, Integer movie_rating_id, String movie_ch, String movie_en,
			Integer movie_runtime, java.sql.Date release_date, byte[] movie_poster, byte[] movie_slide_poster,
			String movie_intro, String casts, String director, String trailer) {

		MovieVO movieVO = new MovieVO();

		movieVO.setMovie_state_id(movie_state_id);
		movieVO.setMovie_rating_id(movie_rating_id);
		movieVO.setMovie_ch(movie_ch);
		movieVO.setMovie_en(movie_en);
		movieVO.setMovie_runtime(movie_runtime);
		movieVO.setRelease_date(release_date);
		movieVO.setMovie_poster(movie_poster);
		movieVO.setMovie_slide_poster(movie_slide_poster);
		movieVO.setMovie_intro(movie_intro);
		movieVO.setCasts(casts);
		movieVO.setDirector(director);
		movieVO.setTrailer(trailer);
		dao.insert(movieVO);
		
		return movieVO;
	}

	
	
	public MovieVO insertType(Integer movie_id, Integer movie_type_id) {

		MovieVO movieVO = new MovieVO();

		movieVO.setMovie_id(movie_id);
		movieVO.setMovie_type_id(movie_type_id);
		
		dao.addType(movieVO);
		
		return movieVO;
	}
	
	
	public MovieVO updateType(Integer movie_id, Integer movie_type_id) {

		MovieVO movieVO = new MovieVO();

		movieVO.setMovie_id(movie_id);
		movieVO.setMovie_type_id(movie_type_id);
		
		dao.updateType(movieVO);
		
		return movieVO;
	}
	
	
	
	
	
	
	
	
	
//movie_state_id=?, movie_rating_id=?, movie_ch=?, movie_en=?, 
//movie_runtime=?, release_date=?, movie_poster=?, movie_slide_poster=?, 
//movie_intro=?, casts=?, director=?, trailer=? where movie_id = ?
	
	public MovieVO updateMovie(Integer movie_id,Integer movie_state_id, Integer movie_rating_id, String movie_ch, String movie_en,
			Integer movie_runtime, java.sql.Date release_date, byte[] movie_poster, byte[] movie_slide_poster,
			String movie_intro, String casts, String director, String trailer, Timestamp movie_updated_time) {
		
		MovieVO movieVO = new MovieVO();
		
		movieVO.setMovie_id(movie_id);
		movieVO.setMovie_state_id(movie_state_id);
		movieVO.setMovie_rating_id(movie_rating_id);
		movieVO.setMovie_ch(movie_ch);
		movieVO.setMovie_en(movie_en);
		movieVO.setMovie_runtime(movie_runtime);
		movieVO.setRelease_date(release_date);
		movieVO.setMovie_poster(movie_poster);
		movieVO.setMovie_slide_poster(movie_slide_poster);
		movieVO.setMovie_intro(movie_intro);
		movieVO.setCasts(casts);
		movieVO.setDirector(director);
		movieVO.setTrailer(trailer);
		movieVO.setMovie_updated_time(movie_updated_time);
		dao.update(movieVO);
		
		return movieVO;
	}
	
	
	public void deleteMovie(Integer movie_id) {
		dao.delete(movie_id);
	}
	
	public void deleteType(Integer movie_id) {
		dao.delete_movie_type(movie_id);
	}
	
	public MovieVO getOneMovie(Integer movie_id) {
		return dao.findByPrimaryKey(movie_id);
	}
	
	public List<MovieVO> getAll(){
		return dao.getAll();
	}
	

	public List<MovieVO> getMovie_New(){
		return dao.getMovie_New();
	}

	public List<MovieVO> getByState_id(Integer movie_state_id){
		return dao.getByState_id( movie_state_id);
	}


	
	public List<MovieVO> getType_New(Integer movie_id){
		return dao.getMovie_type(movie_id);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
