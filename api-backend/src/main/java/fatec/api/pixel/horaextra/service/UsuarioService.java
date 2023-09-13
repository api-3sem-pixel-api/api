package fatec.api.pixel.horaextra.service;

import org.springframework.stereotype.Service;

import fatec.api.pixel.horaextra.model.Usuario;

@Service
public class UsuarioService {

	public void excluirUsuario(Usuario usuario) {
		usuario.setAtivo(false);
	}
}
