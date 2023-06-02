package com.example.demo.Wrapper;

public class ChartDataBar {

	private int cantidad;
	
	private String mes;
	
	private String año;
	
	private int estado;
	

	public ChartDataBar() {
		super();
	}

	public ChartDataBar(int cantidad, String mes, String año, int estado) {
		super();
		this.cantidad = cantidad;
		this.mes = mes;
		this.año = año;
		this.estado = estado;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public String getAño() {
		return año;
	}

	public void setAño(String año) {
		this.año = año;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}
	
	
	
}
