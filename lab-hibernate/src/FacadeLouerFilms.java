import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.*;

public class FacadeLouerFilms {

	private Session uneSession;
	
	public FacadeLouerFilms() throws Exception{
		
		try {
		
			uneSession = HibernateUtil.getSessionFactory().openSession();
		}
		catch (HibernateException e) {
			e.printStackTrace();
			System.exit(1);
		} finally {
			closeSession();
		}
	}
	
	public List chercherFilms(String titre, int anneeMin, int anneeMax, String pays, 
                                    String langue, String genre, String realisateur, 
                                    String acteurs){

		CourtierBDFilm courtierBDFilm = new CourtierBDFilm(uneSession);
		
		return courtierBDFilm.chercherFilms(titre, anneeMin, anneeMax, pays, langue, genre, realisateur, acteurs);
	}
	
	
	public void ajouterClient(String nom, String prenom, String courriel, String numTelephone, 
			                  int numCivique, String rue, String ville, String province, 
			                  String codePostal, Date DateNaissance, String motDePasse, 
			                  String typeCarteCredit, String numCarteCredit, int moisExpiration, 
			                  int anneeExpiration, int cvv){
		
		
		
	}
	
	public void louerFilm(String idClient, String idFilm){
		
	}
	
	public void closeSession(){
		if(uneSession.isOpen())
			uneSession.close();
	}
	
}
