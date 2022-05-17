package web.satisfy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "satisfy")
public class SatisfyBean {
	@Id
	@Column
	private int satisfy_id;
	@Column
	private int mem_id;
	@Column
	private int movie_id;
	@Column
	private int movie_sati;
	
	
	@Override
	public String toString() {
		return "SatisfyBean [satisfy_id=" + satisfy_id + ", mem_id=" + mem_id + ", movie_id=" + movie_id
				+ ", movie_sati=" + movie_sati + "]";
	}
	public int getSatisfy_id() {
		return satisfy_id;
	}
	public void setSatisfy_id(int satisfy_id) {
		this.satisfy_id = satisfy_id;
	}
	public int getMem_id() {
		return mem_id;
	}
	public void setMem_id(int mem_id) {
		this.mem_id = mem_id;
	}
	public int getMovie_id() {
		return movie_id;
	}
	public void setMovie_id(int movie_id) {
		this.movie_id = movie_id;
	}
	public int getMovie_sati() {
		return movie_sati;
	}
	public void setMovie_sati(int movie_sati) {
		this.movie_sati = movie_sati;
	}

}
