package com.comboBox.language;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import org.jdesktop.swingx.HorizontalLayout;

import com.buttons.round.RoundedButton;
import com.language.Language;
import com.spinner.simple.Spinner;

import diaglog.MessageDialog;
import drag_and_drop.DragAndDrop;
import radio_button.RadioButtonCustom;
import util.Mthos;

@SuppressWarnings({ "serial", "rawtypes" })

public class ComboBoxLanguage extends JComboBox {

	public JComboBox<Integer> box;

	boolean cambio;

	static Map<String, String> translations;

	private int cambioIdioma;

	static byte[] bytes;

	Language language;

	static ArrayList<Language> idiomas;

	Integer[] array;

	ArrayList<Language> listaIdiomas;

	public JComboBox<Integer> getBox() {
		return box;
	}

	ImageIcon[] imageIcon = { new ImageIcon(getClass().getResource("/imgs/flags/1.png")),

			new ImageIcon(getClass().getResource("/imgs/flags/2.png")),

			new ImageIcon(getClass().getResource("/imgs/flags/3.png")),

			new ImageIcon(getClass().getResource("/imgs/flags/4.png")),

			new ImageIcon(getClass().getResource("/imgs/flags/5.png")),

			new ImageIcon(getClass().getResource("/imgs/flags/6.png")),

			new ImageIcon(getClass().getResource("/imgs/flags/7.png")),

			new ImageIcon(getClass().getResource("/imgs/flags/8.png")),

			new ImageIcon(getClass().getResource("/imgs/flags/9.png")),

			new ImageIcon(getClass().getResource("/imgs/flags/10.png")),

			new ImageIcon(getClass().getResource("/imgs/flags/11.png")),

			new ImageIcon(getClass().getResource("/imgs/flags/12.png")),

			new ImageIcon(getClass().getResource("/imgs/flags/13.png")),

			new ImageIcon(getClass().getResource("/imgs/flags/14.png")),

			new ImageIcon(getClass().getResource("/imgs/flags/15.png")),

			new ImageIcon(getClass().getResource("/imgs/flags/16.png")),

			new ImageIcon(getClass().getResource("/imgs/flags/17.png")),

			new ImageIcon(getClass().getResource("/imgs/flags/18.png"))

	};

	@Override

	public int getSelectedIndex() {

		int resultado = 0;

		if (box != null) {

			resultado = box.getSelectedIndex();

		}

		return resultado;

	}

	public static void translateSpinner(Spinner drgndrpDragAndDrop, String function, Language language) {

		revisarIdioma();

		if (!translations.isEmpty() && translations.get(language + "." + function) != null) {

			drgndrpDragAndDrop.setLabelText(traducirTexto(translations.get(language + "." + function)));

		}

	}

	private static String traducirTexto(String string) {

		bytes = string.getBytes(StandardCharsets.UTF_8);

		return new String(bytes, StandardCharsets.UTF_8);

	}

	public static void translateMessageDialog(MessageDialog drgndrpDragAndDrop, String title, String msg,
			Language language) {

		revisarIdioma();

		if (!translations.isEmpty() && translations.get(language + "." + msg) != null) {

			drgndrpDragAndDrop.showMessage(traducirTexto(translations.get(language + "." + title)),
					traducirTexto(translations.get(language + "." + msg)));

		}

	}

	public static String translateString(String function, Language language) {

		revisarIdioma();

		String resultado = "";

		if (!translations.isEmpty() && translations.get(language + "." + function) != null) {

			resultado = traducirTexto(translations.get(language + "." + function));

		}

		return resultado;

	}

	public static void translateButtonRoundedWithImage(RoundedButton drgndrpDragAndDrop, String function,
			Language language) {

		revisarIdioma();

		if (!translations.isEmpty() && translations.get(language + "." + function) != null) {

			drgndrpDragAndDrop.setText(traducirTexto(translations.get(language + "." + function)));

		}

	}

	public static void translateJlabel(JLabel drgndrpDragAndDrop, String function, Language language) {

		revisarIdioma();

		if (!translations.isEmpty() && translations.get(language + "." + function) != null) {

			drgndrpDragAndDrop.setText(traducirTexto(translations.get(language + "." + function)));

		}

	}

	public static void translateJButton(JButton drgndrpDragAndDrop, String function, Language language) {

		revisarIdioma();

		if (!translations.isEmpty() && translations.get(language + "." + function) != null) {

			drgndrpDragAndDrop.setText(traducirTexto(translations.get(language + "." + function)));

		}

	}

	public static void translateRadioButtonCustom(RadioButtonCustom drgndrpDragAndDrop, String function,
			Language language) {

		revisarIdioma();

		if (!translations.isEmpty() && translations.get(language + "." + function) != null) {

			drgndrpDragAndDrop.setText(traducirTexto(translations.get(language + "." + function)));

		}

	}

	public static void translateDragAndDrop(DragAndDrop drgndrpDragAndDrop, String function, Language language) {

		revisarIdioma();

		if (!translations.isEmpty() && translations.get(language + "." + function) != null) {

			drgndrpDragAndDrop.setText(traducirTexto(translations.get(language + "." + function)));

		}

	}

	public static void revisarIdioma() {

		try {

			if (translations == null) {

				translations = new HashMap<>();

				leer();

			}

		}

		catch (Exception e) {

		}

	}

	public static void leer() throws IOException {

		Mthos.crearCarpeta(Mthos.rutaActual() + "Languages");

		LinkedList<String> archivos = (LinkedList<String>) Mthos
				.directorio(Mthos.rutaActual() + "Languages" + Mthos.saberSeparador(), "po", false);

		String texto = "";

		String idiomaDefault = "";

		FileReader flE = null;

		BufferedReader fE = null;

		String cabecera = "";

		String traduccion = "";

		String textoTraducido = "";

		for (int i = 0; i < archivos.size(); i++) {

			try {

				flE = new FileReader(archivos.get(i));

				fE = new BufferedReader(flE);

				texto = fE.readLine();

				while (texto != null) {

					if (texto.contains("#: fmain.")) {

						try {

							cabecera = texto.substring(9, texto.length());

							texto = fE.readLine();

							idiomaDefault = texto;

							idiomaDefault = idiomaDefault.substring(7, idiomaDefault.length() - 1);

							texto = fE.readLine();

							traduccion = texto;

							traduccion = traduccion.substring(8, traduccion.length() - 1);

							if (traduccion.isEmpty()) {

								textoTraducido = idiomaDefault;
							}

							else {

								textoTraducido = traduccion;

							}

						}

						catch (Exception e) {

						}

						translations
								.put(archivos.get(i).substring(archivos.get(i).lastIndexOf(Mthos.saberSeparador()) + 1,
										archivos.get(i).length() - 3) + "." + cabecera, textoTraducido);

					}

					texto = fE.readLine();

				}

				fE.close();

				flE.close();

			}

			catch (Exception e) {
				//
			}

			finally {

				if (fE != null) {

					try {

						fE.close();

					}

					catch (IOException e) {
						//
					}

				}

				if (flE != null) {

					try {

						flE.close();

					}

					catch (IOException e) {
						//
					}

				}

			}

		}

	}

	@SuppressWarnings("static-access")
	public ComboBoxLanguage(JFrame frame, Language mainLanguage, Language[] list) throws IOException {

		cambioIdioma = 1;

		this.cambio = false;

		this.translations = new HashMap<>();

		this.listaIdiomas = new ArrayList<>();

		this.listaIdiomas.add(Language.ESPAÑOL);

		this.listaIdiomas.add(Language.DEUTSCH);

		this.listaIdiomas.add(Language.ENGLISH);

		this.listaIdiomas.add(Language.FRANÇAIS);

		this.listaIdiomas.add(Language.РУССКИЙ);

		this.listaIdiomas.add(Language.ITALIANO);

		this.listaIdiomas.add(Language.PORTUGUÊS);

		this.listaIdiomas.add(Language.中國人);

		this.listaIdiomas.add(Language.HINDI);

		this.listaIdiomas.add(Language.日本);

		this.listaIdiomas.add(Language.CATALÀ);

		this.listaIdiomas.add(Language.কোরিয়ান);

		this.listaIdiomas.add(Language.عرب);

		this.listaIdiomas.add(Language.EUSKARA);

		this.listaIdiomas.add(Language.한국어);

		this.listaIdiomas.add(Language.TIẾNG_VIỆT);

		this.listaIdiomas.add(Language.POLSKIE);

		this.listaIdiomas.add(Language.GALEGO);

		this.idiomas = new ArrayList<>();

		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		Container con = frame.getContentPane();

		ComboBoxRenderar rendrar = new ComboBoxRenderar();

		setBackground(Color.WHITE);

		setBorder(new EmptyBorder(15, 3, 5, 3));

		try {

			this.language = mainLanguage;

			verIdiomas(list);

			ponerIdioma(this.language);

			leer();

		}

		catch (Exception e) {

		}

		this.box.addItemListener(null);

		this.box.setRenderer(rendrar);

		con.add(this.box);

		con.setLayout(new HorizontalLayout());

		setVisible(false);

		frame.setVisible(false);

		frame.pack();

		this.box.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {

				if (cambioIdioma % 2 == 0) {

					cambio = true;

				}

				cambioIdioma++;

				if (cambioIdioma == 2) {

					cambioIdioma = 0;

				}

			}

		});

		this.box.addMouseWheelListener(new MouseWheelListener() {

			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {

				try {

					int indice = box.getSelectedIndex();

					if (e.getWheelRotation() < 0) {

						indice--;

						if (indice < 0) {

							indice = 0;

						}

					}

					else {

						indice++;

						if (indice >= box.getItemCount()) {

							indice = box.getItemCount();

							indice--;

						}

					}

					box.setSelectedIndex(indice);

				}

				catch (Exception e1) {

				}

			}

		});

	}

	public Language getLanguage() {

		return idiomas.get(box.getSelectedIndex());

	}

	public String getStringLanguage() {

		byte[] bytes = this.language.toString().getBytes(StandardCharsets.UTF_8);

		return new String(bytes, StandardCharsets.UTF_8);

	}

	public void setLanguage(Language language) {

		this.language = language;

		ponerIdioma(language);

	}

	@SuppressWarnings("static-access")
	public Language setLanguage(int index) {

		this.language = this.idiomas.get(index);

		return this.language;

	}

	@SuppressWarnings("static-access")
	public void verIdiomas(Language[] list) {

		if (list == null) {

			this.array = new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18 };

			this.idiomas = this.listaIdiomas;

		}

		else {

			this.array = new Integer[list.length];

			int y = 0;

			for (int i = 1; i <= list.length; i++) {

				this.array[y] = this.listaIdiomas.indexOf(list[y]) + 1;

				this.idiomas.add(list[y]);

				y++;

			}

		}

		this.box = new JComboBox<>(this.array);

	}

	void ponerIdioma(Language language) {

		this.box.setSelectedItem(idiomas.indexOf(language) + 1);

	}

	public class ComboBoxRenderar extends JLabel implements ListCellRenderer<Object> {

		private static final long serialVersionUID = 1L;

		@Override

		public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
				boolean cellHasFocus) {

			int offset = ((Integer) value).intValue() - 1;

			ImageIcon icon = imageIcon[offset];

			setIcon(icon);

			if (index < 0) {

				index = box.getSelectedIndex();

			}

			cambio = false;

			return this;

		}

	}

}
