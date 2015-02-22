package servlets;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import locationfilms.*;

/**
 * Servlet implementation class DescriptionPersonnage
 */
@WebServlet("/DescriptionPersonnage")
public class DescriptionPersonnage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DescriptionPersonnage() {
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
		// TODO Auto-generated method stub
		
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		
		String strIdPersonne = request.getParameter("idPersonne");
		System.out.println("strIdPersonne: " + strIdPersonne);
		
		// Creer un PrintWriter pour imprimer la page Web de la reponse
		OutputStreamWriter osw = new OutputStreamWriter(response.getOutputStream());
		PrintWriter out = new PrintWriter(osw);
		
		try {
			FacadeLouerFilms flf;
			flf = new FacadeLouerFilms();
			PersonneCinema personneCinema = flf.getPersonneCinemaById(strIdPersonne);
			
			// Entete de la page
			out.println("<html>");
			out.println("<head><title>Description du personnage</title></head>");
			out.println("<body>");
			
			out.println("<form action=\"DescriptionFilm\" method=\"post\">");
			out.println("<label>Fiche descriptive</label>");
			out.println("<br><br>");
			
			out.println("<label>Nom: </label>");
			out.println("<label style=\"position:absolute; left: 200px;\">"  + (personneCinema != null ? personneCinema.getNom() : "") + "</label>");
			out.println("<br><br>");
			
			out.println("<label>Date de naissance: </label>");
			out.println("<label style=\"position:absolute; left: 200px;\">"  + (personneCinema != null ? personneCinema.getDateNaissance() : "") + "</label>");
			out.println("<br><br>");
			
			out.println("<label>Lieu de naissance: </label>");
			out.println("<label style=\"position:absolute; left: 200px;\">"  + (personneCinema != null ? personneCinema.getLieuNaissance() : "") + "</label>");
			out.println("<br><br>");
			
			out.println("<label>Biographie: </label>");
			out.println("<label style=\"position:absolute; left: 200px;\">"  + (personneCinema != null ? personneCinema.getBiographie() : "") + "</label>");
			out.println("<br><br>");
			
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
