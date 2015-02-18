package locationfilms;
// default package
// Created by Sebastien Lago

public class FilmPays implements java.io.Serializable {
	
	private String idFilmPays;
	private String nomPays;
	private Film film;
	
	public FilmPays() { }

	public FilmPays(String idFilmPays, String nomPays) {
		this.idFilmPays = idFilmPays;
		this.nomPays = nomPays;
	}
	
	public String getIdFilmPays() {
		return this.idFilmPays;
	}

	public void setIdFilmPays(String idFilmPays) {
		this.idFilmPays = idFilmPays;
	}
	
	public String getNomPays() {
		return this.nomPays;
	}

	public void setNomPays(String nomPays) {
		this.nomPays = nomPays;
	}
	
	public Film getFilm() {
		return this.film;
	}
	
	public void setFilm(Film film) {
		this.film = film;
	}
}
