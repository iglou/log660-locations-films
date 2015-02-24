package servlets;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import locationfilms.*;

/**
 * Servlet implementation class RechercheFilms
 */
@WebServlet("/RechercheFilms")
public class RechercheFilms extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RechercheFilms() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		
		// Creer un PrintWriter pour imprimer la page Web de la reponse
		OutputStreamWriter osw = new OutputStreamWriter(response.getOutputStream());
		PrintWriter out = new PrintWriter(osw);
		
		// Creer une session
		HttpSession session = request.getSession(false);
		String nomClient = session.getAttribute("nomClient").toString();
		
		try {
			FacadeLouerFilms flf;		
			flf = new FacadeLouerFilms();
			//Film film = new Film();
			//System.out.println("film: " + film.toString());
			
			// Entete de la page
			out.println("<html>");
			out.println("<head><title>Reponse du Servlet Webflix</title></head>");
			out.println("<body>");
			if(nomClient != null)
				out.println("<label style=\"position: absolute; top: 5px; right: 10px\">Bienvenu(e) " + nomClient + "</label>");
			
			//Formulaire de recherche
			out.println("<form action=\"ListeFilms\" method=\"post\">");
			out.println("<label>Recherche de films</label>");
			out.println("<br><br>");
			
			out.println("<label>Titre: </label>");
			out.println("<input name = \"txtTitre\" type=\"text\" name=\"user\" style=\"position:absolute; left: 120px;\">");
			out.println("<br><br>");
			
			int minAnnee = flf.getMinAnnee();
			int maxAnnee = flf.getMaxAnnee();
			
			out.println("<label >Ann&eacute;e</label>");
			out.println("<label style=\"position:absolute; left: 120px;\">de </label>");
			out.println("<select name=\"ddlAnneeDe\" style=\"position:absolute; left: 140px;\">");
			//Insertion des annees
			for(int i = minAnnee; i <= maxAnnee; i++){
				out.println("<option value=\"" + i + "\">" + i + "</option>");
			}
			out.println("</select>");
			
			out.println("<label style=\"position:absolute; left: 200px;\">&agrave; </label>");
			out.println("<select name=\"ddlAnneeA\" style=\"position:absolute; left: 220px;\">");
			//Insertion des annees
			for(int i = minAnnee; i <= maxAnnee; i++){
				out.println("<option value=\"" + i + "\"" + (i == maxAnnee ? "selected" : "") + ">" + i + "</option>");
			}
			out.println("</select>");
			
			out.println("<br><br>");
			out.println("<label>Pays: </label>");
			out.println("<select name=\"ddlPays\" style=\"position:absolute; left: 120px;\">");
			//Insertion des pays
			out.println("<option value=\"\"></option>");
			List lesPays = flf.getPaysList();
			System.out.println("Nb pays: " + lesPays.size());
			Object tempPays;
			for ( Iterator iterPays = lesPays.iterator(); iterPays.hasNext(); ) { 
				tempPays = iterPays.next();
				if(tempPays != null)
					out.println("<option value=\"" + tempPays.toString() + "\">" + tempPays.toString() + "</option>");
			}
			
			out.println("</select>");
			out.println("<br><br>");
			out.println("<label>Langue: </label>");			
			out.println("<select name=\"ddlLangue\" style=\"position:absolute; left: 120px;\">");
			//Insertion des langues
			out.println("<option value=\"\"></option>");
			List lesLangues = flf.getLangueList();
			Object tempLangue;
			for ( Iterator iterLangues = lesLangues.iterator(); iterLangues.hasNext(); ) {
				tempLangue = iterLangues.next();
				if(tempLangue != null)
					out.println("<option value=\"" + tempLangue.toString() + "\">" + tempLangue.toString() + "</option>");
			}
			
			out.println("</select>");
			out.println("<br><br>");
			
			out.println("<label>Genre: </label>");
			out.println("<select name=\"ddlGenre\" style=\"position:absolute; left: 120px;\">");
			//Insertion des genres
			out.println("<option value=\"\"></option>");
			List lesGenres = flf.getGenreList();
			Object tempGenre;
			for ( Iterator iterGenres = lesGenres.iterator(); iterGenres.hasNext(); ) {
				tempGenre = iterGenres.next();
				if(tempGenre != null)
					out.println("<option value=\"" + tempGenre.toString() + "\">" + tempGenre.toString() + "</option>");
			}
			
			out.println("</select>");
			out.println("<br><br>");
			
			out.println("<label>R&eacute;alisateur: </label>");
			out.println("<input name=\"txtRealisateur\" type=\"text\" style=\"position:absolute; left: 120px;\">");
			out.println("<br><br>");
			
			out.println("<label>Acteur(s): </label>");
			out.println("<input name=\"txtActeur\" type=\"text\" style=\"position:absolute; left: 120px;\">");
			out.println("<br><br>");
			out.println("<label>NB: Pour rechercher plusieurs acteurs, s&eacute;parez les nom par un point-virgule. ex: acteur1;acteur2;... </label>");
			out.println("<br><br>");
			
			out.println("<input type=\"submit\" value=\"rechercher\">");
			out.println("</form>");
			
			out.println("</body></html>");
			out.close();
			flf.closeSession();
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
