package ceu.dam.psp.videojuegos.test;

import java.util.List;

import ceu.dam.psp.videojuegos.client.VideojuegoApiClient;
import ceu.dam.psp.videojuegos.client.VideojuegoApiClientImpl;
import ceu.dam.psp.videojuegos.exceptions.ApiException;
import ceu.dam.psp.videojuegos.exceptions.NotFoundException;
import ceu.dam.psp.videojuegos.model.Videojuego;

public class Test {

	public static void main(String[] args) {
		VideojuegoApiClient cliente = new VideojuegoApiClientImpl("6154f844678f4c8897b48b7d2ef07064");
		
		try {
			//Test FindByID
			Videojuego videojuego = cliente.findById("68f66f5f7037b603e8a5ab32");
			System.out.println(videojuego);
			
			//TEST FindByAño
			List<Videojuego> lista = cliente.findByAñoPublicacion(2018);
			System.out.println(lista);
			
			//Test create
//			System.out.println("Crear");
//			Videojuego nuevo = new Videojuego();
//			nuevo.setAñoPublicacion(2005);
//			nuevo.setNombre("LoL");
//			nuevo.setPaisOrigen("EEUU");
//			nuevo.setValoracion(3.0);
//			String id = cliente.create(nuevo);
//			System.out.println("Creado con id: " + id);
			
			//Test Actualizar
			System.out.println("Actualizar");
			videojuego.setAñoPublicacion(videojuego.getAñoPublicacion()+1);
			cliente.update(videojuego);
			System.out.println("Actualizado");
			videojuego = cliente.findById("68f66f5f7037b603e8a5ab32");
			System.out.println(videojuego);
			
			//Test Borrar
			System.out.println("Borrar");
			cliente.delete("68f66f5f7037b603e8a5ab32");
			System.out.println("Borrado");
		} catch (NotFoundException e) {
			System.out.println(e.getMessage());
		} catch (ApiException e) {
			e.printStackTrace();
		}
	}

}
