package web.hall_seat.entity;

import java.sql.Date;

public class Hall_SeatVO implements java.io.Serializable {
	
	private Integer seat_id;
	private Integer seat_state;
	private String seat_name;
	private Integer seat_row;
	private Integer seat_col;
	private Integer seat_left;
	private Integer seat_right;
	private Integer seat_row_aisle1;
	private Integer seat_row_aisle2;
	private Integer seat_no;
	private Integer hall_id;
	private String hall_seat_state_total;
	private String hall_name;
	private Date hall_update;
	
	public Integer getSeat_id() {
		return seat_id;
	}
	public void setSeat_id(Integer seat_id) {
		this.seat_id = seat_id;
	}
	public Integer getSeat_state() {
		return seat_state;
	}
	public void setSeat_state(Integer seat_state) {
		this.seat_state = seat_state;
	}
	public String getSeat_name() {
		return seat_name;
	}
	public void setSeat_name(String seat_name) {
		this.seat_name = seat_name;
	}
	public Integer getSeat_row() {
		return seat_row;
	}
	public void setSeat_row(Integer seat_row) {
		this.seat_row = seat_row;
	}
	public Integer getSeat_col() {
		return seat_col;
	}
	public void setSeat_col(Integer seat_col) {
		this.seat_col = seat_col;
	}
	public Integer getSeat_left() {
		return seat_left;
	}
	public void setSeat_left(Integer seat_left) {
		this.seat_left = seat_left;
	}
	public Integer getSeat_right() {
		return seat_right;
	}
	public void setSeat_right(Integer seat_right) {
		this.seat_right = seat_right;
	}
	public Integer getSeat_row_aisle1() {
		return seat_row_aisle1;
	}
	public void setSeat_row_aisle1(Integer seat_row_aisle1) {
		this.seat_row_aisle1 = seat_row_aisle1;
	}
	public Integer getSeat_row_aisle2() {
		return seat_row_aisle2;
	}
	public void setSeat_row_aisle2(Integer seat_row_aisle2) {
		this.seat_row_aisle2 = seat_row_aisle2;
	}
	public Integer getSeat_no() {
		return seat_no;
	}
	public void setSeat_no(Integer seat_no) {
		this.seat_no = seat_no;
	}
	public Integer getHall_id() {
		return hall_id;
	}
	public void setHall_id(Integer hall_id) {
		this.hall_id = hall_id;
	}
	public String getHall_seat_state_total() {
		return hall_seat_state_total;
	}
	public void setHall_seat_state_total(String hall_seat_state_total) {
		this.hall_seat_state_total = hall_seat_state_total;
	}
	public String getHall_name() {
		return hall_name;
	}
	public void setHall_name(String hall_name) {
		this.hall_name = hall_name;
	}
	public Date getHall_update() {
		return hall_update;
	}
	public void setHall_update(Date hall_update) {
		this.hall_update = hall_update;
	}
	
}