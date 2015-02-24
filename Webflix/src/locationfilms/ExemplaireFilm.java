package locationfilms;

/**
 * ExemplaireFilm
 */
public class ExemplaireFilm implements java.io.Serializable {

	private int codeExemplaire;
	private Film film;
	private LocationEnCours locationEnCours;
	private String disponible;

	public ExemplaireFilm() {
	}

	public ExemplaireFilm(int codeExemplaire, Film film) {
		this.codeExemplaire = codeExemplaire;
		this.film = film;
	}

	public int getCodeExemplaire() {
		return this.codeExemplaire;
	}

	public void setCodeExemplaire(int codeExemplaire) {
		this.codeExemplaire = codeExemplaire;
	}
	
	public Film getFilm() {
		return this.film;
	}
	
	public void setFilm(Film film) {
		this.film = film;
	}
	
	public LocationEnCours getLocationEnCours() {
		return this.locationEnCours;
	}
	
	public void setLocationEnCours(LocationEnCours locationEnCours) {
		this.locationEnCours = locationEnCours;
	}
	
	public String getDisponible() {
		return this.disponible;
	}
	
	public void setDisponible(String disponible) {
		this.disponible = disponible;
	}
}
