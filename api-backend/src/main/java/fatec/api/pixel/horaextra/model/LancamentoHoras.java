package fatec.api.pixel.horaextra.model;

import java.util.Date;

import fatec.api.pixel.horaextra.dto.DadosCadastroLancamentoHoras;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "LancamentoHoras")
@Table(name = "Extrato_Hora")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LancamentoHoras {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String projeto;
	private String motivo;
	private String justificativa;
	private Date dataHoraInicio;
	private Date dataHoraFim;

	@ManyToOne
	@JoinColumn(name = "Id_Cr")
	private Cr cr;

	@ManyToOne
	@JoinColumn(name = "Id_Usuario")
	private Usuario usuario;

	@ManyToOne
	@JoinColumn(name = "Id_Etapa_Extrato")
	private EtapaExtrato etapa;

	@ManyToOne
	@JoinColumn(name = "Id_Modalidade")
	private Modalidade modalidade;

	@ManyToOne
	@JoinColumn(name = "Id_Cliente")
	private Cliente cliente;
	
	public LancamentoHoras(DadosCadastroLancamentoHoras horas) {
		this.projeto = horas.projeto();
		this.motivo = horas.motivo();
		this.justificativa = horas.justificativa();
		this.dataHoraInicio = horas.dataHoraInicio();
		this.dataHoraFim = horas.dataHoraFim();
		this.cr = new Cr(horas.idCr());
		this.usuario = new Usuario(horas.idUsuario());
		this.modalidade = new Modalidade(horas.modalidade());
		this.cliente = new Cliente(horas.idCliente());
		this.etapa = new EtapaExtrato(1l);
	}
}