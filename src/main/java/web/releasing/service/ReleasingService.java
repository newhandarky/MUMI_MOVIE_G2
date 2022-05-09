package web.releasing.service;

import java.util.List;

import web.releasing.dao.ReleasingDAO;
import web.releasing.dao.ReleasingDAO_interface;
import web.releasing.entity.ReleasingVO;

public class ReleasingService {

	private ReleasingDAO_interface dao;
	
	public ReleasingService() {
		dao = new ReleasingDAO();
	}

	public ReleasingVO addReleasing(String movie_state) {		
		ReleasingVO releasingVO = new ReleasingVO();
		releasingVO.setMovie_state(movie_state);
		dao.insert(releasingVO);
		return releasingVO;
	}

	public ReleasingVO updateReleasing(Integer movie_state_id, String movie_state) {
		ReleasingVO releasingVO = new ReleasingVO();
		releasingVO.setMovie_state_id(movie_state_id);
		releasingVO.setMovie_state(movie_state);
		dao.update(releasingVO);
		return releasingVO;
	}

	public List<ReleasingVO> getAll(){
		return dao.getAll();
	}
 	
	

}
