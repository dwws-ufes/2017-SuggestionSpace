package br.ufes.dwws.suggestionSpace.persistence;


import java.util.List;
import java.util.logging.Level;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.ufes.dwws.suggestionSpace.domain.Film;
import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseJPADAO;

@Stateless
public class FilmJPADAO extends BaseJPADAO<Film> implements FilmDAO {

	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	public void deleteAll(){
			List<Film> tmp = this.retrieveAll() ;
			for (Film f : tmp){
				this.delete(f);
			}
	}
	
	@Override
	public List<Film> retrieveByName(String name) {
		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Film> cq = cb.createQuery(Film.class);
		Root<Film> root = cq.from(Film.class);
		cq.where(cb.like(root.<String>get("name"),"%"+name+"%"));		
		TypedQuery<Film> query = entityManager.createQuery(cq);
		List<Film> result = query.getResultList();
		
		return result;
	}

}
