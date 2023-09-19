package fatec.api.pixel.horaextra.repository;

import java.util.List;

import fatec.api.pixel.horaextra.dto.DadosListagemLancamentoHoras;

public interface CustomLancamentoHorasRepository {
	List<DadosListagemLancamentoHoras> findLancamentoHoras(Long idUsuario, Long TipoUsuario);
}
