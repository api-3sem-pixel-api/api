package fatec.api.pixel.horaextra.dto;

import java.util.Date;

public record DadosCadastroLancamentoHoras(
		String projeto,
		Long idCr,
		Long idUsuario,
		Long modalidade,
		String motivo,
		Date dataHoraInicio,
		Date dataHoraFim,
		String justificativa,
		Long idCliente) {
	
}
