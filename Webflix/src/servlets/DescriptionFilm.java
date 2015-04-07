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
 * Servlet implementation class DescriptionFilm
 */
@WebServlet("/DescriptionFilm")
public class DescriptionFilm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DescriptionFilm() {
        super();
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
		
		String strIdFilm = request.getParameter("ddlFilms");
		System.out.println("strFilm: " + strIdFilm);
		
		// Creer un PrintWriter pour imprimer la page Web de la reponse
		OutputStreamWriter osw = new OutputStreamWriter(response.getOutputStream());
		PrintWriter out = new PrintWriter(osw);
		
		try {
			FacadeLouerFilms flf;
			flf = new FacadeLouerFilms();
			Film film;
			if(strIdFilm != null)
				film = flf.getFilmById(strIdFilm);
			else
				film = null;
			
			// Creer une session
			HttpSession session = request.getSession(false);
			Client client = (Client)session.getAttribute("client");
			session.setAttribute("film", film);

			// Entete de la page
			out.println("<html>");
			out.println("<head><title>Description du film</title></head>");
			out.println("<body>");
			
			out.println("<form action=\"LocationServlet\" method=\"post\">");
			out.println("<label>Fiche descriptive du film</label>");
			out.println("<br><br>");
			
			System.out.println("Nb exemplaires: " + film.getExemplaires().size());
			
			if(film != null & film.getPoster() != null){
				out.println("<img src=\"" + film.getPoster() + "\">");
				out.println("<br><br>");
			}
			out.println("<label>Titre: </label>");
			out.println("<label style=\"position:absolute; left: 200px;\">" + (film != null ? film.getTitre() : "") + "</label>");
			out.println("<br><br>");
			
			out.println("<label>Ann&eacute;e: </label>");
			out.println("<label style=\"position:absolute; left: 200px;\">" + (film != null ? film.getAnnee() : "") + "</label>");
			out.println("<br><br>");
			
			out.println("<label>Cote moyenne du film : <label/>" + flf.getCoteMoy(film.getIdFilm()) + "<br><br>");
			
			String strFilmRecommande = "";
			List lesFilmsRecommandes = flf.getFilmRecommandeList(film.getIdFilm(), client.getIdClient());
			Object tempFilm;
			for ( Iterator iterFilm = lesFilmsRecommandes.iterator(); iterFilm.hasNext(); ) { 
				tempFilm = iterFilm.next();
				if(tempFilm != null)
					strFilmRecommande += tempFilm.toString() + ", ";	
			}
			
			out.println("<label>Films recommand&eacute;s : " + strFilmRecommande.substring(0, strFilmRecommande.length() -2) + "</label>");
			out.println("<br><br>");
			
			out.println("<label>Pays de production: </label>");			
			String strPays = "";
			if(film != null){
				for ( Iterator iterPays = film.getPays().iterator(); iterPays.hasNext(); ) { 
					 FilmPays unPaysCharge = (FilmPays) iterPays.next();
					 if(!strPays.isEmpty())
						 strPays += ", ";
					 strPays +=  unPaysCharge.getNomPays();
				 }
			}
			out.println("<label style=\"position:absolute; left: 200px;\">" + strPays + "</label>");
			out.println("<br><br>");
			
			out.println("<label>Langue: </label>");
			out.println("<label style=\"position:absolute; left: 200px;\">" + (film != null ? film.getLangue() : "") + "</label>");
			out.println("<br><br>");
			
			out.println("<label>Dur&eacute;e: </label>");
			out.println("<label style=\"position:absolute; left: 200px;\">" + (film != null ? film.getDuree() : "") + "</label>");
			out.println("<br><br>");
			
			out.println("<label>Genres: </label>");
			
			String strGenres = "";
			if(film != null){
				for ( Iterator iterGenres = film.getGenres().iterator(); iterGenres.hasNext(); ) { 
					 GenreFilm unGenreCharge = (GenreFilm) iterGenres.next();
					 if(!strGenres.isEmpty())
						 strGenres += ", ";
					 strGenres +=  unGenreCharge.getNomGenre();
				 }				
			}
			
			out.println("<label style=\"position:absolute; left: 200px;\">" + strGenres + "</label>");
			out.println("<br><br>");
			
			out.println("<label>R&eacute;alisateur: </label>");			
			out.println("<label style=\"position:absolute; left: 200px;\">" + ((film != null && film.getRealisateur() != null) ? film.getRealisateur().getNom() : "") + "</label>");
			out.println("<br><br>");
			
			out.println("<label>Sc&eacute;naristes: </label>");
			String strScenaristes = "";
			if(film != null){
				for ( Iterator iterScenariste = film.getScenaristes().iterator(); iterScenariste.hasNext(); ) { 
					 Scenariste unScenaristeCharge = (Scenariste) iterScenariste.next();
					 if(!strScenaristes.isEmpty())
						 strScenaristes += ", ";
					 strScenaristes +=  unScenaristeCharge.getNomScenariste();
				 }
			}
			
			out.println("<label style=\"position:absolute; left: 200px;\">" + strScenaristes + "</label>");
			out.println("<br><br>");
			
			out.println("<label>Acteur: </label><label style=\"position:absolute; left: 200px;\">Personnage jou&eacute;: </label>");
			out.println("<br><br>");
			
			if(film != null){
				for ( Iterator iterPersonnage = film.getPersonnages().iterator(); iterPersonnage.hasNext(); ) { 
					 PersonnageFilm unPersonnageCharge = (PersonnageFilm) iterPersonnage.next();
					 out.println("<a href=\"DescriptionPersonnage?idPersonne=" + unPersonnageCharge.getPersonneCinema().getIdPersonneCinema() + "\">" + unPersonnageCharge.getPersonneCinema().getNom() + "</a><label style=\"position:absolute; left: 200px;\">" + unPersonnageCharge.getNomPersonnage() + "</label><br>");
				}
				out.println("<br><br>");
			}
			
			out.println("<label>R&eacute;sum&eacute;: </label><br>");
			out.println("<p>" + (film != null ? film.getResume() : "") + "</p>");
			out.println("<br><br>");
			
			out.println("<input type=\"submit\" value=\"Louer\">");
			System.out.println("film poster: " + film.getPoster());		
			out.println("</form>");
			out.println("</body></html>");
			out.close();
			flf.closeSession();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
