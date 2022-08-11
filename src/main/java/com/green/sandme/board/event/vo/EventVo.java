package com.green.sandme.board.event.vo;

import java.io.UnsupportedEncodingException;

import org.apache.tomcat.util.codec.binary.Base64;

public class EventVo {
	private int eventNum;
	private String eventMember;
	private String eventTitle;
	private String eventContent;
	private byte[] eventSomeFile;
	private byte[] eventFile;
	private byte[] insertEventSomeFile;
	private byte[] insertEventFile;
	private String eventDate;
	
	public byte[] getInsertEventSomeFile() {
		return insertEventSomeFile;
	}
	public void setInsertEventSomeFile(byte[] insertEventSomeFile) {
		this.insertEventSomeFile = insertEventSomeFile;
	}
	public byte[] getInsertEventFile() {
		return insertEventFile;
	}
	public void setInsertEventFile(byte[] insertEventFile) {
		this.insertEventFile = insertEventFile;
	}
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
	public String getEventSomeFile() throws UnsupportedEncodingException {
		byte[] eventSomeFilebyte = eventSomeFile;
		byte[] eventSomeFilebyteEnc64 = Base64.encodeBase64(eventSomeFilebyte);
		String eventSomeFile = new String(eventSomeFilebyteEnc64, "UTF-8");
		
		return eventSomeFile;
	}
	public void setEventSomeFile(byte[] eventSomeFile) {
		this.eventSomeFile = eventSomeFile;
	}
	public String getEventFile() throws UnsupportedEncodingException {
		byte[] eventFilebyte = eventFile;
		byte[] eventFilebyteEnc64 = Base64.encodeBase64(eventFilebyte);
		String eventFile = new String(eventFilebyteEnc64, "UTF-8");
		
		return eventFile;
	}
	public void setEventFile(byte[] eventFile) {
		this.eventFile = eventFile;
	}
}