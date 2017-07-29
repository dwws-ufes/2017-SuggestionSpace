package br.ufes.dwws.suggestionSpace.controller;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.ResourceBundle;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import br.ufes.dwws.suggestionSpace.domain.Admin;
import br.ufes.dwws.suggestionSpace.login.LoginService;
import br.ufes.dwws.suggestionSpace.signup.RegistrationService;
import br.ufes.dwws.util.Mail;
import br.ufes.inf.nemo.jbutler.TextUtils;
import br.ufes.inf.nemo.jbutler.ejb.controller.PersistentObjectConverterFromId;

@Named
@RequestScoped
public class RegistrationController implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private RegistrationService registrationService;

	@Inject
	private HttpServletRequest request;

	@Inject
	private SessionController sessionController;

	@EJB
	private LoginService loginService;

	private Admin admin = new Admin();

	private String role;

	public String register() {

		String rawPwd = admin.getPassword();
		ResourceBundle bundle = FacesContext.getCurrentInstance() .getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "msgs"); 
		
		try {
			
			String md5pwd = TextUtils.produceBase64EncodedMd5Hash(rawPwd);
			admin.setPassword(md5pwd);
			registrationService.register(admin);

			Mail mail = new Mail();
			String message = String.format(
					"<h2>%s</h2>"
					+ "<p>%s %s</p>"
					+ "<p>%s %s",
					bundle.getString("mail.created"), bundle.getString("mail.login"),
					admin.getEmail(), bundle.getString("mail.password"), rawPwd);
			
			mail.send(admin.getEmail(), bundle.getString("mail.subject"), message);
			
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			request.setAttribute("alertType", "danger");
			request.setAttribute("message", bundle.getString("alert.error"));
			return "/signup/index.xhtml";
		}

		sessionController.login(admin.getEmail(), rawPwd);

		getFlash().put("alertType", "success");
		getFlash().put("alertMessage", bundle.getString("alert.adminCreated"));

		return "/index.xhtml?faces-redirect=true";
		
	}

	private Flash getFlash() {
		return FacesContext.getCurrentInstance().getExternalContext().getFlash();
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}