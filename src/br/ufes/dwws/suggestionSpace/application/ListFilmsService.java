package br.ufes.dwws.suggestionSpace.application;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;

import br.ufes.dwws.suggestionSpace.domain.Film;
import br.ufes.dwws.suggestionSpace.persistence.FilmDAO;

@Stateless
@LocalBean
public class ListFilmsService implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@EJB
	private FilmDAO filmDAO;

	@PersistenceContext
	private EntityManager entityManager;

	public List<Film> listFilms(String name) {
		List<Film> films = filmDAO.retrieveByName(name);
		return films;
	}
	
	public List<Film> listFilms() {
		List<Film> films = filmDAO.retrieveAll();
		return films;
	}
	
	public List<Film> fetchFilm(String name) {
		List<Film> filmsList = new ArrayList<Film>();
				
		String query = 	 "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
						"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
						"PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n" +
						"SELECT DISTINCT ?film ?description ?film_name WHERE {\n" + 
						"?film rdf:type <http://dbpedia.org/ontology/Film>\n." +
				    "?film foaf:name ?film_name." + 
				    "?film rdfs:comment ?description ." +
				    "FILTER (LANG(?description) = 'en' && contains(?film_name,\"" + name + "\"))\n" +
				    "} LIMIT 500" ;

		
		QueryExecution queryExecution = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql", query);

		ResultSet results = queryExecution.execSelect();

		while (results.hasNext()) {
			QuerySolution querySolution = results.next();
			Film film = new Film();
			String fn = querySolution.get("film_name").toString() ;
			film.setName(fn.substring(0,fn.length()-3));
			String desc = querySolution.get("description").toString();
			film.setDescription(desc);
			filmsList.add(film);
		}

		queryExecution.close();
		
		return filmsList;

	}
}
