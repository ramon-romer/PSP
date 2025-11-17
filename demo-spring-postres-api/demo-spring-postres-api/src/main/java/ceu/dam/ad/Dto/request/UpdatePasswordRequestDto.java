package ceu.dam.ad.Dto.request;

import lombok.Data;

@Data
public class UpdatePasswordRequestDto {
	private String oldPassword;
	private String newPassword;
}
