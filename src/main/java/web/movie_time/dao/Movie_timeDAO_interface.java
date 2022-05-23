package web.movie_time.dao;

import java.util.List;

import web.movie_time.entity.Movie_timeVO;

public interface Movie_timeDAO_interface {

	public void insert(Movie_timeVO movie_timeVO);

	public void update(Movie_timeVO movie_timeVO);

	public void delete(Integer movie_time_id);

	public Movie_timeVO findByPrimaryKey(Integer movie_time_id);

	public List<Movie_timeVO> getAll();
	
	public Movie_timeVO findIfAlreadyHad(Integer hall, Integer showing, java.sql.Date showing_date);
	
	public List<Movie_timeVO> getNowCh();
	
	public List<Movie_timeVO> getAllCh();

	public List<Movie_timeVO> getHall_Name();
}
