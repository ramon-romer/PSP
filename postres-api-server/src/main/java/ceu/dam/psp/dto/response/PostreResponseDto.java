package ceu.dam.psp.dto.response;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class PostreResponseDto {
	private Long id;
	private String sabor;
	private BigDecimal calorias;
	private BigDecimal peso;
	private String nombre;
	private Boolean gluten;
}
