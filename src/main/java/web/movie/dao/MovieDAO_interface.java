package web.movie.dao;

import java.util.List;

import web.movie.entity.MovieVO;

public interface MovieDAO_interface {
	public void insert(MovieVO movieVO);

	public void addType(MovieVO movieVO);

	public void update(MovieVO movieVO);
	
	public void updateType(MovieVO movieVO);

	public void delete(Integer movie_id);

	public MovieVO findByPrimaryKey(Integer movie_id);

	public List<MovieVO> getAll();


	public List<MovieVO> getMovie_New();

	public List<MovieVO> getMovie_type(Integer movie_id);
	
	public void delete_movie_type(Integer movie_id);


	public List<MovieVO> getByState_id(Integer movie_id);

}
