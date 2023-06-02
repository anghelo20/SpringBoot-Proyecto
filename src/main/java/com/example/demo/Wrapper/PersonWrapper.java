package com.example.demo.Wrapper;

import java.time.LocalDate;



public class PersonWrapper {

	
	private String nombre;
	
	
	private String apellido_P;
	
	
	private String apellido_M;
	
	
	private int dni;
	
	
	private String correo;
	
	
	private LocalDate fecha_Registro;
	
	
	private LocalDate fecha_Accion;
	
	
	private int estado;


	public PersonWrapper(String nombre, String apellido_P, String apellido_M, int dni, String correo,
			LocalDate fecha_Registro, LocalDate fecha_Accion, int estado) {
		super();
		this.nombre = nombre;
		this.apellido_P = apellido_P;
		this.apellido_M = apellido_M;
		this.dni = dni;
		this.correo = correo;
		this.fecha_Registro = fecha_Registro;
		this.fecha_Accion = fecha_Accion;
		this.estado = estado;
	}
	
	public PersonWrapper() {
		
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido_P() {
		return apellido_P;
	}

	public void setApellido_P(String apellido_P) {
		this.apellido_P = apellido_P;
	}

	public String getApellido_M() {
		return apellido_M;
	}

	public void setApellido_M(String apellido_M) {
		this.apellido_M = apellido_M;
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

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}
	
	
}
