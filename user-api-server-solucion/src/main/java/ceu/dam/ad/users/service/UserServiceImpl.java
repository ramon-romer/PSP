package ceu.dam.ad.users.service;

import java.time.LocalDate;
import java.util.Optional;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import ceu.dam.ad.users.exception.DuplicateUserException;
import ceu.dam.ad.users.exception.UserException;
import ceu.dam.ad.users.exception.UserNotFoundException;
import ceu.dam.ad.users.exception.UserUnauthorizedException;
import ceu.dam.ad.users.model.User;
import ceu.dam.ad.users.repository.UserRepository;

@Service
public class UserServiceImpl  implements UserService {

	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository repository;

	@Override
	public User createUser(User user) throws DuplicateUserException, UserException {
		log.debug("Creando nuevo usuario: " + user);
		try {
			// 1. Comprobar si existe usuario con ese email o username
			Optional<User> existsUser = repository.findOneByEmail(user.getEmail());
			if (existsUser.isPresent()) {
				log.debug("Usuario con email repetido");
				throw new DuplicateUserException("Ya existe usuario con el email indicado");
			}
			existsUser = repository.findOneByUsername(user.getUsername());
			if (existsUser.isPresent()) {
				log.debug("Usuario con username repetido");
				throw new DuplicateUserException("Ya existe usuario con el username indicado");
			}
			// 2. Cifrar password y poner fecha alta
			String passwordCifrada = DigestUtils.sha256Hex(user.getPassword());
			user.setPassword(passwordCifrada);
			user.setCreatedDate(LocalDate.now());

			// 3. Insertar usuario
			repository.save(user);

			// 4. Recoger id creado y devolver user completo
			log.debug("Usuario creado con éxito con id " + user.getId());
			return user;
		} catch (DataAccessException e) {
			log.error("Error creando usuario ", e);
			throw new UserException("Error registrando usuario", e);
		}
	}

	@Override
	public void changePassword(Long idUser, String oldPassword, String newPassword)
			throws UserNotFoundException, UserUnauthorizedException, UserException {
		log.debug("Actualizacion password de usuario con id: " + idUser);
		try  {
			// 0. Comprobar que password sean diferentes
			if (newPassword.equals(oldPassword)) {
				log.debug("Pass antigua igual a la nueva, no se hará el cambio ");
				throw new UserUnauthorizedException("La password nueva no puede ser igual a la antigua");
			}

			// 1. Comprobar si usuario existe
			Optional<User> userOpt = repository.findById(idUser);
			if (userOpt.isEmpty()) {
				log.warn("El usuario indicado no existe. ID " + idUser);
				throw new UserNotFoundException("No existe usuario con id " + idUser);
			}
			User user = userOpt.get();
			
			// 2. Comprobamos password antigua
			String passwordCipherOld = DigestUtils.sha256Hex(oldPassword);
			if (!user.getPassword().equals(passwordCipherOld)) {
				log.debug("Pass indicada para cambio incorrecta ");
				throw new UserUnauthorizedException("El password no es correcto");
			}
			
			String passwordCipherNew = DigestUtils.sha256Hex(newPassword);
			user.setPassword(passwordCipherNew);
			repository.save(user);
			log.debug("Password cambiada con exito");
			
		} catch (DataAccessException e) {
			log.error("Error actualizando pass de usuario ", e);
			throw new UserException("Error actualizando usuario", e);
		}
	}

	@Override
	public User login(String login, String password)
			throws UserNotFoundException, UserUnauthorizedException, UserException {
		log.debug("Realizando login con usuario " + login);
		try  {
			// 1. Comprobar si existe login como username o como email
			log.debug("Intentando login por email...");
			Optional<User> userOpt = repository.findOneByEmail(login);
			if (userOpt.isEmpty()) {
				log.debug("Intentando login por username...");
				userOpt = repository.findOneByUsername(login);
			}
			if (userOpt.isEmpty()) {
				log.debug("No existe usuario (email o username)");
				throw new UserNotFoundException("No existe usuario con el login indicado");
			}
			User user = userOpt.get();
			
			// 2. Comprobar password cifrándola previamente
			String passwordCipher = DigestUtils.sha256Hex(password);
			if (!user.getPassword().equals(passwordCipher)) {
				log.debug("Password incorrecta");
				throw new UserUnauthorizedException("Password de usuario incorrecta");
			}
			
			// 3. Actualizamos fecha último login
			try {
				log.debug("Actualizando fecha de último login");
				user.setLastLoginDate(LocalDate.now());
				repository.save(user);
			}
			catch(DataAccessException e) {
				log.error("Error actualizando fecha último login del usuario ", e);
			}
			log.debug("Login correcto");
			return user;
			
		}catch (DataAccessException e) {
			log.error("Error actualizando pass de usuario ", e);
			throw new UserException("Error actualizando usuario", e);
		}
		
	}

	@Override
	public User getUser(Long idUser) throws UserNotFoundException, UserException {
		log.debug("Consultando usuario con id " + idUser);
		try  {
			Optional<User> userOpt = repository.findById(idUser);
			if (userOpt.isEmpty()) {
				throw new UserNotFoundException("No existe usuario con el id indicado");
			}
			return userOpt.get();
		}catch (DataAccessException e) {
			log.error("Error actualizando pass de usuario ", e);
			throw new UserException("Error actualizando usuario", e);
		}
	}

}
