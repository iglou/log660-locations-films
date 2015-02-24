package locationfilms;
import java.util.Date;

// default package
// Generated 3-Feb-2014 7:26:12 PM by Hibernate Tools 4.0.0

public class Forfait implements java.io.Serializable {

	private String idForfait;
	private String typeForfait;
	private double cout;
	private int locationMax;
	private int dureeMax;
	
	public Forfait() {
	}
	
	public Forfait(String idForfait, String typeForfait, double cout, int locationMax, int dureeMax){
		this.idForfait = idForfait;
		this.typeForfait = typeForfait;
		this.cout = cout;
		this.locationMax = locationMax;
		this.dureeMax = dureeMax;
	}

	public String getIdForfait() {
		return this.idForfait;
	}
	
	public void setIdForfait(String idForfait){
		this.idForfait = idForfait;
	}
	
	public String getTypeForfait() {
		return this.typeForfait;
	}
	
	public void setTypeForfait(String typeForfait){
		this.typeForfait = typeForfait;
	}
	
	public double getCout() {
		return this.cout;
	}
	
	public void setCout(double cout){
		this.cout = cout;
	}
	
	public int getLocationMax() {
		return this.locationMax;
	}
	
	public void setLocationMax(int locationMax){
		this.locationMax = locationMax;
	}
	
	public int getDureeMax() {
		return this.dureeMax;
	}
	
	public void setDureeMax(int dureeMax){
		this.dureeMax = dureeMax;
	}

}
