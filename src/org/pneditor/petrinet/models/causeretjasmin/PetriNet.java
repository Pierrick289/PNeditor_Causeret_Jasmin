package org.pneditor.petrinet.models.causeretjasmin;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author Pierrick CAUSERET
 * @author Hugo JASMIN
 */
public class PetriNet implements IPetriNet {
	
	private ArrayList<Place> places;
	private ArrayList<OutArc> outArcs;
	private ArrayList<InArc> inArcs;
	private ArrayList<Transition> transitions;

	/**
	 * The default constructor
	 * 
	 */
	public PetriNet() {
		this.places = new ArrayList<Place>();
		this.outArcs = new ArrayList<OutArc>();
		this.inArcs = new ArrayList<InArc>();
		this.transitions = new ArrayList<Transition>();
	}
	
	/**
	 * 
	 * @return places
	 */
	public ArrayList<Place> getPlaces() {
		return places;
	}

	/**
	 * 
	 * @return outArcs
	 */
	public ArrayList<OutArc> getOutArcs() {
		return outArcs;
	}
	
	/**
	 * 
	 * @return inArcs
	 */
	public ArrayList<InArc> getInArcs() {
		return inArcs;
	}

	/**
	 * 
	 * @return transitions
	 */
	public ArrayList<Transition> getTransitions() {
		return transitions;
	}

	/**
	 * Add a place to the PetriNet
	 * 
	 * @param Place p
	 */
	public Place addPlace(Place p) {
		this.places.add(p);
		return(p);
	}
	
	/**
	 * Remove a place for the PetriNet
	 * 
	 * @param Place p
	 */
	//last modification 13/11 -> remove all the arcs link to the place that is removed
	public void remPlace(Place p) {
		this.places.remove(p);
		for (Iterator<InArc> iterator = this.inArcs.iterator(); iterator.hasNext();) {
	        InArc a = iterator.next();
	        if (a.getPlace().equals(p)) {
	            iterator.remove();
	            this.remArc(a);
	        }
	    }
	    for (Iterator<OutArc> iterator = this.outArcs.iterator(); iterator.hasNext();) {
	        OutArc a = iterator.next();
	        if (a.getPlace().equals(p)) {
	            iterator.remove();
	            this.remArc(a);
	        }
	    }
	}
	
	/**
	 * Add a transition to the PetriNet
	 * 
	 * @param Transition t
	 */
	public Transition addTransition(Transition t) {
		this.transitions.add(t);
		return(t);
	}
	
	/**
	 * Remove a transition from the PetriNet
	 * 
	 * @param Transition t
	 */
	//last modification 13/11 -> remove all the arcs link to the transition that is removed
	public void remTransition(Transition t) {
		this.transitions.remove(t);
		for (InArc ai : t.getInArcs()) {
			this.remArc(ai);
		}
		for (OutArc ao : t.getOutArcs()) {
			this.remArc(ao);
		}
	}
	
	/**
	 * Add an OutArc to the PetriNet between the place p, the transition t with a given weight
	 * 
	 * @param Place p
	 * @param Transition t
	 * @param int weight
	 */
	public OutArc addOutArc(Place p, Transition t, int weight) {
		OutArc arc = new OutArc(p, weight);
		//we check if there is already an arc between the place p and the transition t, and decide what to do.
		if (t.isLinked(p)) {
			System.out.println("there is already an OutArc between t and p");
			//update the arc
			t.getTheLinkOut(p).setWeight(weight);
			return t.getTheLinkOut(p);
		} else {
			this.outArcs.add(arc);
			t.addOutArc(arc);
			return arc;
		}
	}
	
	/**
	 * Add an InArcNormal to the PetriNet between the place p, the transition t with a given weight
	 * 
	 * @param Place p
	 * @param Transition t
	 * @param int weight
	 */
	public InArc addInArcNormal(Place p, Transition t, int weight) {
		InArc arc = new InArc(p, weight);
		if (t.isLinked(p)) {
			System.out.println("there is already an InArc between t and p");
			//we remove the arc, and put the new arc (because the old arc can be a specific arc like a Zero arc, by removing it, it is more simple)
			this.remArc(t.getTheLinkIn(p));
			this.inArcs.add(arc);
			t.addInArcNormal(arc);
			System.out.println("the arc has been changed");
			return arc;
		} else {
			this.inArcs.add(arc);
			t.addInArcNormal(arc);
			return arc;
		}
	}
	
	/**
	 * Add an EmptyArc to the PetriNet between the place p, the transition t
	 * 
	 * @param Place p
	 * @param Transition t
	 * @param int weight
	 */
	public Empty addEmptyArc(Place p, Transition t) {
		Empty arc = new Empty(p);
		if (t.isLinked(p)) {
			System.out.println("there is already an InArc between t and p");
			this.remArc(t.getTheLinkIn(p));
			this.inArcs.add(arc);
			t.addEmptyArc(arc);
			System.out.println("the arc has been changed");
			return arc;
		} else {
			this.inArcs.add(arc);
			t.addEmptyArc(arc);
			return arc;
		}
	}
	
	/**
	 * Add an ZeroArc to the PetriNet between the place p, the transition t
	 * 
	 * @param Place p
	 * @param Transition t
	 * @param int weight
	 */
	public Zero addZeroArc(Place p, Transition t) {
		Zero arc = new Zero(p);
		if (t.isLinked(p)) {
			System.out.println("there is already an InArc between t and p");
			this.remArc(t.getTheLinkIn(p));
			this.inArcs.add(arc);
			t.addZeroArc(arc);
			System.out.println("the arc has been changed");
			return arc;
		} else {
			this.inArcs.add(arc);
			t.addZeroArc(arc);
			return arc;
		}
	}
	
	/**
	 * Remove an Arc from the PetriNet
	 * 
	 * @param Arc a
	 */
	public void remArc(Arc a) {
		if (a instanceof InArc) {
			this.inArcs.remove(a);
		} else if (a instanceof OutArc) {
			this.outArcs.remove(a);
		}
		//now we remove the Arc from the transition it is linked with
		for (Transition t : this.transitions) {
			if (t.isInT(a)) {
				t.remArcT(a);
			}
		}
	}
	
}