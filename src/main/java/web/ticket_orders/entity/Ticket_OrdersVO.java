package web.ticket_orders.entity;

import java.sql.Date;

public class Ticket_OrdersVO implements java.io.Serializable {
	
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
	private String hall_name;
	private Date hall_update;
	private Integer movie_time_id;
	private Integer hall_id;
	private Integer movie_id;
	private String seat_select_state;
	private Integer showing;
	private Date showing_date;
	private Integer ticket_orders_id;
	private Integer mem_id;
	private Date buyticket_date;
	private Integer ticket_list_id;
	private String select_seat_name;	
	private Integer ticket_price;
	private Integer ticket_number;
	private Integer movie_rating_id;
	private String movie_ch;
	private String movie_en;
	private byte[] movie_poster;
	
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
	public Integer getTicket_price() {
		return ticket_price;
	}
	public void setTicket_price(Integer ticket_price) {
		this.ticket_price = ticket_price;
	}
	public Integer getTicket_number() {
		return ticket_number;
	}
	public void setTicket_number(Integer ticket_number) {
		this.ticket_number = ticket_number;
	}
	public Integer getMovie_rating_id() {
		return movie_rating_id;
	}
	public void setMovie_rating_id(Integer movie_rating_id) {
		this.movie_rating_id = movie_rating_id;
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
	public byte[] getMovie_poster() {
		return movie_poster;
	}
	public void setMovie_poster(byte[] movie_poster) {
		this.movie_poster = movie_poster;
	}
}