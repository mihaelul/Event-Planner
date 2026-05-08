package com.example.demo; 

import aplicatieEvenimente.Eveniment; 
import aplicatieEvenimente.Utilizator;
import aplicatieEvenimente.Prioritate;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.Vector;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class WebController {

    private Utilizator utilizatorCurent;

    public WebController() {
        utilizatorCurent = new Utilizator("Mihaela", "mihaela0611@gmail.com", "0731767542", "parolica06");
        
        Eveniment ev1 = new Eveniment("Nunta Ali & Baba", LocalDate.of(2026, 8, 20), 100000d, 200);
        
        ev1.addLocatie(200, "Cort", "Splaiul Independentei nr 290", "Cort Coretescu", Prioritate.Mare, 6000d, "contact@cort.ro");
        ev1.addMuzica(100d, 12, "Mix de toate", "DJ Nita", Prioritate.Mare, "djnita@global.com");
        ev1.addCatering(400d, "Standard", "Melek Pub", Prioritate.Medie, "melek@catering.com");
        ev1.addDecoratiuni("Florale", "Decor Premium", Prioritate.Mica, 3500d, "office@decor.ro");

        Eveniment ev2 = new Eveniment("Banchet", LocalDate.now(), 8000d, 150);
        
        ev2.addLocatie(150, "Sala evenimente", "Strada Florilor 10", "Restaurant Classic", Prioritate.Mare, 3000d, "contact@classic.ro");
        ev2.addMuzica(150d, 6, "Party", "Formatia Live", Prioritate.Medie, "formatia@live.com");

        utilizatorCurent.addEveniment(ev1);
        utilizatorCurent.addEveniment(ev2);
    }

    @GetMapping("/evenimente")
    public Vector<Eveniment> getEvenimente() {
        return utilizatorCurent.getEvenimente();
    }

    @PostMapping("/evenimente")
    public String adaugaEveniment(@RequestBody Eveniment evenimentNou) {
        utilizatorCurent.addEveniment(evenimentNou);
        return "Evenimentul a fost adăugat cu succes!";
    }

    @GetMapping("/evenimente/{numeCautat}")
    public Eveniment getEvenimentDupaNume(@PathVariable String numeCautat) {
        for (Eveniment ev : utilizatorCurent.getEvenimente()) {
            if (ev.getNume().equals(numeCautat)) {
                return ev;
            }
        }
        return null; 
    }

    @PostMapping("/evenimente/{numeEveniment}/servicii")
    public String adaugaServiciuLaEveniment(@PathVariable String numeEveniment, @RequestBody java.util.Map<String, Object> date) {
        Eveniment ev = null;
        for (Eveniment e : utilizatorCurent.getEvenimente()) {
            if (e.getNume().equalsIgnoreCase(numeEveniment)) {
                ev = e;
                break;
            }
        }

        if (ev == null) return "Eroare: Evenimentul nu a fost găsit!";

        String nume = (String) date.get("nume");
        String tip = (String) date.get("tip");
        String prioritateStr = (String) date.get("prioritate");
        Prioritate p = Prioritate.valueOf(prioritateStr); 
        String contact = (String) date.get("contact");

        switch (tip.toLowerCase()) {
            case "locatie":
                int cap = Integer.parseInt(date.get("capacitate").toString());
                double pretL = Double.parseDouble(date.get("pret").toString());
                ev.addLocatie(cap, (String) date.get("tipLocatie"), (String) date.get("adresa"), nume, p, pretL, contact);
                break;
            case "muzica":
                int ore = Integer.parseInt(date.get("numarOre").toString());
                double pretO = Double.parseDouble(date.get("pretOra").toString());
                ev.addMuzica(pretO, ore, (String) date.get("genMuzical"), nume, p, contact);
                break;
            case "catering":
                double pretB = Double.parseDouble(date.get("pretBucata").toString());
                ev.addCatering(pretB, (String) date.get("tipMeniu"), nume, p, contact);
                break;
            case "decoratiuni":
                double pretD = Double.parseDouble(date.get("pret").toString());
                ev.addDecoratiuni((String) date.get("tematica"), nume, p, pretD, contact);
                break;
        }

        return "Serviciul " + nume + " a fost adăugat!";
    }

    @DeleteMapping("/evenimente/{numeEveniment}/servicii/{numeServiciu}")
    public String stergeServiciu(@PathVariable String numeEveniment, @PathVariable String numeServiciu) {
        for (Eveniment ev : utilizatorCurent.getEvenimente()) {
            if (ev.getNume().equalsIgnoreCase(numeEveniment)) {
                boolean eliminat = ev.getServicii().removeIf(s -> s.getNume().equalsIgnoreCase(numeServiciu));
                
                if (eliminat) {
                    ev.calculPret();
                    return "Serviciul a fost șters cu succes!";
                }
            }
        }
        return "Eroare: Serviciul nu a putut fi găsit!";
    }
}