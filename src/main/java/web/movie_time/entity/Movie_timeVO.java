package web.movie_time.entity;

import java.sql.Date;

public class Movie_timeVO {
	
	private	Integer movie_time_id;
	private Integer movie_id;
	private Integer hall_id;
	private Integer showing;
	private String seat_select_state;
	private Date showing_date;
	private String movie_ch;
	private String hall_name;
	
	public String getHall_name() {
		return hall_name;
	}
	public void setHall_name(String hall_name) {
		this.hall_name = hall_name;
	}
	public String getMovie_ch() {
		return movie_ch;
	}
	public void setMovie_ch(String movie_ch) {
		this.movie_ch = movie_ch;
	}
	public String getSeat_select_state() {
		return seat_select_state;
	}
	public void setSeat_select_state(String seat_select_state) {
		this.seat_select_state = seat_select_state;
	}
	public Integer getMovie_time_id() {
		return movie_time_id;
	}
	public void setMovie_time_id(Integer movie_time_id) {
		this.movie_time_id = movie_time_id;
	}
	public Integer getMovie_id() {
		return movie_id;
	}
	public void setMovie_id(Integer movie_id) {
		this.movie_id = movie_id;
	}
	public Integer getHall_id() {
		return hall_id;
	}
	public void setHall_id(Integer hall_id) {
		this.hall_id = hall_id;
	}
	public Integer getShowing() {
		return showing;
	}
	public void setShowing(Integer showing) {
		this.showing = showing;
	}
	public Date getShowing_date() {
		return showing_date;
	}
	public void setShowing_date(Date showing_date) {
		this.showing_date = showing_date;
	}
	@Override
	public String toString() {
		return "Movie_timeVO [movie_time_id=" + movie_time_id + ", movie_id=" + movie_id + ", hall_id=" + hall_id
				+ ", showing=" + showing + ", seat_select_state=" + seat_select_state + ", showing_date=" + showing_date
				+ ", movie_ch=" + movie_ch + "]";
	}
	
	
}
