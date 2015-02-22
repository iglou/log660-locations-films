// Classe Java: ServletWebflix.java

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import javax.servlet.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import oracle.jdbc.pool.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import java.sql.PreparedStatement;

public class ServletWebflix extends HttpServlet {
	// Initialisation du parent
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	// Methode doGet: on utilise l’implementation de doPost
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {
		doPost(request,response);
	}
	// Traitement de la requete:
	// On recoit une chaine de charactere et on retourne un page HTML
	// contenant le titre des films de la BD contenant cette chaine
	// dans leur titre.

	public void doPost(HttpServletRequest requete,HttpServletResponse reponse) throws ServletException, IOException {
		// Creer une session
		HttpSession session = requete.getSession(true);
		// Specifier le type et l’encodage des donnees
		reponse.setContentType("text/html");
		reponse.setCharacterEncoding("utf-8");
		// Creer un PrintWriter pour imprimer la page Web de la reponse
		OutputStreamWriter osw = new OutputStreamWriter(reponse.getOutputStream());
		PrintWriter out = new PrintWriter(osw);
		// Entete de la page
		out.println("<html>");
		out.println("<head><title>Reponse du Servlet Webflix</title></head>");
		out.println("<body>");
		String chaineRecherche = "";
		Connection conn = null;
		PreparedStatement ps = null;
		try {
		// Recuperer le parametre provenant de la page HTML d’entree
			chaineRecherche = requete.getParameter("chaineRecherche");
			
			if (chaineRecherche != null)
			{
				// Ouvrir une connexion en passant par un DataSource
				Context initContext = new InitialContext();
				Context envContext = (Context) initContext.lookup("java:/comp/env");
				OracleDataSource ds = (OracleDataSource) envContext.lookup("jdbc/webflix");
				conn = ds.getConnection();
	
				// Creer une requete au serveur BD		
				ps = conn.prepareStatement("SELECT * "+"FROM Film "+"WHERE titre like ?");
				ps.setString(1,"%" + chaineRecherche + "%");
				// Decoder les resultats
				ResultSet rs = ps.executeQuery();
				while (rs.next())
					out.println(rs.getString("titre") + " (" + rs.getInt("annee") + ")<br>");
			}
		}
		catch (Exception e) {
			// Debug: afficher la trace d’erreur directement dans la page
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			out.println("<p>" + sw.toString() + "</p>");
		}
		finally{
			try{
				// Liberer les connections et resources
				out.println("</body></html>");	
				out.close();
				ps.close();
				conn.close();
			}
			catch(Exception lException){
				lException.printStackTrace();
			}
		}
	}
}