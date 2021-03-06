package br.ufes.inf.nemo.marvin.core.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport;

@Entity
public class Type extends PersistentObjectSupport implements Comparable<Type>{

	/**
	 * 
	 */
	private String name;
	@OneToMany(fetch = FetchType.LAZY)
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
	
	@Override
	public int compareTo(Type o) {
		if (name == null) return 1;
		if (o.name == null) return -1;
		int cmp = name.compareTo(o.name);
		if (cmp != 0) return cmp;

		// If it's the same name, check if it's the same entity.
		return uuid.compareTo(o.uuid);
	}

}
