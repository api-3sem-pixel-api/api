package fatec.api.pixel.horaextra.model;

import fatec.api.pixel.horaextra.dto.DadosLoginUsuario;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "autenticacao_usuario")
@Entity(name = "AutenticacaoUsuario")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AutenticacaoUsuario {

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String login;
	private String senha;

	public AutenticacaoUsuario(DadosLoginUsuario dados) {
		this.login = dados.email();
		this.senha = dados.senha();
	}
}
