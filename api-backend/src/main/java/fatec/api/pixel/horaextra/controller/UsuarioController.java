package fatec.api.pixel.horaextra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import fatec.api.pixel.horaextra.dto.DadosCadastroUsuario;
import fatec.api.pixel.horaextra.model.Usuario;
import fatec.api.pixel.horaextra.repository.UsuarioRepository;
import fatec.api.pixel.horaextra.service.AutenticacaoUsuarioService;
import fatec.api.pixel.horaextra.service.UsuarioService;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	UsuarioRepository repository;
	
	@Autowired
	UsuarioService service;
	
	@Autowired
	AutenticacaoUsuarioService autenticacaoUsuarioService;
	
	@PostMapping
	@Transactional
	public ResponseEntity insert(@RequestBody DadosCadastroUsuario dados, UriComponentsBuilder uriBuilder) {
		var usuario = new Usuario(dados);
		repository.save(usuario);
		
		autenticacaoUsuarioService.insert(dados.cpf());
		
		var uri = uriBuilder.path("/usuario/{id}").buildAndExpand(usuario.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity delete(@PathVariable Long id) {
		var usuario = repository.getReferenceById(id);
		service.excluirUsuario(usuario);

		return ResponseEntity.noContent().build();
		
	}
}
