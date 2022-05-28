package web.info.dao;

import java.util.List;

import web.info.entity.InfoVO;

public interface InfoDAO_interface {
	public void insert(InfoVO infoVO);
	public void update(InfoVO infoVO);
	public void delete(Integer info_id);
	public InfoVO findByPrimaryKey(Integer info_id);
	public List<InfoVO> getAllPublished();
	public List<InfoVO> getAll();

}
