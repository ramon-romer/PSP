package ceu.dam.ad.services;

import ceu.dam.ad.model.Cliente;

public interface ClienteService {

	Cliente crearCliente(Cliente cliente);

	Cliente consultarCliente(String id) throws ClienteNotFoundException;

	Cliente actualizarCliente(Cliente cliente) throws ClienteNotFoundException;

}