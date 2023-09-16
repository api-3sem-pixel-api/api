package fatec.api.pixel.horaextra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fatec.api.pixel.horaextra.dto.DadosListagemLancamentoHoras;
import fatec.api.pixel.horaextra.repository.LancamentoHorasRepository;
import fatec.api.pixel.horaextra.repository.UsuarioRepository;

@Service
public class LancamentoHorasService{
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private LancamentoHorasRepository lancamentoHorasRepository;
	
	
	private static final long ID_TIPO_ADMIN = 3L;
	private static final long ID_TIPO_GESTOR = 2L;
	private static final long ID_TIPO_COLABORADOR = 1L;

	public List<DadosListagemLancamentoHoras> listarLancamento(Long idUsuario){
		var tipoUsuario = usuarioRepository.findTipoUsuarioByIdUsuario(idUsuario);
		DadosListagemLancamentoHoras lancamentoHoras;
		
		if(tipoUsuario.getId() == ID_TIPO_ADMIN) {
			var teste = lancamentoHorasRepository.findLancamentoHoras();
		}
		return null;
	}
}