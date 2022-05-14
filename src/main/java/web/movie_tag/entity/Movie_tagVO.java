package web.movie_tag.entity;

public class Movie_tagVO implements java.io.Serializable{

	private Integer movie_tag_id;
	private Integer movie_id;
	private Integer movie_type_id;
	public Integer getMovie_tag_id() {
		return movie_tag_id;
	}
	public void setMovie_tag_id(Integer movie_tag_id) {
		this.movie_tag_id = movie_tag_id;
	}
	public Integer getMovie_type_id() {
		return movie_type_id;
	}
	public void setMovie_type_id(Integer movie_type_id) {
		this.movie_type_id = movie_type_id;
	}
	public Integer getMovie_id() {
		return movie_id;
	}
	public void setMovie_id(Integer movie_id) {
		this.movie_id = movie_id;
	}
	@Override
	public String toString() {
		return "Movie_tagVO [movie_tag_id=" + movie_tag_id + ", movie_id=" + movie_id + ", movie_type_id="
				+ movie_type_id + "]";
	}
	
	
}
