package br.ufes.dwws.suggestionSpace.persistence;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.ufes.dwws.suggestionSpace.domain.Collection;
import br.ufes.dwws.suggestionSpace.domain.User;
import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseJPADAO;

@Stateless
public class CollectionJPADAO extends BaseJPADAO<Collection> implements CollectionDAO {

	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}
	
	@Override
	public List<Collection> retrieveByCurrentUser(User currentUser){

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Collection> cq = cb.createQuery(Collection.class);
		Root<Collection> root = cq.from(Collection.class);

		if(currentUser.getRole().equals("admin")){
			cq.select(root);
		} else {
			cq.where(cb.equal(root.get("user"), currentUser.getId()));
		}
		TypedQuery<Collection> q = entityManager.createQuery(cq);
		List<Collection> result = q.getResultList();
		return result;
	}

}
