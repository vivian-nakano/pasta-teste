package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Jogos;
import com.example.demo.repository.JogosRepository;
 

@RestController 
@RequestMapping("/jogos") 
@CrossOrigin(origins="*",allowedHeaders="*")
public class JogosController {

	@Autowired //SERVICO DE INJECAO DE DEPENDENCIA DO SPRING. Como é interface, nao dá pra instanciar. Instanciação fica por conta do spring: aí usa autowired
	private JogosRepository repository;
	
	@GetMapping
	public List<Jogos> findAllCategoria(){
		return repository.findAll();
	}
	
	@GetMapping("/{id}")
	public Optional<Jogos> findByIdProduto (@PathVariable Long id){
		return repository.findById(id);
	}
	
	@GetMapping("/titulo/{titulo}") 
	public ResponseEntity<List<Jogos>> GetByTitulo(@PathVariable String titulo) {
		return ResponseEntity.ok(repository.findAllByTituloContainingIgnoreCase(titulo));
	}
	
	@PostMapping
	public ResponseEntity<Jogos>postProdutos (@RequestBody Jogos jogos){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(repository.save(jogos));
	}
	
	@PutMapping("/{id})")
	public Jogos putProduto (@PathVariable Long id, @RequestBody Jogos objJogos) {
		objJogos.setId(id);
		repository.save(objJogos);
		return objJogos;
	}
	
	@DeleteMapping("/{id}")
	public void deleteProduto (@PathVariable Long id) {
		repository.deleteById(id);
	}
}
