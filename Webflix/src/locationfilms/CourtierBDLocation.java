package locationfilms;

import java.util.Date;
import java.util.List;
import org.hibernate.Session;

import locationfilms.*;

public class CourtierBDLocation {

	private Session session;
	
	public CourtierBDLocation(){}
	
	public CourtierBDLocation(Session session){
		this.session = session;
	}
	
	public void setSession(Session session){
		this.session = session;
	}
	
	public boolean louerFilm(ExemplaireFilm exemplaireFilm, Client client){
		session.beginTransaction();
		
		int noIdLocation = 0;
		List list;
		list = session.createQuery("select max(lec.idLocation) as max from LocationEnCours lec").list();
		noIdLocation = (list.get(0) != null) ? ((int)list.get(0) + 1) : 1;
		
		Date currentDate = new Date();
		LocationEnCours locationEnCours = new LocationEnCours(noIdLocation, currentDate, client, exemplaireFilm);
		session.save(locationEnCours);
		session.getTransaction().commit();
		
		return true;
	}
	
	public int getNbLocationClient(String idClient){
		int nbLocationEnCours = 0;
		session.beginTransaction();
		List lesClients = session.createQuery("From LocationEnCours lec inner join lec.client c where c.idClient ='" + idClient + "'").list();
		if(lesClients != null)
			nbLocationEnCours = lesClients.size();
		
		session.getTransaction().commit();
		return nbLocationEnCours;
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

