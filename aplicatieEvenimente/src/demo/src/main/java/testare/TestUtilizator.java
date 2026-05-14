package testare;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import aplicatieEvenimente.Utilizator;
import aplicatieEvenimente.Eveniment;

class TestUtilizator {

	@Test
	void testRemoveEveniment() {
		Utilizator u = new Utilizator("Ana", "ana1@gmail.com", "0756313214", "1234");
	    Eveniment e = new Eveniment("Nunta Anei si lui Ion", LocalDate.now(), 50000.00, 125);

	    u.addEveniment(e);
	    u.removeEveniment(e);

	    assertEquals(0, u.getEvenimente().size());
	    assertFalse(u.getEvenimente().contains(e));
	}

	@Test
	void testAddEveniment() {
		Utilizator u = new Utilizator("Ana", "ana1@gmail.com", "0756313214", "1234");
	    Eveniment e = new Eveniment("Nunta Anei si lui Ion", LocalDate.now(), 50000.00, 125);
		u.addEveniment(e);
	    u.removeEveniment(e);

	    assertEquals(0, u.getEvenimente().size());
	    assertFalse(u.getEvenimente().contains(e));
	}

}
