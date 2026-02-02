package ceu.dam.psp.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Postre {
	private Long id;
	private String sabor;
	private BigDecimal calorias;
	private BigDecimal peso;
	private String nombre;
	private Boolean gluten;
	
	
}
