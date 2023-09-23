package fatec.api.pixel.horaextra.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fatec.api.pixel.horaextra.dto.DadosListagemCr;
import fatec.api.pixel.horaextra.service.CrService;

@RestController()
@RequestMapping("/cr")
@CrossOrigin(origins = "*") 
public class CrController {

	@Autowired
	private CrService service;
	
	@GetMapping("/{idUsuario}")
	public ResponseEntity<List<DadosListagemCr>> listarCr(@PathVariable("idUsuario") Long idUsuario){
		var listagemCr = service.listarCr(idUsuario);
		return ResponseEntity.ok().body(listagemCr);
	}
	
	
}
