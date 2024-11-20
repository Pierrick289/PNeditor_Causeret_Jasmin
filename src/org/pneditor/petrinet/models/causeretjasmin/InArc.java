package org.pneditor.petrinet.models.causeretjasmin;

/**
 * @author Pierrick CAUSERET
 * @author Hugo JASMIN
 */
public class InArc extends Arc{
	
	/**
	 * Initial constructor of an arc
	 * 
	 * @param place
	 * @param weight
	 */
	public InArc(Place place, int weight) {
		super(place, weight);
	}

	/**
	 * Constructor of an arc (adapted for the following steps of the exercise)
	 * 
	 * @param place
	 * @param transition
	 * @param weight
	 */
	public InArc(Place place, Transition transition, int weight) {
		this(place, weight);
		transition.addInArcNormal(this);
	}
	
	/**
	 * The method remove the number of tokens needed to fire the transition
	 */
	public void step() {
		this.place.remTokens(this.getWeight());
	}
	
	/**
	 * @return true if the arc is triggerable, false else
	 */
	public boolean isTrig() {
		return this.getWeight() <= this.place.getTokens();
	}
}
