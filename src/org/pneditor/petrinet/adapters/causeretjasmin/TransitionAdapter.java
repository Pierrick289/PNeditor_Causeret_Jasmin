package org.pneditor.petrinet.adapters.causeretjasmin;

import org.pneditor.petrinet.AbstractTransition;
import org.pneditor.petrinet.models.causeretjasmin.*;

public class TransitionAdapter extends AbstractTransition{

	private Transition transition;
	
	public TransitionAdapter(String label, Transition transition) {
		super(label);
		this.transition = transition;
	}
	
	public Transition getTransition() {
		return this.transition;
	}
	
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
	
}
