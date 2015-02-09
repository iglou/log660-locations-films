import java.util.HashSet;
import java.util.Set;
import java.math.BigDecimal;

//default package
//Generated 3-Feb-2014 7:26:12 PM by Hibernate Tools 4.0.0

public class ExemplaireFilm implements java.io.Serializable {

	private int codeExemplaire;
	private Film film;

	public ExemplaireFilm() {
	}

	public ExemplaireFilm(int codeExemplaire) {
		this.codeExemplaire = codeExemplaire;
		this.film = film;
	}

	public int getcodeExemplaire() {
		return this.codeExemplaire;
	}

	public void setTypeForfait(int codeExemplaire) {
		this.codeExemplaire = codeExemplaire;
	}
	
	public Film getFilm() {
		return this.film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}
}
