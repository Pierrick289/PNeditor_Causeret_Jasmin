package unitTest;

import org.junit.jupiter.api.*;

import org.pneditor.petrinet.models.causeretjasmin.*;

class TransitionTest {
	
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
	
	@BeforeEach
	void setUp() throws Exception {
		t1 = new Transition();
		t2 = new Transition();
		p1 = new Place(5);
		p2 = new Place(26);
		p3 = new Place(2);
		p4 = new Place(4);
		a11 = new OutArc(p1,t1,5);
		a21 = new InArc(p2,t1,21);
		a31 = new Zero(p3,t2);
		a41 = new Empty(p4,t2);
	}

	@Test
	@Order(0)
	void testFire() {
		t1.fire();
		Assertions.assertEquals(p1.getTokens(),10);
		Assertions.assertEquals(p2.getTokens(),5);
		t2.fire();
		Assertions.assertEquals(p3.getTokens(),2);
		Assertions.assertEquals(p4.getTokens(),4);
	}
	
	@Test
	@Order(1)
	void testIsLinked() {
		Assertions.assertTrue(t1.isLinked(p1));
		Assertions.assertFalse(t1.isLinked(p3));
		Assertions.assertTrue(t1.isLinked(p2));
	}
	
	@Test
	@Order(2)
    void testIsInT() {
        Assertions.assertTrue(t1.isInT(a21));
        Assertions.assertTrue(t1.isInT(a11));
        Assertions.assertFalse(t1.isInT(a31));
	}

	@Test
	@Order(3)
	void testRemArcT() {
		t1.remArcT(a11);
		t1.remArcT(a21);
		Assertions.assertFalse(t1.isLinked(p1));
		Assertions.assertFalse(t1.isLinked(p2));
	}
	
	@Test
	@Order(4)
	void testGetTheLink() {
		Assertions.assertEquals(t1.getTheLinkIn(p2).getWeight(), 21);
		Assertions.assertEquals(t1.getTheLinkIn(p1), null);
		Assertions.assertEquals(t1.getTheLinkOut(p1).getWeight(), 5);
		Assertions.assertEquals(t1.getTheLinkOut(p2), null);
	}
	
	@Test
	@Order(5)
	void testGetters() {
		Assertions.assertEquals(t1.getInArcs().getFirst(),a21);
		Assertions.assertEquals(t1.getOutArcs().getFirst(),a11);
	}
}
