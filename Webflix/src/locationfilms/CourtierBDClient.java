package locationfilms;

import locationfilms.*;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
	
	public boolean connection(String userMail, String password){
		
		session.beginTransaction(); 
		
		String getEmail = session.createQuery("select courrielClient from client where courrielClient =" + userMail).toString();
		if(getEmail.isEmpty()){
			System.out.println("Le courriel est inconnu, veuillez rentrez votre adresse mail correctement.");
			return false;
		}
		else{
			String getPassword = session.createQuery("select MotDePasse from client where courrielClient =" + userMail).toString(); 
			MD5Password pswcrytpe = new MD5Password(password);
			if( ! pswcrytpe.equals(getPassword)){
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
	
	public boolean createClient(String nom, String prenom, String courriel, String numTelephone, 
            int numCivique, String rue, String ville, String province, String codePostal, Date DateNaissance, String motDePasse, 
            String typeCarteCredit, String numCarteCredit, int moisExpiration, int anneeExpiration, int cvv){

		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.MONTH, moisExpiration);
		calendar.set(Calendar.YEAR, anneeExpiration);
		Date dateExpiration = calendar.getTime();
		MD5Password psw = new MD5Password(motDePasse);
		Set forfait = new HashSet(0);
		
		Client newClient = new Client();
		newClient.setNomClient(nom);
		newClient.setprenomClient(prenom);
		newClient.setCourrielClient(courriel);
		newClient.setTelephoneClient(numTelephone);
		newClient.setDobClient(DateNaissance);
		
		newClient.setMotDePasseClient(psw);
		newClient.setTypeCarteCredit(typeCarteCredit);
		newClient.setNumeroCarteCredit(numCarteCredit);
		newClient.setDateExpirationCarteCredit(dateExpiration);
		newClient.setCvv(cvv);
		newClient.setForfaitClient(forfait);
		return true;
	}
	
}
