package ceu.dam.ad.users.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ceu.dam.ad.users.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	public Optional<User> findOneByEmail(String email);
	public Optional<User> findOneByUsername(String username);
}
