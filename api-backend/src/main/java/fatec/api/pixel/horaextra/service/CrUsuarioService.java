package fatec.api.pixel.horaextra.service;

import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;

import fatec.api.pixel.horaextra.dto.DadosCadastroCrUsuario;
import fatec.api.pixel.horaextra.model.CrUsuario;
import fatec.api.pixel.horaextra.repository.CrUsuarioRepository;

public class CrUsuarioService {
	
	@Autowired
	CrUsuarioRepository repository;
	
	/*
	public List<DadosCadastroCrUsuario> listarCrUsuario(Long idUsuario){
		return repository.findCrByIdUsuario(idUsuario);
	}
	*/
	
	public CrUsuario inserirCrUsuario(List<DadosCadastroCrUsuario> dados) {
		var crUsuario = new CrUsuario(dados);
		repository.save(crUsuario);
		return crUsuario;
	}
	
	public void atualizarCrUsuario(DadosCadastroCrUsuario dados, Long idUsuario, Long idCr) {
		var atualizacaoCrUsuario = repository.getReferenceById(idUsuario);
		atualizacaoCrUsuario.setIdUsuario(dados.idUsuario());
		atualizacaoCrUsuario.setIdCr(dados.idCr());
	}
}
