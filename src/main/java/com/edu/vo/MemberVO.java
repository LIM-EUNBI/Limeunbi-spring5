package com.edu.vo;

import java.util.Date;

/**
 * DB에서 Model클래스로 입출력, Model에서 Service클래스로 입출력
 * Service에서 Controller클래스 데이터를 입출력, 
 * Controller에서 jsp로 데이터를 주고 받을 때 사용하는 데이터 클래스
 * @author 웅비
 */
public class MemberVO {
	//ERD를 보고 멤버변수를 선언
	private String user_id, user_pw, user_name, email, levels;
	private Date reg_date, update_date;
	private Integer point;
	private Boolean enabled;
	public String getUser_id() {
		return user_id;
	}
	
	@Override
	public String toString() {
		return "MemberVO [user_id=" + user_id + ", user_pw=" + user_pw + ", user_name=" + user_name + ", email=" + email
				+ ", levels=" + levels + ", reg_date=" + reg_date + ", update_date=" + update_date + ", point=" + point
				+ ", enabled=" + enabled + "]";
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_pw() {
		return user_pw;
	}
	public void setUser_pw(String user_pw) {
		this.user_pw = user_pw;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLevels() {
		return levels;
	}
	public void setLevels(String levels) {
		this.levels = levels;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	public Date getUpdate_date() {
		return update_date;
	}
	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}
	public Integer getPoint() {
		return point;
	}
	public void setPoint(Integer point) {
		this.point = point;
	}
	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	
}
