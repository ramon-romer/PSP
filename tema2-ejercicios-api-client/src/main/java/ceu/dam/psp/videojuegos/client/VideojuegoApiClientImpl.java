package ceu.dam.psp.videojuegos.client;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

import ceu.dam.psp.videojuegos.exceptions.ApiException;
import ceu.dam.psp.videojuegos.exceptions.NotFoundException;
import ceu.dam.psp.videojuegos.model.Videojuego;
import lombok.var;

public class VideojuegoApiClientImpl implements VideojuegoApiClient {
	
	// Este atributo contendrá la URL base a la que hacer las peticiones
	private String urlBase = "https://crudcrud.com/api/f8ad99c9a9d442e4b41c68c832cbfe92";
	private static Logger log = LoggerFactory.getLogger(VideojuegoApiClientImpl.class);
	private Gson gson = new Gson();
	
	public VideojuegoApiClientImpl(String uuidUrl) { 
		// El constructor recibe el identificador que ha generado crudcrud.com para nuestro API y construye la URL base
		urlBase = "https://crudcrud.com/api/" + uuidUrl + "/";
	}
	
	// TODO: Implementar el resto de métodos
	@Override
	public Videojuego findById(String id) throws NotFoundException, ApiException {
		String url = urlBase + "/" + id;
		log.debug("Buscando videojuego con ID {}", id);
		
		try(CloseableHttpClient client = HttpClients.createDefault()) {
			HttpGet get = new HttpGet(url);
			CloseableHttpResponse response = client.execute(get);
			Integer code = response.getCode();
			// Si el código es 200, significa que todo fue bien
			if (code == 200) {
                log.debug("Videojuego encontrado con ID {}", id);
                return gson.fromJson(new InputStreamReader (response.getEntity().getContent()), Videojuego.class);
            }
            // Si devuelve 404, el recurso no existe
            else if (code == 404) {
                log.warn("No se encontró videojuego con ID {}", id);
                throw new NotFoundException("Videojuego no encontrado");
            }
            // Cualquier otro código es un error del servidor o petición
            else {
                log.error("Error HTTP inesperado: {}", code);
                throw new ApiException("Error HTTP: " + code);
            }
		} catch (Exception e) {
			log.error("Error al buscar videojuego", e);
            throw new ApiException("Error al buscar videojuego: " + e);
		}
	}

	@Override
	public List<Videojuego> findByAñoPublicacion(Integer año) throws NotFoundException, ApiException {
		log.debug("Buscando videojuegos del año {}", año);
		
		try(CloseableHttpClient client = HttpClients.createDefault()) {
			HttpGet get = new HttpGet(urlBase);
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	public String create(Videojuego videojuego) throws ApiException {
		return null;
	}

	@Override
	public void update(Videojuego videojuego) throws NotFoundException, ApiException {
	}

	@Override
	public void delete(String id) throws NotFoundException, ApiException {
	}

}
