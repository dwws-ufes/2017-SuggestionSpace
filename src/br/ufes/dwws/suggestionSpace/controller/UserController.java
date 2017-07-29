package br.ufes.dwws.suggestionSpace.controller;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.ResourceBundle;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Any;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import br.ufes.dwws.suggestionSpace.application.ManageUsersService;
import br.ufes.dwws.suggestionSpace.converters.StringToSetConverter;
import br.ufes.dwws.suggestionSpace.application.DeleteEvent;
import br.ufes.dwws.suggestionSpace.application.UpdateEvent;
import br.ufes.dwws.suggestionSpace.domain.User;
import br.ufes.dwws.suggestionSpace.login.LoginEvent;
import br.ufes.dwws.util.Mail;
import br.ufes.dwws.util.Role;
import br.ufes.inf.nemo.jbutler.TextUtils;
import br.ufes.inf.nemo.jbutler.ejb.application.CrudService;
import br.ufes.inf.nemo.jbutler.ejb.controller.CrudController;

@Named
@SessionScoped
public class UserController extends CrudController<User> implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private ManageUsersService manageUsersService;

	@Inject
	private SessionController sessionController;
	
	@Inject
	@Any
	private Event<DeleteEvent> deleteEvent;
	
	@Inject
	@Any
	private Event<UpdateEvent> updateEvent;
	
	@Inject
	private HttpServletRequest request;

	private List<User> users;
	private User newUser = new User();
	private User selectedUser;

	private StringToSetConverter str2listConverter = new StringToSetConverter();

	@Inject
	public void init() {
		if(sessionController.hasLoggedUser()){
			users = manageUsersService.retrieveAllUsers();
		}
	}

	public String register() {			
		ResourceBundle bundle = FacesContext.getCurrentInstance() .getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "msgs"); 
		try {
			String rawPwd = newUser.getPassword();
			String md5pwd = TextUtils.produceBase64EncodedMd5Hash(rawPwd);
			newUser.setPassword(md5pwd);
			newUser.setRole(Role.USER);
			manageUsersService.create(newUser);
			Mail mail = new Mail();
			String message = String.format(
					"<h2>%s</h2>"
					+ "<p>%s %s</p>"
					+ "<p>%s %s",
					bundle.getString("mail.created"), bundle.getString("mail.login"),
					newUser.getEmail(), bundle.getString("mail.password"), rawPwd);
			
			mail.send(newUser.getEmail(), bundle.getString("mail.subject"), message);
			
		} catch (NoSuchAlgorithmException|UnsupportedEncodingException e) {
			FacesContext.getCurrentInstance().getExternalContext().getFlash().put("alertType", "danger");
			FacesContext.getCurrentInstance().getExternalContext().getFlash().put("alertMessage",
					bundle.getString("alert.error"));
			return "/collection/form";
		}
		
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("alertType", "success");
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("alertMessage",
				bundle.getString("alert.userCreated"));
		
		newUser = new User();
		refreshListUsers();
		
		return "/user/list?faces-redirect=true";
	}
	
	private void refreshListUsers(){
		if(sessionController.hasLoggedUser()){
			users = manageUsersService.retrieveAllUsers();
		}
	}

	
	public String details(String id) {
		selectedUser = manageUsersService.retrieve(Long.parseLong(id));
		return "/user/details?faces-redirect=true";
	}
	
	public String update(){
		manageUsersService.getDAO().merge(selectedUser);
		refreshListUsers();
		updateEvent.fire(new UpdateEvent());
		return "/user/list?faces-redirect=true";
	}

	public String edit(String id){
		selectedUser = manageUsersService.retrieve(Long.parseLong(id));
		refreshListUsers();
		return "/user/edit?faces-redirect=true";
	}
	
	public String delete(String id){
		selectedUser = manageUsersService.retrieve(Long.parseLong(id));
		manageUsersService.delete(selectedUser);
		refreshListUsers();
		deleteEvent.fire(new DeleteEvent());
		return "/user/list?faces-redirect=true";
	}
	

	public User getNewUser() {
		return newUser;
	}

	public void setNewUser(User newUser) {
		this.newUser = newUser;
	}

	public User getSelectedUser() {
		return selectedUser;
	}

	public void setSelectedUser(User selectedUser) {
		this.selectedUser = selectedUser;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public StringToSetConverter getStr2listConverter() {
		return str2listConverter;
	}

	public void setStr2listConverter(StringToSetConverter str2listConverter) {
		this.str2listConverter = str2listConverter;
	}

	@Override
	protected CrudService<User> getCrudService() {
		return manageUsersService;
	}

	@Override
	protected void initFilters() {
		// TODO Auto-generated method stub
	}

	public SessionController getSessionController() {
		return sessionController;
	}

	public void setSessionController(SessionController sessionController) {
		this.sessionController = sessionController;
	}

}
