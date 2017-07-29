package br.ufes.dwws.suggestionSpace.login;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.EJBTransactionRolledbackException;
import javax.ejb.LocalBean;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import br.ufes.dwws.suggestionSpace.domain.User;
import br.ufes.dwws.suggestionSpace.persistence.UserDAO;
import br.ufes.inf.nemo.jbutler.ejb.persistence.exceptions.MultiplePersistentObjectsFoundException;
import br.ufes.inf.nemo.jbutler.ejb.persistence.exceptions.PersistentObjectNotFoundException;

@Stateless
@LocalBean
public class LoginService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = Logger.getLogger(LoginService.class.getCanonicalName());

	@Resource
	private SessionContext sessionContext;

	@EJB
	private UserDAO userDAO;

	public void login(String email, String password) throws LoginFailedException {

		try {

			User user = userDAO.retrieveByEmail(email);
			String passwordFromBD = user.getPassword();
			
			logger.log(Level.INFO, "pwd from bd: " + passwordFromBD + " pwd from field: " + password );

			if (passwordFromBD == null || !passwordFromBD.equals(password))
				throw new LoginFailedException(LoginFailedException.LoginFailedReason.INCORRECT_PASSWORD);

		} catch (PersistentObjectNotFoundException e) {
			throw new LoginFailedException(e, LoginFailedException.LoginFailedReason.UNKNOWN_USERNAME);
		} catch (MultiplePersistentObjectsFoundException e) {
			throw new LoginFailedException(e, LoginFailedException.LoginFailedReason.MULTIPLE_USERS);
		} catch (EJBTransactionRolledbackException e) {
			throw e;
		}

	}

	public User getCurrentUser() {
		try {
			return userDAO.retrieveByEmail(sessionContext.getCallerPrincipal().getName());
		} catch (PersistentObjectNotFoundException | MultiplePersistentObjectsFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
}
