package org.pneditor.petrinet.adapters.causeretjasmin;

import org.pneditor.petrinet.AbstractArc;
import org.pneditor.petrinet.AbstractNode;
import org.pneditor.petrinet.ResetArcMultiplicityException;
import org.pneditor.petrinet.models.causeretjasmin.*;

public class ArcAdapter extends AbstractArc {
	
	private Arc arc;
	
	@Override
	public AbstractNode getSource() {
		if (this.arc instanceof OutArc) {
			return new PlaceAdapter("", this.arc.getPlace());
		} else {
			return new TransitionAdapter("", this.getTransition());
		}
	}
	
	@Override
	public AbstractNode getDestination() {
			if (this.arc instanceof OutArc) {
				return new TransitionAdapter("", this.getTransition());
			} else {
				return new PlaceAdapter("", this.arc.getPlace());
			}
	}

	private Transition getTransition() {
		return ;
	}
	
	@Override
	public boolean isReset() {
		return this.arc instanceof Zero;
	}

	@Override
	public boolean isRegular() {
		// TODO Auto-generated method stub
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

