package fatec.api.pixel.horaextra.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import fatec.api.pixel.horaextra.dto.DadosCadastroCliente;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "Cliente")
@Entity(name = "Cliente")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "Razao_Social")
	private String razaoSocial;
	private String cnpj;
	private boolean ativo;
	
	@JsonIgnore
	@OneToMany(mappedBy = "cliente")
	private List<LancamentoHoras>lancamento;
	
	public Cliente(Long idCliente) {
		this.id = idCliente;
	}
	
	public Cliente(DadosCadastroCliente dados) {
		this.id = dados.idCliente();
		this.razaoSocial = dados.razaoSocialCliente();
		this.cnpj = dados.cnpjCliente();
		this.ativo = true;
	}
}
