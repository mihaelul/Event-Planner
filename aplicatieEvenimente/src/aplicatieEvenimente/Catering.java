package aplicatieEvenimente;

public class Catering extends Servicii{
	private double PretPersoana;
	private String tipMeniu;
	
	public Catering(double PretPersoana, String tipMeniu, String Nume, Prioritate prioritate, String Contact){
		super(Nume, prioritate, Contact);
		this.PretPersoana=PretPersoana;
		this.tipMeniu=tipMeniu;
	}
	
	public double getPretPersoana() {
		return PretPersoana;
	}
	
	public void setPretPersoana(double PretPersoana) {
		this.PretPersoana=PretPersoana;
	}
	
	public String getTipMeniu() {
		return tipMeniu;
	}
	
	public void setTipMeniu(String tipMeniu) {
		this.tipMeniu=tipMeniu;
	}
	
	public void calculPret(int nrInvitati) {
		double pretTotal = this.getPretPersoana()*nrInvitati;
		setPret(pretTotal);
		System.out.println("Pret actual: "+ pretTotal);
	}
	
	public void afisare() {
		super.afisare();
		System.out.println("Cu pretul de persoana "+PretPersoana+" si tip de meniu "+tipMeniu);
	}

}
