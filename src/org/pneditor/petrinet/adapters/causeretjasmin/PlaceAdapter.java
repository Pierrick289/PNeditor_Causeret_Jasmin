package org.pneditor.petrinet.adapters.causeretjasmin;

import org.pneditor.petrinet.AbstractPlace;
import org.pneditor.petrinet.models.causeretjasmin.*;

public class PlaceAdapter extends AbstractPlace {
	
	private Place P;
	
	public PlaceAdapter(Place P) {
		super("label");
		this.P = P;
	}
	
	//TODO mettre les adpateurs pour toutes les méthodes ! 
	
	@Override
	public void addToken() {
		P.addTokens(1);
		
	}

	@Override
	public void removeToken() {
		P.remTokens(1);
		
	}

	@Override
	public int getTokens() {
		return P.getTokens();
	}

	@Override
	public void setTokens(int tokens) {
		P.setTokens(tokens);
		
	}

}
