package fatec.api.pixel.horaextra.service;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class CalculoVerbaService {

	public void calculoVerba(LocalDate dataInicio, LocalDate dataFim) {
		
	}
	
	private boolean isDiaUtil(LocalDate data) {
		DayOfWeek diaDaSemana = data.getDayOfWeek();
		return diaDaSemana != DayOfWeek.SATURDAY && diaDaSemana != DayOfWeek.SUNDAY;

	}

}
