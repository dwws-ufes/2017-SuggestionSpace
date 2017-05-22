package br.ufes.inf.nemo.marvin.core.controller;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudService;
import br.ufes.inf.nemo.jbutler.ejb.application.filters.LikeFilter;
import br.ufes.inf.nemo.jbutler.ejb.controller.CrudController;
import br.ufes.inf.nemo.marvin.core.application.ManageTypesService;
import br.ufes.inf.nemo.marvin.core.domain.Type;

@Named
@SessionScoped
public class ManageTypesController extends CrudController<Type> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB private ManageTypesService manageTypesService;
	@Override
	protected CrudService<Type> getCrudService() {
		return manageTypesService;
	}

	@Override
	protected void initFilters() {
		addFilter(new LikeFilter("manageTypes.filter.byName", "name", getI18nMessage("msgsCore", "manageTypes.text.filter.byName")));		
	}

}
