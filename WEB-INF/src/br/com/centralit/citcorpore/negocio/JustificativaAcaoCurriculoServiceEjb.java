package br.com.centralit.citcorpore.negocio;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.List;

import br.com.centralit.citcorpore.bean.ComandoDTO;
import br.com.centralit.citcorpore.integracao.ComandoDao;
import br.com.centralit.citcorpore.integracao.JustificativaAcaoCurriculoDao;
import br.com.citframework.excecao.LogicException;
import br.com.citframework.excecao.ServiceException;
import br.com.citframework.integracao.CrudDAO;
import br.com.citframework.service.CrudServicePojoImpl;

/**
 * @author ygor.magalhaes
 *
 */
public class JustificativaAcaoCurriculoServiceEjb extends CrudServicePojoImpl implements JustificativaAcaoCurriculoService {

    protected CrudDAO getDao() throws ServiceException {
	return new JustificativaAcaoCurriculoDao();
    }

    protected void validaCreate(Object arg0) throws Exception {
    }

    protected void validaDelete(Object arg0) throws Exception {
    }

    protected void validaUpdate(Object arg0) throws Exception {
    }

    protected void validaFind(Object obj) throws Exception {
    }

    public Collection list(List ordenacao) throws LogicException, RemoteException, ServiceException {
	return null;
    }

    public Collection list(String ordenacao) throws LogicException, RemoteException,
	    ServiceException {
	return null;
    }


}
