package web.movie_type.entity;

public class Movie_typeVO implements java.io.Serializable{

	private Integer movie_type_id;
	private String movie_type_en;
	private String movie_type_ch;
	public Integer getMovie_type_id() {
		return movie_type_id;
	}
	public void setMovie_type_id(Integer movie_type_id) {
		this.movie_type_id = movie_type_id;
	}
	public String getMovie_type_en() {
		return movie_type_en;
	}
	public void setMovie_type_en(String movie_type_en) {
		this.movie_type_en = movie_type_en;
	}
	public String getMovie_type_ch() {
		return movie_type_ch;
	}
	public void setMovie_type_ch(String movie_type_ch) {
		this.movie_type_ch = movie_type_ch;
	}
	
}
