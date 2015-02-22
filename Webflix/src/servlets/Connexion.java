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

import locationfilms.*;

/**
 * Servlet implementation class RechercheFilms
 */
@WebServlet("/Connexion")
public class Connexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Connexion() {
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
		
		// Creer un PrintWriter pour imprimer la page Web de la reponse
		OutputStreamWriter osw = new OutputStreamWriter(response.getOutputStream());
		PrintWriter out = new PrintWriter(osw);
		
		try {			
			// Entete de la page
			out.println("<html>");
			out.println("<head><title>Connexion à la location de films</title></head>");
			out.println("<body>");
			
			//Formulaire de connexion
			out.println("<form action=\"connexion\" method=\"post\">");
			out.println("<label>Connexion</label>");
			out.println("<br><br>");
			
			out.println("<label>Entrez votre adresse mail : </label>");
			out.println("<input name = \"email\" type=\"text\" \">");
			out.println("<br><br>");
			
			out.println("<label>Entrez votre mot de passe : </label>");
			out.println("<input name = \"mdp\" type=\"password\" \">");
			out.println("<br><br>");
			
			out.println("<input type=\"submit\" value=\"Valider\">");
			out.println("</form>");
			
			out.println("</body></html>");
			out.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
