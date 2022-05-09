package web.ticket_list.dao;

import java.util.List;

import web.ticket_list.entity.Ticket_listVO;

public interface Ticket_listDAO_interface {
	public void insert(Ticket_listVO ticket_listVO);
	public void update(Ticket_listVO ticket_listVO);
	public void delete(Integer ticket_list_id);
	public Ticket_listVO findByPrimaryKey(Integer ticket_list_id);
	public List<Ticket_listVO> getAll();
	
}
