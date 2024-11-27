package unitTest;

import org.junit.jupiter.api.*;

import org.pneditor.petrinet.models.causeretjasmin.*;

class ArcTest {
	
	private OutArc a1;
	private InArc a2;
	private Zero a3;
	private Empty a4;
	private OutArc a11;
	private InArc a21;
	private Zero a31;
	private Empty a41;
	private Place p1;
	private Place p2;
	private Place p3;
	private Place p4;
	private Transition t1;
	private Transition t2;
	private Transition t3;
	private Transition t4;
	
	@BeforeEach
	void setUp() throws Exception {
		t1 = new Transition();
		t2 = new Transition();
		t3 = new Transition();
		t4 = new Transition();
		p1 = new Place(5);
		p2 = new Place(6);
		p3 = new Place(0);
		p4 = new Place(4);
		a1 = new OutArc(p1,3);
		a11 = new OutArc(p1,t1,5);
		a2 = new InArc(p2,5);
		a21 = new InArc(p2,t2,21);
		a3 = new Zero(p3);
		a31 = new Zero(p3,t3);
		a4 = new Empty(p4);
		a41 = new Empty(p4,t4);
	}

	@Test
	@Order(0)
	void testSetGet() {
		Assertions.assertEquals(a1.getWeight(), 3);
		a1.setWeight(5);
		Assertions.assertEquals(a1.getWeight(), 5);
		Assertions.assertEquals(a1.getPlace(), p1);
	}
	
	@Test
	@Order(1)
	void testIsTrig() {
		p2.setTokens(2);
		Assertions.assertFalse(a2.isTrig());
		p2.setTokens(65);
		Assertions.assertTrue(a21.isTrig());
		p3.setTokens(0);
		Assertions.assertTrue(a3.isTrig());
		p3.setTokens(4);
		Assertions.assertFalse(a31.isTrig());
		p4.setTokens(0);
		Assertions.assertFalse(a4.isTrig());
		p4.setTokens(4);
		Assertions.assertTrue(a41.isTrig());
	}
	
	@Test
	@Order(2)
	void testStep() {
		a1.step();
		Assertions.assertEquals(p1.getTokens(), 8);
		a2.step();
		Assertions.assertEquals(p2.getTokens(), 1);
		a3.step();
		Assertions.assertEquals(p3.getTokens(), 0);
		a4.step();
		Assertions.assertEquals(p4.getTokens(), 0);
	}
}
