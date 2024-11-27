package org.pneditor.petrinet.adapters.causeretjasmin;

import org.pneditor.petrinet.AbstractArc;
import org.pneditor.petrinet.AbstractNode;
import org.pneditor.petrinet.ResetArcMultiplicityException;
import org.pneditor.petrinet.models.causeretjasmin.*;

public class ArcAdapter extends AbstractArc {
	
	private TransitionAdapter transition;
	private Arc arc;
	
	public ArcAdapter(Arc arc) {
		this.arc = arc;
	}
	
	public Arc getArc() {
		return this.arc;
	}
	
	@Override
	public AbstractNode getSource() {
		if (this.arc instanceof InArc) {
			return new PlaceAdapter("",this.arc.getPlace());
		} else {
			return transition;
		}
	}
	
	@Override
	public AbstractNode getDestination() {
			if (this.arc instanceof InArc) {
				return transition;
			} else {
				return new PlaceAdapter("",this.arc.getPlace());
			}
	}
	
	public void setTransition(TransitionAdapter transition) {
		this.transition = transition;
	}

	public TransitionAdapter getTransition() {
		return this.transition;
	}
	
	@Override
	public boolean isReset() {
		return this.arc instanceof Zero;
	}

	@Override
	public boolean isRegular() {
		return !(this.arc instanceof Empty || this.arc instanceof Zero);
	}

	@Override
	public boolean isInhibitory() {
		return this.arc instanceof Empty;
	}

	@Override
	public int getMultiplicity() throws ResetArcMultiplicityException {
		if (this.isReset()) {
			throw new ResetArcMultiplicityException();
		}
		return this.arc.getWeight();
	}

	@Override
	public void setMultiplicity(int multiplicity) throws ResetArcMultiplicityException {
		if (this.isReset()) {
			throw new ResetArcMultiplicityException();
		}
		this.arc.setWeight(multiplicity);
	}

}

