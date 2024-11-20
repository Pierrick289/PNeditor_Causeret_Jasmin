package org.pneditor.petrinet.models.causeretjasmin;

/**
 * @author Pierrick CAUSERET
 * @author Hugo JASMIN
 */
public interface IPetriNet {
	
	/**
	 * Add the Place p to the PetriNet
	 * 
	 * @param p the Place
	 */
	Place addPlace(Place p);
	
	/**
	 * Remove the Place p from the PetriNet
	 * 
	 * @param p
	 */
	void remPlace(Place p);
	
	/**
	 * Add the Transition t to the PetriNet
	 * 
	 * @param t the Transition
	 */
	Transition addTransition(Transition t);
	
	/**
	 * Remove the Transition t from the PetriNet
	 * 
	 * @param t
	 */
	void remTransition(Transition t);
	
	/**
	 * Add the Arc a to the PetriNet and to the transition t
	 * 
	 * @param p the Place linked to the Arc
	 * @param t the Transition linked to the Arc
	 * @param weight the weight of the arc
	 */
	OutArc addOutArc(Place p, Transition t, int weight);
	
	/**
	 * Add the Arc a to the PetriNet and to the transition t
	 * 
	 * @param p the Place linked to the Arc
	 * @param t the Transition linked to the Arc
	 * @param weight the weight of the arc
	 */
	InArc addInArcNormal(Place p, Transition t, int weight);
	
	/**
	 * Add the Arc a to the PetriNet and to the transition t
	 * 
	 * @param p the Place linked to the Arc
	 * @param t the Transition linked to the Arc
	 */
	Empty addEmptyArc(Place p, Transition t);
	
	/**
	 * Add the Arc a to the PetriNet and to the transition t
	 * 
	 * @param p the Place linked to the Arc
	 * @param t the Transition linked to the Arc
	 */
	Zero addZeroArc(Place p, Transition t);
	
	/**
	 * Remove the Arc a from the PetriNet
	 * 
	 * @param a
	 */
	void remArc(Arc a);
}
