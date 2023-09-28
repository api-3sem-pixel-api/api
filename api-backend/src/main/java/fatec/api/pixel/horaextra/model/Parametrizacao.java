package fatec.api.pixel.horaextra.model;

import java.util.Date;

import fatec.api.pixel.horaextra.dto.DadosParametrizacao;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Parametrizacao {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Date dataInicioPagamento;
	private Date dataFimPagamento;
	private String inicioHorarioNoturno;
	private String fimHorarioNoturno;

	public Parametrizacao(DadosParametrizacao dados) {
		this.dataInicioPagamento = dados.dataInicioPagamento();
		this.dataFimPagamento = dados.dataFimPagamento();
		this.inicioHorarioNoturno = dados.inicioHorarioNoturno();
		this.fimHorarioNoturno = dados.fimHorarioNoturno();
	}
}
