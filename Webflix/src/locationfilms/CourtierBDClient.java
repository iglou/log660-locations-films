package locationfilms;
import java.util.List;
import org.hibernate.Session;

public class CourtierBDClient {

	private Session session;
	
	public CourtierBDClient(){}
	
	public CourtierBDClient(Session session){
		this.session = session;
	}
	
	public void setSession(Session session){
		this.session = session;
	}
	
	public Client chercherClient(String courriel, String motDePasse){
		session.beginTransaction(); 
		String strQuery;
		
		strQuery = "SELECT c FROM Client c inner join c.personne p WHERE p.courriel ='" + courriel + "'";
		
		System.out.println("Executing query: " + strQuery);
		List lesClients = session.createQuery(strQuery).list(); 
		session.getTransaction().commit(); 
		
		if(lesClients.size() > 0)
			return (Client)lesClients.get(0);
		
		return null;
	}
	
	public boolean connection(String userMail, String password){
		
		session.beginTransaction(); 
		
		String getEmail = session.createQuery("select courrielClient from client where courrielClient =" + userMail).toString();
		if(getEmail.isEmpty()){
			System.out.println("Le courriel est inconnu, veuillez rentrez votre adresse mail correctement.");
			return false;
		}
		else{
			String getPassword = session.createQuery("select MotDePasse from client where courrielClient =" + userMail).toString(); 
			if( ! password.equals(getPassword)){
				System.out.println("Mauvais mot de passe, veuillez rentrez le bon mot de passe.");
				return false;
			}
			else{
				System.out.println("Connection accéptée.");
			}
		}
		session.getTransaction().commit();
		return true;
	}
	
	
}
