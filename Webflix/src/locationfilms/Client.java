package locationfilms;

// default package
// Generated 3-Feb-2014 7:26:12 PM by Hibernate Tools 4.0.0

public class Client implements java.io.Serializable {

	private Personne personne;
	private String idClient;
	private String typeCarteCredit;
	private String numeroCarteCredit;
	private int moisExpiration;
	private int anneeExpiration;
	private int cvv;
	private Forfait forfait;
	
	public Client() {
	}

	public Client(Personne personne, String idClient, String typeCarteCredit, String numeroCarteCredit, int moisExpiration, int anneeExpiration, int cvv, Forfait forfait){
		this.personne = personne;
		this.idClient = idClient;
		this.typeCarteCredit = typeCarteCredit;
		this.numeroCarteCredit = numeroCarteCredit;
		this.moisExpiration = moisExpiration;
		this.anneeExpiration = anneeExpiration;
		this.cvv = cvv;
		this.forfait = forfait;
	}

	public Personne getPersonne() {
		return this.personne;
	}
	
	public void setPersonne(Personne personne){
		this.personne = personne;
	}

	public String getIdClient() {
		return this.idClient;
	}
	
	public void setIdClient(String idClient){
		this.idClient = idClient;
	}
	
	public String getTypeCarteCredit() {
		return this.typeCarteCredit;
	}
	
	public void setTypeCarteCredit(String typeCarteCredit){
		this.typeCarteCredit = typeCarteCredit;
	}
	
	public String getNumeroCarteCredit() {
		return this.numeroCarteCredit;
	}
	
	public void setNumeroCarteCredit(String numeroCarteCredit){
		this.numeroCarteCredit = numeroCarteCredit;
	}
	
	public int getMoisExpiration() {
		return this.moisExpiration;
	}
	
	public void setMoisExpiration(int moisExpiration){
		this.moisExpiration = moisExpiration;
	}
	
	public int getAnneeExpiration() {
		return this.anneeExpiration;
	}
	
	public void setAnneeExpiration(int anneeExpiration){
		this.anneeExpiration = anneeExpiration;
	}
	
	public int getCvv() {
		return this.cvv;
	}
	
	public void setCvv(int cvv){
		this.cvv = cvv;
	}
	
	public Forfait getForfait() {
		return this.forfait;
	}
	
	public void setForfait(Forfait forfait){
		this.forfait = forfait;
	}

}
