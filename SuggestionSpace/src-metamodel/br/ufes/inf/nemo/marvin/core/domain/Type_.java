package br.ufes.inf.nemo.marvin.core.domain;

import br.ufes.inf.nemo.jbutler.ejb.persistence.PersistentObjectSupport_;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-05-19T21:06:10.700-0300")
@StaticMetamodel(Type.class)
public class Type_ extends PersistentObjectSupport_ {
	public static volatile SingularAttribute<Type, String> name;
	public static volatile ListAttribute<Type, Content> contents;
}
