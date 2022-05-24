package web.movie_time.service;

import java.util.List;

import web.movie_time.dao.Movie_timeDAO;
import web.movie_time.dao.Movie_timeDAO_interface;
import web.movie_time.entity.Movie_timeVO;

public class Movie_timeService {

	private Movie_timeDAO_interface dao;

	public Movie_timeService() {
		dao = new Movie_timeDAO();
	}

//	hall_id, movie_id, showing, showing_date
	public Movie_timeVO addMovie_time(Integer hall_id, Integer movie_id, Integer showing, java.sql.Date showing_date) {

		Movie_timeVO movie_timeVO = new Movie_timeVO();

		movie_timeVO.setHall_id(hall_id);
		movie_timeVO.setMovie_id(movie_id);
		movie_timeVO.setShowing(showing);
		movie_timeVO.setShowing_date(showing_date);
		dao.insert(movie_timeVO);

		return movie_timeVO;

	}

//	hall_id=?, movie_id=?, showing=?, showing_date=? "
//	+ where movie_time_id=?"
	public Movie_timeVO updateMovie_time(Integer movie_time_id, Integer hall_id, Integer movie_id, Integer showing,
			java.sql.Date showing_date) {

		Movie_timeVO movie_timeVO = new Movie_timeVO();

		movie_timeVO.setMovie_time_id(movie_time_id);
		movie_timeVO.setHall_id(hall_id);
		movie_timeVO.setMovie_id(movie_id);
		movie_timeVO.setShowing(showing);
		movie_timeVO.setShowing_date(showing_date);
		dao.update(movie_timeVO);

		return movie_timeVO;

	}

	public void deleteMovie_time(Integer movie_time_id) {
		dao.delete(movie_time_id);
	}

	public Movie_timeVO getOneMovie_time(Integer movie_time_id) {
		return dao.findByPrimaryKey(movie_time_id);
	}

	public List<Movie_timeVO> getAll() {
		return dao.getAll();
	}

	public List<Movie_timeVO> getAllCh() {
		return dao.getAllCh();
	}

	public Movie_timeVO getAlreadyHad(Integer hall_id, Integer showing, java.sql.Date showing_date) {
		return dao.findIfAlreadyHad(hall_id, showing, showing_date);
	}

	public List<Movie_timeVO> getReleasingNowCh() {
		return dao.getNowCh();
	}

	public List<Movie_timeVO> getHall_Name() {
		return dao.getHall_Name();
	}

}
