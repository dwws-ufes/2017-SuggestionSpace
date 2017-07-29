package br.ufes.dwws.suggestionSpace.application;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufes.dwws.suggestionSpace.domain.Collection;
import br.ufes.dwws.suggestionSpace.domain.User;
import br.ufes.dwws.suggestionSpace.persistence.CollectionDAO;
import br.ufes.inf.nemo.jbutler.ejb.application.CrudServiceBean;
import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO;

@Stateless
@PermitAll
public class ManageCollectionsServiceBean extends CrudServiceBean<Collection> implements ManageCollectionsService {

	private static final long serialVersionUID = 1L;

	@EJB
	private CollectionDAO collectionDAO;

	@Override
	public BaseDAO<Collection> getDAO() {
		return collectionDAO;
	}

	@Override
	public List<Collection> list(User currentUser) {
		return collectionDAO.retrieveByCurrentUser(currentUser);
	}
}
