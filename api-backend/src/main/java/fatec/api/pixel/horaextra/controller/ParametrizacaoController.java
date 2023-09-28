package fatec.api.pixel.horaextra.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import fatec.api.pixel.horaextra.dto.DadosParametrizacao;
import fatec.api.pixel.horaextra.service.ParametrizacaoService;

@RestController
@RequestMapping("/parametrizacao")
public class ParametrizacaoController {

	@Autowired
	private ParametrizacaoService service;
	
	@PostMapping
	public ResponseEntity cadastrar(@RequestBody DadosParametrizacao dados) {
		service.cadastrar(dados);
		return ResponseEntity.created(null).build();
	}
	
	@GetMapping
	public ResponseEntity<List<DadosParametrizacao>> listar(){
		var dados = service.listar();
		return ResponseEntity.ok().body(dados);
	}
}
