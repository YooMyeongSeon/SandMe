package com.green.sandme.board.event.vo;

public class EventVo {
	private int eventNum;
	private String eventMember;
	private String eventTitle;
	private String eventContent;
	private byte[] eventSomeFile;
	private byte[] eventFile;
	private String eventDate;
	
	public int getEventNum() {
		return eventNum;
	}
	public void setEventNum(int eventNum) {
		this.eventNum = eventNum;
	}
	public String getEventMember() {
		return eventMember;
	}
	public void setEventMember(String eventMember) {
		this.eventMember = eventMember;
	}
	public String getEventTitle() {
		return eventTitle;
	}
	public void setEventTitle(String eventTitle) {
		this.eventTitle = eventTitle;
	}
	public String getEventContent() {
		return eventContent;
	}
	public void setEventContent(String eventContent) {
		this.eventContent = eventContent;
	}
	public String getEventDate() {
		return eventDate;
	}
	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}
	public byte[] getEventSomeFile() {
		return eventSomeFile;
	}
	public void setEventSomeFile(byte[] eventSomeFile) {
		this.eventSomeFile = eventSomeFile;
	}
	public byte[] getEventFile() {
		return eventFile;
	}
	public void setEventFile(byte[] eventFile) {
		this.eventFile = eventFile;
	}
}