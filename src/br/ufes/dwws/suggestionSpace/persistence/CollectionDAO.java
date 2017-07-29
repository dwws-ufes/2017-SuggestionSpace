package br.ufes.dwws.suggestionSpace.persistence;

import java.util.List;

import javax.ejb.Local;

import br.ufes.dwws.suggestionSpace.domain.Collection;
import br.ufes.dwws.suggestionSpace.domain.User;
import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO;

@Local
public interface CollectionDAO extends BaseDAO<Collection> {
	
	public List<Collection> retrieveByCurrentUser(User currentUser);
}
