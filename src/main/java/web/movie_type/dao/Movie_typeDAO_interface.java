package web.movie_type.dao;

import java.util.List;

import web.movie_type.entity.Movie_typeVO;

public interface Movie_typeDAO_interface {

	
	public void insert(Movie_typeVO Movie_typeVO);
	public void update(Movie_typeVO Movie_typeVO);
	public void delete(Integer movie_type_id);
	public Movie_typeVO findByPrimaryKey(Integer movie_type_id);
	public List<Movie_typeVO> getAll();
}
