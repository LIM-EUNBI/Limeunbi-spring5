package com.edu.vo;
/**
 * 회원관리, 게시물관리 페이징처리 + 검색처리하는 클래스
 * @author 은비
 * 
 *
 */

public class PageVO {
	private int queryStartNo; // 한페이지당 보여줄 row수 계산
	private int queryPerPageNum; // 한페이지당 보여줄 row수 계산
	private Integer page; // 현재페이지
	private int perPageNum; //UI 하단에 보여줄 페이징 개수 계산 변수
	private int totalCount; //계산식의 기초값
	private int startPage; // perPageNum으로 구하는 UI 하단 시작번호
	private int endPage; // perPageNum으로 구하는 UI 하단 끝번호
	
	private boolean prev; //UI 하단 이전페이지
	private boolean next; //UI 하단 다음페이지
	
	private String search_keyword;
	private String search_type;
	
	@Override
	public String toString() {
		return "PageVO [queryStartNo=" + queryStartNo + ", queryPerPageNum=" + queryPerPageNum + ", page=" + page
				+ ", perPageNum=" + perPageNum + ", totalCount=" + totalCount + ", startPage=" + startPage
				+ ", endPage=" + endPage + ", prev=" + prev + ", next=" + next + ", search_keyword=" + search_keyword
				+ ", search_type=" + search_type + "]";
	}
	public int getQueryStartNo() {
		queryStartNo = (this.page-1)*queryPerPageNum; // jsp에서 파라미터로 받을 때 1부터 계산해서 들어와서 -1을 해준다.
		return queryStartNo;
	}
	public void setQueryStartNo(int queryStartNo) {
		this.queryStartNo = queryStartNo;
	}
	public int getQueryPerPageNum() {
		return queryPerPageNum;
	}
	public void setQueryPerPageNum(int queryPerPageNum) {
		this.queryPerPageNum = queryPerPageNum;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public int getPerPageNum() {
		return perPageNum;
	}
	public void setPerPageNum(int perPageNum) {
		this.perPageNum = perPageNum;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		calcPage();
	}
	private void calcPage() {
		// totalCount 변수값을 기반으로 prev, next, startPage, endPage 등을 구현
		int tempEnd = (int) Math.ceil(page/(double)this.perPageNum)*this.perPageNum;
		// 만약 11페이지를 클릭하면, 임시 끝페이지가 20.
		this.startPage = (tempEnd - this.perPageNum) + 1;
		// 1-10까지는 시작페이지가 1, 11부터는 11이 시작페이지가 된다.
		
		//위 startPage변수 jsp에서 반복문의 시작 값으로 사용.
		if(tempEnd*this.queryPerPageNum > this.totalCount) {
			this.endPage = (int)Math.ceil((this.totalCount/(double)this.queryPerPageNum));
		} else {
			this.endPage = tempEnd;
		}
		
		// prev, next 구하기
		this.prev = this.startPage != 1;
		this.next = this.endPage*this.queryPerPageNum < this.totalCount;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public boolean isPrev() {
		return prev;
	}
	public void setPrev(boolean prev) {
		this.prev = prev;
	}
	public boolean isNext() {
		return next;
	}
	public void setNext(boolean next) {
		this.next = next;
	}
	public String getSearch_keyword() {
		return search_keyword;
	}
	public void setSearch_keyword(String search_keyword) {
		this.search_keyword = search_keyword;
	}
	public String getSearch_type() {
		return search_type;
	}
	public void setSearch_type(String search_type) {
		this.search_type = search_type;
	}
	
}
