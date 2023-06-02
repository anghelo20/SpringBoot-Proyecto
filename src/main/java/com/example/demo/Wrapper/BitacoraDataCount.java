package com.example.demo.Wrapper;

public class BitacoraDataCount {
	
	private int accion;
	
	
	
	private int Cantidad;
	
	public BitacoraDataCount() {
		
		
	}

	public BitacoraDataCount(int accion, String descripcion, int cantidad) {
		super();
		this.accion = accion;
		Cantidad = cantidad;
	}

	public int getAccion() {
		return accion;
	}

	public void setAccion(int accion) {
		this.accion = accion;
	}

	

	public int getCantidad() {
		return Cantidad;
	}

	public void setCantidad(int cantidad) {
		Cantidad = cantidad;
	}
	
	

}
