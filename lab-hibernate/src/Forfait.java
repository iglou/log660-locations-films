// default package
// Generated 3-Feb-2014 7:26:12 PM by Hibernate Tools 4.0.0

public class Forfait implements java.io.Serializable {

	private int forfaitId;
	private String typeForfait;
	private int coutForfait;
	private int locationMax;
	private int dureeMax;

	public Forfait() {
	}

	public Forfait(int forfaitId, String typeForfait, int coutForfait, int locationMax, int dureeMax) {
		this.forfaitId = forfaitId;
		this.typeForfait = typeForfait;
		this.coutForfait = coutForfait;
		this.locationMax = locationMax;
		this.dureeMax = dureeMax;
	}

	public int getForfaitId() {
		return this.forfaitId;
	}

	public String getTypeForfait() {
		return this.typeForfait;
	}

	public void setTypeForfait(String typeForfait) {
		this.typeForfait = typeForfait;
	}
	
	public int getcoutForfait() {
		return this.coutForfait;
	}
	
	public int getLocationMaxForfait() {
		return this.locationMax;
	}
	
	public int getDureeMaxForfait() {
		return this.dureeMax;
	}
}
