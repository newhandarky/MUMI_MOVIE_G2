package web.movie_rating.dao;

import java.util.List;

import web.movie_rating.entity.Movie_ratingVO;
import web.movie_tag.entity.Movie_tagVO;

public interface Movie_ratingDAO_interface {

	public void insert(Movie_ratingVO Movie_ratingVO);
	public void update(Movie_ratingVO Movie_ratingVO);
	public void delete(Integer movie_rating_id);
	public Movie_ratingVO findByPrimaryKey(Integer movie_rating_id);
	public List<Movie_ratingVO> getAll();
	
}
