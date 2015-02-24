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
 * Servlet implementation class LocationServlet
 */
@WebServlet("/LocationServlet")
public class LocationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LocationServlet() {
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
		String strMessage = "";
		try {
			FacadeLouerFilms flf;
			flf = new FacadeLouerFilms();
			
			// Creer une session
			HttpSession session = request.getSession(false);
			
			Film film = (Film)session.getAttribute("film");
			Client client = (Client)session.getAttribute("client");
			int locationMax = (int) session.getAttribute("locationMax");
			
			if(client != null && film != null){
				
				int nbLocations = flf.getNbLocationClient(client.getIdClient());
				
				System.out.println("client id: " + client.getIdClient() + " Location max: " + locationMax);
				
				if(nbLocations >= locationMax){
					strMessage = "<font color=red>Le nombre de locations maximales permis par le forfait a été atteint.</font>";
				}
				else{
					
					ExemplaireFilm copie;
					for ( Iterator iterExemplaire = film.getExemplaires().iterator(); iterExemplaire.hasNext(); ) { 
						copie = (ExemplaireFilm)iterExemplaire.next();
						if(copie != null && copie.getDisponible().equalsIgnoreCase("Y")){
							flf.louerFilm(copie, client);
							strMessage = "<font color=green>La location a &eacute;t&eacute; enregistr&eacute;e avec succ&egrave;s.</font>";
							break;
						}
						strMessage = "<font color=red>Il n'y a plus d'exemplaire de disponible.</font>";
					}
				}
			}
			else
			{
				response.sendRedirect("Login.html");
			}
			flf.closeSession();
		}
		catch (Exception e) {
			strMessage = "<font color=red>Une erreur interne est survenue.</font>";
			e.printStackTrace();
		}
		finally{
			// Entete de la page
			out.println("<html>");
			out.println("<head><title>Location du film en cours</title></head>");
			out.println("<body>");
			out.println(strMessage);
			out.println("<br><br>");
			out.println("</body></html>");
			out.close();
			
		}
		
	}

}
