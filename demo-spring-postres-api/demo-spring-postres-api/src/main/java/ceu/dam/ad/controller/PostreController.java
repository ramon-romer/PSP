package ceu.dam.ad.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ceu.dam.ad.PostresApiApplication;
import ceu.dam.ad.Dto.request.PostreRequestDto;
import ceu.dam.ad.Dto.request.PostreResponseDto;
import ceu.dam.ad.model.Postre;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;


@RestController
@RequestMapping("/postre")
@SecurityRequirement(name = "Authorization")
public class PostreController {
	//@Autowired 
	//private ModelMapper mapper;
	

	@GetMapping("/{id}")
	public Postre getById(@PathVariable Long id) {
		return new Postre(id, "chocolate", new BigDecimal(300), new BigDecimal(200), "Tarta de chocolate", false);
	}

	@PostMapping()
	public PostreResponseDto create(@RequestBody PostreRequestDto postreDto) {
		// Obtenemos entity desde RequestDTO
		Postre postreEntity = new ModelMapper().map(postreDto, Postre.class);
		// Llamar al servicio para insertar pasando el entity
		postreEntity.setId(744L);
		//Obtenemos ResposeDTO desde entity
		return new ModelMapper().map(postreEntity, PostreResponseDto.class);
		
	}

	@GetMapping()
	public List<Postre> search(@RequestParam(required = false) String Sabor,
			@RequestParam(required = false) String nombre) {
		List<Postre> postres = new ArrayList<>();

		for (int i = 0; i < postres.size(); i++) {
			postres.add(new Postre(i + 100L, "vainilla", new BigDecimal(320), new BigDecimal(250), "Helado de vainilla",
					true));
		}
		return postres;
	}

	@DeleteMapping("/{id}")
	public void deletePostre(@PathVariable Long id) {
		System.out.println("Borrado");
	}

	@PutMapping("/{id}")
	public Postre actualizarPostre(@PathVariable Long id, @RequestBody Postre postre) {
		System.out.println("Actualizado");
		postre.setId(id);
		return postre;
	}

}
