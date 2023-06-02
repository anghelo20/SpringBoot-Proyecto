package com.example.demo.Wrapper;

import java.time.LocalDate;


public class MemoWrapper {

	private int n_memo;

	private int estado;

	private int dni;

	private String correo;

	private LocalDate fecha_Registro;

	private LocalDate fecha_Accion;
	
	
	public MemoWrapper() {
		
	}

	public MemoWrapper(int n_memo, int estado, int dni, String correo, LocalDate fecha_Registro,
			LocalDate fecha_Accion) {
		super();
		this.n_memo = n_memo;
		this.estado = estado;
		this.dni = dni;
		this.correo = correo;
		this.fecha_Registro = fecha_Registro;
		this.fecha_Accion = fecha_Accion;
	}

	public int getN_memo() {
		return n_memo;
	}

	public void setN_memo(int n_memo) {
		this.n_memo = n_memo;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public LocalDate getFecha_Registro() {
		return fecha_Registro;
	}

	public void setFecha_Registro(LocalDate fecha_Registro) {
		this.fecha_Registro = fecha_Registro;
	}

	public LocalDate getFecha_Accion() {
		return fecha_Accion;
	}

	public void setFecha_Accion(LocalDate fecha_Accion) {
		this.fecha_Accion = fecha_Accion;
	}
	
	
	


}
