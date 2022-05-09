package web.ticket_list.entity;

import java.sql.Date;

public class Ticket_listVO implements java.io.Serializable{

		private Integer ticket_list_id;
		private Integer ticket_orders_id;
		private Integer seat_id;
		private Integer movie_time_id;
		private Integer ticket_num;
		private Integer ticket_price;
		
		public Integer getTicket_list_id() {
			return ticket_list_id;
		}
		public void setTicket_list_id(Integer ticket_list_id) {
			this.ticket_list_id = ticket_list_id;
		}
		public Integer getTicket_orders_id() {
			return ticket_orders_id;
		}
		public void setTicket_orders_id(Integer ticket_orders_id) {
			this.ticket_orders_id = ticket_orders_id;
		}
		public Integer getSeat_id() {
			return seat_id;
		}
		public void setSeat_id(Integer seat_id) {
			this.seat_id = seat_id;
		}
		public Integer getMovie_time_id() {
			return movie_time_id;
		}
		public void setMovie_time_id(Integer movie_time_id) {
			this.movie_time_id = movie_time_id;
		}
		public Integer getTicket_num() {
			return ticket_num;
		}
		public void setTicket_num(Integer ticket_num) {
			this.ticket_num = ticket_num;
		}
		public Integer getTicket_price() {
			return ticket_price;
		}
		public void setTicket_price(Integer ticket_price) {
			this.ticket_price = ticket_price;
		}
		

	
}
