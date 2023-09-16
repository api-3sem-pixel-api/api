package fatec.api.pixel.horaextra.model;

import java.util.List;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "Cr")
@Entity(name = "Cr")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cr {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String sigla;
	@Column(name = "Codigo_CR")
	private String codigo;
	
	@OneToMany(mappedBy = "cr")
	private List<LancamentoHoras> lancamento;
	
	/*
	@ManyToMany(mappedBy = "cr")
	private Set<Usuario> usuarios;
	*/
	
	public Cr(Long idCr) {
		this.id = idCr;
	}
}
