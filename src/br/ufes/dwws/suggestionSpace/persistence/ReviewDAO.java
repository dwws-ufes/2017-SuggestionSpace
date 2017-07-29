package br.ufes.dwws.suggestionSpace.persistence;

import java.util.List;

import javax.ejb.Local;

import br.ufes.dwws.suggestionSpace.domain.Collection;
import br.ufes.dwws.suggestionSpace.domain.Review;
import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO;

@Local
public interface ReviewDAO extends BaseDAO<Review>{

	public List<Review> retrieveByCollection(Collection collection);
	
}
