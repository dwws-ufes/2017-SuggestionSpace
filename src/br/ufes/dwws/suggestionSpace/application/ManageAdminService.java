package br.ufes.dwws.suggestionSpace.application;

import java.util.List;

import javax.ejb.Local;

import br.ufes.dwws.suggestionSpace.domain.Admin;
import br.ufes.inf.nemo.jbutler.ejb.application.CrudService;

@Local
public interface ManageAdminService extends CrudService<Admin> {
	
	public List<Admin> list();

}
