package br.ufes.inf.nemo.marvin.core.domain;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport_;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-05-20T16:18:21.732-0300")
@StaticMetamodel(Content.class)
public class Content_ extends PersistentObjectSupport_ {
	public static volatile SingularAttribute<Content, String> description;
	public static volatile SingularAttribute<Content, String> name;
	public static volatile SingularAttribute<Content, Integer> identifier;
	public static volatile SingularAttribute<Content, Type> type;
	public static volatile SingularAttribute<Content, Genre> genre;
}
