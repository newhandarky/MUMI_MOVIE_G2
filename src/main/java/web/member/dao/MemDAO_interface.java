package web.member.dao;

import java.util.List;

import web.member.entity.MemVO;

public interface MemDAO_interface {
	public MemVO checkAccount(String mem_account);
	public void insert(MemVO memVO);
	public void update(MemVO memVO);
	public void delete(Integer mem_id);
	public void updateState(Integer mem_id);
	public void updateClose(MemVO memVO);
	public void updateOpen(MemVO memVO);
	public void changePWD(String mem_account, String mem_password);
	public MemVO findByPrimaryKey(Integer mem_id);
	public MemVO findByAccount(String mem_account);
	public List<MemVO> getAll();
	public MemVO login(String mem_account, String mem_password);


//  public List<MemVO> getAll(Map<String, String[]> map); 
}
	