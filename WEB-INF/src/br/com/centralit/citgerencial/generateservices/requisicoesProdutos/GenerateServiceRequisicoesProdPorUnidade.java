package br.com.centralit.citgerencial.generateservices.requisicoesProdutos;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import br.com.centralit.citcorpore.bean.ItemRequisicaoProdutoDTO;
import br.com.centralit.citcorpore.bean.RequisicaoProdutoDTO;
import br.com.centralit.citcorpore.integracao.ItemRequisicaoProdutoDao;
import br.com.centralit.citcorpore.integracao.RequisicaoProdutoDao;
import br.com.centralit.citcorpore.util.CITCorporeUtil;
import br.com.centralit.citcorpore.util.Enumerados.SituacaoItemRequisicaoProduto;
import br.com.centralit.citgerencial.bean.GerencialGenerateService;
import br.com.citframework.util.SQLConfig;

/**
 * @author rodrigo.oliveira
 * @since 14/08/2012
 */
public class GenerateServiceRequisicoesProdPorUnidade extends GerencialGenerateService {

	private HashMap novoParametro = new HashMap();
	
	public List execute(HashMap parametersValues, Collection paramtersDefinition) throws ParseException {
		
	    Set set = parametersValues.entrySet();
	    Iterator i = set.iterator();

	    while(i.hasNext()){
	      Map.Entry entrada = (Map.Entry)i.next();
	      getNovoParametro().put(entrada.getKey(), entrada.getValue());
	    }
	    
		String datainicial = (String) getNovoParametro().get("PARAM.dataInicial");
		String datafinal = (String) getNovoParametro().get("PARAM.dataFinal");
		
		Date datafim = new Date();
		Date datainicio = new Date();
		SimpleDateFormat formatoBanco = new SimpleDateFormat("yyyy-MM-dd");
		try {
			datainicio = new SimpleDateFormat("dd/MM/yyyy").parse(datainicial);
			datafim = new SimpleDateFormat("dd/MM/yyyy").parse(datafinal); 
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Calendar calendar = Calendar.getInstance();  
		calendar.setTime(datafim);  
		calendar.add(GregorianCalendar.DATE, 1);  
		
		if (CITCorporeUtil.SGBD_PRINCIPAL.equalsIgnoreCase(SQLConfig.MYSQL)){
        		getNovoParametro().put("PARAM.dataInicial", formatoBanco.format(datainicio));
        		getNovoParametro().put("PARAM.dataFinal", formatoBanco.format(calendar.getTime()));
		}else{
        		getNovoParametro().put("PARAM.dataInicial", new java.sql.Date(datainicio.getTime()));
        		getNovoParametro().put("PARAM.dataFinal", new java.sql.Date(calendar.getTime().getTime()));		    
		}
		
		Collection col = new ArrayList();
		List listaRetorno = null;
		RequisicaoProdutoDao requisicaoDao = new RequisicaoProdutoDao();
		
		try {
			Collection<RequisicaoProdutoDTO> colRequisicoes = requisicaoDao.consultaRequisicoesPorUnidade(getNovoParametro());
			if (colRequisicoes != null) {
			    for (RequisicaoProdutoDTO requisicaoDto : colRequisicoes) {
			        double valor = 0;
			        requisicaoDto.setDataHoraSolicitacao(requisicaoDto.getDataHoraSolicitacao());
			        Collection<ItemRequisicaoProdutoDTO> colItens = new ItemRequisicaoProdutoDao().findByIdSolicitacaoServico(requisicaoDto.getIdSolicitacaoServico());
			        if (colItens != null) {
			            for (ItemRequisicaoProdutoDTO itemRequisicaoDto: colItens) {
			            	if (itemRequisicaoDto.getPrecoAproximado() == null)
			            		continue;
			                valor += itemRequisicaoDto.getPrecoAproximado().doubleValue() * itemRequisicaoDto.getQuantidade().intValue();
			            }
			        }
                    col.add(new Object[] {requisicaoDto.getNomeUnidadeSolicitante(),
                                          requisicaoDto.getIdSolicitacaoServico(),
                                          requisicaoDto.getDataHoraSolicitacaoStr(),
                                          requisicaoDto.getContrato(),
                                          requisicaoDto.getCentroCusto(),
                                          requisicaoDto.getProjeto(),
                                          requisicaoDto.getServico(),
                                          requisicaoDto.getDescrSituacao(),
                                          valor
                                         });
                }
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(col != null && !col.isEmpty()){
			listaRetorno = (List) col;
		}else{
			listaRetorno = new ArrayList();
		}
		
		//resetando par�metro
		setNovoParametro(null);
		
		return listaRetorno;
	}
	
	public HashMap getNovoParametro() {
		return novoParametro;
	}

	public void setNovoParametro(HashMap novoParametro) {
		this.novoParametro = novoParametro;
	}

}
