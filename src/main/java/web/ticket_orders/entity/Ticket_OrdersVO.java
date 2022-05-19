package web.ticket_orders.entity;

import java.sql.Date;

public class Ticket_OrdersVO implements java.io.Serializable {
	
	private Integer movie_time_id;
	private Integer hall_id;
	private Integer movie_id;
	private String seat_select_state;
	private Integer showing;
	private Date showing_date;
	private Integer ticket_orders_id;
	private Integer mem_id;
	private Date buyticket_date;
	private Integer total_price;
	private Integer ticket_list_id;
	private String select_seat_name;
	private Integer ticket_number;
	
	public Integer getMovie_time_id() {
		return movie_time_id;
	}
	public void setMovie_time_id(Integer movie_time_id) {
		this.movie_time_id = movie_time_id;
	}
	public Integer getHall_id() {
		return hall_id;
	}
	public void setHall_id(Integer hall_id) {
		this.hall_id = hall_id;
	}
	public Integer getMovie_id() {
		return movie_id;
	}
	public void setMovie_id(Integer movie_id) {
		this.movie_id = movie_id;
	}
	public String getSeat_select_state() {
		return seat_select_state;
	}
	public void setSeat_select_state(String seat_select_state) {
		this.seat_select_state = seat_select_state;
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
	public Integer getTicket_orders_id() {
		return ticket_orders_id;
	}
	public void setTicket_orders_id(Integer ticket_orders_id) {
		this.ticket_orders_id = ticket_orders_id;
	}
	public Integer getMem_id() {
		return mem_id;
	}
	public void setMem_id(Integer mem_id) {
		this.mem_id = mem_id;
	}
	public Date getBuyticket_date() {
		return buyticket_date;
	}
	public void setBuyticket_date(Date buyticket_date) {
		this.buyticket_date = buyticket_date;
	}
	public Integer getTotal_price() {
		return total_price;
	}
	public void setTotal_price(Integer total_price) {
		this.total_price = total_price;
	}
	public Integer getTicket_list_id() {
		return ticket_list_id;
	}
	public void setTicket_list_id(Integer ticket_list_id) {
		this.ticket_list_id = ticket_list_id;
	}
	public String getSelect_seat_name() {
		return select_seat_name;
	}
	public void setSelect_seat_name(String select_seat_name) {
		this.select_seat_name = select_seat_name;
	}
	public Integer getTicket_number() {
		return ticket_number;
	}
	public void setTicket_number(Integer ticket_number) {
		this.ticket_number = ticket_number;
	}
	
}