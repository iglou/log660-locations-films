package locationfilms;

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
	
	public Film getFilmById(String idFilm){
		session.beginTransaction(); 
		String strQuery;
		
		strQuery = "FROM Film f WHERE f.idFilm ='" + idFilm + "'";
		
		System.out.println("Executing query: " + strQuery);
		List lesFilms = session.createQuery(strQuery).list(); 
		session.getTransaction().commit(); 
		
		if(lesFilms.size() > 0)
			return (Film)lesFilms.get(0);
		
		return null;
	}
	
	public PersonneCinema getPersonneCinemaById(String idPersonneCinema){
		session.beginTransaction(); 
		String strQuery;
		
		strQuery = "FROM PersonneCinema pc WHERE pc.idPersonneCinema ='" + idPersonneCinema + "'";
		
		System.out.println("Executing query: " + strQuery);
		List lesPersonnesCinema = session.createQuery(strQuery).list(); 
		session.getTransaction().commit(); 
		
		if(lesPersonnesCinema.size() > 0)
			return (PersonneCinema)lesPersonnesCinema.get(0);
		
		return null;
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
		session.getTransaction().commit(); 
		return lesGenres;
	}
	
	public List getPaysList(){
		session.beginTransaction();
		
		List lesPays = session.createQuery(
		"select distinct fp.nomPays from FilmPays fp"
		).list();
		session.getTransaction().commit(); 
		return lesPays;
	}
	
	public List getLangueList(){
		session.beginTransaction();
		
		List lesLangues = session.createQuery(
		"select distinct f.langue from Film f"
		).list();
		session.getTransaction().commit(); 
		return lesLangues;
	}
	
	public double getCoteMoy(String idFilm){
		session.beginTransaction();
		List coteMoy = session.createSQLQuery(
				"select moyenne from matmoy where idFilm = '"+ idFilm + "'").list();
		session.getTransaction().commit(); 
		
		if(coteMoy.size() > 0)
			return Double.parseDouble(coteMoy.get(0).toString());
		
		return 0;
	}
	
	public List getFilmsRecommandes(String idFilm, String idClient){
		session.beginTransaction();
		
		String strQuery = " select titre from " + 
		                   " (select flm1.idfilm, flm2.titre, flm1.correlation  from " + 
				          " (select " + 
		                   " (case when ma.idfilm1 = '" + idFilm + "' then ma.idfilm2 " + 
				          " else ma.idfilm1 END) as idfilm, " + 
		                   " ma.correlation " + 
				          " from MATCORRR ma  " +
		                   " where (ma.idfilm1 = '" + idFilm + "' or ma.idfilm2 = '" + idFilm + "') ) flm1 " +
				          " inner join film flm2 on flm1.idfilm= flm2.idfilm " + 
		                   " where not exists(select * from LOCATIONENCOURS lec " + 
				          " inner join exemplairefilm ef on lec.codeexemplaire = ef.codeexemplaire " +
		                   " where lec.idclient = '" + idClient + "' and ef.idfilm = flm1.idfilm ) " +
				          " order by correlation desc ) flm3 " + 
		                   " where ROWNUM <= 3 ";
		
		System.out.println("idfilm rec: " + idFilm + " idClient: " + idClient);
		List filmsRecommandes = session.createSQLQuery(strQuery).list();
		session.getTransaction().commit(); 
		return filmsRecommandes;
	}
}
