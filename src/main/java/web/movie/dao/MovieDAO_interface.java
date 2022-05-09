package web.movie.dao;

import java.util.*;

import web.movie.entity.MovieVO;

public interface MovieDAO_interface {
	public void insert(MovieVO movieVO);
	public void update(MovieVO movieVO);
	public void delete(Integer movie_id);
	public MovieVO findByPrimaryKey(Integer movie_id);
	public List<MovieVO> getAll();
}
