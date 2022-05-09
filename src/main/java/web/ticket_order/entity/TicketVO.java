package web.ticket_order.entity;

import java.sql.Date;

public class TicketVO implements java.io.Serializable{
	private Integer ticket_orders_id;
	private Integer mem_id;
	private Date buyticket_date;
	private byte[] ticket_qrcode;
	private Integer total_price;
	
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
	public byte[] getTicket_qrcode() {
		return ticket_qrcode;
	}
	public void setTicket_qrcode(byte[] ticket_qrcode) {
		this.ticket_qrcode = ticket_qrcode;
	}
	public Integer getTotal_price() {
		return total_price;
	}
	public void setTotal_price(Integer total_price) {
		this.total_price = total_price;
	}
}
