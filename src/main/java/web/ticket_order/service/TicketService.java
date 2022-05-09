package web.ticket_order.service;

import java.sql.Date;
import java.util.List;

import web.ticket_order.dao.TicketDAO;
import web.ticket_order.dao.TicketDAO_interface;
import web.ticket_order.entity.TicketVO;

public class TicketService {
	
	private TicketDAO_interface dao;

	public TicketService() {
		dao = new TicketDAO();
	}

	public TicketVO addTicket( Integer mem_id, Date buyticket_date,
			byte[] ticket_qrcode, Integer total_price) {

		TicketVO ticketVO = new TicketVO();

		ticketVO.setMem_id(mem_id);
		ticketVO.setBuyticket_date(buyticket_date);
		ticketVO.setTicket_qrcode(ticket_qrcode);
		ticketVO.setTotal_price(total_price);
		dao.insert(ticketVO);

		return ticketVO;
	}

	public TicketVO updateTicket(Integer ticket_orders_id, Integer mem_id, Date buyticket_date,
			byte[] ticket_qrcode, Integer total_price){

		TicketVO ticketVO = new TicketVO();

		ticketVO.setTicket_orders_id(ticket_orders_id);
		ticketVO.setMem_id(mem_id);
		ticketVO.setBuyticket_date(buyticket_date);
		ticketVO.setTicket_qrcode(ticket_qrcode);
		ticketVO.setTotal_price(total_price);
		dao.update(ticketVO);

		return ticketVO;
	}

	public void deleteTicket(Integer ticket_orders_id) {
		dao.delete(ticket_orders_id);
	}

	public TicketVO getOneTicket(Integer ticket_orders_id) {
		return dao.findByPrimaryKey(ticket_orders_id);
	}

	public List<TicketVO> getAll() {
		return dao.getAll();
	}
}
