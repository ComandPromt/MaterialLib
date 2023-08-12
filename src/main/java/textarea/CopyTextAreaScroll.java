package textarea;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import com.buttons.simple.SimpleButton;
import com.spinner.simple.Spinner;

import util.Mthos;

@SuppressWarnings("serial")

public class CopyTextAreaScroll extends JPanel {

	private JPanel panel;

	private TextAreaScroll texto;

	Spinner spinner;

	int fontSize;

	private int minimumSize;

	private int maximumSize;

	public void setEditable(boolean editable) {

		this.texto.setEditable(editable);

	}

	public void setMaximumSize(int maximumSize) {

		this.spinner.setMaxValor(this.minimumSize);

		this.maximumSize = maximumSize;

	}

	public void setMinimumSize(int minimumSize) {

		this.spinner.setMinValor(this.minimumSize);

		this.minimumSize = minimumSize;

	}

	public void setPanelLeftColor(Color color) {

		this.panel.setBackground(Color.WHITE);

	}

	public String getText() {

		return this.texto.getText();

	}

	public void setText(String text) {

		this.texto.setText(text);

	}

	public void setTextFont(Font font) {

		this.texto.setFont(font);

	}

	public void setLabelFontText(Font font) {

		this.texto.setFont(font);

	}

	public void setLabelText(String text) {

		this.texto.setLabelText(text);

	}

	private void aumentar() {

		if (this.maximumSize == 0 || this.texto.getTextFont().getSize() < this.maximumSize) {

			this.texto.setTextFont(this.texto.getTextFont().deriveFont((float) this.texto.getTextFont().getSize() + 1));

			spinner.setValor(this.texto.getTextFont().getSize() + 1);

		}

	}

	private void rueda(MouseWheelEvent e) {

		if (e.getWheelRotation() < 0) {

			aumentar();

		}

		else {

			disminuir();

		}

	}

	private void disminuir() {

		int fuente = this.texto.getTextFont().getSize();

		if (fuente > this.minimumSize) {

			fuente--;

		}

		this.texto.setTextFont(this.texto.getTextFont().deriveFont((float) fuente));

		spinner.setValor(fuente);

	}

	public void setBorderColor(Color color) {

		this.panel.setBorder(new LineBorder(color));

	}

	public void setFontSize(int size) {

		this.fontSize = size;

		this.texto.setTextFont(this.texto.getTextFont().deriveFont((float) size));

	}

	private void resetear() {

		spinner.setValor(fontSize);

		texto.setTextFont(texto.getTextFont().deriveFont((float) fontSize));

	}

	public CopyTextAreaScroll() {

		setLayout(new BorderLayout(0, 0));

		this.minimumSize = 12;

		this.maximumSize = 100;

		this.texto = new TextAreaScroll();

		this.fontSize = this.texto.getFont().getSize();

		spinner = new Spinner();

		spinner.setMinValor(this.minimumSize);

		spinner.setMaxValor(this.maximumSize);

		spinner.setValor(this.minimumSize);

		this.panel = new JPanel();

		this.panel.setBorder(new LineBorder(new Color(0, 0, 0)));

		this.panel.addMouseWheelListener(new MouseWheelListener() {

			@Override

			public void mouseWheelMoved(MouseWheelEvent e) {

				rueda(e);

			}

		});

		this.panel.setBackground(Color.WHITE);

		add(this.panel, BorderLayout.WEST);

		GridBagLayout gbl_panel = new GridBagLayout();

		gbl_panel.columnWidths = new int[] { 0, 0 };

		gbl_panel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0 };

		gbl_panel.columnWeights = new double[] { 0.0, Double.MIN_VALUE };

		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };

		this.panel.setLayout(gbl_panel);

		SimpleButton disminuir = new SimpleButton("");

		disminuir.addMouseWheelListener(new MouseWheelListener() {

			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {

				disminuir();

			}

		});

		disminuir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				disminuir();

			}

		});

		spinner.getEditor().addKeyListener(new KeyAdapter() {

			@Override

			public void keyReleased(KeyEvent e) {

				texto.setTextFont(texto.getTextFont().deriveFont((float) spinner.getValor()));

			}

		});

		GridBagConstraints gbc_spinner = new GridBagConstraints();

		gbc_spinner.insets = new Insets(0, 0, 5, 0);

		gbc_spinner.gridx = 0;

		gbc_spinner.gridy = 0;

		panel.add(spinner, gbc_spinner);

		disminuir.setIcon(new ImageIcon(CopyTextAreaScroll.class.getResource("/imgs/imagenes/zoom-out.png")));

		GridBagConstraints gbc_disminuir = new GridBagConstraints();

		gbc_disminuir.insets = new Insets(0, 0, 5, 0);

		gbc_disminuir.gridx = 0;

		gbc_disminuir.gridy = 1;

		this.panel.add(disminuir, gbc_disminuir);

		SimpleButton aumentar = new SimpleButton("");

		aumentar.addMouseWheelListener(new MouseWheelListener() {

			@Override

			public void mouseWheelMoved(MouseWheelEvent e) {

				aumentar();

			}

		});

		aumentar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				aumentar();

			}

		});

		aumentar.setIcon(new ImageIcon(CopyTextAreaScroll.class.getResource("/imgs/imagenes/zoom-in.png")));

		GridBagConstraints gbc_aumentar = new GridBagConstraints();

		gbc_aumentar.insets = new Insets(0, 0, 5, 0);

		gbc_aumentar.gridx = 0;

		gbc_aumentar.gridy = 2;

		this.panel.add(aumentar, gbc_aumentar);

		SimpleButton restaurar = new SimpleButton("");

		restaurar.addActionListener(new ActionListener() {

			@Override

			public void actionPerformed(ActionEvent e) {

				resetear();

			}

		});

		restaurar.setIcon(new ImageIcon(CopyTextAreaScroll.class.getResource("/imgs/imagenes/actualizar.png")));

		GridBagConstraints gbc_restaurar = new GridBagConstraints();

		gbc_restaurar.insets = new Insets(0, 0, 5, 0);

		gbc_restaurar.gridx = 0;

		gbc_restaurar.gridy = 3;

		this.panel.add(restaurar, gbc_restaurar);

		SimpleButton copiar = new SimpleButton("");

		copiar.addMouseWheelListener(new MouseWheelListener() {

			@Override

			public void mouseWheelMoved(MouseWheelEvent e) {

				Mthos.copy(texto.getText());

			}

		});

		copiar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Mthos.copy(texto.getText());

			}

		});

		copiar.setIcon(new ImageIcon(CopyTextAreaScroll.class.getResource("/imgs/imagenes/copy.png")));

		GridBagConstraints gbc_copiar = new GridBagConstraints();

		gbc_copiar.insets = new Insets(0, 0, 5, 0);

		gbc_copiar.gridx = 0;

		gbc_copiar.gridy = 4;

		panel.add(copiar, gbc_copiar);

		SimpleButton limpiar = new SimpleButton("");

		limpiar.addMouseWheelListener(new MouseWheelListener() {

			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {

				texto.clean();

			}

		});

		limpiar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				texto.clean();

			}

		});

		limpiar.setIcon(new ImageIcon(CopyTextAreaScroll.class.getResource("/imgs/imagenes/clean.png")));

		GridBagConstraints gbc_limpiar = new GridBagConstraints();

		gbc_limpiar.insets = new Insets(0, 0, 5, 0);

		gbc_limpiar.gridx = 0;

		gbc_limpiar.gridy = 5;

		panel.add(limpiar, gbc_limpiar);

		add(this.texto, BorderLayout.CENTER);

	}

}