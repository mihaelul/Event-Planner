package testare;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import aplicatieEvenimente.Eveniment;
import aplicatieEvenimente.Prioritate;
import aplicatieEvenimente.Servicii;

import org.junit.jupiter.api.Test;

class TestEveniment {

	@Test
	void testAddLocatie() {
	    Eveniment e = new Eveniment("Nunta", LocalDate.now(), 10000.00, 100);

	    e.addLocatie(200, "restaurant", "adresa", "Loc1", Prioritate.Medie, 3000, "contact");

	    assertEquals(1, e.getServicii().size());
	    assertEquals(3000, e.getPretTotal());
	}

	@Test
	void testAddMuzica() {
	    Eveniment e = new Eveniment("Nunta", LocalDate.now(), 10000.0, 100);

	    e.addMuzica(100, 5, "pop", "DJ", Prioritate.Mare, "contact");

	    assertEquals(1, e.getServicii().size());
	    assertEquals(500, e.getPretTotal()); // 100 * 5
	}

	@Test
	void testAddDecoratiuni() {
	    Eveniment e = new Eveniment("Nunta", LocalDate.now(), 10000.0, 100);

	    e.addDecoratiuni("rustic", "Decor", Prioritate.Mica, 1500, "contact");

	    assertEquals(1, e.getServicii().size());
	    assertEquals(1500, e.getPretTotal());
	}

	@Test
	void testAddCatering() {
	    Eveniment e = new Eveniment("Nunta", LocalDate.now(), 10000.0, 50);

	    e.addCatering(100, "meniu", "Catering1", Prioritate.Mare, "contact");

	    assertEquals(1, e.getServicii().size());
	    assertEquals(5000, e.getPretTotal()); // 100 * 50
	}
	@Test
	void testRemoveServiciu() {
	    Eveniment e = new Eveniment("Nunta", LocalDate.now(), 10000.0, 100);

	    e.addDecoratiuni("rustic", "Decor", Prioritate.Mica, 1000, "contact");

	    Servicii s = e.getServicii().get(0);

	    e.removeServiciu(s);

	    assertEquals(0, e.getServicii().size());
	    assertEquals(0, e.getPretTotal());
	}

	@Test
	void testCalculPret() {
	    Eveniment e = new Eveniment("Nunta", LocalDate.now(), 10000.0, 100);

	    e.addDecoratiuni("rustic", "Decor", Prioritate.Mica, 1000, "contact");
	    e.addMuzica(100, 5, "pop", "DJ", Prioritate.Mare, "contact");

	    e.calculPret();

	    assertEquals(1500, e.getPretTotal());
	}
}
