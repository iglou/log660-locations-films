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
			strQuery += " AND upper(f.titre) like '%" + titre.toUpperCase() + "%' ";
		
		if(!langue.isEmpty())
			strQuery += " AND upper(f.langue) = '" + langue.toUpperCase() + "' ";
		
		strQuery += " AND f.annee >= " + anneeMin + " AND f.annee <= " + anneeMax;
			
		if(!pays.isEmpty())
			strQuery += " AND f.idFilm in (" +
		                 "select fp.film.idFilm from FilmPays fp " +
					     " where fp.film.idFilm = f.idFilm AND upper(fp.nomPays) = '" + pays.toUpperCase() + "')";
		
		if(!genre.isEmpty())
			strQuery += " AND f.idFilm in (" +
	                 "select gf.film.idFilm from GenreFilm gf " +
				     " where gf.film.idFilm = f.idFilm AND upper(gf.nomGenre) = '" + genre.toUpperCase() + "')";
			
		if(!realisateur.isEmpty())
			strQuery += " AND f.realisateur.idPersonneCinema in (" +
		                 "select pc.idPersonneCinema from PersonneCinema pc " +
					     " where pc.idPersonneCinema = f.realisateur.idPersonneCinema AND upper(pc.nom) like '%" + realisateur.toUpperCase() + "%')";
		
		if(!acteurs.isEmpty()){
			String[] arrActeurs = acteurs.split(";");
			for(int i = 0; i < arrActeurs.length; i++){
				strQuery += " AND f.idFilm in (" +
		                 "select pf.film.idFilm from PersonnageFilm pf " +
					     " where pf.film.idFilm = f.idFilm " +
					     " AND upper(pf.personneCinema.nom) like '%" + arrActeurs[i].toUpperCase() + "%' )";;
			}			
		}
		 
		strQuery += " ORDER BY f.titre ASC"; 
		
		System.out.println("Executing query: " + strQuery);
		List lesFilms = session.createQuery(strQuery).list(); 
				 
		session.getTransaction().commit(); 
		return lesFilms;
	}
	
	public int getMinAnnee(){
		session.beginTransaction(); 
		
		List minAnnee = session.createQuery(
		"select min(f.annee) as min from Film f").list(); 
		
		session.getTransaction().commit(); 
		
		if(minAnnee.size() > 0)
			return Integer.parseInt(minAnnee.get(0).toString());
		
		return Integer.MIN_VALUE;
	}
	
	public int getMaxAnnee(){
		session.beginTransaction(); 
		
		List maxAnnee = session.createQuery(
		"select max(f.annee) as max from Film f").list(); 
		
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
		"select distinct coalesce(f.langue, '') from Film f"
		).list();
		
		return lesLangues;
	}
}
