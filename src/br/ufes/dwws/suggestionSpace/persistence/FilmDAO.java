package br.ufes.dwws.suggestionSpace.persistence;

import java.util.List;

import javax.ejb.Local;

import br.ufes.dwws.suggestionSpace.domain.Film;
import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO;

@Local
public interface FilmDAO extends BaseDAO<Film>{
	public void deleteAll() ;

	List<Film> retrieveByName(String name);
}
