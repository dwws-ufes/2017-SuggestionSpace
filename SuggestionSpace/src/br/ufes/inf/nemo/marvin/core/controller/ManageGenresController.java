package br.ufes.inf.nemo.marvin.core.controller;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudService;
import br.ufes.inf.nemo.jbutler.ejb.application.filters.LikeFilter;
import br.ufes.inf.nemo.jbutler.ejb.controller.CrudController;
import br.ufes.inf.nemo.marvin.core.application.ManageGenresService;
import br.ufes.inf.nemo.marvin.core.domain.Genre;

@Named
@SessionScoped
public class ManageGenresController extends CrudController<Genre> {
	
	private static final long serialVersionUID = 1L;
	@EJB private ManageGenresService manageGenresService;
	@Override
	protected CrudService<Genre> getCrudService() {
		return manageGenresService;
	}

	@Override
	protected void initFilters() {
		addFilter(new LikeFilter("manageGenres.filter.byName", "name", getI18nMessage("msgsCore", "manageGenres.text.filter.byName")));		
	}

}
