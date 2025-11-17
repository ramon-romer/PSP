package ceu.dam.ad.users.service;

import ceu.dam.ad.users.exception.DuplicateUserException;
import ceu.dam.ad.users.exception.UserException;
import ceu.dam.ad.users.exception.UserNotFoundException;
import ceu.dam.ad.users.exception.UserUnauthorizedException;
import ceu.dam.ad.users.model.User;

public interface UserService {
	
	public User createUser(User user) throws DuplicateUserException, UserException;
	
	public void changePassword(Long idUser, String oldPassword, String newPassword) throws UserNotFoundException, UserUnauthorizedException, UserException;
	
	public User login(String login, String password) throws UserNotFoundException, UserUnauthorizedException, UserException;
	
	public User getUser(Long idUser) throws UserNotFoundException, UserException;
	
	
	
	
}
