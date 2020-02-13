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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.exemplo.algamoney.api.model.Pessoa;
import com.exemplo.algamoney.api.repository.PessoaRepository;
import com.exemplo.algamoney.api.service.PessoaService;

@RestController
@RequestMapping("/pessoas")
public class PessoaResource {

	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private PessoaService pessoaService;
	
//======== Lista pessoas ============================
	@GetMapping
	public List<Pessoa> listar(){

		return pessoaRepository.findAll();
		
	}
	
//======== Inclui uma nova pessoa ============================	
	@PostMapping
	public ResponseEntity<Pessoa> criar(@Valid @RequestBody Pessoa pessoa, HttpServletResponse response) {
		Pessoa pessoaSalva = pessoaRepository.save(pessoa);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}")
				.buildAndExpand(pessoaSalva.getCodigo()).toUri();
			response.setHeader("Location", uri.toASCIIString());
			
			return ResponseEntity.created(uri).body(pessoaSalva);
	}

//======== Pesquisa pessoa pelo código ============================	
		@GetMapping("/{codigo}")
		public ResponseEntity<Pessoa> buscarPeloCodigo(@PathVariable Long codigo) {
		    Optional<Pessoa> categoria = this.pessoaRepository.findById(codigo);
		    return categoria.isPresent() ? 
		            ResponseEntity.ok(categoria.get()) : ResponseEntity.notFound().build();
		}
		
//======== Deletar/Excluir uma pessoa ============================
		@PutMapping
		@DeleteMapping("/{codigo}")
		@ResponseStatus(HttpStatus.NO_CONTENT)
		public void remover(@PathVariable Long codigo) {
		  this.pessoaRepository.deleteById(codigo);
		}
		
//======== Atualizar Completa de pessoa com PUT============================	
		@PutMapping("/{codigo}")
		public ResponseEntity<Pessoa> atualizar(@PathVariable Long codigo, @Valid @RequestBody Pessoa pessoa) {
			Pessoa pessoaSalva = pessoaService.atualizar(codigo, pessoa);
			return ResponseEntity.ok(pessoaSalva);
		}	
		
		//======== Atualizar Parcial de pessoa com PUT============================			
}
