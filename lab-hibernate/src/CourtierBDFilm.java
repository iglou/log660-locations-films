import java.util.List;
import org.hibernate.Session;

public class CourtierBDFilm {

	private Session session;
	
	public CourtierBDFilm(){}
	
	public CourtierBDFilm(Session session){
		this.session = session;
	}
	
	public void setSession(Session session){
		this.session = session;
	}
	
	public List chercherFilms(String titre, int anneeMin, int anneeMax, String pays, 
			                  String langue, String genre, String realisateur, 
			                  String acteurs){
		
		session.beginTransaction(); 
		
		String strQuery;
		
		strQuery = "FROM Film f WHERE (0=0) ";
		
		if(!titre.isEmpty())
			strQuery += " AND upper(f.titre) like '%" + titre + "%' ";
		
		if(!langue.isEmpty())
			strQuery += " AND f.langue like '%" + langue + "%' ";
		
		strQuery += " AND f.annee >= " + anneeMin + " AND f.annee <= " + anneeMax;
			
		if(!pays.isEmpty())
			strQuery += " AND f.idfilm in (" +
		                 "select fp.idfilm from FilmPays fp + " +
					     " where fp.idfilm = f.idfilm AND fp.nompays = '" + pays + "')";
		
		if(!genre.isEmpty())
			strQuery += " AND f.idfilm in (" +
		                 "select gf.idfilm from GenreFilm gf + " +
					     " where gf.idfilm = f.idfilm AND gf.nomgenre = '" + genre + "')";
		
		if(!realisateur.isEmpty())
			strQuery += " AND f.idrealisateur in (" +
		                 "select pc.idpersonnecinema from PersonneCinema pc + " +
					     " where pc.idpersonnecinema = f.idrealisateur AND upper(pc.nom) like '%" + realisateur.toUpperCase() + "%')";
		
		if(!acteurs.isEmpty()){
			strQuery += " AND f.idfilm in (" +
		                 "select pf.idfilm from PersonnageFilm pf + " +
					     " inner join PersonneCinema pc" + 
					     " where pf.idfilm = f.idfilm ";
			
			String[] arrActeurs = acteurs.split("\\s+");
			
			for(int i = 0; i < arrActeurs.length; i++){
				strQuery += " AND upper(pc.nom) like '%" + arrActeurs[0].toUpperCase() + "%' ";
			}
			strQuery += ")";
		}
		 
		strQuery += "ORDER BY f.titre ASC"; 
		
		List lesFilms = session.createQuery(strQuery).list(); 
				 
		session.getTransaction().commit(); 
		return lesFilms;
	}
	
	public int getMinAnnee(){
		session.beginTransaction(); 
		
		List minAnnee = session.createQuery(
		"select min(f.annee) as min from film f").list(); 
		
		session.getTransaction().commit(); 
		
		if(minAnnee.size() > 0)
			return Integer.parseInt(minAnnee.get(0).toString());
		
		return Integer.MIN_VALUE;
	}
	
	public int getMaxAnnee(){
		session.beginTransaction(); 
		
		List maxAnnee = session.createQuery(
		"select max(f.annee) as max from film f").list(); 
		
		session.getTransaction().commit(); 
		
		if(maxAnnee.size() > 0)
			return Integer.parseInt(maxAnnee.get(0).toString());
		
		return Integer.MAX_VALUE;
	}
	
	public List getGenreList(){
		session.beginTransaction();
		
		List lesGenres = session.createQuery(
		"select distinct gf.nomGenre from GenreFilm gf"
		).list();
		
		return lesGenres;
	}
	
	public List getPaysList(){
		session.beginTransaction();
		
		List lesPays = session.createQuery(
		"select distinct fp.nomPays from FilmPays fp"
		).list();
		
		return lesPays;
	}
	
	public List getLangueList(){
		session.beginTransaction();
		
		List lesLangues = session.createQuery(
		"select distinct f.langue from Film f"
		).list();
		
		return lesLangues;
	}
}
