package fatec.api.pixel.horaextra.dto;

import java.util.Date;

public record DadosParametrizacao(
		int dataInicioPagamento, 
		int dataFimPagamento,
		String inicioHorarioNoturno,
		String fimHorarioNoturno,
		int V1601,
		int V1602,
		int V3000,
		int V3001,
		int V1809,
		int V3016) {

}
