package locationfilms;
// default package
// Created by Sebastien Lago

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class PersonnageFilm implements java.io.Serializable {

	private Film film;
	private PersonneCinema personneCinema;
	private String nomPersonnage;
	private String idPersonnageFilm;
	
	public PersonnageFilm() {
	}

	public PersonnageFilm(String idPersonnageFilm, Film film, PersonneCinema personneCinema, String nomPersonnage) {
		this.idPersonnageFilm = idPersonnageFilm;
		this.film = film;
		this.personneCinema = personneCinema;
		this.nomPersonnage = nomPersonnage;
	}
	
	public String getIdPersonnageFilm() {
		return this.idPersonnageFilm;
	}

	public void setIdPersonnageFilm(String idPersonnageFilm) {
		this.idPersonnageFilm = idPersonnageFilm;
	}
	
	public Film getFilm(){
		return this.film;
	}
	
	public void setFilm(Film film) {
		this.film = film;
	}
	
	public PersonneCinema getPersonneCinema() {
		return this.personneCinema;
	}

	public void setPersonneCinema(PersonneCinema personneCinema) {
		this.personneCinema = personneCinema;
	}

	public String getNomPersonnage() {
		return this.nomPersonnage;
	}

	public void setNomPersonnage(String nomPersonnage) {
		this.nomPersonnage = nomPersonnage;
	}

}

