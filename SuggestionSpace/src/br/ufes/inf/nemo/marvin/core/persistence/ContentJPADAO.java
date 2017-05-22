package br.ufes.inf.nemo.marvin.core.persistence;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseJPADAO;
import br.ufes.inf.nemo.marvin.core.domain.Content;
import br.ufes.inf.nemo.marvin.core.domain.Content_;

@Stateless
public class ContentJPADAO extends BaseJPADAO<Content> implements ContentDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** The logger. */
	private static final Logger logger = Logger.getLogger(ContentJPADAO.class.getCanonicalName());

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	@Override
	public List<Content> retrieveByName(String name) {
		
		logger.log(Level.FINE, "Retrieving the academic whose e-mail is \"{0}\"...", name);
		
		// Constructs the query over the Content class.
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Content> cq = cb.createQuery(Content.class);
		Root<Content> root = cq.from(Content.class);

		// Filters the query with the name.
		cq.where(cb.equal(root.get(Content_.name), name));
		List<Content> result = entityManager.createQuery(cq).getResultList();
		logger.log(Level.INFO, "Retrieve Content by the name \"{0}\" returned \"{1}\"", new Object[] { name, result });
		return result;
	}

}
