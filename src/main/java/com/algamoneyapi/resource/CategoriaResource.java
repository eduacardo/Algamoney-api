package com.algamoneyapi.resource;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.algamoneyapi.model.Categoria;
import com.algamoneyapi.repository.CategoriasRepository;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriasRepository categoriasRepository;
	
	@GetMapping
	public List<Categoria> listar(){		
		return categoriasRepository.findAll();		
	}
	
	@PostMapping
	public ResponseEntity<Categoria> criar(@RequestBody Categoria categoria, HttpServletResponse response){

	 Categoria categoriaSalva =  categoriasRepository.save(categoria);
	 
	 URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}")
			   .buildAndExpand(categoriaSalva.getCodigo()).toUri();
	 response.setHeader("Location", uri.toASCIIString());
	 
	return ResponseEntity.created(uri).body(categoriaSalva); 	 
	 
	}
	
	@GetMapping("/{codigo}")
	public Categoria buscaPeloCodigo(@PathVariable Long codigo) {		
		return categoriasRepository.findOne(codigo);		
	}
	
	
}
