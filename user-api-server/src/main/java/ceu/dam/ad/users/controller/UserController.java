package ceu.dam.ad.users.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ceu.dam.ad.users.dto.LoginRequestDto;
import ceu.dam.ad.users.dto.UpdatePasswordRequestDto;
import ceu.dam.ad.users.dto.UserResponseDto;
import ceu.dam.ad.users.dto.NewUserResponseDto;
import ceu.dam.ad.users.dto.UserResquestDto;
import ceu.dam.ad.users.exception.DuplicateUserException;
import ceu.dam.ad.users.exception.UserException;
import ceu.dam.ad.users.exception.UserNotFoundException;
import ceu.dam.ad.users.exception.UserUnauthorizedException;
import ceu.dam.ad.users.model.User;
import ceu.dam.ad.users.service.UserService;
import ceu.dam.ad.users.service.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService service;
	
	@PostMapping
	public NewUserResponseDto createUser(@RequestBody @Valid UserResquestDto newUser) throws DuplicateUserException, UserException {
		ModelMapper modelMapper = new ModelMapper();
		User user = modelMapper.map(newUser, User.class);
		user = service.createUser(user);
		return modelMapper.map(user, NewUserResponseDto.class);
	}

	@PutMapping("/{id}/password")
	public void changePassword(@PathVariable Long id,@Valid @RequestBody UpdatePasswordRequestDto request ) throws UserNotFoundException, UserUnauthorizedException, UserException {
		service.changePassword(id, request.getOldPassword(), request.getNewPassword());
	}
	@PostMapping("/login")
	public LoginRequestDto login (@Valid @RequestBody LoginRequestDto request) throws UserNotFoundException, UserUnauthorizedException, UserException {
		User user = service.login(request.getLogin(), request.getPassword());
		return new ModelMapper().map(user, LoginRequestDto.class);
	}
	
	@GetMapping
	@Operation(summary = "Permite hacer login a un usuario utilizando ")
	public UserResponseDto getById(@PathVariable Long id) throws UserNotFoundException, UserException {
		User user = service.getUser(id);
		return new ModelMapper().map(user, UserResponseDto.class);
	}
}
