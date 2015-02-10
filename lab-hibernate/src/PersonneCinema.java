// default package
// Created by Sebastien Lago

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class PersonneCinema implements java.io.Serializable {

	private String idPersonneCinema;
	private String nom;
	private Date dateNaissance;
	private String lieuNaissance;
	private String biographie;
	private Set personnages = new HashSet(0);
	private Set filmRealises = new HashSet(0);
	
	public PersonneCinema() {
	}

	public PersonneCinema(String idPersonneCinema, String nom) {
		this.idPersonneCinema = idPersonneCinema;
		this.nom = nom;
	}

	public PersonneCinema(String idPersonneCinema, String nom, Date dateNaissance, String lieuNaissance) {
		this.idPersonneCinema = idPersonneCinema;
		this.nom = nom;
		this.dateNaissance = dateNaissance;
		this.lieuNaissance = lieuNaissance;
	}
	
	public String getIdPersonneCinema() {
		return this.idPersonneCinema;
	}

	public void setIdPersonneCinema(String idPersonneCinema) {
		this.idPersonneCinema = idPersonneCinema;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public Date getDateNaissance() {
		return this.dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}
	
	public String getLieuNaissance() {
		return this.lieuNaissance;
	}

	public void setLieuNaissance(String lieuNaissance) {
		this.lieuNaissance = lieuNaissance;
	}
	
	public String getBiographie() {
		return this.biographie;
	}

	public void setBiographie(String biographie) {
		this.biographie = biographie;
	}
	
	public Set getPersonnages() {
		return this.personnages;
	}

	public void setPersonnages(Set personnages) {
		this.personnages = personnages;
	}
	
	public Set getFilmRealises(){
		return filmRealises;
	}
	
	public void setFilmRealises(Set filmRealises){
		this.filmRealises = filmRealises;
	}
}

