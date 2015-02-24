package locationfilms;
import java.util.HashSet;
import java.util.Set;
import java.util.Date;

//default package
//Generated 3-Feb-2014 7:26:12 PM by Hibernate Tools 4.0.0

public class LocationEnCours implements java.io.Serializable {

	private int idLocation;
	private Date dateLocation;
	private Client client;
	private ExemplaireFilm exemplaireFilm;

	public LocationEnCours() {
	}

	public LocationEnCours(int idLocation, Date dateLocation, Client client, ExemplaireFilm exemplaireFilm) {
		this.idLocation = idLocation;
		this.dateLocation = dateLocation;
		this.client = client;
		this.exemplaireFilm = exemplaireFilm;
	}

	public int getIdLocation() {
		return this.idLocation;
	}
	
	public void setIdLocation(int idLocation) {
		this.idLocation = idLocation;
	}

	public Date getDateLocation() {
		return this.dateLocation;
	}
	
	public void setDateLocation(Date dateLocation) {
		this.dateLocation = dateLocation;
	}
	
	public Client getClient() {
		return this.client;
	}
	
	public void setClient(Client client) {
		this.client = client;
	}
	
	public ExemplaireFilm getExemplaireFilm() {
		return this.exemplaireFilm;
	}
	
	public void setExemplaireFilm(ExemplaireFilm exemplaireFilm) {
		this.exemplaireFilm = exemplaireFilm;
	}

}
