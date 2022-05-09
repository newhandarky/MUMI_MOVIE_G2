package web.hall_seat.dao;

import java.util.*;

import web.hall_seat.entity.Hall_SeatVO;

public interface Hall_SeatDAO_interface {
          public void insert(Hall_SeatVO hall_seatVO);
          public void update(Hall_SeatVO hall_seatVO);
//          public void delete(Integer emp_id);
          public Hall_SeatVO findByPrimaryKey(Integer hall_id);
//          public List<Hall_SeatVO> getAll();
          //萬用複合查詢(傳入參數型態Map)(回傳 List)
//        public List<EmpVO> getAll(Map<String, String[]> map); 
          public List<Hall_SeatVO> getSeatInfo(Integer hall_id);
}
