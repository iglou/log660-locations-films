package locationfilms;
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
			//closeSession();
		}
	}
	
	public List chercherFilms(String titre, int anneeMin, int anneeMax, String pays, 
                                    String langue, String genre, String realisateur, 
                                    String acteurs){

		CourtierBDFilm courtierBDFilm = new CourtierBDFilm(uneSession);
		
		return courtierBDFilm.chercherFilms(titre, anneeMin, anneeMax, pays, langue, genre, realisateur, acteurs);
	}
	
	public Film getFilmById(String idFilm){
		CourtierBDFilm courtierBDFilm = new CourtierBDFilm(uneSession);
		return courtierBDFilm.getFilmById(idFilm);
	}
	
	public PersonneCinema getPersonneCinemaById(String idPersonneCinema){
		CourtierBDFilm courtierBDFilm = new CourtierBDFilm(uneSession);
		return courtierBDFilm.getPersonneCinemaById(idPersonneCinema);
	}
	
	public List getPaysList(){
		CourtierBDFilm courtierBDFilm = new CourtierBDFilm(uneSession);
		return courtierBDFilm.getPaysList();
	}
	
	public List getGenreList(){
		CourtierBDFilm courtierBDFilm = new CourtierBDFilm(uneSession);
		return courtierBDFilm.getGenreList();
	}
	
	public List getLangueList(){
		CourtierBDFilm courtierBDFilm = new CourtierBDFilm(uneSession);
		return courtierBDFilm.getLangueList();
	}
	
	public double getCoteMoy(String idFilm){
		CourtierBDFilm courtierBDFilm = new CourtierBDFilm(uneSession);
		return courtierBDFilm.getCoteMoy(idFilm);
	}
	
	public List getFilmRecommandeList(String idFilm, String idClient){
		CourtierBDFilm courtierBDFilm = new CourtierBDFilm(uneSession);
		return courtierBDFilm.getFilmsRecommandes(idFilm, idClient);
	}
	
	public int getMinAnnee(){
		CourtierBDFilm courtierBDFilm = new CourtierBDFilm(uneSession);
		return courtierBDFilm.getMinAnnee();
	}
	
	public int getMaxAnnee(){
		CourtierBDFilm courtierBDFilm = new CourtierBDFilm(uneSession);
		return courtierBDFilm.getMaxAnnee();
	}
	
	public Client chercherClient(String courriel, String motDePasse){
		CourtierBDClient courtierBDClient = new CourtierBDClient(uneSession);
		return courtierBDClient.chercherClient(courriel, motDePasse);
	}
	
	public boolean louerFilm(ExemplaireFilm exemplaireFilm, Client client){
		CourtierBDLocation courtierBDLocation = new CourtierBDLocation(uneSession);
		return courtierBDLocation.louerFilm(exemplaireFilm, client);
	}
	
	public int getNbLocationClient(String idClient){
		CourtierBDLocation courtierBDLocation = new CourtierBDLocation(uneSession);
		return courtierBDLocation.getNbLocationClient(idClient);
	}
	
	public void closeSession(){
		if(uneSession.isOpen())
			uneSession.close();
	}
	
}
