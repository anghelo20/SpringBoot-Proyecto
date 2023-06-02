package com.example.demo.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;


@Entity
@Table(name = "bitacora")
public class Bitacora {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
	@Column(name = "Fecha_hora")
	private LocalDateTime Fecha_hora;
	
	@Column(name= "user_id")
	private int user_id;


    public Bitacora() {
    	
    }
	public Bitacora(LocalDateTime fecha_hora, int user_id) {
		super();
		this.Fecha_hora = fecha_hora;
		this.user_id = user_id;
		
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getFecha_hora() {
		return Fecha_hora;
	}

	public void setFecha_hora(LocalDateTime fecha_hora) {
		Fecha_hora = fecha_hora;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	

	

    
    
}
