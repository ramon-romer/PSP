package ceu.dam.ad.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "clientes")
public class Cliente {
	@Id
	private String id;
	private String nombre;
	private Integer edad;
	private List<Direccion> direcciones;

}
