package web.info.service;

import java.sql.Timestamp;
import java.util.List;

import web.info.dao.InfoDAO;
import web.info.dao.InfoDAO_interface;
import web.info.entity.InfoVO;

public class InfoService {
	private InfoDAO_interface dao;
	
	public InfoService() {
		dao = new InfoDAO();
	}
	
	public InfoVO addInfo(String info_title, byte[] info_pic, String info_des ) {  // Integer emp_id  Integer info_state

		InfoVO infoVO = new InfoVO();

//		infoVO.setEmp_id(emp_id);
		infoVO.setInfo_title(info_title);
		infoVO.setInfo_pic(info_pic);
		infoVO.setInfo_des(info_des);
//		infoVO.setInfo_state(info_state);
		
		dao.insert(infoVO);

		return infoVO;
	}
	
	public InfoVO updateInfo(Integer info_id, String info_title, byte[] info_pic, String info_des, Integer info_state) { 
		
		InfoVO infoVO = new InfoVO();

		infoVO.setInfo_id(info_id);
		infoVO.setInfo_title(info_title);
		infoVO.setInfo_pic(info_pic);
		infoVO.setInfo_des(info_des);
		infoVO.setInfo_state(info_state);

		dao.update(infoVO);
		
		return infoVO;
	}
	
	public void deleteInfo(Integer info_id) {
		dao.delete(info_id);
	}
	
	public InfoVO getOneInfo(Integer info_id) {
		return dao.findByPrimaryKey(info_id);
	}
	
	public List<InfoVO> getAllPublished() {
		return dao.getAllPublished();
	}
	
	public List<InfoVO> getAll() {
		return dao.getAll();
	}

}
