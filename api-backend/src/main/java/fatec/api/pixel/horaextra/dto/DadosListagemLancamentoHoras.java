package fatec.api.pixel.horaextra.dto;

import java.util.Date;

public record DadosListagemLancamentoHoras(
		Long id,
		String cr,
		String cliente,
		String projeto,
		Date inicio,
		String modalidade,
		Date fim,
		String solicitante,
		String justificativa,
		Long status) {
}
