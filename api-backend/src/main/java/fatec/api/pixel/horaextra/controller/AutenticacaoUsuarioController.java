package fatec.api.pixel.horaextra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fatec.api.pixel.horaextra.dto.DadosLoginUsuario;
import fatec.api.pixel.horaextra.model.AutenticacaoUsuario;
import fatec.api.pixel.horaextra.repository.AutenticacaoUsuarioRepository;
import fatec.api.pixel.horaextra.util.PasswordUtils;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/autenticaoUsuario")
public class AutenticacaoUsuarioController {
	
	private PasswordUtils passwordUtils = new PasswordUtils();
	
	@Autowired
	AutenticacaoUsuarioRepository repository;
	
	@PostMapping
	@Transactional
	public void insert(@RequestBody DadosLoginUsuario dados) {
		var autenticacaoUsuario = new AutenticacaoUsuario(dados.login(), passwordUtils.encrypt(dados.senha()));
		repository.save(autenticacaoUsuario);
	}
}
