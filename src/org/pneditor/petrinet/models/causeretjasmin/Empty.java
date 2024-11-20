package org.pneditor.petrinet.models.causeretjasmin;

/**
 * @author Pierrick CAUSERET
 * @author Hugo JASMIN
 */
public class Empty extends InArc{
	
	/**
	 * Initial constructor for an arc
	 * 
	 * @param place
	 */
	public Empty(Place place) {
		super(place, 0);
	}
	
	/**
	 * Constructor of an arc (adapted for the following steps of the exercise)
	 * 
	 * @param place
	 * @param transition
	 */
	public Empty(Place place, Transition transition) {
		this(place);
		transition.addEmptyArc(this);
	}
	
	/**
	 * The method empties the place
	 */
	public void step() {
		this.place.remTokens(this.place.getTokens());
	}
	
	/**
	 * @return true if the arc does have some tokens, false else
	 */
	public boolean isTrig() {
		return this.place.getTokens() > 0;
	}
}
