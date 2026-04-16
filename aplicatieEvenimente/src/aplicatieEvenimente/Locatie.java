package aplicatieEvenimente;

public class Locatie extends Servicii{
	private int Capacitate;
	private String tipLocatie;
	private String adresa;
	
	public Locatie(int Capacitate, String tipLocatie, String adresa, String Nume, Prioritate prioritate, double Pret, String Contact){
		super(Nume, prioritate, Pret, Contact);
		this.Capacitate=Capacitate;
		this.tipLocatie=tipLocatie;
		this.adresa=adresa;
	}
	
	public int getCapacitate() {
		return Capacitate;
	}
	
	public void setCapacitate(int Capacitate) {
		this.Capacitate=Capacitate;
	}
	
	public String getTipLocatie() {
		return tipLocatie;
	}
	
	public void setTipLocatie(String tipLocatie) {
		this.tipLocatie=tipLocatie;
	}
	
	public String getAdresa() {
		return adresa;
	}
	
	public void setAdresa(String adresa) {
		this.adresa=adresa;
	}
	
	public boolean verificaCapacitete(int nrInvitati) {
		return true;
	}
	
	
	public void afisare() {
		super.afisare();
		System.out.println("Cu capacitate "+Capacitate+" cu tipul de locatie "+tipLocatie+" si adresa "+adresa);
	}

}
