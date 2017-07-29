package br.ufes.dwws.suggestionSpace.controller;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import br.ufes.dwws.suggestionSpace.application.DeleteEvent;
import br.ufes.dwws.suggestionSpace.application.ManageCollectionsService;
import br.ufes.dwws.suggestionSpace.application.ManageUsersService;
import br.ufes.dwws.suggestionSpace.application.UpdateEvent;
import br.ufes.dwws.suggestionSpace.application.ManageReviewService;
import br.ufes.dwws.suggestionSpace.domain.Collection;
import br.ufes.dwws.suggestionSpace.domain.Review;
import br.ufes.inf.nemo.jbutler.ejb.application.CrudService;
import br.ufes.inf.nemo.jbutler.ejb.controller.CrudController;
import br.ufes.inf.nemo.jbutler.ejb.controller.PersistentObjectConverterFromId;

@Named
@SessionScoped
public class CollectionController extends CrudController<Collection> implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private ManageCollectionsService manageCollectionsService;
	
	@EJB
	private ManageReviewService manageReviewService;

	@EJB
	private ManageUsersService manageUsersService;

	@Inject
	private SessionController sessionController;

	@Inject
	private HttpServletRequest request;

	private List<Collection> collections;
	private List<Review> review;
	private Collection newCollection = new Collection();
	private Collection selectedCollection;

	@Inject
	public void init() {
		collections = manageCollectionsService.list(sessionController.getCurrentUser());
	}

	public String schedule() {
		ResourceBundle bundle = FacesContext.getCurrentInstance()
				.getApplication()
				.getResourceBundle(FacesContext.getCurrentInstance(), "msgs");
		try {
			newCollection.setUser(manageUsersService.getDAO().retrieveById(sessionController.getCurrentUser().getId()));
			manageCollectionsService.create(newCollection);
			FacesContext.getCurrentInstance().getExternalContext().getFlash().put("alertType", "success");
			FacesContext.getCurrentInstance().getExternalContext().getFlash().put("alertMessage",bundle.getString("alert.collectionCreated"));
			newCollection = new Collection();
			refreshListCollection();
			return "/collection/list?faces-redirect=true";
		} catch (Exception e) {
			FacesContext.getCurrentInstance().getExternalContext().getFlash().put("alertType", "danger");
			FacesContext.getCurrentInstance().getExternalContext().getFlash().put("alertMessage",
					bundle.getString("alert.error"));
			return "/collection/form?faces-redirect=true";
		}
	}
	
	public void refreshListCollection(){
		collections = manageCollectionsService.list(sessionController.getCurrentUser());
	}

	public String details(String id) {
		selectedCollection = manageCollectionsService.retrieve(Long.parseLong(id));
		review = manageReviewService.list(selectedCollection);
		return "/collection/details?faces-redirect=true";
	}
	
	public void onDeleteUser(@Observes DeleteEvent deleteEvent) {
		refreshListCollection();
	}
	
	public void onUpdateUser(@Observes UpdateEvent updateEvent) {
		refreshListCollection();
	}

	public String update() {
		manageCollectionsService.getDAO().merge(selectedCollection);
		ResourceBundle bundle = FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "msgs");
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("alertType", "success");
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("alertMessage",bundle.getString("alert.collectionEdited"));
		refreshListCollection();
		return "/collection/list?faces-redirect=true";
	}

	public String edit(String id) {
		selectedCollection = manageCollectionsService.retrieve(Long.parseLong(id));
		return "/collection/edit?faces-redirect=true";
	}

	public String delete(String id) {
		selectedCollection = manageCollectionsService.retrieve(Long.parseLong(id));
		manageCollectionsService.delete(selectedCollection);
		ResourceBundle bundle = FacesContext.getCurrentInstance().getApplication()
				.getResourceBundle(FacesContext.getCurrentInstance(), "msgs");
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("alertType", "success");
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("alertMessage",
				bundle.getString("alert.collectionDeleted"));
		refreshListCollection();
		return "/collection/list?faces-redirect=true";
	}
	
	

	public Collection getNewCollection() {
		return newCollection;
	}

	public void setNewCollection(Collection newCollection) {
		this.newCollection = newCollection;
	}

	public ManageCollectionsService getCollectionsService() {
		return manageCollectionsService;
	}

	public void setCollectionsService(ManageCollectionsService manageCollectionsService) {
		this.manageCollectionsService = manageCollectionsService;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}


	public List<Collection> getCollections() {
		return collections;
	}

	public void setCollections(List<Collection> collections) {
		this.collections = collections;
	}

	public Collection getSelectedCollection() {
		return selectedCollection;
	}

	public void setSelectedCollection(Collection collection) {
		this.selectedCollection = collection;
	}

	@Override
	protected CrudService<Collection> getCrudService() {
		return manageCollectionsService;
	}

	@Override
	protected void initFilters() {
	}

	public SessionController getSessionController() {
		return sessionController;
	}

	public void setSessionController(SessionController sessionController) {
		this.sessionController = sessionController;
	}

	public List<Review> getReview() {
		return review;
	}

	public void setReview(List<Review> review) {
		this.review = review;
	}

	public ManageReviewService getManageReviewService() {
		return manageReviewService;
	}

	public void setManageReviewService(ManageReviewService manageReviewService) {
		this.manageReviewService = manageReviewService;
	}
	
}
