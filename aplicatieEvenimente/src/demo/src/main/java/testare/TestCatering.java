package testare;

import static org.junit.jupiter.api.Assertions.*;

import aplicatieEvenimente.Catering;
import Clase.Prioritate;

import org.junit.jupiter.api.Test;

class TestCatering {

	@Test
	void testCalculPret() {
	    Catering c = new Catering(100, "nunta", "Catering1", Prioritate.Mare, "contact");

	    c.calculPret(50);

	    assertEquals(5000, c.getPret());
	}

}
