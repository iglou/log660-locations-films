package servlets;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
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
public class ValidationConnexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ValidationConnexion() {
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
		
		String strMail = request.getParameter("email");
		String strPassword = request.getParameter("mdp");
		
		FacadeConnexion fc;		
		try {
			fc = new FacadeConnexion();
			Boolean connexion = fc.connexion(strMail, strPassword);
		
			// Creer un PrintWriter pour imprimer la page Web de la reponse
			OutputStreamWriter osw = new OutputStreamWriter(response.getOutputStream());
			PrintWriter out = new PrintWriter(osw);
			
			// Entete de la page
			out.println("<html>");
			out.println("<head><title>Reponse du Servlet Webflix</title></head>");
			out.println("<body>");
			
			if(connexion){
				response.sendRedirect("RechercheFilms.java");
			}
			else{
				response.sendRedirect("Connexion.java");
			}
			
			out.println("</body></html>");
			out.close();
			fc.closeSession();
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
