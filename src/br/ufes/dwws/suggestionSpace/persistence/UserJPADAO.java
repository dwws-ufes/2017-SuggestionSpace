package br.ufes.dwws.suggestionSpace.persistence;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.ufes.dwws.suggestionSpace.domain.User;
import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseJPADAO;
import br.ufes.inf.nemo.jbutler.ejb.persistence.exceptions.MultiplePersistentObjectsFoundException;
import br.ufes.inf.nemo.jbutler.ejb.persistence.exceptions.PersistentObjectNotFoundException;

@Stateless
public class UserJPADAO extends BaseJPADAO<User> implements UserDAO {
	
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = Logger.getLogger(UserJPADAO.class.getCanonicalName());

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public User retrieveByEmail(String email)
			throws PersistentObjectNotFoundException, MultiplePersistentObjectsFoundException {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<User> cq = cb.createQuery(User.class);
		Root<User> root = cq.from(User.class);

		cq.where(cb.equal(root.get("email"), email));
		User result = executeSingleResultQuery(cq, email);
	
		logger.log(Level.INFO, "User by the email \"{0}\" returned \"{1}\"", new Object[] { email, result });
		
		return result;
	}
	
	
	public List<User> retrieveByRole(String role)
			throws PersistentObjectNotFoundException, MultiplePersistentObjectsFoundException {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<User> cq = cb.createQuery(User.class);
		Root<User> root = cq.from(User.class);

		cq.where(cb.equal(root.get("role"), role));
		List<User> result = entityManager.createQuery(cq).getResultList();
		
		return result;
		
	}
	

	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

}
