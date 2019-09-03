package com.ftn.dto;

import java.util.ArrayList;

public class RezervacijaSobaDTO {
	
	private String startDate;
	private String endDate;
	private ArrayList<String> roomList;
	
	public RezervacijaSobaDTO() {
		
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public ArrayList<String> getRoomList() {
		return roomList;
	}

	public void setRoomList(ArrayList<String> roomList) {
		this.roomList = roomList;
	}
	
	

}
