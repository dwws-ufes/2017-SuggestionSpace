package br.ufes.dwws.suggestionSpace.controller;

import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import br.ufes.dwws.suggestionSpace.application.ListFilmsService;
import br.ufes.dwws.suggestionSpace.application.ManageReviewService;
import br.ufes.dwws.suggestionSpace.domain.Collection;
import br.ufes.dwws.suggestionSpace.domain.Film;
import br.ufes.dwws.suggestionSpace.domain.Review;
import br.ufes.dwws.suggestionSpace.persistence.FilmDAO;
import br.ufes.inf.nemo.jbutler.ejb.application.CrudService;
import br.ufes.inf.nemo.jbutler.ejb.controller.CrudController;
import br.ufes.inf.nemo.jbutler.ejb.controller.PersistentObjectConverterFromId;

@Named
@SessionScoped
public class ReviewController extends CrudController<Review> {

	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(ReviewController.class.getCanonicalName());

	@EJB
	private ManageReviewService manageReviewService;

	@EJB
	private ListFilmsService listFilmsService;

	@EJB
	FilmDAO filmDAO ;
	
	private Collection collection;
	private Review review = new Review();
	private List<Film> films;

	private PersistentObjectConverterFromId<Film> filmConverter;


	public List<Film> getFilms() {
		return films;
	}

	public void setFilms(List<Film> films) {
		this.films = films;
	}

	public PersistentObjectConverterFromId<Film> getFilmConverter() {
		return filmConverter;
	}

	public void setFilmConverter(PersistentObjectConverterFromId<Film> filmConverter) {
		this.filmConverter = filmConverter;
	}

	
	@Override
	protected CrudService<Review> getCrudService() {
		return manageReviewService;
	}

	@Override
	protected void initFilters() {
	}

	public String create(Collection collection) {
		System.out.println("Entrei no create");
		this.collection = collection;
		String name = collection.getName().trim() ;
		for (Film p : listFilmsService.fetchFilm(name)) {
			manageReviewService.save(p);
		}
		films = listFilmsService.listFilms(name);
		filmConverter = new PersistentObjectConverterFromId<Film>(filmDAO);
		return "/review/form?faces-redirect=true";
	}

	public String diagnose() {
		ResourceBundle bundle = FacesContext.getCurrentInstance().getApplication()
				.getResourceBundle(FacesContext.getCurrentInstance(), "msgs");
		try {
			
			review.setCollection(collection);
			manageReviewService.create(review);
			FacesContext.getCurrentInstance().getExternalContext().getFlash().put("alertType", "success");
			FacesContext.getCurrentInstance().getExternalContext().getFlash().put("alertMessage",
					bundle.getString("alert.reviewCreated"));
			

			/* created new instances for news review */
			review = new Review();
		
			return "/collection/list?faces-redirect=true";
			
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().getExternalContext().getFlash().put("alertType", "danger");
			FacesContext.getCurrentInstance().getExternalContext().getFlash().put("alertMessage",
					bundle.getString("alert.error"));
			return "/collection/details?faces-redirect=true";
		}
	}

	public ManageReviewService getManageReviewService() {
		return manageReviewService;
	}

	public void setManageReviewService(ManageReviewService manageReviewService) {
		this.manageReviewService = manageReviewService;
	}

	public Review getReview() {
		return review;
	}

	public void setReview(Review review) {
		this.review = review;
	}

	public Collection getCollection() {
		return collection;
	}

	public void setCollection(Collection collection) {
		this.collection = collection;
	}

}
