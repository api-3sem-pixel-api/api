package fatec.api.pixel.horaextra.model;

import java.util.Date;

import fatec.api.pixel.horaextra.dto.DadosCadastroLancamentoHoras;
import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Column;
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
@Table(name = "extrato_hora")
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
	@Column(name = "datahora_Inicio")
	private Date dataInicio;
	@Column(name = "datahora_Fim")
	private Date dataFim;

	@ManyToOne
	@JoinColumn(name = "id_cr")
	private Cr cr;

	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;

	@ManyToOne
	@JoinColumn(name = "id_etapa_extrato")
	private EtapaExtrato etapa;

	@ManyToOne
	@JoinColumn(name = "Id_Modalidade")
	private Modalidade modalidade;

	@ManyToOne
	@JoinColumn(name = "id_cliente")
	private Cliente cliente;
	
	public LancamentoHoras(DadosCadastroLancamentoHoras horas) {
		this.projeto = horas.projeto();
		this.motivo = horas.motivo();
		this.justificativa = horas.justificativa();
		this.dataInicio = horas.dataHoraInicio();
		this.dataFim = horas.dataHoraFim();
		this.cr = new Cr(horas.idCr());
		this.usuario = new Usuario(horas.idUsuario());
		this.modalidade = new Modalidade(horas.modalidade());
		this.cliente = new Cliente(horas.idCliente());
		this.etapa = new EtapaExtrato(1l);
	}
}