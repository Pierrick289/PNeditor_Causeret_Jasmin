package org.pneditor.petrinet.adapters.causeretjasmin;

import org.pneditor.petrinet.AbstractTransition;
import org.pneditor.petrinet.models.causeretjasmin.*;

public class TransitionAdapter extends AbstractTransition{

	private Transition transition;
	
	public TransitionAdapter(String label, Transition transition) {
		super(label);
		this.transition = transition;
	}
	
	/**
	 * 
	 * @return transition
	 */
	public Transition getTransition() {
		return this.transition;
	}
	
	/**
	 * Add an arc to the transition
	 * @param arc
	 */
	public void add(Arc arc) {
		if (arc instanceof OutArc) {
			this.getTransition().addOutArc((OutArc)arc);
		} else if (arc instanceof Empty) {
			this.getTransition().addEmptyArc((Empty)arc);
		} else if (arc instanceof Zero) {
			this.getTransition().addZeroArc((Zero)arc);
		} else {
			this.getTransition().addInArcNormal((InArc)arc);
		}
	}
	
	/**
	 * Refers to the fire() method of Transition
	 */
	public void fire() {
		this.transition.fire();
	}
	
	/**
	 * 
	 * @return true if the transition is triggerable, false else
	 */
	public boolean isTrig() {
		boolean isTrigTransition = true;
		for (InArc ia : this.transition.getInArcs()) {
			isTrigTransition = isTrigTransition && ia.isTrig();
		}
		return isTrigTransition;
	}
	
	/**
	 * Removes an arc from the transition
	 * @param arc
	 */
	public void remArcT(ArcAdapter arc) {
		this.getTransition().remArcT(arc.getArc());
	}
	
}
