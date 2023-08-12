package panelTask;

import java.awt.event.KeyEvent;

import javax.swing.JTextArea;

@SuppressWarnings("serial")
class Text extends JTextArea {

	@Override
	protected void processComponentKeyEvent(KeyEvent ke) {

		if (ke.getID() == KeyEvent.KEY_PRESSED && ke.getKeyCode() == KeyEvent.VK_TAB) {

			ke.consume();

			if (ke.isShiftDown()) {

				transferFocusBackward();

			}

			else {

				transferFocus();

			}

		}

		else {

			super.processComponentKeyEvent(ke);

		}

	}

}
