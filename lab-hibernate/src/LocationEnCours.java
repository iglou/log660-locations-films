import java.util.HashSet;
import java.util.Set;
import java.util.Date;
import java.util.Date;


//default package
//Generated 3-Feb-2014 7:26:12 PM by Hibernate Tools 4.0.0

public class LocationEnCours implements java.io.Serializable {

	private int idLocationEnCours;
	private Date dateLocation;
	private Set client = new HashSet(0);
	private Set exemplaireFilm = new HashSet(0);

	public LocationEnCours() {
	}

	public LocationEnCours(int idLocationEnCours, Date dateLocation, Set client, Set exemplaireFilm) {
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
	
	public Set getClientLocataire() {
		return this.client;
	}
	
	public void setClientLocataire(Set client) {
		this.client = client;
	}
	
	public Set getExemplaireFilm() {
		return this.exemplaireFilm;
	}
	
	public void setExemplaireFilm(Set exemplaireFilm) {
		this.exemplaireFilm = exemplaireFilm;
	}

}
