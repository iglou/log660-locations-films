package locationfilms;
import java.util.Date;

// default package
// Generated 3-Feb-2014 7:26:12 PM by Hibernate Tools 4.0.0

public class Personne implements java.io.Serializable {

	private String idPersonne;
	private String nom;
	private String prenom;
	private String courriel;
	private String telephone;
	private Date dateNaissance;
	private String motDePasse;
	private Client client;
	
	public Personne() {
	}

	public Personne(String idPersonne, String nom, String prenom, String courriel, String telephone, Date dateNaissance, String motDePasse){
		this.idPersonne = idPersonne;
		this.nom = nom;
		this.prenom = prenom;
		this.courriel = courriel;
		this.telephone = telephone;
		this.dateNaissance = dateNaissance;
		this.motDePasse = motDePasse;
	}

	public String getIdPersonne() {
		return this.idPersonne;
	}
	
	public void setIdPersonne(String idPersonne){
		this.idPersonne = idPersonne;
	}
	
	public String getNom() {
		return this.nom;
	}
	
	public void setNom(String nom){
		this.nom = nom;
	}
	
	public String getPrenom() {
		return this.prenom;
	}
	
	public void setPrenom(String prenom){
		this.prenom = prenom;
	}
	
	public String getCourriel() {
		return this.courriel;
	}
	
	public void setCourriel(String courriel){
		this.courriel = courriel;
	}
	
	public String getTelephone() {
		return this.telephone;
	}
	
	public void setTelephone(String telephone){
		this.telephone = telephone;
	}
	
	public Date getDateNaissance() {
		return this.dateNaissance;
	}
	
	public void setDateNaissance(Date dateNaissance){
		this.dateNaissance = dateNaissance;
	}

	public String getMotDePasse() {
		return this.motDePasse;
	}
	
	public void setMotDePasse(String motDePasse){
		this.motDePasse = motDePasse;
	}

}
