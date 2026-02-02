package ceu.dam.ad.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import ceu.dam.ad.model.Cliente;

@Repository
public interface ClienteRepository extends MongoRepository<Cliente, String>{


}
