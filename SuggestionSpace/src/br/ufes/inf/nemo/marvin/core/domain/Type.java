package br.ufes.inf.nemo.marvin.core.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport;

@Entity
public class Type extends PersistentObjectSupport {

	/**
	 * 
	 */
	private String name;
	@OneToMany
	private List<Content> contents;
	
	public List<Content> getContents() {
		return contents;
	}

	public void setContents(List<Content> contents) {
		this.contents = contents;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	private static final long serialVersionUID = 1L;

}
