package br.ufes.dwws.suggestionSpace.application;

import java.util.List;

import javax.ejb.Local;

import br.ufes.dwws.suggestionSpace.domain.Collection;
import br.ufes.dwws.suggestionSpace.domain.User;
import br.ufes.inf.nemo.jbutler.ejb.application.CrudService;

@Local
public interface ManageCollectionsService extends CrudService<Collection> {
	
	public List<Collection> list(User currentUser);

}
