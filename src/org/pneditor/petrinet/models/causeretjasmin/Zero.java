package org.pneditor.petrinet.models.causeretjasmin;

/**
 * @author Pierrick CAUSERET
 * @author Hugo JASMIN
 */
public class Zero extends InArc{

	public Zero(Place place) {
		super(place, 0);
	}
	
	/**
	 * Constructor of an arc (adapted for the following steps of the exercise)
	 * 
	 * @param place
	 * @param transition
	 */
	public Zero(Place place, Transition transition) {
		this(place);
		transition.addZeroArc(this);
	}
	
	/**
	 * The method does nothing
	 */
	public void step() {
		this.place.remTokens(0); //not necessary to write this line
	}
	
	/**
	 * @return true if the arc does not have any token, false else
	 */
	public boolean isTrig() {
		return this.place.getTokens() == 0;
	}

}
