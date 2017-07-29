package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.ResourceFactory;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.RDFS;

import br.ufes.dwws.suggestionSpace.domain.Film;
import br.ufes.dwws.suggestionSpace.domain.Review;
import br.ufes.dwws.suggestionSpace.persistence.FilmDAO;
import br.ufes.dwws.suggestionSpace.persistence.ReviewDAO;

@WebServlet(urlPatterns = "/data/filmreviews")
public class ListFilmReviewInRdfServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(ListFilmReviewInRdfServlet.class.getCanonicalName());

	private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

	@EJB
	private ReviewDAO reviewDAO;

	public ListFilmReviewInRdfServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/xml");

		List<Review> reviewList = reviewDAO.retrieveAll();

		Model model = ModelFactory.createDefaultModel();

		String myNS = "http://localhost:8080/SuggestionSpace/data/filmReview/";
		String grNS = "http://purl.org/obo/goodrelations/v1#";

		model.setNsPrefix("gr", grNS);
		
		Resource grReview = ResourceFactory.createResource(grNS + "Review");
		Property grReviewDescription = ResourceFactory.createProperty(grNS +
		"reviewDescription");
		Property grUser = ResourceFactory.createProperty(grNS +
		"user");
		
		for (Review review : reviewList) {

			model.createResource(myNS + review.getId())
					.addProperty(RDF.type, grReview)
					.addProperty(grReviewDescription, review.getDescription())
					.addProperty(RDFS.label, review.getFilm().getName()) 
					.addProperty(grUser, review.getCollection().getUser().getName()) ;
		}

		try (PrintWriter out = response.getWriter()) {
			model.write(out, "RDF/XML");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
