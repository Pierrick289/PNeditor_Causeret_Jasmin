package org.pneditor.petrinet.adapters.causeretjasmin;

import org.pneditor.petrinet.AbstractPlace;
import org.pneditor.petrinet.models.causeretjasmin.*;

public class PlaceAdapter extends AbstractPlace {
	
	private Place place;
	
	public PlaceAdapter(Place place) {
		super("label");
		this.place = place;
	}
	
	public Place getPlace() {
		return this.place;
	}
		
	@Override
	public void addToken() {
		place.addTokens(1);
		
	}

	@Override
	public void removeToken() {
		place.remTokens(1);
		
	}

	@Override
	public int getTokens() {
		return place.getTokens();
	}

	@Override
	public void setTokens(int tokens) {
		place.setTokens(tokens);
	}
}
