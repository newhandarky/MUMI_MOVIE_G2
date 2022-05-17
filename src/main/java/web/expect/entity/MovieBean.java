package web.expect.entity;

import java.sql.Date;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="MOVIE")
public class MovieBean {
	@Id
	@Column
	private int movie_id;
	@Column
	private int movie_rating_id;
	@Column
	private int	movie_state_id;
	@Column
	private int emp_id; 
	@Column
	private Date movie_updated_time; 
	@Column
	private String movie_ch;
	@Column
	private String movie_en;
	@Column
	private int movie_runtime;
	@Column
	private Date release_date;
	@Column
	private byte[] movie_poster;
	@Column
	private byte[] movie_slide_poster;
	@Column
	private String movie_intro;
	@Column
	private String casts;
	@Column
	private	String director;
	@Column
	private	String trailer;
	@Column
	private	int expect_num;
	@Column
	private	int expect_total;
	@Column
	private	int sati_num;
	@Column
	private int sati_total;
	@Column
	private int movie_likes;
	@Override
	public String toString() {
		return "MovieBean [movie_id=" + movie_id + ", movie_rating_id=" + movie_rating_id + ", movie_state_id="
				+ movie_state_id + ", emp_id=" + emp_id + ", movie_updated_time=" + movie_updated_time + ", movie_ch="
				+ movie_ch + ", movie_en=" + movie_en + ", movie_runtime=" + movie_runtime + ", release_date="
				+ release_date + ", movie_poster=" + Arrays.toString(movie_poster) + ", movie_slide_poster="
				+ Arrays.toString(movie_slide_poster) + ", movie_intro=" + movie_intro + ", casts=" + casts
				+ ", director=" + director + ", trailer=" + trailer + ", expect_num=" + expect_num + ", expect_total="
				+ expect_total + ", sati_num=" + sati_num + ", sati_total=" + sati_total + ", movie_likes="
				+ movie_likes + "]";
	}
	public int getMovie_id() {
		return movie_id;
	}
	public void setMovie_id(int movie_id) {
		this.movie_id = movie_id;
	}
	public int getMovie_rating_id() {
		return movie_rating_id;
	}
	public void setMovie_rating_id(int movie_rating_id) {
		this.movie_rating_id = movie_rating_id;
	}
	public int getMovie_state_id() {
		return movie_state_id;
	}
	public void setMovie_state_id(int movie_state_id) {
		this.movie_state_id = movie_state_id;
	}
	public int getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(int emp_id) {
		this.emp_id = emp_id;
	}
	public Date getMovie_updated_time() {
		return movie_updated_time;
	}
	public void setMovie_updated_time(Date movie_updated_time) {
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
	public int getMovie_runtime() {
		return movie_runtime;
	}
	public void setMovie_runtime(int movie_runtime) {
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
	public int getExpect_num() {
		return expect_num;
	}
	public void setExpect_num(int expect_num) {
		this.expect_num = expect_num;
	}
	public int getExpect_total() {
		return expect_total;
	}
	public void setExpect_total(int expect_total) {
		this.expect_total = expect_total;
	}
	public int getSati_num() {
		return sati_num;
	}
	public void setSati_num(int sati_num) {
		this.sati_num = sati_num;
	}
	public int getSati_total() {
		return sati_total;
	}
	public void setSati_total(int sati_total) {
		this.sati_total = sati_total;
	}
	public int getMovie_likes() {
		return movie_likes;
	}
	public void setMovie_likes(int movie_likes) {
		this.movie_likes = movie_likes;
	}

}
