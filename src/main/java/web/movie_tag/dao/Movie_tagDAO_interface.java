package web.movie_tag.dao;

import java.util.List;

import web.movie_tag.entity.Movie_tagVO;

public interface Movie_tagDAO_interface {

	public void insert(Movie_tagVO Movie_tagVO);
	public void update(Movie_tagVO Movie_tagVO);
	public void delete(Integer movie_tag_id);
	public Movie_tagVO findByPrimaryKey(Integer movie_tag_id);
	public List<Movie_tagVO> getAll();
	public List<Movie_tagVO> getAllCh();
	public List<Movie_tagVO> getMovieCh();
	public List<Movie_tagVO> getTagByMovie(Integer movie_id);
	
}
