package web.movie_time.entity;

import java.sql.Timestamp;

public class HallVO {

	private Integer hall_id;
	private Integer seat_total;
	private String hall_name;
	private Timestamp hall_update;
	public Integer getHall_id() {
		return hall_id;
	}
	public void setHall_id(Integer hall_id) {
		this.hall_id = hall_id;
	}
	public Integer getSeat_total() {
		return seat_total;
	}
	public void setSeat_total(Integer seat_total) {
		this.seat_total = seat_total;
	}
	public String getHall_name() {
		return hall_name;
	}
	public void setHall_name(String hall_name) {
		this.hall_name = hall_name;
	}
	public Timestamp getHall_update() {
		return hall_update;
	}
	public void setHall_update(Timestamp hall_update) {
		this.hall_update = hall_update;
	}
	
	
	
	
}
