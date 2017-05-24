package br.ufes.inf.nemo.marvin.core.domain;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport;
@Entity
public class Content extends PersistentObjectSupport implements Comparable<Content>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Basic
	@NotNull
	@Size(max = 250)
	private String description;
	
	@Basic
	@NotNull
	@Size(max = 50)
	private String name;
	
	private int identifier;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Type type;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Genre genre;
	
	public Genre getGenre() {
		return genre;
	}
	public void setGenre(Genre genre) {
		this.genre = genre;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	public int getIdentifier() {
		return identifier;
	}
	public void setIdentifier(int identifier) {
		this.identifier = identifier;
	}
	@Override
	public int compareTo(Content o) {
		
		if (name == null) return 1;
		if (o.name == null) return -1;
		int cmp = name.compareTo(o.name);
		if (cmp != 0) return cmp;

		// If it's the same name, check if it's the same entity.
		return uuid.compareTo(o.uuid);
	}
}
