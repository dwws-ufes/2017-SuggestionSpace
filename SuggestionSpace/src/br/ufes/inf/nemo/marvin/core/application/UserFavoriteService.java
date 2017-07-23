package br.ufes.inf.nemo.marvin.core.application;

import javax.ejb.Local;

import br.ufes.inf.nemo.marvin.core.domain.Content;

import java.io.Serializable;
import java.util.List;

@Local
public interface UserFavoriteService  extends Serializable{
	List<Content> getFavorites();
}
