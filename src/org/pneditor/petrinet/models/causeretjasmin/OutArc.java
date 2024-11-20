package org.pneditor.petrinet.models.causeretjasmin;

/**
 * @author Pierrick CAUSERET
 * @author Hugo JASMIN
 */
public class OutArc extends Arc{
	
	/**
	 * Initial constructor for an arc
	 * 
	 * @param place
	 * @param weight
	 */
	public OutArc(Place place, int weight) {
		super(place, weight);
	}
	
	/**
	 * Constructor of an arc (adapted for the following steps of the exercise)
	 * 
	 * @param place
	 * @param transition
	 * @param weight
	 */
	public OutArc(Place place, Transition transition, int weight) {
		this(place, weight);
		transition.addOutArc(this);
	}
	
	/**
	 * The method add the number of tokens needed when the transition if fired
	 */
	public void step() {
		this.place.addTokens(this.getWeight());
	}
	
}
