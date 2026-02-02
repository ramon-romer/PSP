package ceu.dam.ad.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ceu.dam.ad.model.Cliente;
import ceu.dam.ad.repositories.ClienteRepository;

@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteRepository repository;
	
	@Override
	public Cliente crearCliente(Cliente cliente) {
		return repository.save(cliente);
	}
	
	@Override
	public Cliente consultarCliente(String id) throws ClienteNotFoundException {
		return repository.findById(id).orElseThrow(() -> new ClienteNotFoundException("No hay cliente con ese ID"));
	}
	
	@Override
	public Cliente actualizarCliente(Cliente cliente) throws ClienteNotFoundException {
		consultarCliente(cliente.getId());
		return repository.save(cliente);
	}

}
