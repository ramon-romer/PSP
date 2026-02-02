package ceu.dam.psp.dto.request;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class PostreRequestDto {

	private String sabor;
	private BigDecimal calorias;
	private BigDecimal peso;
	private String nombre;
	private Boolean gluten;
	
	
}
