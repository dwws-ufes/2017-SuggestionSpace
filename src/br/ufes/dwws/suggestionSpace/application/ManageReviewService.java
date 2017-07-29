package br.ufes.dwws.suggestionSpace.application;

import java.util.List;

import javax.ejb.Local;

import br.ufes.dwws.suggestionSpace.domain.Collection;
import br.ufes.dwws.suggestionSpace.domain.Film;
import br.ufes.dwws.suggestionSpace.domain.Review;
import br.ufes.inf.nemo.jbutler.ejb.application.CrudService;


@Local
public interface ManageReviewService extends CrudService<Review>{
	
	public List<Review> list(Collection collection);
	
	public void deleteAllMovies() ;
	
	public void save(Film f) ;
}
