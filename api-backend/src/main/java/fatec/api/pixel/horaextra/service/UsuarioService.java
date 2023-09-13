package fatec.api.pixel.horaextra.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fatec.api.pixel.horaextra.dto.DadosCadastroUsuario;
import fatec.api.pixel.horaextra.model.Usuario;
import fatec.api.pixel.horaextra.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	UsuarioRepository repository;
	
	@Autowired
	AutenticacaoUsuarioService autenticacaoUsuarioService;
	
	public void excluirUsuario(Usuario usuario) {
		usuario.setAtivo(false);
	}
	
	public Usuario inserirUsuario(DadosCadastroUsuario dados) {
		var usuario = new Usuario(dados);
		var autenticacaoUsuario = autenticacaoUsuarioService.insert(dados.cpf());
		usuario.setAutenticacaoUsuario(autenticacaoUsuario);
		repository.save(usuario);
		return usuario;
	}
}
