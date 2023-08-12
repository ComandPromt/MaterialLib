package util;

import java.awt.Color;
import java.awt.Component;
import java.awt.Shape;
import java.awt.Window;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowListener;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.reflect.Method;

import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

public class WindowsUtil {

	private static final String FRAME_ACTIVE_PROPERTY = "Frame.active";

	public static void makeWindowNonOpaque(Window window) {

		window.setBackground(new Color(0, 0, 0, 0));

		quietlyTryToMakeWindowNonOqaque(window);

	}

	public static void makeWindowsOpacity(Window window, float factor) {

		if (factor >= 0 & factor <= 1)

			window.setOpacity(factor);

	}

	public static void makeWindowsShape(Window window, Shape shape) {

		window.setShape(shape);

	}

	public static void makeWindowsShape(Window window, float x, float y, float w, float h, float arcw, float arch) {

		Shape s = new RoundRectangle2D.Float(x, y, w, h, arcw, arch);

		window.setShape(s);

	}

	public static void makeWindowsShape(Window window, float x, float y, float w, float h) {

		Shape s = new Rectangle2D.Float(x, y, w, h);

		window.setShape(s);

	}

	private static void quietlyTryToMakeWindowNonOqaque(Window window) {

		try {

			Class<?> clazz = Class.forName("com.sun.awt.AWTUtilities");

			Method method = clazz.getMethod("setWindowOpaque", java.awt.Window.class, Boolean.TYPE);

			method.invoke(clazz, window, false);
		}

		catch (Exception e) {

		}

	}

	public static WindowFocusListener createAndInstallRepaintWindowFocusListener(Window window) {

		WindowFocusListener windowFocusListener = new WindowFocusListener() {

			@Override

			public void windowGainedFocus(WindowEvent e) {

				e.getWindow().repaint();

			}

			@Override

			public void windowLostFocus(WindowEvent e) {

				e.getWindow().repaint();

			}

		};

		window.addWindowFocusListener(windowFocusListener);

		return windowFocusListener;

	}

	public static boolean isParentWindowFocused(Component component) {

		Window window = SwingUtilities.getWindowAncestor(component);

		return window != null && window.isFocused();

	}

	public static void installWindowFocusListener(WindowFocusListener focusListener, JComponent component) {

		component.addPropertyChangeListener(FRAME_ACTIVE_PROPERTY,

				createFrameFocusPropertyChangeListener(focusListener, component));

	}

	public static void installJComponentRepainterOnWindowFocusChanged(JComponent component) {

		component.addAncestorListener(createAncestorListener(component, createWindowListener(component)));

	}

	private static AncestorListener createAncestorListener(final JComponent component,
			final WindowListener windowListener) {

		return new AncestorListener() {

			@Override

			public void ancestorAdded(AncestorEvent event) {

				Window window = SwingUtilities.getWindowAncestor(component);

				if (window != null) {

					window.removeWindowListener(windowListener);

					window.addWindowListener(windowListener);

				}

			}

			@Override
			public void ancestorRemoved(AncestorEvent event) {

			}

			@Override
			public void ancestorMoved(AncestorEvent event) {

			}

		};

	}

	private static WindowListener createWindowListener(final JComponent component) {
		return new WindowAdapter() {

			@Override

			public void windowActivated(WindowEvent e) {

				component.repaint();

			}

			@Override

			public void windowDeactivated(WindowEvent e) {

				component.repaint();

			}

		};

	}

	private static PropertyChangeListener createFrameFocusPropertyChangeListener(
			final WindowFocusListener focusListener, final JComponent component) {
		return new PropertyChangeListener() {

			@Override

			public void propertyChange(PropertyChangeEvent evt) {

				Window window = SwingUtilities.getWindowAncestor(component);

				boolean hasFocus = (Boolean) component.getClientProperty(FRAME_ACTIVE_PROPERTY);

				if (hasFocus) {

					focusListener.windowGainedFocus(new WindowEvent(window, WindowEvent.WINDOW_GAINED_FOCUS));

				}

				else {

					focusListener.windowLostFocus(new WindowEvent(window, WindowEvent.WINDOW_LOST_FOCUS));

				}

			}

		};

	}

}