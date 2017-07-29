package br.ufes.dwws.suggestionSpace.validators;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import br.ufes.dwws.suggestionSpace.persistence.UserDAO;
import br.ufes.inf.nemo.jbutler.ejb.persistence.exceptions.MultiplePersistentObjectsFoundException;
import br.ufes.inf.nemo.jbutler.ejb.persistence.exceptions.PersistentObjectNotFoundException;

@ManagedBean
@RequestScoped
public class EmailValidator implements Validator {

	@EJB
	UserDAO userDAO;
	
	private static final Logger logger = Logger.getLogger(EmailValidator.class.getCanonicalName());

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

		String email = (String) value;
		
		if(!email.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"))
		{
			throw new ValidatorException(new FacesMessage("E-mail format invalid"));
		}

		try {
			
			logger.log(Level.INFO, "entrou em validator");
			
			if (userDAO.retrieveByEmail(email) != null){
				logger.log(Level.INFO, "j� tem email cadastrado");
				throw new ValidatorException(new FacesMessage("E-mail already registered"));
			}

		} catch (PersistentObjectNotFoundException | MultiplePersistentObjectsFoundException e1) {
			
			logger.log(Level.INFO, "lancou excessao");
			
			if (e1 instanceof MultiplePersistentObjectsFoundException)
				throw new ValidatorException(new FacesMessage("E-mail already registered"));
		}

	}

}