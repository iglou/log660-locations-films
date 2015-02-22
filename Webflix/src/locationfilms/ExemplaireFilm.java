package locationfilms;

import java.util.HashSet;
import java.util.Set;
import java.math.BigDecimal;

//default package
//Generated 3-Feb-2014 7:26:12 PM by Hibernate Tools 4.0.0

public class ExemplaireFilm implements java.io.Serializable {

	private int codeExemplaire;
	private Set film = new HashSet(0);

	public ExemplaireFilm() {
	}

	public ExemplaireFilm(int codeExemplaire, Set film) {
		this.codeExemplaire = codeExemplaire;
		this.film = film;
	}

	public int getcodeExemplaire() {
		return this.codeExemplaire;
	}

	public void setTypeForfait(int codeExemplaire) {
		this.codeExemplaire = codeExemplaire;
	}
	
	public Set getFilm() {
		return this.film;
	}

	public void setFilm(Set film) {
		this.film = film;
	}
}
