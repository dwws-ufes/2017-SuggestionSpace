package br.ufes.dwws.suggestionSpace.application;

import java.util.List;

import javax.ejb.Local;

import br.ufes.dwws.suggestionSpace.domain.User;
import br.ufes.inf.nemo.jbutler.ejb.application.CrudService;

@Local
public interface ManageUsersService extends CrudService<User> {
	public List<User> retrieveAllUsers() ;
}