package aplicatieEvenimente;

public class Muzica extends Servicii{
	private double PretOra;
	private int NumarOre;
	private String genMuzical;
	
	public Muzica(double PretOra, int NumarOre, String genMuzical, String Nume, Prioritate prioritate, String Contact){
		super(Nume, prioritate, Contact);
		this.PretOra=PretOra;
		this.NumarOre=NumarOre;
		calculPret();
		this.genMuzical=genMuzical;
	}
	
	public double getPretOra() {
		return PretOra;
	}
	
	public void setPretOra(double PretOra) {
		this.PretOra=PretOra;
	}
	
	public int getNumarOre() {
		return NumarOre;
	}
	
	public void setNumarOre(int NumarOre) {
		this.NumarOre=NumarOre;
	}
	
	public String getGenMuzical() {
		return genMuzical;
	}
	
	public void setGenMuzical(String genMuzical) {
		this.genMuzical=genMuzical;
	}
	
	public void calculPret() {
		
		double pretTotal = this.getNumarOre()*this.getPretOra();
		setPret(pretTotal);
		System.out.println("Pret actual: "+ pretTotal);
	}
	
	public void afisare() {
		super.afisare();
		System.out.println("Cu pretul/ora "+PretOra+" pentru un numar de "+NumarOre+" ore si genul muzical "+genMuzical);
	}

}