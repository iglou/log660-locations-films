
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Main {
	public static void main(String[] args) {
		Session uneSession = HibernateUtil.getSessionFactory().openSession();
		try {
			uneSession = HibernateUtil.getSessionFactory().openSession(); 
			 uneSession.beginTransaction(); 
			 List lesFilms = uneSession.createQuery( 
			 "FROM Film f " + 
			 "WHERE f.titre = 'Twins' OR f.titre = 'Monsters vs Aliens' " +
			 "ORDER BY f.titre ASC").list(); 
			 
			 System.out.println( lesFilms.size() + " films trouvés:" ); 
			 
			 for ( Iterator iterFilms = lesFilms.iterator(); iterFilms.hasNext(); ) { 
				 Film unFilmCharge = (Film) iterFilms.next(); 
				 //System.out.println("Film:" + unFilmCharge.getTitre() + " Realisateur: " + unFilmCharge.getRealisateur().getNom()); 
				 //System.out.println("Annee:" + unFilmCharge.getAnnee()); 
				 //System.out.println("Langue:" + unFilmCharge.getLangue());
				 //System.out.println("Duree:" + unFilmCharge.getDuree()); 
				 //System.out.println("Resume:" + unFilmCharge.getResume()); 
				 
				 //displayGenres(unFilmCharge.getGenres());
				 //displayPays(unFilmCharge.getPays());
				 //displayScenaristes(unFilmCharge.getScenaristes());
				 //displayPersonnages(unFilmCharge.getPersonnages());
				 displayActeurs(unFilmCharge.getPersonnages());
				 
				 /*System.out.println("Genres de film(" + unFilmCharge.getGenres().size() + "):" ); 
			 
				 for ( Iterator iterGenres = unFilmCharge.getGenres().iterator(); iterGenres.hasNext(); ) { 
					 GenreFilm unGenreCharge = (GenreFilm) iterGenres.next(); 
					 System.out.println(" " + unGenreCharge.getNomGenre() ); 
				 } */
			 } 
			 uneSession.getTransaction().commit(); 
			 uneSession.close(); 
			 System.exit(0);
			
		} catch (HibernateException e) {
			e.printStackTrace();
			System.exit(1);
		} finally {
			if(uneSession.isOpen())
				uneSession.close();
		}
	}
	
	public static void displayGenres(Set genres){
		System.out.println("Genres de film(" + genres.size() + "):" ); 
		 
		 for ( Iterator iterGenres = genres.iterator(); iterGenres.hasNext(); ) { 
			 GenreFilm unGenreCharge = (GenreFilm) iterGenres.next(); 
			 System.out.println(" " + unGenreCharge.getNomGenre() ); 
		 }
	}
	
	public static void displayPays(Set pays){
		System.out.println("Pays de film(" + pays.size() + "):" ); 
		 
		 for ( Iterator iterPays = pays.iterator(); iterPays.hasNext(); ) { 
			 FilmPays unPaysCharge = (FilmPays) iterPays.next(); 
			 System.out.println(" " + unPaysCharge.getNomPays() ); 
		 }
	}
	
	public static void displayScenaristes(Set scenaristes){
		System.out.println("Scenaristes de film(" + scenaristes.size() + "):" ); 
		 
		 for ( Iterator iterScenariste = scenaristes.iterator(); iterScenariste.hasNext(); ) { 
			 Scenariste unPaysCharge = (Scenariste) iterScenariste.next(); 
			 System.out.println(" " + unPaysCharge.getNomScenariste() ); 
		 }
	}
	
	public static void displayPersonnages(Set personnages){
		System.out.println("Personnages de film(" + personnages.size() + "):" ); 
		 
		 for ( Iterator iterPersonnage = personnages.iterator(); iterPersonnage.hasNext(); ) { 
			 PersonnageFilm unPersonnageCharge = (PersonnageFilm) iterPersonnage.next(); 
			 System.out.println("pers: " + unPersonnageCharge.getNomPersonnage() + " acteur: " + unPersonnageCharge.getPersonneCinema().getNom()); 
		 }
	}
	
	public static void displayActeurs(Set personnages){
		System.out.println("Personnages de film(" + personnages.size() + "):" ); 
		
		
		 for ( Iterator iterPersonnage = personnages.iterator(); iterPersonnage.hasNext(); ) { 
			 
			 PersonnageFilm unPersonnageCharge = (PersonnageFilm) iterPersonnage.next();
			 
			 PersonneCinema acteur =  unPersonnageCharge.getPersonneCinema();
			 
			 System.out.println("pers: " + unPersonnageCharge.getNomPersonnage() + " acteur: " + acteur.getNom());
			 System.out.println("Lieu naissance: " + acteur.getLieuNaissance()); 
			 System.out.println("Date naissance: " + acteur.getDateNaissance().toString());
			 System.out.println("Biographie: " + acteur.getBiographie());
		 }
	}
}

