package br.ufes.dwws.suggestionSpace.application;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufes.dwws.suggestionSpace.domain.Collection;
import br.ufes.dwws.suggestionSpace.domain.Film;
import br.ufes.dwws.suggestionSpace.domain.Review;
import br.ufes.dwws.suggestionSpace.persistence.FilmDAO;
import br.ufes.dwws.suggestionSpace.persistence.ReviewDAO;
import br.ufes.inf.nemo.jbutler.ejb.application.CrudServiceBean;
import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO;

@Stateless
@PermitAll
public class ManageReviewServiceBean extends CrudServiceBean<Review> implements ManageReviewService {
	private static final long serialVersionUID = 1L;

	@EJB
	private ReviewDAO reviewDAO;

	@EJB
	private FilmDAO filmDAO ;
	
	@Override
	public BaseDAO<Review> getDAO() {
		return reviewDAO;
	}

	@Override
	public List<Review> list(Collection collection) {
		return reviewDAO.retrieveByCollection(collection);
	}
	
	public void deleteAllMovies(){
		filmDAO.deleteAll(); 
	}
	
	public void save(Film f){
		filmDAO.save(f);
	}

}
