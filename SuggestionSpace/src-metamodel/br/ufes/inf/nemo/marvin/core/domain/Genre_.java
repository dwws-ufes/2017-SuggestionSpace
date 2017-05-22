package br.ufes.inf.nemo.marvin.core.domain;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport_;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-05-19T21:04:28.739-0300")
@StaticMetamodel(Genre.class)
public class Genre_ extends PersistentObjectSupport_ {
	public static volatile SingularAttribute<Genre, String> name;
	public static volatile ListAttribute<Genre, Content> contents;
}
