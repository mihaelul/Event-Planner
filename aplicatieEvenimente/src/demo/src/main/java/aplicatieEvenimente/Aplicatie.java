package aplicatieEvenimente;

import java.time.LocalDate;

public class Aplicatie {


	public static void main(String[] args) {
		
		Utilizator u1 = new Utilizator("Mihaela", "mihaela0611@gmail.com", "0731767542", "parolica06");
		Eveniment ev = new Eveniment("Banchet", LocalDate.now(), 8000d, 150);

        System.out.println("=== TEST INITIAL ===");
        ev.afisareEveniment();
        
        u1.addEveniment(ev);
 
        /*
        Muzica m1 = new Muzica(100d, 12, "Manele", "DJ", Prioritate.Mare, "DJNita@Global.com");
        Catering c1 = new Catering(400d, "Vegan","Catering Basic", Prioritate.Mica, "MelekPub@gmail.com");
        Servicii s2 = new Servicii("Fotograf", Prioritate.Medie, 2200d, "SellyMedia@gmail.com");
        Decoratiuni d1 = new Decoratiuni("Craciun" , "Decor Premium", Prioritate.Mica, 3500d, "Jumbo@gmail.com");
        Locatie l1 = new Locatie(120, "Cort", "Splaiul Independentei nr 290" , "Camin P5", Prioritate.Mica, 3500d, "Jumbo@gmail.com");
        Servicii s3 = new Servicii("Candy Bar", Prioritate.Medie, 1200d, "Elian@fructe.com");
        */


        System.out.println("\n=== TEST ADAUGARE SERVICII ===");

        ev.addMuzica(100d, 12, "Manele", "DJ", Prioritate.Mare, "DJNita@Global.com");
        //c1.calculPret(ev.getInvitati());
        ev.addCatering(400d, "Vegan","Catering Basic", Prioritate.Mica, "MelekPub@gmail.com");
        //ev.addServiciu(s2);
        ev.addDecoratiuni("Craciun" , "Decor Premium", Prioritate.Mica, 3500d, "Jumbo@gmail.com");
        ev.addLocatie(120, "Cort", "Splaiul Independentei nr 290" , "Camin P5", Prioritate.Mica, 3500d, "Jumbo@gmail.com");
        //ev.addServiciu(s3);
        
        System.out.println("\n=== STARE DUPA ADAUGARE ===");
        u1.afisare();

       /* System.out.println("\n=== STARE DUPA ADAUGARE ===");
        ev.afisareEveniment();
        */

        System.out.println("\n=== TEST ELIMINARE SERVICIU ===");
        Muzica m = new Muzica(100d, 12, "Manele", "DJ", Prioritate.Mare, "DJNita@Global.com");
        ev.removeServiciu(m);

        System.out.println("\n=== STARE DUPA ELIMINARE ===");
        //ev.afisareEveniment();
        u1.afisare();

        System.out.println("\n=== TEST MODIFICARE BUGET MIC ===");
        ev.modificareBuget(4000d);

        System.out.println("\n=== TEST MODIFICARE BUGET MARE ===");
        ev.modificareBuget(10000000d);

        System.out.println("\n=== TEST RECALCUL MANUAL ===");
        ev.calculPret();
        u1.afisare();
        //ev.afisareEveniment();

        System.out.println("\n=== TEST ELIMINARE SERVICIU INEXISTENT ===");
        
        Servicii temp = new Servicii("Serviciu Fals", Prioritate.Mare, 999d, "Elian@fructe.com");
        ev.removeServiciu(temp);
        
	}


}
