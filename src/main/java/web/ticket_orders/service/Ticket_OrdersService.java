package web.ticket_orders.service;

import java.util.List;

import web.ticket_orders.dao.Ticket_OrdersDAO;
import web.ticket_orders.dao.Ticket_OrdersDAO_interface;
import web.ticket_orders.entity.Ticket_OrdersVO;

public class Ticket_OrdersService {

	private Ticket_OrdersDAO_interface dao;

	public Ticket_OrdersService() {
		dao = new Ticket_OrdersDAO();
	}

	public Ticket_OrdersVO addOrders(Integer mem_id) {
		
		Ticket_OrdersVO ticket_ordersVO = new Ticket_OrdersVO();
		
		ticket_ordersVO.setMem_id(mem_id);
		dao.addOrders(ticket_ordersVO);
		
		return ticket_ordersVO;
	}
	
	public Ticket_OrdersVO addTime_Number(Integer mem_id, Integer movie_time_id, Integer ticket_number) {

		Ticket_OrdersVO ticket_ordersVO = new Ticket_OrdersVO();
		
		ticket_ordersVO.setMem_id(mem_id);
		ticket_ordersVO.setMovie_time_id(movie_time_id);
		ticket_ordersVO.setTicket_number(ticket_number);

		dao.addTime_Number(ticket_ordersVO);

		return ticket_ordersVO;
	}
	
	
	public Ticket_OrdersVO choose_Seat(Integer movie_time_id, String seat_select_state, String select_seat_name, Integer ticket_list_id) {

		Ticket_OrdersVO ticket_ordersVO = new Ticket_OrdersVO();
		ticket_ordersVO.setMovie_time_id(movie_time_id);
		ticket_ordersVO.setSeat_select_state(seat_select_state);
		ticket_ordersVO.setSelect_seat_name(select_seat_name);
		ticket_ordersVO.setTicket_list_id(ticket_list_id);
		
		dao.choose_Seat(ticket_ordersVO);

		return ticket_ordersVO;
	}
	
	public List<Ticket_OrdersVO> getSeat(Integer movie_time_id) {
		return dao.getSeat(movie_time_id);
	}
	
	public List<Ticket_OrdersVO> getTicket_List_Id_Number(Integer mem_id) {
		return dao.getTicket_List_Id_Number(mem_id);
	}
	
	public List<Ticket_OrdersVO> getList_Ticket_Price(Integer mem_id) {
		return dao.getList_Ticket_Price(mem_id);
	}
	
	public List<Ticket_OrdersVO> getMem_Order(Integer mem_id) {
		return dao.getMem_Order(mem_id);
	}
	
	public List<Ticket_OrdersVO> getOnline_Moive() {
		return dao.getOnline_Moive();
	}

	public void deleteOrders(Integer mem_id) {
		dao.deleteOrders(mem_id);
	}
	
	public void deleteOrdersSeat(Integer mem_id) {
		dao.deleteOrdersSeat(mem_id);
	}
	
	public List<Ticket_OrdersVO> getMoive_Id(Integer movie_id) {
		return dao.getMoive_Id(movie_id);
	}
	
}
