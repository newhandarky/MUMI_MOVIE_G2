package web.movie.entity;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Arrays;

public class MovieVO implements java.io.Serializable{

	private Integer movie_id;
	private Integer movie_state_id;
	private Integer movie_rating_id;
	private Integer emp_id;
	private Timestamp movie_updated_time;
	private String movie_ch;
	private String movie_en;
	private Integer movie_runtime;
	private Date release_date;
	private byte[] movie_poster;
	private byte[] movie_slide_poster;
	private String movie_intro;
	private String casts;
	private String director;
	private String trailer;
	private Integer expect_num;
	private Integer sati_num;
	private Integer movie_likes;
	private Integer expect_total;
	private Integer sati_total;
	private Integer movie_type_id;
	private Integer movie_tag_id;
	private String movie_type_ch;
	
	public String getMovie_type_ch() {
		return movie_type_ch;
	}
	public void setMovie_type_ch(String movie_type_ch) {
		this.movie_type_ch = movie_type_ch;
	}
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
	public Integer getMovie_state_id() {
		return movie_state_id;
	}
	public void setMovie_state_id(Integer movie_state_id) {
		this.movie_state_id = movie_state_id;
	}
	public Integer getMovie_rating_id() {
		return movie_rating_id;
	}
	public void setMovie_rating_id(Integer movie_rating_id) {
		this.movie_rating_id = movie_rating_id;
	}
	public Timestamp getMovie_updated_time() {
		return movie_updated_time;
	}
	public void setMovie_updated_time(Timestamp movie_updated_time) {
		this.movie_updated_time = movie_updated_time;
	}
	public String getMovie_ch() {
		return movie_ch;
	}
	public void setMovie_ch(String movie_ch) {
		this.movie_ch = movie_ch;
	}
	public String getMovie_en() {
		return movie_en;
	}
	public void setMovie_en(String movie_en) {
		this.movie_en = movie_en;
	}
	public Integer getMovie_runtime() {
		return movie_runtime;
	}
	public void setMovie_runtime(Integer movie_runtime) {
		this.movie_runtime = movie_runtime;
	}
	public Date getRelease_date() {
		return release_date;
	}
	public void setRelease_date(Date release_date) {
		this.release_date = release_date;
	}
	public byte[] getMovie_poster() {
		return movie_poster;
	}
	public void setMovie_poster(byte[] movie_poster) {
		this.movie_poster = movie_poster;
	}
	public byte[] getMovie_slide_poster() {
		return movie_slide_poster;
	}
	public void setMovie_slide_poster(byte[] movie_slide_poster) {
		this.movie_slide_poster = movie_slide_poster;
	}
	public String getMovie_intro() {
		return movie_intro;
	}
	public void setMovie_intro(String movie_intro) {
		this.movie_intro = movie_intro;
	}
	public String getCasts() {
		return casts;
	}
	public void setCasts(String casts) {
		this.casts = casts;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getTrailer() {
		return trailer;
	}
	public void setTrailer(String trailer) {
		this.trailer = trailer;
	}
	public Integer getExpect_num() {
		return expect_num;
	}
	public void setExpect_num(Integer expect_num) {
		this.expect_num = expect_num;
	}
	public Integer getSati_num() {
		return sati_num;
	}
	@Override
	public String toString() {
		return "MovieVO [movie_id=" + movie_id + ", movie_state_id=" + movie_state_id + ", movie_rating_id="
				+ movie_rating_id + ", emp_id=" + emp_id + ", movie_updated_time=" + movie_updated_time + ", movie_ch="
				+ movie_ch + ", movie_en=" + movie_en + ", movie_runtime=" + movie_runtime + ", release_date="
				+ release_date + ", movie_poster=" + Arrays.toString(movie_poster) + ", movie_slide_poster="
				+ Arrays.toString(movie_slide_poster) + ", movie_intro=" + movie_intro + ", casts=" + casts
				+ ", director=" + director + ", trailer=" + trailer + ", expect_num=" + expect_num + ", sati_num="
				+ sati_num + ", movie_likes=" + movie_likes + ", expect_total=" + expect_total + ", sati_total="
				+ sati_total + "]";
	}
	public void setSati_num(Integer sati_num) {
		this.sati_num = sati_num;
	}
	public Integer getMovie_likes() {
		return movie_likes;
	}
	public void setMovie_likes(Integer movie_likes) {
		this.movie_likes = movie_likes;
	}
	public Integer getExpect_total() {
		return expect_total;
	}
	public void setExpect_total(Integer expect_total) {
		this.expect_total = expect_total;
	}
	public Integer getSati_total() {
		return sati_total;
	}
	public void setSati_total(Integer sati_total) {
		this.sati_total = sati_total;
	}
	public Integer getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(Integer emp_id) {
		this.emp_id = emp_id;
	}
	
	
}
