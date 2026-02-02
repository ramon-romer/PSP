package ceu.dam.ad;

import java.util.ArrayList;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import ceu.dam.ad.model.Cliente;
import ceu.dam.ad.model.Direccion;
import ceu.dam.ad.services.ClienteNotFoundException;
import ceu.dam.ad.services.ClienteService;

@SpringBootApplication
public class DemoMongoClientesApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(DemoMongoClientesApplication.class, args);
		ClienteService service = context.getBean(ClienteService.class);
		
		Cliente cliente = new Cliente();
		cliente.setNombre("Blas de los montes");
		cliente.setEdad(32);
		cliente.setDirecciones(new ArrayList<>());
		for (int i = 0; i < 3; i++) {
			cliente.getDirecciones().add(new Direccion());
			cliente.getDirecciones().get(i).setCiudad("Ciudad" + i);
			cliente.getDirecciones().get(i).setCp("0000" + i);
		}
		
		System.out.println("Vamos a probar a crear un cliente: " + cliente);
		Cliente nuevoCliente = service.crearCliente(cliente);
		System.out.println("Cliente creado: " + nuevoCliente);
		
		System.out.println("Vamos a probar a consultar un cliente con id: " + nuevoCliente.getId());
		try {
			Cliente clienteConsultado = service.consultarCliente(nuevoCliente.getId());
			System.out.println(clienteConsultado);
		} catch (ClienteNotFoundException e) {
			System.out.println("No existe!!");
		}
		
		
		
	}

}







