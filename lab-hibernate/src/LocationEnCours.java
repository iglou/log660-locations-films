import java.util.HashSet;
import java.util.Set;
import java.util.Date;
import java.util.Date;


//default package
//Generated 3-Feb-2014 7:26:12 PM by Hibernate Tools 4.0.0

public class LocationEnCours implements java.io.Serializable {

	private int idLocationEnCours;
	private Date dateLocation;
	private Client client;
	private ExemplaireFilm exemplaireFilm;

	public LocationEnCours() {
	}

	public LocationEnCours(int idLocationEnCours, Date dateLocation, Client client, ExemplaireFilm exemplaireFilm) {
		this.idLocationEnCours = idLocationEnCours;
		this.dateLocation = dateLocation;
		this.client = client;
		this.exemplaireFilm = exemplaireFilm;
	}

	public int getIdLocationEnCours() {
		return this.idLocationEnCours;
	}

	public Date getdateLocation() {
		return this.dateLocation;
	}
	
	public void setdateLocation(Date dateLocation) {
		this.dateLocation = dateLocation;
	}
	
	public Client getClientLocataire() {
		return this.client;
	}
	
	public void setClientLocataire(Client client) {
		this.client = client;
	}
	
	public ExemplaireFilm getExemplaireFilm() {
		return this.exemplaireFilm;
	}
	
	public void setExemplaireFilm(ExemplaireFilm exemplaireFilm) {
		this.exemplaireFilm = exemplaireFilm;
	}

}
