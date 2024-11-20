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
	
	
}
