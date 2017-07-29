package br.ufes.dwws.suggestionSpace.persistence;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.ufes.dwws.suggestionSpace.domain.Admin;
import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseJPADAO;

@Stateless
public class AdminJPADAO extends BaseJPADAO<Admin> implements AdminDAO {
	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}
}
