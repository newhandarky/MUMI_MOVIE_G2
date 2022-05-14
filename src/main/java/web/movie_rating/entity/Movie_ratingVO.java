package web.movie_rating.entity;

public class Movie_ratingVO implements java.io.Serializable{
	
	private Integer movie_rating_id;
	private String movie_rating_ch;
	private String movie_rating_en;
	private byte[] movie_rating_pic;
	public Integer getMovie_rating_id() {
		return movie_rating_id;
	}
	public void setMovie_rating_id(Integer movie_rating_id) {
		this.movie_rating_id = movie_rating_id;
	}
	public String getMovie_rating_ch() {
		return movie_rating_ch;
	}
	public void setMovie_rating_ch(String movie_rating_ch) {
		this.movie_rating_ch = movie_rating_ch;
	}
	public String getMovie_rating_en() {
		return movie_rating_en;
	}
	public void setMovie_rating_en(String movie_rating_en) {
		this.movie_rating_en = movie_rating_en;
	}
	public byte[] getMovie_rating_pic() {
		return movie_rating_pic;
	}
	public void setMovie_rating_pic(byte[] movie_rating_pic) {
		this.movie_rating_pic = movie_rating_pic;
	}
	
	
	
}
