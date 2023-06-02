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
@Table(name="memos")
public class Memo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private int n_memo;
	
	private int user_id;
	
	@Column(columnDefinition="text", length=10485760)
	private String razon;
	
	private String asunto;
	
	@Column(name = "Fecha_Registro")
	private LocalDate fecha_RegistroM;
	
	@Column(name = "estado_memo")
	private int estado;
	
	@OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "memoBita_id", referencedColumnName = "id")
    private List<Bitacora> listB = new ArrayList<>();
	
	public Memo() {
		
	}
	
	public Memo(int n_memo, int user_id, String razon,String asunto, LocalDate fecha_RegistroM, int estado) {
		super();
		this.n_memo = n_memo;
		this.user_id = user_id;
		this.razon = razon;
		this.asunto = asunto;
		this.fecha_RegistroM = fecha_RegistroM;
		this.estado = estado;
	}
	
	public Memo(long id,String razon,String asunto) {
		this.id = id;
		this.razon = razon;
		this.asunto = asunto;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getN_memo() {
		return n_memo;
	}
	public void setN_memo(int n_memo) {
		this.n_memo = n_memo;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getRazon() {
		return razon;
	}
	public void setRazon(String razon) {
		this.razon = razon;
	}
	
	
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public LocalDate getFecha_RegistroM() {
		return fecha_RegistroM;
	}

	public void setFecha_RegistroM(LocalDate fecha_RegistroM) {
		this.fecha_RegistroM = fecha_RegistroM;
	}

	public List<Bitacora> getListB() {
		return listB;
	}

	public void setListB(List<Bitacora> listB) {
		this.listB = listB;
	}
	
	
	
	
}
