package fatec.api.pixel.horaextra.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fatec.api.pixel.horaextra.dto.DadosParametrizacao;
import fatec.api.pixel.horaextra.model.Parametrizacao;
import fatec.api.pixel.horaextra.repository.ParametrizacaoRepository;

@Service
public class ParametrizacaoService {

	@Autowired
	private ParametrizacaoRepository repository;
	
	public void cadastrar(DadosParametrizacao dados) {
		var parametrizacao = new Parametrizacao(dados);
		repository.save(parametrizacao);
	}
	
	public List<DadosParametrizacao> listar(){
		var parametrizacoes = repository.findAll();
		var dados = new ArrayList<DadosParametrizacao>();
		for(Parametrizacao param : parametrizacoes) {
			dados.add(new DadosParametrizacao(param.getDataInicioPagamento(),param.getDataFimPagamento(), 
					param.getInicioHorarioNoturno(), param.getFimHorarioNoturno()));
		}
		return dados;
	}
}
