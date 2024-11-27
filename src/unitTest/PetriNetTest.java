package unitTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import org.pneditor.petrinet.models.causeretjasmin.*;

class PetriNetTest {
    private PetriNet petriNet;
    private Place p1;
    private Place p2;
    private Transition t1;

    @BeforeEach
    void setUp() {
        petriNet = new PetriNet();
        p1 = new Place();
        p2 = new Place();
        t1 = new Transition();
        petriNet.addTransition(t1);
        petriNet.addPlace(p1);
    }

    @Test
    @Order(0)
    void testAddPlace() {
        petriNet.addPlace(p1);
        assertTrue(petriNet.getPlaces().contains(p1));
    }

    @Test
    @Order(1)
    void testRemPlace() {
    	petriNet.addEmptyArc(p1, t1);
    	petriNet.addOutArc(p2, t1, 7);
        petriNet.remPlace(p1);
        petriNet.remPlace(p2);
        assertFalse(petriNet.getPlaces().contains(p1));
        assertEquals(petriNet.getInArcs().size(),0);
        assertEquals(petriNet.getOutArcs().size(),0);
    }

    @Test
    @Order(2)
    void testAddTransition() {
        petriNet.addTransition(t1);
        assertTrue(petriNet.getTransitions().contains(t1));
    }

    @Test
    @Order(3)
    void testRemTransition() {
    	petriNet.addEmptyArc(p1, t1);
    	petriNet.addOutArc(p2, t1, 5);
        petriNet.remTransition(t1);
        assertFalse(petriNet.getTransitions().contains(t1));
        assertEquals(petriNet.getInArcs().size(),0);
        assertEquals(petriNet.getOutArcs().size(),0);
    }

    @Test
    @Order(4)
    void testAddOutArc() {
        OutArc oa = petriNet.addOutArc(p1, t1, 5);
        assertTrue(petriNet.getOutArcs().contains(oa));
        assertEquals(5, oa.getWeight());
        assertTrue(t1.isLinked(p1));
        petriNet.addOutArc(p1, t1, 3);
        assertEquals(t1.getTheLinkOut(p1).getWeight(),3);
    }

    @Test
    @Order(5)
    void testAddInArcNormal() {
        InArc ia = petriNet.addInArcNormal(p1, t1, 3);
        assertTrue(petriNet.getInArcs().contains(ia));
        assertEquals(3, ia.getWeight());
        assertTrue(t1.isLinked(p1));
        petriNet.addInArcNormal(p1, t1, 5);
        assertEquals(t1.getTheLinkIn(p1).getWeight(),5);
    }

    @Test
    @Order(6)
    void testAddEmptyArc() {
        Empty e = petriNet.addEmptyArc(p1, t1);
        assertTrue(petriNet.getInArcs().contains(e));
        assertTrue(t1.isLinked(p1));
        petriNet.addEmptyArc(p1, t1);
        assertEquals(t1.getTheLinkIn(p1).getWeight(),0);
    }

    @Test
    @Order(7)
    void testAddZeroArc() {
        Zero z = petriNet.addZeroArc(p1, t1);
        assertTrue(petriNet.getInArcs().contains(z));
        assertTrue(t1.isLinked(p1));
        petriNet.addZeroArc(p1, t1);
        assertEquals(t1.getTheLinkIn(p1).getWeight(),0);
    }

    @Test
    @Order(8)
    void testRemArc() {
        OutArc oa = petriNet.addOutArc(p1, t1, 4);
        petriNet.remArc(oa);
        assertFalse(petriNet.getOutArcs().contains(oa));
        assertFalse(t1.isInT(oa));
    }
}

