package fatec.api.pixel.horaextra.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fatec.api.pixel.horaextra.dto.DadosAlteracaoStatusLancamento;
import fatec.api.pixel.horaextra.dto.DadosListagemLancamentoHoras;
import fatec.api.pixel.horaextra.model.EtapaExtrato;
import fatec.api.pixel.horaextra.repository.LancamentoHorasRepository;
import fatec.api.pixel.horaextra.repository.UsuarioRepository;

@Service
public class LancamentoHorasService{
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private LancamentoHorasRepository lancamentoHorasRepository;
	
	public List<DadosListagemLancamentoHoras> listarLancamento(Long idUsuario){
		var tipoUsuario = usuarioRepository.findTipoUsuarioByIdUsuario(idUsuario);
		List<DadosListagemLancamentoHoras> lancamentoHoras = new ArrayList<DadosListagemLancamentoHoras>();
		return lancamentoHoras = lancamentoHorasRepository.findLancamentoHoras(idUsuario, tipoUsuario.getId());
	}
	
	public void alterarStatus(DadosAlteracaoStatusLancamento dados) {
		var lancamento = lancamentoHorasRepository.getReferenceById(dados.idLancamento());
		lancamento.setEtapa(new EtapaExtrato(dados.status()));
		lancamento.setJustificativa(dados.justificativa());
	}
}