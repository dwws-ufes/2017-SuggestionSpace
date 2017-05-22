package br.ufes.inf.nemo.marvin.core.application;



import java.util.Date;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudServiceBean;
import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO;
import br.ufes.inf.nemo.marvin.core.domain.User;
import br.ufes.inf.nemo.marvin.core.persistence.UserDAO;
@Stateless
@PermitAll
public class ManageUsersServiceBean extends CrudServiceBean<User> implements ManageUsersService {

	/**
	 * 
	 */
	@EJB private UserDAO userDAO;
	private static final long serialVersionUID = 1L;
	@Override
	public BaseDAO<User> getDAO() {
		return userDAO;
	}
	@Override
	protected User validate(User newEntity, User oldEntity) {
		// New academics must have their creation date set.
		Date now = new Date(System.currentTimeMillis());
		if (oldEntity == null) newEntity.setCreationDate(now);

		// All academics have their last update date set when persisted.
		newEntity.setLastUpdateDate(now);

		return newEntity;
	}

}
