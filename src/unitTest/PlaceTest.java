package unitTest;

import org.junit.jupiter.api.*;

import org.pneditor.petrinet.models.causeretjasmin.*;

class PlaceTest {
	
	private Place p1;
	private Place p2;
	
	@BeforeEach
	void setUp() throws Exception {
		this.p1 = new Place();
		this.p2 = new Place(5);
	}

	@Test
	@Order(0)
	void testConstructorGetter() {
		Assertions.assertEquals(p1.getTokens(), 0);
		Assertions.assertEquals(p2.getTokens(), 5);
		try {
			Place p3 = new Place(-5);
			Assertions.fail("Une exception NegativeTokensException aurait du etre levee");
		} catch (NegativeTokensException nte) {}
	}
	
	@Test
	@Order(1)
	void testSetAddRemTokens() {
		p1.setTokens(3);
		Assertions.assertEquals(p1.getTokens(), 3);
		p1.addTokens(2);
		Assertions.assertEquals(p1.getTokens(), 5);
		p1.remTokens(3);
		Assertions.assertEquals(p1.getTokens(), 2);
		try {
			p1.setTokens(-3);
			Assertions.fail("Une exception NegativeTokensException aurait du etre levee");
		} catch (NegativeTokensException nte) {}
		try {
			p1.addTokens(-2);
			Assertions.fail("Une exception NegativeTokensException aurait du etre levee");
		} catch (NegativeTokensException nte) {}
		try {
			p1.remTokens(-3);
			Assertions.fail("Une exception NegativeTokensException aurait du etre levee");
		} catch (NegativeTokensException nte) {}
	}

}
