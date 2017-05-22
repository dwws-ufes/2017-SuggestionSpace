package br.ufes.inf.nemo.marvin.core.application;


import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import br.ufes.inf.nemo.jbutler.ejb.application.CrudServiceBean;
import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO;
import br.ufes.inf.nemo.marvin.core.domain.Content;
import br.ufes.inf.nemo.marvin.core.persistence.ContentDAO;

@Stateless
@PermitAll
public class ManageContentsServiceBean extends CrudServiceBean<Content> implements ManageContentsService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EJB private ContentDAO contentDAO;
	
	@Override
	public BaseDAO<Content> getDAO() {
		return contentDAO;
	}
}
