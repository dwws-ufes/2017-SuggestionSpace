package br.ufes.inf.nemo.marvin.core.application;


import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudServiceBean;
import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO;
import br.ufes.inf.nemo.marvin.core.domain.Type;
import br.ufes.inf.nemo.marvin.core.persistence.TypeDAO;

@Stateless
@PermitAll
public class ManageTypesServiceBean extends CrudServiceBean<Type> implements ManageTypesService {

	private static final long serialVersionUID = 1L;
	@EJB private TypeDAO typeDAO;
	@Override
	public BaseDAO<Type> getDAO() {
		return typeDAO;
	}

}
