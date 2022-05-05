package web.ticket_order.dao;

import java.util.List;

import web.ticket_order.entity.TicketVO;

public interface TicketDAO_interface {
    public void insert(TicketVO ticketVO);
    public void update(TicketVO ticketVO);
    public void delete(Integer ticket_orders_id);
    public TicketVO findByPrimaryKey(Integer ticket_orders_id);
    public List<TicketVO> getAll();
	
}
