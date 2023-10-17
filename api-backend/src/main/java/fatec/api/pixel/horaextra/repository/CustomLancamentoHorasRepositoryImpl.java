package fatec.api.pixel.horaextra.repository;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
		String jpql = "SELECT eh.id,"
				+ "	   cr.nome,"
				+ "       cliente.razao_social,"
				+ "       eh.projeto,"
				+ "       eh.dataHora_Inicio,"
				+ "       modalidade.descricao,"
				+ "       eh.dataHora_Fim,"
				+ "       usuario.nome,"
				+ "       eh.justificativa,"
				+ "       eh.motivo,"
				+ "       eh.id_etapa_extrato"
				+ " FROM extrato_hora eh"
				+ " INNER JOIN cr cr on eh.id_cr = cr.id"
				+ " INNER JOIN cliente cliente on eh.id_cliente = cliente.id"
				+ " INNER JOIN modalidade modalidade on eh.id_modalidade = modalidade.id"
				+ " INNER JOIN usuario usuario on eh.id_usuario = usuario.id";
		
				if(idTipoUsuario == ID_TIPO_COLABORADOR) {
					jpql += " WHERE usuario.id = :idUsuario";
				}
				if(idTipoUsuario == ID_TIPO_GESTOR) {
					jpql += " INNER JOIN cr_usuario crUsuario on crUsuario.id_Cr = cr.id";
					jpql += " WHERE crUsuario.id_usuario = :idUsuario";
				}
				if(idTipoUsuario == ID_TIPO_ADMIN) {
					jpql += " WHERE eh.id_etapa_extrato in (:etapas)";
				}
					
				TypedQuery<Object[]> query = (TypedQuery<Object[]>) entityManager.createNativeQuery(jpql, Object[].class);
				if(idTipoUsuario == ID_TIPO_COLABORADOR || idTipoUsuario == ID_TIPO_GESTOR) {
					query.setParameter("idUsuario", idUsuario);
				}else {
					query.setParameter("etapas", Arrays.asList(3,5,2));
				}
					
				
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
