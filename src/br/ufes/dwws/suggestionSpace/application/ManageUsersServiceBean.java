package br.ufes.dwws.suggestionSpace.application;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufes.dwws.suggestionSpace.domain.User;
import br.ufes.dwws.suggestionSpace.persistence.UserDAO;
import br.ufes.inf.nemo.jbutler.ejb.application.CrudServiceBean;
import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO;

@Stateless
@PermitAll
public class ManageUsersServiceBean extends CrudServiceBean<User> implements ManageUsersService {

	private static final long serialVersionUID = 1L;
	
	@EJB
	private UserDAO userDAO;
	
	@Override
	public BaseDAO<User> getDAO() {
		return userDAO;
	}
	
	public List<User> retrieveAllUsers(){
		return userDAO.retrieveAll();
	}

}
