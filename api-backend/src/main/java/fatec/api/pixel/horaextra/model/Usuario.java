package fatec.api.pixel.horaextra.model;

import java.util.List;

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

@Table(name = "Usuario")
@Entity(name = "Usuario")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String cpf_cnpj;
	private String nome;
	private String telefone;
	private String email;
	
	@OneToMany(mappedBy = "usuario")
	private List<LancamentoHoras> lancamento;
	
	public Usuario(Long idUsuario) {
		this.id = idUsuario;
	}

}
