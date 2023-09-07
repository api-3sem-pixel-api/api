package fatec.api.pixel.horaextra.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fatec.api.pixel.horaextra.model.AutenticacaoUsuario;

public interface AutenticacaoUsuarioRepository extends JpaRepository<AutenticacaoUsuario, Long> {

}
