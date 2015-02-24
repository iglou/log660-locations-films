package servlets;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.HibernateException;

import locationfilms.*;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
		String courriel = request.getParameter("txtCourriel");
		String motDePasse = request.getParameter("pwdMotDePasse");
		String strMessage = "";
		
		try {
			FacadeLouerFilms flf;	
			flf = new FacadeLouerFilms();
			Client client = flf.chercherClient(courriel, motDePasse);
			
			if(client != null){
				if(client.getPersonne().getMotDePasse().equals(motDePasse)){
					// Creer une session.
					HttpSession session = request.getSession(true);
					// Sauvegarder le client dans la session en cours.
					session.setAttribute("idClient", client.getIdClient());
					session.setAttribute("nomClient", client.getPersonne().getPrenom() + " " + client.getPersonne().getNom());
					session.setAttribute("client", client);
					session.setAttribute("locationMax", client.getForfait().getLocationMax());
					response.sendRedirect("RechercheFilms");
				}
				else
					strMessage = "Erreur. Mot de passe invalide.";
			}
			else
				strMessage = "Erreur. Aucun client ne correspond à l'adresse fournie.";
			
			if(!strMessage.isEmpty()){
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/Login.html");
				PrintWriter out= response.getWriter();
				out.println("<font color=red>" + strMessage + "</font>");
				rd.include(request, response);
				out.close();
			}
			
			flf.closeSession();
		}
		catch (HibernateException e) {
			e.printStackTrace();
			System.exit(1);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
