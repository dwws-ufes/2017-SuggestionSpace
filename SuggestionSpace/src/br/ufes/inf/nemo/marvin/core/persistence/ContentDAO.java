package br.ufes.inf.nemo.marvin.core.persistence;

import java.util.List;

import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO;
import br.ufes.inf.nemo.jbutler.ejb.persistence.exceptions.MultiplePersistentObjectsFoundException;
import br.ufes.inf.nemo.jbutler.ejb.persistence.exceptions.PersistentObjectNotFoundException;
import br.ufes.inf.nemo.marvin.core.domain.Content;

public interface ContentDAO extends BaseDAO<Content> {
	
	List<Content> retrieveByName(String name) throws PersistentObjectNotFoundException, MultiplePersistentObjectsFoundException;
}
