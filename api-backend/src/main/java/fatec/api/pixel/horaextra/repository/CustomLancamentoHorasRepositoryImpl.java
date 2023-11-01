package fatec.api.pixel.horaextra.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import fatec.api.pixel.horaextra.dto.DadosDashboard;
import fatec.api.pixel.horaextra.dto.DadosDashboardHoras;
import fatec.api.pixel.horaextra.dto.DadosListagemLancamentoHoras;
import fatec.api.pixel.horaextra.dto.DadosRetornoDashboard;
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
	
	public List<DadosRetornoDashboard> findHoras(DadosDashboard dados, DadosDashboardHoras horas){
		String jpql = "SELECT SUM(TIMESTAMPDIFF(HOUR,a.DataHora_Inicio, a.DataHora_Fim)) Horas,"
				+ "    b.Razao_Social,"
				+ "    c.Nome,"
				+ "    a.Id_Usuario,"
				+ "    a.Modalidade"
				+ "	   from ("
				+ "	   select"
				+ "			Id_Cliente,"
				+ "            Id_Cr,"
				+ "            Id_Modalidade,"
				+ "            Id_Usuario,"
				+ "            case"
				+ "				   when DATE_FORMAT(DataHora_Inicio, '%H:%i:s') >= :horarioNoturno and (DATE_FORMAT(DataHora_Fim , '%H:%i:s') <= :horarioMatutino or DATE_FORMAT(DataHora_Fim , '%H:%i:s') >= :horarioMatutino) and Id_Modalidade = 1 then 'horaExtraNoturna'"
				+ "                when DATE_FORMAT(DataHora_Inicio, '%H:%i:s') < :horarioNoturno and DATE_FORMAT(DataHora_Fim , '%H:%i:s') > :horarioMatutino and Id_Modalidade = 1 then 'horaExtraMatutina'"
				+ "                when DATE_FORMAT(DataHora_Inicio, '%H:%i:s') >= :horarioNoturno and (DATE_FORMAT(DataHora_Fim , '%H:%i:s') <= :horarioMatutino or DATE_FORMAT(DataHora_Fim , '%H:%i:s') >= :horarioMatutino) and Id_Modalidade = 2 then 'sobreavisoNoturno'"
				+ "                when DATE_FORMAT(DataHora_Inicio, '%H:%i:s') < :horarioNoturno and DATE_FORMAT(DataHora_Fim , '%H:%i:s') > :horarioMatutino and Id_Modalidade = 2 then 'sobreavisoMatutino'"
				+ "            end Modalidade,"
				+ "            DataHora_Inicio,"
				+ "            DataHora_Fim"
				+ "            from extrato_hora"
				+ " 	WHERE id_cliente = :idCliente"
				+ " 	AND id_cr = :idCr"
				+ " 	AND dataHora_inicio >= :dataInicio"
				+ " 	AND dataHora_fim <= :dataFim"
				+ " 	) as a"
				+ " 	join cliente b on a.Id_cliente = b.id"
				+ " 	join cr c on c.Id = a.id_cr"
				+ " 	join modalidade d on d.Id = a.id_Modalidade"
				+ " 	group by a.Modalidade, b.Razao_Social, c.Nome, d.Descricao, Id_Usuario";
		
		TypedQuery<Object[]> query = (TypedQuery<Object[]>) entityManager.createNativeQuery(jpql, Object[].class);
		query.setParameter("horarioNoturno", horas.horarioNoturno());
		query.setParameter("horarioMatutino", horas.horarioMatutino());
		query.setParameter("idCliente", dados.idCliente());
		query.setParameter("idCr", dados.idCr());
		query.setParameter("dataInicio", dados.dataInicio());
		query.setParameter("dataFim", dados.dataFim());
		
		List<Object[]> result = query.getResultList();
		List<DadosRetornoDashboard> dadosRetornoDashboard = new ArrayList<DadosRetornoDashboard>();
		
		for(Object[] object : result) {
			dadosRetornoDashboard.add(new DadosRetornoDashboard(
					Double.valueOf(object[0].toString()),
					(String) object[1],
					(String) object[2],
					(int) object[3],
					(String) object[4]));
		}
		return dadosRetornoDashboard;
	}
}