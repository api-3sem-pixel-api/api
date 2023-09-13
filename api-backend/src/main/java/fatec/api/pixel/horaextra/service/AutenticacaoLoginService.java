package fatec.api.pixel.horaextra.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fatec.api.pixel.horaextra.repository.AutenticacaoUsuarioRepository;
import fatec.api.pixel.horaextra.repository.UsuarioRepository;

@Service
public class AutenticacaoLoginService implements UserDetailsService {

	@Autowired
	private AutenticacaoUsuarioRepository repository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return repository.findByLogin(username);
	}
	
	public Long getIdTipoUsuario(String cpf) {
		var usuario = usuarioRepository.findByCpf(cpf);
		return usuario.getTipoUsuario().getId();
	}
	
	
}
