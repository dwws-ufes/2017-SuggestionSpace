package br.ufes.dwws.suggestionSpace.signup;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;

import br.ufes.dwws.suggestionSpace.domain.Admin;
import br.ufes.dwws.suggestionSpace.domain.Film;
import br.ufes.dwws.util.Role;

@Stateless @LocalBean
public class RegistrationService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public void register(Admin admin) {
		admin.setRole(Role.ADMIN);
		entityManager.persist(admin);
	}

}
