package fatec.api.pixel.horaextra.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fatec.api.pixel.horaextra.model.LancamentoHoras;

public interface LancamentoHorasRepository extends JpaRepository<LancamentoHoras, Long>{
	
}
