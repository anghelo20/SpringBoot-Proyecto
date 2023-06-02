package com.example.demo.Wrapper;

import java.util.ArrayList;

public class QueryData {
	String type;
	String action;
	String dateD;
	ArrayList<String> dateR;
	String typeDate;
	String prevState;
	String currentState;
	
	public QueryData() {
		
	}
	
	
	public QueryData(String type, String action, String dateD, ArrayList<String> dateR, String typeDate,
			String prevState, String currentState) {
		super();
		this.type = type;
		this.action = action;
		this.dateD = dateD;
		this.dateR = dateR;
		this.typeDate = typeDate;
		this.prevState = prevState;
		this.currentState = currentState;
	}


	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getDateD() {
		return dateD;
	}
	public void setDateD(String dateD) {
		this.dateD = dateD;
	}
	public ArrayList<String> getDateR() {
		return dateR;
	}
	public void setDateR(ArrayList<String> dateR) {
		this.dateR = dateR;
	}
	public String getPrevState() {
		return prevState;
	}
	public void setPrevState(String prevState) {
		this.prevState = prevState;
	}
	public String getCurrentState() {
		return currentState;
	}
	public void setCurrentState(String currentState) {
		this.currentState = currentState;
	}


	public String getTypeDate() {
		return typeDate;
	}


	public void setTypeDate(String typeDate) {
		this.typeDate = typeDate;
	}
	
	
	

}
