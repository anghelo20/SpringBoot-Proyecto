package com.example.demo.Action;

import java.util.List;

import com.example.demo.model.Accion;

public interface AccionDao {
	
	public List<Accion> getAccions();
	
	public void saveAccions(Accion accion);
	
	public Accion getAccionById(int id);
	
	public void createBitacAccion(Accion accion);

}
