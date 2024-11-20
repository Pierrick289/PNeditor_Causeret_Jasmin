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

	public Transition getTransition() {
		for 
	}
	
	@Override
	public AbstractNode getDestination() {
			if (this.arc instanceof OutArc) {
				return ;
			} else {
				return ;
			}
	}

	@Override
	public boolean isReset() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isRegular() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isInhibitory() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getMultiplicity() throws ResetArcMultiplicityException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setMultiplicity(int multiplicity) throws ResetArcMultiplicityException {
		// TODO Auto-generated method stub
		
	}

}

