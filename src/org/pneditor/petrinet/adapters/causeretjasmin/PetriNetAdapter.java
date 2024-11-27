package org.pneditor.petrinet.adapters.causeretjasmin;

import org.pneditor.petrinet.AbstractArc;
import org.pneditor.petrinet.AbstractNode;
import org.pneditor.petrinet.AbstractPlace;
import org.pneditor.petrinet.AbstractTransition;
import org.pneditor.petrinet.PetriNetInterface;
import org.pneditor.petrinet.ResetArcMultiplicityException;
import org.pneditor.petrinet.UnimplementedCaseException;
import org.pneditor.petrinet.models.causeretjasmin.*;

public class PetriNetAdapter extends PetriNetInterface {
		
	@Override
	public AbstractPlace addPlace() {
		return new PlaceAdapter("",new Place());
	}

	@Override
	public AbstractTransition addTransition() {
		TransitionAdapter transition = new TransitionAdapter("", new Transition());
		return transition;
	}

	@Override
	public AbstractArc addRegularArc(AbstractNode source, AbstractNode destination) throws UnimplementedCaseException {
		if (source.getClass() == PlaceAdapter.class) {
			PlaceAdapter place = (PlaceAdapter)source;
			TransitionAdapter transition = (TransitionAdapter)destination;
			InArc arc = new InArc(place.getPlace(),1);
			ArcAdapter adaptedArc = new ArcAdapter(arc);
			adaptedArc.setTransition(transition);
			transition.add(arc);
			return adaptedArc;
		} else {
			PlaceAdapter place = (PlaceAdapter)destination;
			TransitionAdapter transition = (TransitionAdapter)source;
			OutArc arc = new OutArc(place.getPlace(),1);
			ArcAdapter adaptedArc = new ArcAdapter(arc);
			adaptedArc.setTransition(transition);
			transition.add(arc);
			return adaptedArc;
		}
	}

	@Override
	public AbstractArc addInhibitoryArc(AbstractPlace place, AbstractTransition transition) throws UnimplementedCaseException {
		PlaceAdapter placeAdapted = (PlaceAdapter)place;
		TransitionAdapter transitionAdapted = (TransitionAdapter)transition;
		Zero arc = new Zero(placeAdapted.getPlace());
		ArcAdapter adaptedArc = new ArcAdapter(arc);
		adaptedArc.setTransition(transitionAdapted);
		transitionAdapted.add(arc);
		return adaptedArc;
	}

	@Override
	public AbstractArc addResetArc(AbstractPlace place, AbstractTransition transition) throws UnimplementedCaseException {
		PlaceAdapter placeAdapted = (PlaceAdapter)place;
		TransitionAdapter transitionAdapted = (TransitionAdapter)transition;
		Empty arc = new Empty(placeAdapted.getPlace());
		ArcAdapter adaptedArc = new ArcAdapter(arc);
		adaptedArc.setTransition(transitionAdapted);
		transitionAdapted.add(arc);
		return adaptedArc;
	}

	@Override
	public void removePlace(AbstractPlace place) {
		for (AbstractTransition transition : this.getTransitions()) {
			for (AbstractArc arc : this.getConnectedArcs(transition)) {
				ArcAdapter arcAdapted = (ArcAdapter)arc;
				if (arcAdapted.getSource().equals(place) || arcAdapted.getDestination().equals(place)) {
					this.removeArc(arc);
				}
			}
		}
	}

	@Override
	public void removeTransition(AbstractTransition transition) {
		for (AbstractArc arc : this.getConnectedArcs(transition)) {
			this.removeAbstractArc(arc);
		}
	}

	@Override
	public void removeArc(AbstractArc arc) {
		ArcAdapter arcAdapted = (ArcAdapter)arc;
		if (arcAdapted.getDestination().getClass() == TransitionAdapter.class) {
			TransitionAdapter transition = (TransitionAdapter)arcAdapted.getDestination();
			transition.getTransition().remArcT((InArc)arcAdapted.getArc());
		}
		else {
			TransitionAdapter transition = (TransitionAdapter)arcAdapted.getSource();
			transition.getTransition().remArcT((OutArc)arcAdapted.getArc());
		}
	}

	@Override
	public boolean isEnabled(AbstractTransition transition) throws ResetArcMultiplicityException {
		TransitionAdapter transitionAdapted = (TransitionAdapter)transition;
		return transitionAdapted.isTrig();
	}

	@Override
	public void fire(AbstractTransition transition) throws ResetArcMultiplicityException {
		TransitionAdapter transitionAdapted = (TransitionAdapter)transition;
		transitionAdapted.fire();
	}
	
	
}
