package com.contextmenu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;
import javax.swing.JToolTip;
import javax.swing.SwingConstants;
import javax.swing.text.JTextComponent;
import javax.swing.undo.UndoManager;

import com.toolTip.ToolTipLlamada;

@SuppressWarnings("serial")

public class DefaultContextMenu extends JPopupMenu {

	private Clipboard clipboard;

	private UndoManager undoManager;

	private JMenuItem undo;

	private JMenuItem redo;

	private JMenuItem cut;

	private JMenuItem copy;

	private JMenuItem paste;

	private JMenuItem delete;

	private JMenuItem selectAll;

	private JTextComponent textComponent;

	private String text;

	private Color fondo;

	private Color colorTexto;

	private Color border;

	private Font fuente;

	@Override
	public void setToolTipText(String text) {

		setToolTip(text, null, null, null, null);

	}

	public void setToolTip(String text, Color background, Color foreground, Color border, Font font) {

		calcularTooltip(text, background, foreground, border, font);

	}

	private void calcularTooltip(String text, Color background, Color foreground, Color border, Font font) {

		if (background == null) {

			background = new Color(32, 39, 55);

		}

		if (foreground == null) {

			foreground = Color.WHITE;

		}

		if (border == null) {

			border = new Color(173, 173, 173);

		}

		if (font == null) {

			try {

				font = getFont().deriveFont(20f);

			}

			catch (Exception e) {

				font = new Font("Dialog", Font.PLAIN, 20);

			}

		}

		this.text = text;

		this.fondo = background;

		this.colorTexto = foreground;

		this.border = border;

		this.fuente = font;

		super.setToolTipText(text);

	}

	@Override
	public JToolTip createToolTip() {

		if (text == null || fondo == null || colorTexto == null || border == null) {

			return super.createToolTip();

		}

		else {

			ToolTipLlamada tip = new ToolTipLlamada(text, fondo, colorTexto, border, fuente);

			tip.setComponent(this);

			return tip;

		}

	}

	public DefaultContextMenu() {

		setBackground(Color.WHITE);

		undoManager = new UndoManager();

		clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

		addPopupMenuItems();

	}

	private void addPopupMenuItems() {

		undo = new JMenuItem("Undo");

		undo.setHorizontalAlignment(SwingConstants.CENTER);

		undo.setForeground(Color.BLACK);

		undo.setBackground(Color.WHITE);

		undo.setFont(new Font("Dialog", Font.PLAIN, 16));

		undo.setEnabled(false);

		undo.addActionListener(event -> undoManager.undo());

		add(undo);

		redo = new JMenuItem("Redo");

		redo.setHorizontalAlignment(SwingConstants.CENTER);

		redo.setForeground(Color.BLACK);

		redo.setBackground(Color.WHITE);

		redo.setFont(new Font("Dialog", Font.PLAIN, 16));

		redo.setEnabled(false);

		redo.addActionListener(event -> undoManager.redo());

		add(redo);

		cut = new JMenuItem("Cut");

		cut.setHorizontalAlignment(SwingConstants.CENTER);

		cut.setForeground(Color.BLACK);

		cut.setBackground(Color.WHITE);

		cut.setFont(new Font("Dialog", Font.PLAIN, 16));

		cut.setEnabled(false);

		cut.addActionListener(event -> textComponent.cut());

		add(new JSeparator());

		add(cut);

		copy = new JMenuItem("Copy");

		copy.setHorizontalAlignment(SwingConstants.CENTER);

		copy.setForeground(Color.BLACK);

		copy.setBackground(Color.WHITE);

		copy.setFont(new Font("Dialog", Font.PLAIN, 16));

		copy.setEnabled(false);

		copy.addActionListener(event -> textComponent.copy());

		add(copy);

		paste = new JMenuItem("Paste");

		paste.setHorizontalAlignment(SwingConstants.CENTER);

		paste.setForeground(Color.BLACK);

		paste.setBackground(Color.WHITE);

		paste.setFont(new Font("Dialog", Font.PLAIN, 16));

		paste.setEnabled(false);

		paste.addActionListener(event -> textComponent.paste());

		add(paste);

		delete = new JMenuItem("Delete");

		delete.setHorizontalAlignment(SwingConstants.CENTER);

		delete.setForeground(Color.BLACK);

		delete.setBackground(Color.WHITE);

		delete.setFont(new Font("Dialog", Font.PLAIN, 16));

		delete.setEnabled(false);

		delete.addActionListener(event -> textComponent.replaceSelection(""));

		add(delete);

		selectAll = new JMenuItem("Select All");

		selectAll.setHorizontalAlignment(SwingConstants.CENTER);

		selectAll.setForeground(Color.BLACK);

		selectAll.setBackground(Color.WHITE);

		selectAll.setFont(new Font("Dialog", Font.PLAIN, 16));

		selectAll.setEnabled(false);

		selectAll.addActionListener(event -> textComponent.selectAll());

		add(new JSeparator());

		add(selectAll);

	}

	private void addTo(JTextComponent textComponent) {

		textComponent.addKeyListener(new KeyAdapter() {

			@SuppressWarnings("deprecation")
			@Override

			public void keyPressed(KeyEvent pressedEvent) {

				if (((pressedEvent.getKeyCode() == KeyEvent.VK_Z) && ((pressedEvent.getModifiersEx()
						& Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()) != 0)) && undoManager.canUndo()) {

					undoManager.undo();

				}

				if (((pressedEvent.getKeyCode() == KeyEvent.VK_Y) && ((pressedEvent.getModifiersEx()
						& Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()) != 0)) && undoManager.canRedo()) {

					undoManager.redo();

				}

			}

		});

		textComponent.addMouseListener(new MouseAdapter() {

			@Override

			public void mousePressed(MouseEvent releasedEvent) {

				handleContextMenu(releasedEvent);

			}

			@Override

			public void mouseReleased(MouseEvent releasedEvent) {

				handleContextMenu(releasedEvent);

			}

		});

		textComponent.getDocument().addUndoableEditListener(event -> undoManager.addEdit(event.getEdit()));

	}

	private void handleContextMenu(MouseEvent releasedEvent) {

		if (releasedEvent.getButton() == MouseEvent.BUTTON3) {

			processClick(releasedEvent);

		}

	}

	private void processClick(MouseEvent event) {

		textComponent = (JTextComponent) event.getSource();

		textComponent.requestFocus();

		boolean enableUndo = undoManager.canUndo();

		boolean enableRedo = undoManager.canRedo();

		boolean enableCut = false;

		boolean enableCopy = false;

		boolean enablePaste = false;

		boolean enableDelete = false;

		boolean enableSelectAll = false;

		String selectedText = textComponent.getSelectedText();

		text = textComponent.getText();

		if (text != null && text.length() > 0) {

			enableSelectAll = true;

		}

		if (selectedText != null && selectedText.length() > 0) {

			enableCut = true;

			enableCopy = true;

			enableDelete = true;

		}

		if (clipboard.isDataFlavorAvailable(DataFlavor.stringFlavor) && textComponent.isEnabled()) {

			enablePaste = true;

		}

		undo.setEnabled(enableUndo);

		redo.setEnabled(enableRedo);

		cut.setEnabled(enableCut);

		copy.setEnabled(enableCopy);

		paste.setEnabled(enablePaste);

		delete.setEnabled(enableDelete);

		selectAll.setEnabled(enableSelectAll);

		show(textComponent, event.getX(), event.getY());

	}

	public static void addDefaultContextMenu(JTextComponent component) {

		DefaultContextMenu defaultContextMenu = new DefaultContextMenu();

		defaultContextMenu.addTo(component);

	}

}
