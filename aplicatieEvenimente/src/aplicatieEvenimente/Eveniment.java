package aplicatieEvenimente;
import java.util.ArrayList;
import java.util.List;

import java.time.LocalDate;

import java.util.Comparator;

import java.util.Scanner;

public class Eveniment {
	private String Nume;
	private LocalDate Data;
	private List<Servicii> servicii;
	private Double Buget;
	private Double PretTotal;
	private int numarPersoane;
	
	public Eveniment(String Nume, LocalDate Data,Double Buget, int numarPersoane) {
		this.Nume = Nume;
		this.Data = Data;
		this.servicii = new ArrayList<>();
		this.Buget = Buget;
		this.PretTotal=0d;
		this.numarPersoane = numarPersoane;
	}
	
	public String getNume() {
		return this.Nume;
	}
	
	/*public void addServiciu(Servicii serviciuNou) {
		if (serviciuNou == null) {
	        System.out.println("Serviciu invalid!");
	        return;
	    }

	    servicii.add(serviciuNou);	
	    Double total = this.PretTotal+serviciuNou.getPret();
	    System.out.println("Serviciu adaugat! Total curent: " + total);
		calculPret();
		

	}*/
	public void addLocatie(int Capacitate, String tipLocatie, String adresa, String Nume, Prioritate prioritate, double Pret, String Contact)
	{
		Locatie l = new Locatie(Capacitate, tipLocatie, adresa, Nume, prioritate, Pret, Contact);
		servicii.add(l);
		
		Double total = this.PretTotal+l.getPret();
	    System.out.println("Serviciu adaugat! Total curent: " + total);
	    calculPret();
		
	}
	
	public void addMuzica(double PretOra, int NumarOre, String genMuzical, String Nume, Prioritate prioritate, String Contact)
	{
		Muzica m = new Muzica(PretOra, NumarOre, genMuzical, Nume, prioritate, Contact);
		servicii.add(m);
		
		Double total = this.PretTotal+m.getPret();
	    System.out.println("Serviciu adaugat! Total curent: " + total);
	    calculPret();
	}
	
	public void addDecoratiuni(String Tematica, String Nume, Prioritate prioritate, double Pret, String Contact)
	{
		Decoratiuni d = new Decoratiuni(Tematica, Nume, prioritate, Pret, Contact);
		servicii.add(d);
		
		Double total = this.PretTotal+d.getPret();
	    System.out.println("Serviciu adaugat! Total curent: " + total);
	    calculPret();
	}
	
	public void addCatering(double PretPersoana, String tipMeniu, String Nume, Prioritate prioritate, String Contact)
	{
		Catering c = new Catering(PretPersoana, tipMeniu, Nume, prioritate, Contact);
		c.setPret(PretPersoana*numarPersoane);
		servicii.add(c);
		
		Double total = this.PretTotal+c.getPret();
	    System.out.println("Serviciu adaugat! Total curent: " + total);
	    calculPret();
	}
	
	public void removeServiciu(Servicii serviciu) {
		if (servicii.remove(serviciu)) {
	        System.out.println("Serviciul a fost eliminat cu succes!");
	    } else {
	        System.out.println("Serviciul nu a fost gasit!");
	        return;
	    }
		
		Double total = this.PretTotal-serviciu.getPret();
	    System.out.println("Total curent dupa eliminare: " + total);
		
	    
		calculPret();
		
	}
	
	public void modificareBuget(double bugetNou) {
		this.Buget = bugetNou;
		
		if(this.PretTotal>(Buget+(0.2)*Buget))
		{
			recomandare();
		}
	}
	
	public void calculPret() {
		this.PretTotal = 0d;
		
		for(Servicii s : servicii) {
			this.PretTotal+=s.getPret();
		}
		 
		if(this.PretTotal>(Buget+(0.2)*Buget))
		{
			System.out.print("Buget actual:" + this.Buget);
			recomandare();
		}
	}
	
	public void recomandare() {
		System.out.println("Ati depasit bugetul!");
		
		servicii.sort(
			    Comparator
			        .comparing(Servicii::getPrioritate)
			        .thenComparing(Comparator.comparingDouble(Servicii::getPret).reversed())
		);
		
		 System.out.println("\n=== RECOMANDARE - BUGET ATINS! ===");
		
		System.out.println("Avand in vedere prioritatea setata de tine...");
		System.out.println("Ar trebui sa cauti un pachet diferit pentru a te incadra in buget.");
		System.out.println("Noi iti recomandam sa schimbi furnizorul pentru unul din urmatoarele servicii pentru a te incadra in buget:");

		for(int i=0; i<Math.min(3, servicii.size()); i++) {
			servicii.get(i).afisare();
		}
		
		/*
		System.out.println("Alege urmatorul pas: ");
		System.out.println("1-elimina un serviciu");
		System.out.println("2-creste bugetul");
		System.out.println("Orice alta tasta pentru a reveni la meniu");
		
		Scanner scanner = new Scanner(System.in);
		
		if (!scanner.hasNextInt()) {
	        System.out.println("Optiune invalida!");
	        return;
	    }
		
		int decizie = scanner.nextInt();
		switch(decizie) {
		case 1:
			
			System.out.println("Alege nr-ul evenimentului e care vrei sa il elimini");
			
			int i=1;
			for(Servicii s : servicii) {
				System.out.print(i+".");
				s.afisare();
				i++;
			}
			
			if (!scanner.hasNextInt()) {
                System.out.println("Optiune invalida!");
                return;
            }
			
			decizie = scanner.nextInt();
			
			if(decizie >= 1 && decizie<=servicii.size()) {
				
				servicii.remove(servicii.get(decizie-1));
				
				System.out.println("Serviciile ramase dupa eliminare:" );
				for(Servicii s : servicii) {
					s.afisare();
				}
			}else {
			    System.out.println("Optiune invalida!");
			}
			
			
			return;
		case 2:
			System.out.println("Introdu noul buget: ");
			
			if (!scanner.hasNextDouble()) {
                System.out.println("Buget invalid!");
                return;
            }
			Double bugetNou=scanner.nextDouble();
			this.modificareBuget(bugetNou);
			System.out.println("Bugetul a fost actualizat la: " + this.Buget);
			return;			
			
		default:
			return;
		}
		*/
	}
	
	public void afisareEveniment() {
		System.out.println("Numele evenimentului: "+ this.Nume);
		System.out.println("Data de sustinere a evenimentului: "+ this.Data);
		for(Servicii s : servicii) {
			s.afisare();
		}
		System.out.println("Bugetul setat: "+ this.Buget);
		System.out.println("Costul curent al evenimentului :"+ this.PretTotal);
		System.out.println("Numarul de invitati doriti: "+ this.numarPersoane);
		
	}
	
	public int getInvitati()
	{
		return numarPersoane;
	}
	
}
