package fatec.api.pixel.horaextra.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fatec.api.pixel.horaextra.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Usuario findByCpf(String cpf);

}
