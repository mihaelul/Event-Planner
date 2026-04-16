package aplicatieEvenimente;

public class Decoratiuni extends Servicii{
	private double PretPachet;
	private String Tematica;
	
	public Decoratiuni( String Tematica, String Nume, Prioritate prioritate, double Pret, String Contact){
		super(Nume, prioritate, Pret, Contact);
		this.Tematica=Tematica;
	}
	
	public double getPretPachet() {
		return PretPachet;
	}
	
	public void setPretPachet(double PretPachet) {
		this.PretPachet=PretPachet;
	}
	
	public String getTematica() {
		return Tematica;
	}
	
	public void setTematica(String Tematica) {
		this.Tematica=Tematica;
	}
	
	
	public void afisare() {
		super.afisare();
		System.out.println("Cu pretul de persoana "+PretPachet+" si tip de meniu "+Tematica);
	}
}
