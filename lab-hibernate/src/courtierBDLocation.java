import java.util.List;
import org.hibernate.Session;

public class courtierBDLocation {

	private Session session;
	
	public courtierBDLocation(){}
	
	public courtierBDLocation(Session session){
		this.session = session;
	}
	
	public void setSession(Session session){
		this.session = session;
	}
	
	public boolean locationFilm(int idFilm, String emailClient){
		
		session.beginTransaction(); 
		
		int locationMax = session.createQuery("select locationMax from locationEnCours, client where locationEnCours.idClient = client.idClient and client.courrielClient =" + emailClient).executeUpdate();
		int nbLocationEnCours = session.createQuery("select count(*) from locationEnCours, client where client.idClient = locationEnCours.idClient and client.courrielClient" + emailClient).getFetchSize();
		int nbExemplaireFilm = session.createQuery("select count(*) from exemplaireFilm, film where film.idFilm = exemplaireFilm.idFilm and exemplaireFilm.idFilm = " + idFilm).executeUpdate();
		if(nbLocationEnCours >= locationMax){
			System.out.println("Vous avez atteint le nombre maximal de location que votre abonnement permet.");
			return false;
		}
		else if(nbExemplaireFilm <= 0){
			System.out.println("Il n'y a plus d'exemplaire disponible de ce film.");
			return false;
		}
		else{
			String idClient = session.createQuery("select idClient from client where courrielClient = " + emailClient).toString();
			String idExemplaireFilm = session.createQuery("select idExemplaireFilm from exemplaireFilm limit 1").toString();
			List machin = session.createQuery("insert into locationEnCours (dateLocation, idClient, idExemplaireFilm) values (CURDATE(), idClient, idExemplaireFilm)").list();
		}		 
		session.getTransaction().commit();
		return true;
	}
	
	
}
