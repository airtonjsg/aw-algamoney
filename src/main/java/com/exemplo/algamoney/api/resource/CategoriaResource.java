package com.exemplo.algamoney.api.resource;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.exemplo.algamoney.api.model.Categoria;
import com.exemplo.algamoney.api.repository.CategoriaRepository;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaRepository categoriaRepository;

//======== Lista categorias ============================
	@GetMapping
	public List<Categoria> listar() {
		return categoriaRepository.findAll();

	}

//======== Inclui uma nova categoria ============================
	@PostMapping
	//@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Categoria> criar(@Valid @RequestBody Categoria categoria, 
			HttpServletResponse response) {

		Categoria categoriaSalva = categoriaRepository.save(categoria);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/codigo")
				.buildAndExpand(categoriaSalva.getCodigo()).toUri();

		response.setHeader("Location", uri.toASCIIString());

		return ResponseEntity.created(uri).body(categoriaSalva);
	}
	
//======== Pesquisa categoria pelo código ============================	
	@GetMapping("/{codigo}")
	public ResponseEntity<Categoria> buscarPeloCodigo(@PathVariable Long codigo) {
	    Optional<Categoria> categoria = this.categoriaRepository.findById(codigo);
	    return categoria.isPresent() ? 
	            ResponseEntity.ok(categoria.get()) : ResponseEntity.notFound().build();
	}
	
//======== Deletar/Excluir uma categoria ============================
		@DeleteMapping("/{codigo}")
		@ResponseStatus(HttpStatus.NO_CONTENT)
		public void remover(@PathVariable Long codigo) {
		  this.categoriaRepository.deleteById(codigo);
		}	
}