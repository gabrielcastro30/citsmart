package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import br.com.centralit.citcorpore.bean.CategoriaServicoDTO;
import br.com.centralit.citcorpore.bean.ContratoDTO;
import br.com.centralit.citcorpore.bean.TipoServicoDTO;
import br.com.citframework.dto.IDto;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;

/**
 * @author leandro.viana
 * 
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class TipoServicoDao extends CrudDaoDefaultImpl {

	private static final long serialVersionUID = 1153789196419291108L;

	public TipoServicoDao() {
		super(Constantes.getValue("DATABASE_ALIAS"), null);
	}

	public Class getBean() {
		return TipoServicoDTO.class;
	}

	public Collection getFields() {
		Collection listFields = new ArrayList();
		listFields.add(new Field("idTipoServico", "idTipoServico", true, true, false, false));
		listFields.add(new Field("idEmpresa", "idEmpresa", false, false, false, false));
		listFields.add(new Field("nomeTipoServico", "nomeTipoServico", false, false, false, false));
		listFields.add(new Field("situacao", "situacao", false, false, false, false));
		return listFields;
	}

	/**
	 * Obt�m o nome da tabela a ser usada no banco de dados.
	 */
	public String getTableName() {
		return "TIPOSERVICO";
	}

	public Collection find(IDto obj) throws Exception {
		List ordem = new ArrayList();
		ordem.add(new Order("nomeTipoServico"));
		return super.find(obj, ordem);
	}

	public Collection list() throws Exception {
		/*
		 * List list = new ArrayList(); list.add(new Order("nomeTipoServico"));
		 * 
		 * return super.list(list);
		 */
		Collection colFinal = new ArrayList();
		Collection col = super.list("nomeTipoServico");
		for (Iterator it = col.iterator(); it.hasNext();) {
			TipoServicoDTO tipoServicoDTO = (TipoServicoDTO) it.next();
			if (tipoServicoDTO.getSituacao() == null || tipoServicoDTO.getSituacao().equalsIgnoreCase("A")) {
				colFinal.add(tipoServicoDTO);
			}
		}
		return colFinal;
	}

	/**
	 * Verifica se tipo servi�o.
	 * 
	 * @param tipoServicoDTO
	 * @return true - existe; false - n�o existe;
	 * @throws PersistenceException
	 */
	public boolean verificarSeTipoServicoExiste(TipoServicoDTO tipoServicoDTO) throws PersistenceException {
		StringBuffer sql = new StringBuffer();
		List parametros = new ArrayList();

		sql.append("SELECT nomeTipoServico FROM tiposervico ");
		sql.append("WHERE nomeTipoServico LIKE ? AND situacao LIKE ? ");
		parametros.add(tipoServicoDTO.getNomeTipoServico());
		parametros.add("A");

		if (tipoServicoDTO.getIdTipoServico() != null) {
			sql.append("AND idtiposervico <> ?");
			parametros.add(tipoServicoDTO.getIdTipoServico());
		}

		List tiposServico = execSQL(sql.toString(), parametros.toArray());

		if (tiposServico != null && !tiposServico.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Retorna lista de Tipo Servi�o por nome.
	 * 
	 * @return Collection
	 * @throws Exception
	 */
	public Collection findByNome(TipoServicoDTO tipoServicoDTO) throws Exception {
		List condicao = new ArrayList();
		List ordenacao = new ArrayList();

		condicao.add(new Condition("nomeTipoServico", "=", tipoServicoDTO.getNomeTipoServico()));
		ordenacao.add(new Order("nomeTipoServico"));
		return super.findByCondition(condicao, ordenacao);
	}

}
