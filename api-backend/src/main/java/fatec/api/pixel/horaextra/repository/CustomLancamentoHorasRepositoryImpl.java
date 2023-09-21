package fatec.api.pixel.horaextra.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fatec.api.pixel.horaextra.dto.DadosListagemLancamentoHoras;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

public class CustomLancamentoHorasRepositoryImpl implements CustomLancamentoHorasRepository {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	private static final long ID_TIPO_ADMIN = 3L;
	private static final long ID_TIPO_GESTOR = 2L;
	private static final long ID_TIPO_COLABORADOR = 1L;
	
	public List<DadosListagemLancamentoHoras> findLancamentoHoras(Long idUsuario, Long idTipoUsuario){
		String jpql = "SELECT eh.Id,"
				+ "	   cr.Nome,"
				+ "       cliente.Razao_Social,"
				+ "       eh.Projeto,"
				+ "       eh.DataHora_Inicio,"
				+ "       modalidade.Descricao,"
				+ "       eh.DataHora_Fim,"
				+ "       usuario.Nome,"
				+ "       eh.Justificativa,"
				+ "       eh.Motivo,"
				+ "       eh.Id_Etapa_Extrato"
				+ " FROM extrato_hora eh"
				+ " INNER JOIN Cr cr on eh.Id_Cr = cr.Id"
				+ " INNER JOIN Cliente cliente on eh.Id_Cliente = cliente.Id"
				+ " INNER JOIN Modalidade modalidade on eh.Id_Modalidade = modalidade.Id"
				+ " INNER JOIN Usuario usuario on eh.Id_Usuario = usuario.Id";
		
				if(idTipoUsuario == ID_TIPO_COLABORADOR) {
					jpql += " WHERE usuario.Id = :idUsuario";
				}
				if(idTipoUsuario == ID_TIPO_GESTOR) {
					jpql += " INNER JOIN cr_usuario crUsuario on crUsuario.Id_Cr = cr.Id";
					jpql += " WHERE crUsuario.Id_Usuario = :idUsuario";
				}
				
				TypedQuery<Object[]> query = (TypedQuery<Object[]>) entityManager.createNativeQuery(jpql, Object[].class);
				if(idTipoUsuario == ID_TIPO_COLABORADOR || idTipoUsuario == ID_TIPO_GESTOR)
					query.setParameter("idUsuario", idUsuario);
				
				List<Object[]> result = query.getResultList();
				List<DadosListagemLancamentoHoras> dadosListagemLancamentoHoras = new ArrayList<DadosListagemLancamentoHoras>();
				
				for(Object[] object : result) {
					dadosListagemLancamentoHoras.add(new DadosListagemLancamentoHoras(
							Long.valueOf(object[0].toString()) ,
							(String) object[1],
							(String) object[2],
							(String) object[3],
							(Date) object[4], 
							(String) object[5], 
							(Date) object[6],
							(String) object[7],
							(String) object[8],
							(String) object[9],
							Long.valueOf(object[10].toString())));
				}
				return dadosListagemLancamentoHoras;
	}
}
