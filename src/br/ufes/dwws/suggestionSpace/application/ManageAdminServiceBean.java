package br.ufes.dwws.suggestionSpace.application;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufes.dwws.suggestionSpace.domain.Admin;
import br.ufes.dwws.suggestionSpace.persistence.AdminDAO;
import br.ufes.inf.nemo.jbutler.ejb.application.CrudServiceBean;
import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO;

@Stateless
@PermitAll
public class ManageAdminServiceBean extends CrudServiceBean<Admin> implements ManageAdminService {

	private static final long serialVersionUID = 1L;

	@EJB
	private AdminDAO adminDAO;

	@Override
	public BaseDAO<Admin> getDAO() {
		return adminDAO;
	}

	@Override
	public List<Admin> list() {
		return adminDAO.retrieveAll();
	}
}
