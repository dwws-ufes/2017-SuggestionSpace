package br.ufes.dwws.suggestionSpace.controller;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Any;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Literal;

import br.ufes.dwws.suggestionSpace.domain.User;
import br.ufes.dwws.suggestionSpace.login.LoginEvent;
import br.ufes.dwws.suggestionSpace.login.LoginFailedException;
import br.ufes.dwws.suggestionSpace.login.LoginService;
import br.ufes.dwws.suggestionSpace.login.LoginFailedException.LoginFailedReason;
import br.ufes.inf.nemo.jbutler.TextUtils;

@Named
@SessionScoped
public class SessionController implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(SessionController.class.getCanonicalName());

	@EJB
	private LoginService loginService;

	@Inject
	@Any
	private Event<LoginEvent> loginEvent;

	private User currentUser;
	private String email;
	private String password;
	private Boolean hasLoggedUser;
	private String language = "en-US";

	public void changeLanguage(String language) {
		this.setLanguage(language);
	}

	public String login() {
		return login(email, password);
	}

	public String login(String username, String pwd) {

		try {

			try {
				loginService.login(username, TextUtils.produceBase64EncodedMd5Hash(pwd));
			} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
				e.printStackTrace();
				throw new LoginFailedException(e, LoginFailedReason.MD5_ERROR);
			}

			try {
				HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
						.getRequest();
				request.login(username, pwd);
				logger.log(Level.INFO, "login done by JAAS");
			} catch (Exception e) {
				e.printStackTrace();
				logger.log(Level.INFO, "http error when login by JAAS");
				throw new LoginFailedException(e, LoginFailedReason.NO_HTTP_REQUEST);
			}

		} catch (LoginFailedException e) {

			switch (e.getReason()) {

			case INCORRECT_PASSWORD:
				logger.log(Level.INFO, "Incorrect password");
				return null;
			case UNKNOWN_USERNAME:
				logger.log(Level.INFO, "unknown username");
				return null;
			default:
				logger.log(Level.INFO, "unknown error");
				return null;

			}

		}

		currentUser = loginService.getCurrentUser();

		if (currentUser != null)
			loginEvent.fire(new LoginEvent(currentUser));

		logger.log(Level.INFO, "login current user: " + currentUser);

		return "/index.xhtml?faces-redirect=true";
	}

	public String logout() {
		getRequest().getSession().invalidate();
		return "/index?faces-redirect=true";
	}

	private HttpServletRequest getRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	}

	public Boolean getHasLoggedUser() {
		return hasLoggedUser;
	}

	public void setHasLoggedUser(Boolean hasLoggedUser) {
		this.hasLoggedUser = hasLoggedUser;
	}

	public User getCurrentUser() {
		logger.log(Level.INFO, "getCurrentUser -> current user: " + currentUser);
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean hasLoggedUser() {
		return currentUser != null;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}
}
