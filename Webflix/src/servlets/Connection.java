package servlets;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Connection
 */
@WebServlet("/Connection")
public class Connection extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Connection() {
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
		
		// Entete de la page
		out.println("<html>");
		out.println("<head><title>Connection Webflix</title></head>");
		out.println("<body>");
		
		out.println("<form action=\"connecter\" method=\"post\">");
		out.println("<label>Courriel: </label>");
		out.println("<input name = \"txtCourriel\" type=\"text\" style=\"position:absolute; left: 120px;\">");
		out.println("<br><br>");
		out.println("<label>Mot de passe: </label>");
		out.println("<input name = \"txtMotDePasse\" type=\"password\" style=\"position:absolute; left: 120px;\">");
		out.println("<br><br>");
		
		out.println("<input type=\"submit\" value=\"search\">");
		out.println("</form>");
		
		out.println("</body></html>");
		out.close();
		
	}

}
