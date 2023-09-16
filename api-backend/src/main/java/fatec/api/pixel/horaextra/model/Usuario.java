package fatec.api.pixel.horaextra.model;

import java.util.List;
import java.util.Set;

import fatec.api.pixel.horaextra.dto.DadosCadastroUsuario;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Usuario")
@Table(name = "usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name="Id_Tipo_Usuario")
	private TipoUsuario tipoUsuario;
	@Column(name = "Cpf_Cnpj")
	private String cpf;
	private String nome;
	private String telefone;
	private String email;
	private boolean ativo;
	
	@OneToOne
	@JoinColumn(name="Id_Autenticacao_Usuario")
	private AutenticacaoUsuario autenticacaoUsuario;
	
	@OneToMany(mappedBy = "usuario")
	private List<LancamentoHoras> lancamento;
	
	/*
	@ManyToMany
	@JoinTable(name="cr_usuario",
	joinColumns= @JoinColumn(name="Id_Usuario", referencedColumnName="id", nullable = true),
	inverseJoinColumns= @JoinColumn(name="Id_Cr", referencedColumnName="id", nullable = true))
	private Set<Cr> cr;
	*/
	public Usuario(DadosCadastroUsuario dados) {
		this.tipoUsuario = new TipoUsuario(dados.idTipoUsuario());
		this.cpf = dados.cpf();
		this.nome = dados.nome();
		this.telefone = dados.telefone();
		this.email = dados.email();
		this.ativo = true;
	}

	public Usuario(Long idUsuario) {
		this.id = idUsuario;
	}

}
