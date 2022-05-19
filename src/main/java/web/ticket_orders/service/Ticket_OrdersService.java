package web.ticket_orders.service;

import java.util.List;

import web.select_seat.entity.Select_SeatVO;
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
	
	public List<Ticket_OrdersVO> getSeat(Integer movie_time_id) {
		return dao.getSeat(movie_time_id);
	}
	
	public Ticket_OrdersVO choose_Seat(Integer movie_time_id, String seat_select_state) {

		Ticket_OrdersVO ticket_ordersVO = new Ticket_OrdersVO();
		ticket_ordersVO.setMovie_time_id(movie_time_id);
		ticket_ordersVO.setSeat_select_state(seat_select_state);
		dao.choose_Seat(ticket_ordersVO);

		return ticket_ordersVO;
	}
	
	public List<Ticket_OrdersVO> getTicket_List_Id_Number(Integer mem_id) {
		return dao.getTicket_List_Id_Number(mem_id);
	}
	
//
//	public Hall_SeatVO updateSeat_State(Integer seat_id, Integer seat_state) {
//
//		Hall_SeatVO hall_seatVO = new Hall_SeatVO();
//		hall_seatVO.setSeat_id(seat_id);
//		hall_seatVO.setSeat_state(seat_state);
//		dao.update(hall_seatVO);
//
//		return hall_seatVO;
//	}
//
//	public void deleteHall(Integer hall_id) {
//		dao.delete(hall_id);
//	}
//
//	public Hall_SeatVO getOneHall_Seat(Integer hall_id) {
//		return dao.findByPrimaryKey(hall_id);
//	}
//
//	public List<Hall_SeatVO> getHall_Name() {
//		return dao.getHall_Name();
//	}
	
	public List<Ticket_OrdersVO> getMoive_Id(Integer movie_id) {
		return dao.getMoive_Id(movie_id);
	}
	
//	public List<Ticket_OrdersVO> getMem_Ticket_Orders_Id(Integer mem_id) {
//		return dao.getMem_Ticket_Orders_Id(mem_id);
//	}
	
//	public Hall_SeatVO addHall(String hall_name) {
//
//		Hall_SeatVO hall_seatVO = new Hall_SeatVO();
//		
//		hall_seatVO.setHall_name(hall_name);
//		dao.insert_hall(hall_seatVO);
//
//		return hall_seatVO;
//	}
//	
//	public List<Hall_SeatVO> getHall_New() {
//		return dao.getHall_New();
//	}
}
