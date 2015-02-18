package locationfilms;
// default package
// Created by Sebastien Lago

/**
 * GenreFilm
 */
public class Scenariste implements java.io.Serializable {
	
	private String idScenaristeFilm;
	private String nomScenariste;
	private Film film;
	
	public Scenariste() { }

	public Scenariste(String idScenaristeFilm, String nomScenariste) {
		this.idScenaristeFilm = idScenaristeFilm;
		this.nomScenariste = nomScenariste;
	}
	
	public String getIdScenaristeFilm() {
		return this.idScenaristeFilm;
	}

	public void setIdScenaristeFilm(String idScenaristeFilm) {
		this.idScenaristeFilm = idScenaristeFilm;
	}
	
	public String getNomScenariste() {
		return this.nomScenariste;
	}

	public void setNomScenariste(String nomScenariste) {
		this.nomScenariste = nomScenariste;
	}
	
	public Film getFilm() {
		return this.film;
	}
	
	public void setFilm(Film film) {
		this.film = film;
	}
}
