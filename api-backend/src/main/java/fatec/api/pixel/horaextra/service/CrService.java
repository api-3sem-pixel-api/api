package fatec.api.pixel.horaextra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fatec.api.pixel.horaextra.dto.DadosListagemCr;
import fatec.api.pixel.horaextra.repository.CrRepository;

@Service
public class CrService {

	@Autowired
	CrRepository repository;
	
	public List<DadosListagemCr> listarCr(Long idUsuario){
		return repository.findCrByIdUsuario(idUsuario);
	}
}
