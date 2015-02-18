package servlets;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		
		String strIdFilm = request.getParameter("ddlFilms");
		System.out.println("strFilm: " + strIdFilm);
		
		// Creer un PrintWriter pour imprimer la page Web de la reponse
		OutputStreamWriter osw = new OutputStreamWriter(response.getOutputStream());
		PrintWriter out = new PrintWriter(osw);
		
		try {
			FacadeLouerFilms flf;
			flf = new FacadeLouerFilms();
			Film film = flf.getFilmById(strIdFilm);
						
			// Entete de la page
			out.println("<html>");
			out.println("<head><title>Description du film</title></head>");
			out.println("<body>");
			
			out.println("<form action=\"DescriptionFilm\" method=\"post\">");
			out.println("<label>Film</label>");
			out.println("<br><br>");
			
			out.println("<label>Titre: </label>");
			out.println("<label style=\"position:absolute; left: 200px;\">" + film.getTitre() + "</label>");
			out.println("<br><br>");
			
			out.println("<label>Ann&eacute;e: </label>");
			out.println("<label style=\"position:absolute; left: 200px;\">" + film.getAnnee() + "</label>");
			out.println("<br><br>");
			
			out.println("<label>Pays de production: </label>");
			out.println("<label style=\"position:absolute; left: 200px;\">USA</label>");
			out.println("<br><br>");
			
			out.println("<label>Langue: </label>");
			out.println("<label style=\"position:absolute; left: 200px;\">" + film.getLangue() + "</label>");
			out.println("<br><br>");
			
			out.println("<label>Dur&eacute;e: </label>");
			out.println("<label style=\"position:absolute; left: 200px;\">" + film.getDuree() + "</label>");
			out.println("<br><br>");
			
			out.println("<label>Genres: </label>");
			out.println("<label style=\"position:absolute; left: 200px;\">Comedy</label>");
			out.println("<br><br>");
			
			out.println("<label>R&eacute;alisateur: </label>");
			out.println("<label style=\"position:absolute; left: 200px;\">Comedy</label>");
			out.println("<br><br>");
			
			out.println("<label>Sc&eacute;naristes: </label>");
			out.println("<label style=\"position:absolute; left: 200px;\">Scenariste1</label>");
			out.println("<br><br>");
			
			out.println("<label>Acteur: </label><label style=\"position:absolute; left: 200px;\">Personnage jou&eacute;: </label>");
			out.println("<br><br>");
			
			out.println("<label>Acteur1 </label><label style=\"position:absolute; left: 200px;\"> pers1</label><br>");
			out.println("<label>Acteur2 </label><label style=\"position:absolute; left: 200px;\"> pers2</label><br>");
			out.println("<br><br>");
			
			out.println("<input type=\"submit\" value=\"Submit\">");
			
			out.println("</form>");
			out.println("</body></html>");
			out.close();
			flf.closeSession();
		
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
