package org.pneditor.petrinet.models.causeretjasmin;

import java.util.ArrayList;

/**
 * @author Pierrick CAUSERET
 * @author Hugo JASMIN
 */
public class Transition {
	
	private ArrayList<InArc> inArcs;
	private ArrayList<OutArc> outArcs;
	
	/**
	 * Constructor with a list of inArcs and a list of outArcs
	 * 
	 * @param inArcs
	 * @param outArcs
	 */
	public Transition(ArrayList<InArc> inArcs, ArrayList<OutArc> outArcs) {
		this.inArcs = inArcs;
		this.outArcs = outArcs;
	}
	
	/**
	 * Constructor with empty lists of in/outArcs
	 */
	public Transition() {
		this(new ArrayList<InArc>(), new ArrayList<OutArc>());
	}
	
	/**
	 * Getter of inArcs
	 */
	public ArrayList<InArc> getInArcs(){
		return this.inArcs;
	}
	
	/**
	 * Getter of outArcs
	 */
	public ArrayList<OutArc> getOutArcs(){
		return this.outArcs;
	}
	
	/**
	 * The function verifies if the transition can be triggered and if it is able to, it runs the transition
	 */
	public void fire() {
		boolean isTrigTransition = true;
		for (InArc ia : inArcs) {
			isTrigTransition = isTrigTransition && ia.isTrig();
		}
		if (isTrigTransition) {
			for (InArc ia : inArcs) {
				ia.step();
			}
			for (OutArc ia : outArcs) {
				ia.step();
			}
		} else {
			System.out.println("Transition not triggerable");
		}
	}
	
	public void addOutArc(OutArc arc) {
		outArcs.add(arc);
	}
	
	public void addInArcNormal(InArc arc) {
		inArcs.add(arc);
	}
	
	public void addEmptyArc(Empty arc) {
		inArcs.add(arc);
	}
	
	public void addZeroArc(Zero arc) {
		inArcs.add(arc);
	}
	
	
	/**
	 * Return true if the Arc a is linked to the transition t
	 * i.e. if a is in this.inArcs or in this.outArcs
	 * Else return false
	 * @param a
	 */
	public boolean isInT(Arc a) {
		return this.inArcs.contains(a) || this.outArcs.contains(a);
	}	
	
	/**
	 * Remove the Arc a from the transition
	 * 
	 * @param a
	 */
	public void remArcT(Arc a) {
		if (a instanceof InArc) {
			this.inArcs.remove(a);
		} else if (a instanceof OutArc) {
			this.outArcs.remove(a);
		}
	}
	
	/**
	 * Return true if the transition is linked by an Arc to the place p 
	 * i.e. if there is an arc in this.outArcs or in this.inArcs which have the instance p
	 * Else return false
	 * @param p
	 */
	public boolean isLinked(Place p) {
		boolean isLinked = false;
		for (OutArc ao : this.outArcs) {
			if (ao.getPlace().equals(p)) {
				return true;
			}
		}
		for (InArc ai : this.inArcs) {
			if (ai.getPlace().equals(p)) {
				isLinked = true;
			}
		}
		return isLinked;
	}
	
	/**
	 * @requires this.isLinked(p)==true
	 * @param p
	 * @return the OutArc between the transition and a place p
	 */
	public OutArc getTheLinkOut(Place p) {
		for (OutArc ao : this.outArcs) {
			if (ao.getPlace().equals(p)) {
				return ao;
			}
		}
		System.out.println("There is no outgoing link between the transition and the place!");
		return null;//normally never happen
	}
	
	/**
	 * @requires this.isLinked(p)==true
	 * @param p
	 * @return the InArc between the transition and a place p
	 */
		public InArc getTheLinkIn(Place p) {
			for (InArc ai : this.inArcs) {
				if (ai.getPlace().equals(p)) {
					return ai;
				}
			}
			System.out.println("There is no incoming link between the transition and the place!");
			return null;//normally never happen
		}
}
