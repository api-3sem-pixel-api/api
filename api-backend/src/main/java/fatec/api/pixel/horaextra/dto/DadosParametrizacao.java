package fatec.api.pixel.horaextra.dto;

import java.util.Date;

public record DadosParametrizacao(
		Date dataInicioPagamento, 
		Date dataFimPagamento,
		String inicioHorarioNoturno,
		String fimHorarioNoturno,
		String verba,
		Double valorVerba) {

}
