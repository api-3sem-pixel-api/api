package fatec.api.pixel.horaextra.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fatec.api.pixel.horaextra.dto.DadosListagemCliente;
import fatec.api.pixel.horaextra.service.ClienteService;

@RestController()
@RequestMapping("/cliente")
@CrossOrigin(origins = "*")
public class ClienteController {

	@Autowired
	private ClienteService service;
	
	@GetMapping
	public ResponseEntity<List<DadosListagemCliente>> listarCliente(){
		var listagemCliente = service.listarCliente();
		return ResponseEntity.ok().body(listagemCliente);
	}
}