package br.ufes.inf.nemo.marvin.core.controller;

import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Named;

import br.ufes.inf.nemo.jbutler.TextUtils;
import br.ufes.inf.nemo.jbutler.ejb.application.CrudService;
import br.ufes.inf.nemo.jbutler.ejb.application.filters.LikeFilter;
import br.ufes.inf.nemo.jbutler.ejb.controller.CrudController;
import br.ufes.inf.nemo.marvin.core.application.ManageUsersService;
import br.ufes.inf.nemo.marvin.core.domain.User;

@Named
@SessionScoped
public class ManageUsersController extends CrudController<User> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB private ManageUsersService manageUsersService;
	
	private String repeatPassword;
	
	private static final Logger logger = Logger.getLogger(ManageUsersController.class.getCanonicalName());

	
	/** Getter for repeatPassword. */
	public String getRepeatPassword() {
		return repeatPassword;
	}

	/** Setter for repeatPassword. */
	public void setRepeatPassword(String repeatPassword) {
		this.repeatPassword = repeatPassword;
	}
	
	
	public void ajaxCheckPasswords() {
		checkPasswords();
	}
	
	public void suggestShortName() {
		// If the name was filled and the short name is still empty, suggest the first name as short name.
		String name = selectedEntity.getName();
		String shortName = selectedEntity.getShortName();
		if ((name != null) && ((shortName == null) || (shortName.length() == 0))) {
			int idx = name.indexOf(" ");
			selectedEntity.setShortName((idx == -1) ? name : name.substring(0, idx).trim());
			logger.log(Level.FINE, "Suggested \"{0}\" as short name for \"{1}\"", new Object[] { selectedEntity.getShortName(), name });
		}
		else logger.log(Level.FINEST, "Short name not suggested: empty name or short name already filled (name is \"{0}\", short name is \"{1}\")", new Object[] { name, shortName });
	}
	
	private boolean checkPasswords() {
		if (((repeatPassword != null) && (!repeatPassword.equals(selectedEntity.getPassword()))) || ((repeatPassword == null) && (selectedEntity.getPassword() != null))) {
			logger.log(Level.INFO, "Password and repeated password are not the same");
			addGlobalI18nMessage("msgsCore", FacesMessage.SEVERITY_WARN, "installSystem.error.passwordsDontMatch.summary", "installSystem.error.passwordsDontMatch.detail");
			return false;
		}
		return true;
	}
	
	@Override
	protected CrudService<User> getCrudService() {
		return manageUsersService;
	}
	
	@Override
	protected void initFilters() {
		addFilter(new LikeFilter("manageUsers.filter.byName", "name", getI18nMessage("msgsCore", "manageUsers.text.filter.byName")));		
	}
	
	@Override
	public String save(){
		try {
			selectedEntity.setPassword(TextUtils.produceMd5Hash(selectedEntity.getPassword()));			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return super.save();
	}

}
