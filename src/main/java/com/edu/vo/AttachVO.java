package com.edu.vo;

import java.util.Date;

/**
 * 첨부파일 멤버변수 클래스
 * @author 은비
 *
 */
public class AttachVO {
	private String save_file_name; // UUID클래스(해시데이터를 생성하는 클래스)
	private String real_file_name; // UI상으로 보이는 파일명
	private Date reg_date; // 첨부파일 저장일시
	private Integer bno; // 외래키- 게시물 번호
	
	
	public String getSave_file_name() {
		return save_file_name;
	}
	public void setSave_file_name(String save_file_name) {
		this.save_file_name = save_file_name;
	}
	public String getReal_file_name() {
		return real_file_name;
	}
	public void setReal_file_name(String real_file_name) {
		this.real_file_name = real_file_name;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	public Integer getBno() {
		return bno;
	}
	public void setBno(Integer bno) {
		this.bno = bno;
	}
	
	
}
