package web.info.entity;

import java.sql.Timestamp;

public class InfoVO implements java.io.Serializable{
	private Integer info_id;
	private Integer emp_id;
	private String info_title;
	private byte[] info_pic;
	private String info_des;
	private Timestamp info_date;
	private Integer info_state;
//	private Timestamp info_update;
//	private Integer editor_id;
	
	public Integer getInfo_id() {
		return info_id;
	}
	public void setInfo_id(Integer info_id) {
		this.info_id = info_id;
	}
	public Integer getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(Integer emp_id) {
		this.emp_id = emp_id;
	}
	public String getInfo_title() {
		return info_title;
	}
	public void setInfo_title(String info_title) {
		this.info_title = info_title;
	}
	public byte[] getInfo_pic() {
		return info_pic;
	}
	public void setInfo_pic(byte[] info_pic) {
		this.info_pic = info_pic;
	}
	public String getInfo_des() {
		return info_des;
	}
	public void setInfo_des(String info_des) {
		this.info_des = info_des;
	}
	public Timestamp getInfo_date() {
		return info_date;
	}
	public void setInfo_date(Timestamp info_date) {
		this.info_date = info_date;
	}
	public Integer getInfo_state() {
		return info_state;
	}
	public void setInfo_state(Integer info_state) {
		this.info_state = info_state;
	}
//	public Timestamp getInfo_update() {
//		return info_update;
//	}
//	public void setInfo_update(Timestamp info_update) {
//		this.info_update = info_update;
//	}
//	public Integer getEditor_id() {
//		return editor_id;
//	}
//	public void setEditor_id(Integer editor_id) {
//		this.editor_id = editor_id;
//	}
	
		
}
