package simulation;

import java.util.Collection;
import java.util.List;

import view.Canvas;

public class Listener {

	private Canvas myView;
	private int myKey;

	public Listener(Canvas view, int key) {
		myView = view;
		myKey = key;
	}

	public Listener() {
	}

	public void update(List<Force> forces) {
		if (!myView.getLastKeyHeuristic())
			return;
		myView.setLastKeyHeuristic(false);

		Collection<Integer> keysPressed = myView.getKeysPressed();

		if (!keysPressed.contains(myKey))
			return;

		for (Force f : forces) {

			f.toggleState();

		}

	}

}
