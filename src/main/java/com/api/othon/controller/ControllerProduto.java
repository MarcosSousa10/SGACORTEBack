package com.api.othon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.othon.model.Produto;
import com.api.othon.model.repository.Repository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/java")
@CrossOrigin("*")
public class ControllerProduto {
	@Autowired
	private Repository consulta;

	@GetMapping("/produto/{codigo}")
	public ResponseEntity<List<Produto>> findCodPRodutos(@PathVariable String codigo) {
		List<Produto> list = consulta.findAllCodProdutos(codigo);
		return ResponseEntity.ok(list);
	}

	@GetMapping("/{codigo}")
	public ResponseEntity<List<Produto>> findCod(@PathVariable String codigo) {
		List<Produto> list = consulta.findAllCod(codigo);
		return ResponseEntity.ok(list);
	}

	@GetMapping("/codigo/{codigo}")
	public ResponseEntity<?> findCode(@PathVariable String codigo) {
		Optional<Produto> list = consulta.findAll(codigo);
		return new ResponseEntity<>(list, HttpStatus.valueOf(202));

	}

	@GetMapping("/codauxiliar/{codigo}")
	public ResponseEntity<?> codauxiliar(@PathVariable String codigo) {
		Optional<Produto> list = consulta.find(codigo);
		return new ResponseEntity<>(list, HttpStatus.valueOf(202));

	}
}