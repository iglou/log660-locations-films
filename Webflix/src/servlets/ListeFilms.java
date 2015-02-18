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
 * Servlet implementation class ListeFilms
 */
@WebServlet("/ListeFilms")
public class ListeFilms extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListeFilms() {
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
		int anneeDe, anneeA;
		
		String strTitre = request.getParameter("txtTitre");
		String strAnneeDe = request.getParameter("ddlAnneeDe");
		String strAnneeA = request.getParameter("ddlAnneeA");
		String strPays = request.getParameter("ddlPays");
		String strLangue = request.getParameter("ddlLangue");
		String strGenre = request.getParameter("ddlGenre");
		String strRealisateur = request.getParameter("txtRealisateur");
		String strActeurs = request.getParameter("txtActeur");
		
		if(strAnneeDe != null)
			anneeDe = Integer.parseInt(strAnneeDe);
		else
			anneeDe = 0;
		
		if(strAnneeA != null)
			anneeA = Integer.parseInt(strAnneeA);
		else
			anneeA = 3000;
		
		System.out.println("pays: " + strPays);
		
		FacadeLouerFilms flf;		
		try {
			flf = new FacadeLouerFilms();
			List lesFilms = flf.chercherFilms(strTitre, anneeDe, anneeA, strPays, strLangue, strGenre, strRealisateur, strActeurs);
		
		
			// Creer un PrintWriter pour imprimer la page Web de la reponse
			OutputStreamWriter osw = new OutputStreamWriter(response.getOutputStream());
			PrintWriter out = new PrintWriter(osw);
			
			// Entete de la page
			out.println("<html>");
			out.println("<head><title>Reponse du Servlet Webflix</title></head>");
			out.println("<body>");
			
			//Formulaire de liste de films
			out.println("<form action=\"DescriptionFilm\" method=\"post\">");
			out.println("<label>Film(s) trouv&eacute;(s)</label>");
			out.println("<br><br>");
			out.println("<select size=\"10\" name=\"ddlFilms\">");
			
			//Object tempFilm;
			for ( Iterator iterFilm = lesFilms.iterator(); iterFilm.hasNext(); ) { 
				Film unFilmCharge = (Film) iterFilm.next();
					out.println("<option value=\"" + unFilmCharge.getIdFilm() + "\">" + unFilmCharge.getTitre() + "</option>");
			}
			
			
			out.println("</select>");
			out.println("<br><br>");
			
			// Creer une session
			//HttpSession session = request.getSession(true);
			
			out.println("<input type=\"submit\" value=\"Fiche description\">");
			
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
