package com.green.sandme.board.notice.vo;

import java.sql.Timestamp;

public class BoardVO {
	private int boardNum;
	private String boardUser;
	private String boardTitle;
	private String boardContent;
	private Timestamp boardDate;
	private int boardReadNum;
	
	public int getBoardNum() {
		return boardNum;
	}
	public void setBoardNum(int boardNum) {
		this.boardNum = boardNum;
	}
	public String getBoardUser() {
		return boardUser;
	}
	public void setBoardUser(String boardUser) {
		this.boardUser = boardUser;
	}
	public String getBoardTitle() {
		return boardTitle;
	}
	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}
	public String getBoardContent() {
		return boardContent;
	}
	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}
	public Timestamp getBoardDate() {
		return boardDate;
	}
	public void setBoardDate(Timestamp boardDate) {
		this.boardDate = boardDate;
	}
	public int getBoardReadNum() {
		return boardReadNum;
	}
	public void setBoardReadNum(int boardReadNum) {
		this.boardReadNum = boardReadNum;
	}
}
