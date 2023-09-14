package fatec.api.pixel.horaextra.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fatec.api.pixel.horaextra.dto.DadosCadastroLancamentoHoras;
import fatec.api.pixel.horaextra.model.LancamentoHoras;
import fatec.api.pixel.horaextra.repository.LancamentoHorasRepository;

@RestController
@RequestMapping("/lancamentoHoras")
public class LancamentoHorasController {
	
	@Autowired
	private LancamentoHorasRepository repository;
	
	@PostMapping
	public ResponseEntity lancamento(@RequestBody DadosCadastroLancamentoHoras horas) {
		var lancamento = new LancamentoHoras(horas);
		repository.save(lancamento);
		return ResponseEntity.created(null).build();
	}
	
	@GetMapping
	public ResponseEntity<List<LancamentoHoras>> findAll() {
		List<LancamentoHoras> list = repository.findAll();		
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<LancamentoHoras> findById(@PathVariable Long id) {
		LancamentoHoras extrato = repository.findById(id).get();		
		return ResponseEntity.ok().body(extrato);
	}
}