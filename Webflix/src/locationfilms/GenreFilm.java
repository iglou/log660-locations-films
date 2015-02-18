package locationfilms;
// default package
// Created by Sebastien Lago

/**
 * GenreFilm
 */
public class GenreFilm implements java.io.Serializable {
	
	private String idGenreFilm;
	private String nomGenre;
	private Film film;
	
	public GenreFilm() { }

	public GenreFilm(String idGenreFilm, String nomGenre) {
		this.idGenreFilm = idGenreFilm;
		this.nomGenre = nomGenre;
	}
	
	public String getIdGenreFilm() {
		return this.idGenreFilm;
	}

	public void setIdGenreFilm(String idGenreFilm) {
		this.idGenreFilm = idGenreFilm;
	}
	
	public String getNomGenre() {
		return this.nomGenre;
	}

	public void setNomGenre(String nomGenre) {
		this.nomGenre = nomGenre;
	}
	
	public Film getFilm() {
		return this.film;
	}
	
	public void setFilm(Film film) {
		this.film = film;
	}
}
