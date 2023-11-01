package fatec.api.pixel.horaextra.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fatec.api.pixel.horaextra.dto.DadosListagemCr;
import fatec.api.pixel.horaextra.dto.DadosListagemLancamentoHoras;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

public class CustomCrRepositoryImpl implements CustomCrRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<DadosListagemCr> findCrByIdUsuario(Long id) {
		String jpql = "SELECT cr.id,"
				+ "	          cr.nome"
				+ "    FROM cr cr"
				+ "    INNER JOIN cr_usuario crUsuario on crUsuario.id_cr = cr.id"
				+ "    WHERE crUsuario.id_usuario = :idUsuario ";
		
				
				TypedQuery<Object[]> query = (TypedQuery<Object[]>) entityManager.createNativeQuery(jpql, Object[].class);
				query.setParameter("idUsuario", id);
				
				List<Object[]> result = query.getResultList();
				List<DadosListagemCr> dadosListagemCr = new ArrayList<DadosListagemCr>();
				
				for(Object[] object : result) {
					dadosListagemCr.add(new DadosListagemCr(Long.valueOf(object[0].toString()) , (String) object[1]));
				}
				return dadosListagemCr;
	}

}
