package com.example.demo.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="person")
public class Person {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "Apellido_P")
	private String apellido_P;
	
	@Column(name = "Apellido_M")
	private String apellido_M;
	
	@Column(name = "DNI")
	private int dni;
	
	@Column(name = "Correo")
	private String correo;
	
	@Column(name = "Fecha_Registro")
	private LocalDate fecha_Registro;
	
	@Column(name = "estado")
	private int estado;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_person", referencedColumnName = "id")
	private List<Memo> memos = new ArrayList<>();

	@OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "personBita_id", referencedColumnName = "id")
    private List<Bitacora> listB = new ArrayList<>();
	
	public Person() {
		
	}
	
	public Person(Long id ,String nombre, String apellido_P, String apellido_M, int dni, String correo) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido_P = apellido_P;
		this.apellido_M = apellido_M;
		this.dni = dni;
		this.correo = correo;
	}
	
	public Person(String nombre, String apellido_P, String apellido_M, int dni, String correo) {
		super();
		this.nombre = nombre;
		this.apellido_P = apellido_P;
		this.apellido_M = apellido_M;
		this.dni = dni;
		this.correo = correo;
	}
	
	public Person(String nombre, String apellido_P, String apellido_M, int dni, String correo,int estado) {
		super();
		this.nombre = nombre;
		this.apellido_P = apellido_P;
		this.apellido_M = apellido_M;
		this.dni = dni;
		this.correo = correo;
		this.estado =estado;
	}



	public long getId_person() {
		return id;
	}



	public void setId_person(int id_person) {
		this.id = id_person;
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



	public List<Memo> getMemos() {
		return memos;
	}



	public void setMemos(List<Memo> memos) {
		this.memos = memos;
	}

	

	public LocalDate getFecha_Registro() {
		return fecha_Registro;
	}

	public void setFecha_Registro(LocalDate fecha_Registro) {
		this.fecha_Registro = fecha_Registro;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public List<Bitacora> getListB() {
		return listB;
	}

	public void setListB(List<Bitacora> listB) {
		this.listB = listB;
	}
	
	
	
	
	
}
