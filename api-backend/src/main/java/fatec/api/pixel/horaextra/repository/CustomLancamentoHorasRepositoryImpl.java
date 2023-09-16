package fatec.api.pixel.horaextra.repository;

import java.util.ArrayList;
import java.util.List;

import fatec.api.pixel.horaextra.dto.DadosListagemLancamentoHoras;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

public class CustomLancamentoHorasRepositoryImpl implements CustomLancamentoHorasRepository {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public List<DadosListagemLancamentoHoras> findLancamentoHoras(){
		String jpql = "SELECT eh.Id"
				+ "	   cr.Nome,"
				+ "       cliente.Razao_Social,"
				+ "       eh.Projeto,"
				+ "       eh.DataHora_Inicio,"
				+ "       modalidade.Descricao,"
				+ "       eh.DataHora_Fim,"
				+ "       usuario.Nome,"
				+ "       eh.Justificativa,"
				+ "       eh.Id_Etapa_Extrato"
				+ " FROM extrato_hora eh"
				+ " INNER JOIN Cr cr on eh.Id_Cr = cr.Id"
				+ " INNER JOIN Cliente cliente on eh.Id_Cliente = cliente.Id"
				+ " INNER JOIN Modalidade modalidade on eh.Id_Modalidade = modalidade.Id"
				+ " INNER JOIN Usuario usuario on eh.Id_Usuario = usuario.Id";
				
				TypedQuery<Object[]> query = entityManager.createQuery(jpql, Object[].class);
				
				List<Object[]> result = query.getResultList();
				List<DadosListagemLancamentoHoras> dadosListagemLancamentoHoras = new ArrayList<DadosListagemLancamentoHoras>();
				
				for(Object[] object : result) {
					dadosListagemLancamentoHoras.add(new DadosListagemLancamentoHoras(null, jpql, jpql, jpql, null, jpql, null, jpql, jpql, null));
				}
				return dadosListagemLancamentoHoras;
	}
	
}
