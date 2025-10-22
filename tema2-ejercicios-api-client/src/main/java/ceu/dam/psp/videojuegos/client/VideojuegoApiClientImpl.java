package ceu.dam.psp.videojuegos.client;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

import ceu.dam.psp.videojuegos.exceptions.ApiException;
import ceu.dam.psp.videojuegos.exceptions.NotFoundException;
import ceu.dam.psp.videojuegos.model.Videojuego;

public class VideojuegoApiClientImpl implements VideojuegoApiClient {

	private String urlBase = "https://crudcrud.com/api/f8ad99c9a9d442e4b41c68c832cbfe92";
	private static Logger log = LoggerFactory.getLogger(VideojuegoApiClientImpl.class);
	private Gson gson = new Gson();

	public VideojuegoApiClientImpl(String uuidUrl) {
		// El constructor recibe el identificador que ha generado crudcrud.com para
		// nuestro API y construye la URL base
		urlBase = "https://crudcrud.com/api/" + uuidUrl + "/videojuego/";
	}

	@Override
	public Videojuego findById(String id) throws NotFoundException, ApiException {
		try {
			URI url = URI.create(urlBase + id);
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder(url).GET().build();
			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
			if (response.statusCode() == 404) {
				log.warn("No existe videojuego con ID indicado");
				throw new NotFoundException("No se ha encontrado ningun videojuego");
			}
			Gson gson = new Gson();
			Videojuego videojuego = gson.fromJson(response.body(), Videojuego.class);
			return videojuego;

		} catch (Exception e) {
			throw new ApiException("Error al realizar peticion al API", e);
		}

	}

	@Override
	public List<Videojuego> findByAñoPublicacion(Integer año) throws NotFoundException, ApiException {
		try {
			URI url = URI.create(urlBase);
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder(url).GET().build();
			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

			Gson gson = new Gson();
			List<Videojuego> lista = Arrays.asList(gson.fromJson(response.body(), Videojuego[].class));

			lista = lista.stream().filter(v -> v.getAñoPublicacion().equals(año)).toList();

			if (lista.isEmpty()) {
				log.warn("No se ha encontrado ningun videojuego");
				throw new NotFoundException("No se ha encontrado ningun videojuego");
			}

			return lista;

		} catch (NotFoundException e) {
			throw e;
		} catch (Exception e) {
			throw new ApiException("Error al realizar peticion al API", e);
		}

	}

	@Override
	public String create(Videojuego videojuego) throws ApiException {
		try {
			URI url = URI.create(urlBase);
			HttpClient client = HttpClient.newHttpClient();
			
			Gson gson = new Gson();
			String json = gson.toJson(videojuego);
			
			HttpRequest request = HttpRequest.newBuilder(url).
					POST(BodyPublishers.ofString(json))
					.header("Content-Type", "application/json")
					.build();
			
			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
			
			Videojuego juegoCreado = gson.fromJson(response.body(), Videojuego.class);
			
			return juegoCreado.get_id();

		} catch (Exception e) {
			throw new ApiException("Error al realizar peticion al API", e);
		}
	}

	@Override
	public void update(Videojuego videojuego) throws NotFoundException, ApiException {
		try {
			URI url = URI.create(urlBase + videojuego.get_id());
			HttpClient client = HttpClient.newHttpClient();
			videojuego.set_id(null);
			
			Gson gson = new Gson();
			String json = gson.toJson(videojuego);
			HttpRequest request = HttpRequest.newBuilder(url).
					PUT(BodyPublishers.ofString(json))
					.header("Content-Type", "application/json")
					.build();
			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
			if (response.statusCode() == 404) {
				log.warn("No existe videojuego con ID indicado");
				throw new NotFoundException("No se ha encontrado ningun videojuego");
			}
			
		} catch (NotFoundException e) {
			throw e;
		} catch (Exception e) {
			throw new ApiException("Error al realizar peticion al API", e);
		}
	}

	@Override
	public void delete(String id) throws NotFoundException, ApiException {
		try {
			URI url = URI.create(urlBase + id);
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder(url).DELETE().build();
			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
			if (response.statusCode() == 404) {
				log.warn("No existe videojuego con ID indicado");
				throw new NotFoundException("No se ha encontrado ningun videojuego");
			}
			Gson gson = new Gson();
			Videojuego videojuego = gson.fromJson(response.body(), Videojuego.class);

		} catch (Exception e) {
			throw new ApiException("Error al realizar peticion al API", e);
		}

	}

}
