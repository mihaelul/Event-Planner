package aplicatieEvenimente;

import java.util.Vector;

public class Utilizator {

	private String nume;
	private Vector<Eveniment> evenimente;
	private String telefon;
	private String email;
	private String parola;
	
	
	public Utilizator(String nume, String email, String telefon, String parola) {
        this.nume = nume;
        this.email = email;
        this.telefon = telefon;
        this.parola = parola;
        this.evenimente = new Vector<>(); 
    }
	
	
	public Vector<Eveniment> getEvenimente()
	{
		return evenimente;
	}
	
	public void removeEveniment(Eveniment e)
	{
		for(int i=0;i<evenimente.size(); i++)
		{
			if (evenimente.remove(e)) {
	            System.out.println("S-a eliminat evenimentul: " + e.getNume());
	        } else {
	            System.out.println("Evenimentul " + e.getNume() + " nu a fost găsit.");
	        }
		}
	}
	
	public void addEveniment(Eveniment e)
	{
		if (e != null) {
            evenimente.add(e);
            System.out.println("S-a adaugat evenimentul: " + e.getNume());
        }
	}
	
	public void afisare()
	{
		for(int i=0;i<evenimente.size(); i++)
		{
			evenimente.get(i).afisareEveniment();
		}
	}
	
}
