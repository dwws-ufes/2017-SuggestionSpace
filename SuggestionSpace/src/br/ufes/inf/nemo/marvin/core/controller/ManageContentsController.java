package br.ufes.inf.nemo.marvin.core.controller;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.ufes.inf.nemo.jbutler.ejb.application.CrudService;
import br.ufes.inf.nemo.jbutler.ejb.application.filters.LikeFilter;
import br.ufes.inf.nemo.jbutler.ejb.controller.CrudController;
import br.ufes.inf.nemo.marvin.core.application.ManageContentsService;
import br.ufes.inf.nemo.marvin.core.domain.Content;

@Named
@SessionScoped
public class ManageContentsController extends CrudController<Content> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB private ManageContentsService manageContentsService;

	@Override
	protected CrudService<Content> getCrudService() {
		// TODO Auto-generated method stub
		return manageContentsService;
	}

	@Override
	protected void initFilters() {
		addFilter(new LikeFilter("manageAcademics.filter.byName", "name", getI18nMessage("msgsCore", "manageContents.text.filter.byName")));	
	}
}
