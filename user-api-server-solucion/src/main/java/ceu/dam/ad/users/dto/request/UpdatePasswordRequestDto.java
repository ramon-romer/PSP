package ceu.dam.ad.users.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdatePasswordRequestDto {
	
	@NotBlank(message = "La password antigua no puede ser vacía")
	private String oldPassword;
	
	@NotBlank(message = "La password nueva no puede ser vacía")
	@Size(min = 8, message = "La password nueva tiene que tener un tamaño mayor a 7")
	private String newPassword;
	
}
