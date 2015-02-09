import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.Date;

// default package
// Generated 3-Feb-2014 7:26:12 PM by Hibernate Tools 4.0.0

public class Client implements java.io.Serializable {

	private int clientId;
	private String nomClient;
	private String prenomClient;
	private String courrielClient;
	private String telClient;
	private Date dateNaissance;
	private String MotDePasse;
	private String TypeCarteCredit;
	private String NumeroCarteCredit;
	private Date dateExpiration;
	private int cvv;
	private Forfait forfaitChoisi;

	public Client() {
	}

	public Client(int clientId, String nomClient, String prenomClient, String courrielClient, String telClient, Date dateNaissance, String MotDePasse, String TypeCarteCredit, String NumeroCarteCredit, Date dateExpiration, int cvv, Forfait forfaitChoisi) {
		this.clientId = clientId;
		this.nomClient = nomClient;
		this.prenomClient = prenomClient;
		this.courrielClient = courrielClient;
		this.telClient = telClient;
		this.dateNaissance = dateNaissance;
		this.MotDePasse = MotDePasse;
		this.TypeCarteCredit = TypeCarteCredit;
		this.NumeroCarteCredit = NumeroCarteCredit;
		this.dateExpiration = dateExpiration;
		this.cvv = cvv;
		this.forfaitChoisi = forfaitChoisi;
	}

	public int getClientId() {
		return this.clientId;
	}

	public String getPrenomClient() {
		return this.prenomClient;
	}

	public void setprenomClient(String prenomClient) {
		this.prenomClient = prenomClient;
	}
	
	public String getNomClient() {
		return this.nomClient;
	}
	
	public void setNomClient(String nomClient) {
		this.nomClient = nomClient;
	}
	
	public String getCourrielClient() {
		return this.courrielClient;
	}
	
	public void setCourrielClient(String courrielClient) {
		this.courrielClient = courrielClient;
	}
	
	public String getTelephoneClient() {
		return this.telClient;
	}
	
	public void setTelephoneClient(String telClient) {
		this.telClient = telClient;
	}
	
	public String getMotDePasseClient() {
		return this.MotDePasse;
	}
	
	public void setMotDePasseClient(String MotDePasse) {
		this.MotDePasse = MotDePasse;
	}
	
	public Date getDobClient() {
		return this.dateNaissance;
	}
	
	public void setDobClient(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}
	
	public String getTypeCarteCredit() {
		return this.TypeCarteCredit;
	}
	
	public void setTypeCarteCredit(String TypeCarteCredit) {
		this.TypeCarteCredit = TypeCarteCredit;
	}
	
	public String getNumeroCarteCredit() {
		return this.NumeroCarteCredit;
	}
	
	public void setNumeroCarteCredit(String NumeroCarteCredit) {
		this.NumeroCarteCredit = NumeroCarteCredit;
	}
	
	public Date getDateExpirationCarteCredit() {
		return this.dateExpiration;
	}
	
	public void setDateExpirationCarteCredit(Date dateExpiration) {
		this.dateExpiration = dateExpiration;
	}
	
	public int getCvv() {
		return this.cvv;
	}
	
	public void setCvv(int cvv) {
		this.cvv = cvv;
	}
	
	public Forfait getForfaitClient() {
		return this.forfaitChoisi;
	}
	
	public void setForfaitClient(Forfait forfaitChoisi) {
		this.forfaitChoisi = forfaitChoisi;
	}
}
