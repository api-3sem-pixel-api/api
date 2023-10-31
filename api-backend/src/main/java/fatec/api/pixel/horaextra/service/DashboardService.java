package fatec.api.pixel.horaextra.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fatec.api.pixel.horaextra.dto.DadosDashboard;
import fatec.api.pixel.horaextra.dto.DadosDashboardHoras;
import fatec.api.pixel.horaextra.dto.DadosRetornoDashboard;
import fatec.api.pixel.horaextra.repository.LancamentoHorasRepository;
import fatec.api.pixel.horaextra.repository.ParametrizacaoRepository;

@Service
public class DashboardService {

	@Autowired
	LancamentoHorasRepository repository;
	
	@Autowired
	ParametrizacaoRepository parametrizacaoRepository;
	
	public List<DadosRetornoDashboard> findDashboard(DadosDashboard dados){
		 List<DadosRetornoDashboard> dadosRetorno = new ArrayList<DadosRetornoDashboard>();
		 DadosDashboardHoras horas = new DadosDashboardHoras(horarioNoturno(), horarioMatutino());
		 
		 return dadosRetorno = repository.findHoras(dados, horas);
	}
	
	public String horarioNoturno() {
		return parametrizacaoRepository.findInicioHorarioNoturno();
	}
	
	public String horarioMatutino() {
		return parametrizacaoRepository.findFimHorarioNoturno();
	}
	
	/*
	 * public List<DadosListagemLancamentoHoras> listarLancamento(Long idUsuario){
		var tipoUsuario = usuarioRepository.findTipoUsuarioByIdUsuario(idUsuario);
		List<DadosListagemLancamentoHoras> lancamentoHoras = new ArrayList<DadosListagemLancamentoHoras>();
		return lancamentoHoras = lancamentoHorasRepository.findLancamentoHoras(idUsuario, tipoUsuario.getId());
	}
	 */
}