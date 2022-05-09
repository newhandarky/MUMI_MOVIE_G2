package web.releasing.dao;

import java.util.List;

import web.releasing.entity.ReleasingVO;

public interface ReleasingDAO_interface {
	public void insert(ReleasingVO ReleasingVO);
	public void update(ReleasingVO ReleasingVO);
	public List<ReleasingVO> getAll();
}
