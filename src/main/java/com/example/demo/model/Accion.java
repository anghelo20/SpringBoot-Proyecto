package com.example.demo.model;

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
@Table(name = "accion")
public class Accion {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    
	
	@Column(name = "descripcion")
	private String descripcion;

	@OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "accion_id", referencedColumnName = "id")
    private List<Bitacora> listB = new ArrayList<>();

	public Accion(String descripcion) {
		super();
		this.descripcion = descripcion;
	}
	
	public Accion() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<Bitacora> getListB() {
		return listB;
	}

	public void setListB(List<Bitacora> listB) {
		this.listB = listB;
	}

	
    
    
    

}
