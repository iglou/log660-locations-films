import java.util.List;
import org.hibernate.Session;

public class courtierBDClient {

	private Session session;
	
	public courtierBDClient(){}
	
	public courtierBDClient(Session session){
		this.session = session;
	}
	
	public void setSession(Session session){
		this.session = session;
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
