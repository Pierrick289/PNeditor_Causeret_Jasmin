package org.pneditor.petrinet.models.causeretjasmin;

/**
 * Abstract class for the arcs of the PetriNet
 * 
 * @author Pierrick CAUSERET
 * @author Hugo JASMIN
 */
public abstract class Arc {
	protected Place place;
	protected int weight;
	
	/**
	 * Constructor of an arc
	 * 
	 * @param place
	 * @param weight
	 */
	public Arc(Place place, int weight) {
		this.place = place;
		this.weight = weight;
	}
	
	/**
	 * Set the weight of the arc
	 * 
	 * @param weight
	 */
	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	/**
	 * 
	 * @return the weight of the arc
	 */
	public int getWeight() {
		return this.weight;
	}
	
	/**
	 * 
	 * @return the place of the arc
	 */
	public Place getPlace() {
		return this.place;
	}
	
	/**
	 * Abstract method for the step() method
	 */
	public abstract void step();
	
}
