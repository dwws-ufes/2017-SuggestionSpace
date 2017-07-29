package br.ufes.dwws.suggestionSpace.persistence;

import javax.ejb.Local;

import br.ufes.dwws.suggestionSpace.domain.User;
import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO;
import br.ufes.inf.nemo.jbutler.ejb.persistence.exceptions.MultiplePersistentObjectsFoundException;
import br.ufes.inf.nemo.jbutler.ejb.persistence.exceptions.PersistentObjectNotFoundException;

@Local
public interface UserDAO extends BaseDAO<User> {
	User retrieveByEmail(String email) throws PersistentObjectNotFoundException, MultiplePersistentObjectsFoundException;
}
