package web.ticket_list.service;

import java.sql.Date;
import java.util.List;

import web.ticket_order.dao.TicketDAO;
import web.ticket_order.dao.TicketDAO_interface;
import web.ticket_list.dao.Ticket_listDAO;
import web.ticket_list.dao.Ticket_listDAO_interface;
import web.ticket_list.entity.Ticket_listVO;


public class Ticket_listService {
	
	private Ticket_listDAO_interface dao;

	public Ticket_listService() {
		dao = new Ticket_listDAO();
	}

	public Ticket_listVO addTicket_list( Integer ticket_orders_id, Integer seat_id,
			Integer movie_time_id,Integer ticket_num, Integer ticket_price) {

		Ticket_listVO ticket_listVO = new Ticket_listVO();

		ticket_listVO.setTicket_orders_id(ticket_orders_id);
		ticket_listVO.setSeat_id(seat_id);
		ticket_listVO.setMovie_time_id(movie_time_id);
		ticket_listVO.setTicket_num(ticket_num);
		ticket_listVO.setTicket_price(ticket_price);
		dao.insert(ticket_listVO);

		return ticket_listVO;
	}

	public Ticket_listVO updateTicket_list(Integer ticket_list_id, Integer ticket_orders_id, Integer seat_id,
			Integer movie_time_id,Integer ticket_num, Integer ticket_price){

		Ticket_listVO ticket_listVO = new Ticket_listVO();

		ticket_listVO.setTicket_list_id(ticket_list_id);
		ticket_listVO.setTicket_orders_id(ticket_orders_id);
		ticket_listVO.setSeat_id(seat_id);
		ticket_listVO.setMovie_time_id(movie_time_id);
		ticket_listVO.setTicket_num(ticket_num);
		ticket_listVO.setTicket_price(ticket_price);
		dao.update(ticket_listVO);

		return ticket_listVO;
	}

	public void deleteTicket_list(Integer ticket_list_id) {
		dao.delete(ticket_list_id);
	}

	public Ticket_listVO getOneTicket_list(Integer ticket_list_id) {
		return dao.findByPrimaryKey(ticket_list_id);
	}

	public List<Ticket_listVO> getAll() {
		return dao.getAll();
	}
	
}
