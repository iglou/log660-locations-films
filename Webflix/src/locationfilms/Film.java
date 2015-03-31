package locationfilms;
// default package
// Created by Sebastien Lago

import java.util.HashSet;
import java.util.Set;

public class Film implements java.io.Serializable {

	private String idFilm;
	private String titre;
	private int annee;
	private String langue;
	private int duree;
	private String resume;
	private String poster;
	private Set genres = new HashSet(0);
	private Set pays = new HashSet(0);
	private Set scenaristes = new HashSet(0);
	private Set personnages = new HashSet(0);
	private Set exemplaires = new HashSet(0);
	private PersonneCinema realisateur;
	
	public Film() {
	}

	public Film(String idFilm, String titre) {
		this.idFilm = idFilm;
		this.titre = titre;
	}

	public Film(String idFilm, String titre, Set genres) {
		this.idFilm = idFilm;
		this.titre = titre;
		this.genres = genres;
	}
	
	public String getIdFilm() {
		return this.idFilm;
	}

	public void setIdFilm(String idFilm) {
		this.idFilm = idFilm;
	}

	public String getTitre() {
		return this.titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}
	
	public int getAnnee(){
		return this.annee;
	}
	
	public void setAnnee(int annee){
		this.annee = annee;
	}
	
	public String getLangue(){
		return this.langue;
	}
	
	public void setLangue(String langue){
		this.langue = langue;
	}
	
	public int getDuree(){
		return this.duree;
	}
	
	public void setDuree(int duree){
		this.duree = duree;
	}
	
	public String getResume(){
		return this.resume;
	}
	
	public void setResume(String resume){
		this.resume = resume;
	}
	
	public String getPoster(){
		return this.poster;
	}
	
	public void setPoster(String poster){
		this.poster = poster;
	}
	
	public Set getGenres() {
		return this.genres;
	}

	public void setGenres(Set genres) {
		this.genres = genres;
	}
	
	public Set getPays() {
		return this.pays;
	}

	public void setPays(Set pays) {
		this.pays = pays;
	}
	
	public Set getScenaristes() {
		return this.scenaristes;
	}

	public void setScenaristes(Set scenaristes) {
		this.scenaristes = scenaristes;
	}
	
	public Set getPersonnages() {
		return this.personnages;
	}

	public void setPersonnages(Set personnages) {
		this.personnages = personnages;
	}
	
	public Set getExemplaires() {
		return this.exemplaires;
	}

	public void setExemplaires(Set exemplaires) {
		this.exemplaires = exemplaires;
	}
	
	public PersonneCinema getRealisateur(){
		return this.realisateur;
	}
	
	public void setRealisateur(PersonneCinema realisateur){
		this.realisateur = realisateur;
	}

}
