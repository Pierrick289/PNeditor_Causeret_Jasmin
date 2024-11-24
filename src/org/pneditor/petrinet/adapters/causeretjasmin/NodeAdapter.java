package org.pneditor.petrinet.adapters.causeretjasmin;

import org.pneditor.petrinet.AbstractNode;
import org.pneditor.petrinet.models.causeretjasmin.Place;

public class NodeAdapter extends AbstractNode {

	public NodeAdapter(String label) {
		super(label);
	}

	@Override
	public boolean isPlace() {
		return this.getClass().equals(Place.class);
	}

}
