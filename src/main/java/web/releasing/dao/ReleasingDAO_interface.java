package web.releasing.dao;

import java.util.List;
import web.releasing.entity.ReleasingVO;

public interface ReleasingDAO_interface {
	public void insert(ReleasingVO ReleasingVO);
	public void update(ReleasingVO ReleasingVO);
	public void delete(Integer movie_state_id);
	public ReleasingVO findByPrimaryKey(Integer movie_state_id);
	public List<ReleasingVO> getAll();
}
