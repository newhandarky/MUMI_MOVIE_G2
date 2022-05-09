package web.hall_seat.service;

import java.sql.Array;
import java.util.List;

import web.hall_seat.dao.Hall_SeatDAO;
import web.hall_seat.dao.Hall_SeatDAO_interface;
import web.hall_seat.entity.Hall_SeatVO;

public class Hall_SeatService {

	private Hall_SeatDAO_interface dao;

	public Hall_SeatService() {
		dao = new Hall_SeatDAO();
	}

	public Hall_SeatVO addHall_Seat(Integer hall_id, String seat_state, String seat_name, Integer seat_row, Integer seat_col, Integer seat_left, Integer seat_right, Integer seat_row_aisle1, Integer seat_row_aisle2, String seat_no) {

		Hall_SeatVO hall_seatVO = new Hall_SeatVO();
		
		hall_seatVO.setHall_id(hall_id);
		hall_seatVO.setSeat_state(seat_state);
		hall_seatVO.setSeat_name(seat_name);
		hall_seatVO.setSeat_row(seat_row);
		hall_seatVO.setSeat_col(seat_col);
		hall_seatVO.setSeat_left(seat_left);
		hall_seatVO.setSeat_right(seat_right);
		hall_seatVO.setSeat_row_aisle1(seat_row_aisle1);
		hall_seatVO.setSeat_row_aisle2(seat_row_aisle2);
		hall_seatVO.setSeat_no(seat_no);

		dao.insert(hall_seatVO);

		return hall_seatVO;
	}

	public Hall_SeatVO updateSeat_State(String seat_id, String seat_state) {

		Hall_SeatVO hall_seatVO = new Hall_SeatVO();
		hall_seatVO.setSeat_id(seat_id);
		hall_seatVO.setSeat_state(seat_state);
		dao.update(hall_seatVO);

		return hall_seatVO;
	}
//
//	public void deleteEmp(Integer emp_id) {
//		dao.delete(emp_id);
//	}
//
	public Hall_SeatVO getOneHall_Seat(Integer hall_id) {
		return dao.findByPrimaryKey(hall_id);
	}
//
//	public List<Hall_SeatVO> getAll() {
//		return dao.getAll();
//	}
	
	public List<Hall_SeatVO> getSeatInfo(Integer hall_id) {
		return dao.getSeatInfo(hall_id);
	}
}
