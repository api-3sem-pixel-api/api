package fatec.api.pixel.horaextra.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fatec.api.pixel.horaextra.dto.DadosCadastroUsuario;
import fatec.api.pixel.horaextra.model.TipoUsuario;
import fatec.api.pixel.horaextra.model.Usuario;
import fatec.api.pixel.horaextra.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	UsuarioRepository repository;
	
	@Autowired
	AutenticacaoUsuarioService autenticacaoUsuarioService;
	
	public void atualizarUsuario(DadosCadastroUsuario dados, Long id) {
		var atualizacaoUsuario = repository.getReferenceById(id);
		atualizacaoUsuario.setTipoUsuario(new TipoUsuario(dados.idTipoUsuario()));
		atualizacaoUsuario.setCpf(dados.cpf());
		atualizacaoUsuario.setNome(dados.nome());
		atualizacaoUsuario.setTelefone(dados.telefone());
		atualizacaoUsuario.setEmail(dados.email());
	}
		
	public Usuario inserirUsuario(DadosCadastroUsuario dados) {
		var usuario = new Usuario(dados);
		var autenticacaoUsuario = autenticacaoUsuarioService.insert(dados.cpf());
		usuario.setAutenticacaoUsuario(autenticacaoUsuario);
		repository.save(usuario);
		return usuario;
	}
	
	public void excluirUsuario(Usuario usuario) {
		usuario.setAtivo(false);
	}
}
