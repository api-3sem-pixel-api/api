package fatec.api.pixel.horaextra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fatec.api.pixel.horaextra.model.CrUsuario;

public interface CrUsuarioRepository extends JpaRepository<CrUsuario, Long>{

	public List<CrUsuario> findCrUsuarioByIdCr(Long idCr);
}
