package web.releasing.entity;

public class ReleasingVO implements java.io.Serializable{

	private Integer movie_state_id;
	private String movie_state;
	public Integer getMovie_state_id() {
		return movie_state_id;
	}
	public void setMovie_state_id(Integer movie_state_id) {
		this.movie_state_id = movie_state_id;
	}
	public String getMovie_state() {
		return movie_state;
	}
	public void setMovie_state(String movie_state) {
		this.movie_state = movie_state;
	}
	
}
