package br.ufes.inf.nemo.marvin.core.application;


import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudServiceBean;
import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO;
import br.ufes.inf.nemo.marvin.core.domain.Genre;
import br.ufes.inf.nemo.marvin.core.persistence.GenreDAO;

@Stateless
@PermitAll
public class ManageGenresServiceBean extends CrudServiceBean<Genre> implements ManageGenresService {

	private static final long serialVersionUID = 1L;
	
	@EJB private GenreDAO genreDAO;
	
	@Override
	public BaseDAO<Genre> getDAO() {
		return genreDAO;
	}

}
