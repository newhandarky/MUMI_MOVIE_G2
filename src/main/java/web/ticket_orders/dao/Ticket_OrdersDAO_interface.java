package web.ticket_orders.dao;

import java.util.List;

import web.ticket_orders.entity.Ticket_OrdersVO;

public interface Ticket_OrdersDAO_interface {
	      public void addOrders(Ticket_OrdersVO ticket_ordersVO);
          public void addTime_Number(Ticket_OrdersVO ticket_ordersVO);
          public List<Ticket_OrdersVO> getSeat(Integer movie_time_id);
          public List<Ticket_OrdersVO> getTicket_List_Id_Number(Integer mem_id);
          public List<Ticket_OrdersVO> getList_Ticket_Price(Integer mem_id);
          public List<Ticket_OrdersVO> getMem_Order(Integer mem_id);
          public void choose_Seat(Ticket_OrdersVO ticket_ordersVO);
//          public void update(Hall_SeatVO hall_seatVO);
          public void deleteOrders(Integer mem_id);
          public void deleteOrdersSeat(Integer mem_id);
//          public Hall_SeatVO findByPrimaryKey(Integer hall_id);
//          public List<Hall_SeatVO> getHall_Name();
          //萬用複合查詢(傳入參數型態Map)(回傳 List)
//        public List<EmpVO> getAll(Map<String, String[]> map); 
          public List<Ticket_OrdersVO> getOnline_Moive();
          public List<Ticket_OrdersVO> getMoive_Id(Integer movie_id);
//          public void insert_hall(Hall_SeatVO hall_seatVO);
//          public List<Hall_SeatVO> getHall_New();
}
