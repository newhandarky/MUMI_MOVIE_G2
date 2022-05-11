package web.member.service;

import java.sql.Date;
import java.util.List;

import web.member.dao.MemDAO;
import web.member.dao.MemDAO_interface;
import web.member.entity.MemVO;

public class MemService {

	private MemDAO_interface dao;

	public MemService() {
		dao = new MemDAO();
	}

	public MemVO addMem(String mem_account, String mem_name, String mem_phone, String mem_password, String mem_nickname, 
			Date mem_register) {

		MemVO memVO = new MemVO();

		memVO.setMem_account(mem_account);
		memVO.setMem_name(mem_name);
		memVO.setMem_phone(mem_phone);
		memVO.setMem_password(mem_password);
		memVO.setMem_nickname(mem_nickname);
		memVO.setMem_register(mem_register);
//		memVO.setMem_pic(mem_pic);

		dao.insert(memVO);

		return memVO;
	}

	public MemVO updateMem(Integer mem_id, String mem_name, String mem_phone, String mem_address, String mem_password, 
			String mem_nickname, Date mem_birthday, byte[] mem_pic, Integer mem_gender) { 
		
	
		MemVO memVO = new MemVO();

		
		memVO.setMem_id(mem_id);
		memVO.setMem_name(mem_name);
		memVO.setMem_nickname(mem_nickname);
		memVO.setMem_phone(mem_phone);
		memVO.setMem_password(mem_password);
		memVO.setMem_address(mem_address);
		memVO.setMem_birthday(mem_birthday);
		memVO.setMem_pic(mem_pic);
		memVO.setMem_gender(mem_gender);

		dao.update(memVO);
		

		return memVO;
	}
	
	public MemVO updateClose(Integer mem_id){	
		MemVO memVO = new MemVO();
		
		memVO.setMem_state(0);
		
		dao.updateClose(memVO);

		return memVO;
	}
	
	public MemVO updateOpen(Integer mem_id){	
		MemVO memVO = new MemVO();
		
		memVO.setMem_state(1);
		
		dao.updateOpen(memVO);
		
		return memVO;
	}
		
	public void deleteMem(Integer mem_id) {
		dao.delete(mem_id);
	}

	public MemVO getOneMem(Integer mem_id) {
		return dao.findByPrimaryKey(mem_id);
	}
	
	public MemVO getOneMemByAccount(String mem_account) {
		return dao.findByAccount(mem_account);
	}

	public List<MemVO> getAll() {
		return dao.getAll();
	}
	
	public MemVO updateState(Integer mem_id) {
		MemVO memVO = new MemVO();
		
		memVO.setMem_state(mem_id);	
		
		dao.updateState(mem_id);
		
		return memVO;
	}

}
