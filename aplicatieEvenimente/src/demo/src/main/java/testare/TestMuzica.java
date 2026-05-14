package testare;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import aplicatieEvenimente.Muzica;
import aplicatieEvenimente.Prioritate;

class TestMuzica {

	@Test
	void testCalculPret() {
	    Muzica m = new Muzica(120.00, 2, "Manele", "Fuego", Prioritate.Mare, "FuegoBoss@yahoo.com");
	    
	    m.calculPret();
	    
	    assertEquals(240, m.getPret());
	}

}
