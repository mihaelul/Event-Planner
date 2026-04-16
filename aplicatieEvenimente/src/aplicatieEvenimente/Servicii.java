package aplicatieEvenimente;

public class Servicii {
	private String Nume;
	private Prioritate prioritate;
	private double Pret;
	private String Contact; //numar telefon
	
	Servicii(String Nume, Prioritate prioritate, double Pret, String Contact){
		this.Nume=Nume;
		this.Pret=Pret;
		this.Contact=Contact;
		this.prioritate=prioritate;
	}
	
	Servicii(String Nume, Prioritate prioritate, String Contact){
		this.Nume=Nume;
		this.Contact=Contact;
		this.prioritate=prioritate;
	}
	
	public String getNume() {
		return Nume;
	}
	
	public void setNume(String Nume) {
		this.Nume=Nume;
	}
	
	public Prioritate getPrioritate() {
		return prioritate;
	}
	
	public void setPrioritate(Prioritate prioritate) {
		this.prioritate=prioritate;
	}
	
	public double getPret() {
		return Pret;
	}
	
	public void setPret(double Pret) {
		this.Pret=Pret;
	}
	
	public String getContact() {
		return Contact;
	}
	
	
	public void setContact(String Contact) {
		this.Contact=Contact;
	}
	
	public void afisare() {
		System.out.println("Serviciul "+Nume+" are pretul "+Pret+", persoana de contact este "+Contact+ " si are prioritate " +prioritate);
	}
	
	public void calculPret(){
	}
	
}