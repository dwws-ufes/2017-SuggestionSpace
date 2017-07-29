package br.ufes.dwws.suggestionSpace.persistence;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import java.util.List;

import br.ufes.dwws.suggestionSpace.domain.Collection;
import br.ufes.dwws.suggestionSpace.domain.Review;
import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseJPADAO;

@Stateless
public class ReviewJPADAO extends BaseJPADAO<Review> implements ReviewDAO {

	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}
	
	@Override
	public List<Review> retrieveByCollection(Collection collection) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Review> cq = cb.createQuery(Review.class);
		Root<Review> root = cq.from(Review.class);

		cq.where(cb.equal(root.get("collection"), collection));
	
		TypedQuery<Review> q = entityManager.createQuery(cq);
		List<Review> result = q.getResultList();
		return result;
	}

}
