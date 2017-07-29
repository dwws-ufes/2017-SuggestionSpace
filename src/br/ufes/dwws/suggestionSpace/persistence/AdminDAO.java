package br.ufes.dwws.suggestionSpace.persistence;

import javax.ejb.Local;

import br.ufes.dwws.suggestionSpace.domain.Admin;
import br.ufes.inf.nemo.jbutler.ejb.persistence.BaseDAO;

@Local
public interface AdminDAO extends BaseDAO<Admin>{}