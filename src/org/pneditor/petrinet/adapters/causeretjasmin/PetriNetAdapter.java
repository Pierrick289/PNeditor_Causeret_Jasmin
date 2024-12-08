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
	
	private PetriNet pn;
	
	public PetriNetAdapter(PetriNet pn) {
		this.pn = pn;
	}
	
	@Override
	public AbstractPlace addPlace() {
		Place p = new Place();
		pn.addPlace(p);
		return new PlaceAdapter("",p);
	}

	@Override
	public AbstractTransition addTransition() {
		Transition t = new Transition();
		pn.addTransition(t);
		return new TransitionAdapter("",t);
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
			pn.addInArcNormal(place.getPlace(), transition.getTransition(), 1);
			return adaptedArc;
		} else {
			PlaceAdapter place = (PlaceAdapter)destination;
			TransitionAdapter transition = (TransitionAdapter)source;
			OutArc arc = new OutArc(place.getPlace(),1);
			ArcAdapter adaptedArc = new ArcAdapter(arc);
			adaptedArc.setTransition(transition);
			transition.add(arc);
			pn.addOutArc(place.getPlace(), transition.getTransition(), 1);
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
		pn.addZeroArc(placeAdapted.getPlace(), transitionAdapted.getTransition());
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
		pn.addEmptyArc(placeAdapted.getPlace(), transitionAdapted.getTransition());
		return adaptedArc;
	}

	@Override
	public void removePlace(AbstractPlace place) {
		PlaceAdapter placeAdapted = (PlaceAdapter)place;
		pn.remPlace(placeAdapted.getPlace());
	}

	@Override
	public void removeTransition(AbstractTransition transition) {
		TransitionAdapter transitionAdapted = (TransitionAdapter)transition;
		pn.remTransition(transitionAdapted.getTransition());
	}

	@Override
	public void removeArc(AbstractArc arc) {
		ArcAdapter arcAdapted = (ArcAdapter)arc;
		pn.remArc(arcAdapted.getArc());
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
