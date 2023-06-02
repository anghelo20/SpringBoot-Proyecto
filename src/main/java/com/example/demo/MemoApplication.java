package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
	import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.Action.AccionDao;
import com.example.demo.model.Accion;


@SpringBootApplication
public class MemoApplication implements CommandLineRunner    
 {	
	@Autowired
	private AccionDao accionDao;
	
	public static void main(String[] args) {
		SpringApplication.run(MemoApplication.class, args);	
	

	}

	@Override
	public void run(String... args) throws Exception {
		
		List<Accion> listA = accionDao.getAccions();
		
		if(listA.size() == 0) {
			List<String> list=new ArrayList<String>();  
			 //Adding elements in the List  
			 list.add("Crear Persona");  
			 list.add("Buscar Persona");  
			 list.add("Actualizar Persona");  
			 list.add("Eliminar Persona");  
			 list.add("Crear Memo");  
			 list.add("Buscar Memo");  
			 list.add("Actualizar Memo");  
			 list.add("Eliminar Memo"); 
			 //Iterating the List element using for-each loop  
			 System.out.print("ds");
			for(String descr:list)  {
			  Accion accion = new Accion(descr);
			  accionDao.saveAccions(accion);
			}  
	
		}
		
		
	}

	


}
