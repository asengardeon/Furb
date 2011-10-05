package Interfaces;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Element;

public class LineNumbering extends JScrollPane {

	private static final long serialVersionUID = 1L;
	private static JTextArea jta;
	private static JTextArea lines;
	private boolean modify = false;

	public LineNumbering() {
		jta = new JTextArea();
		/**
		 * O editor ja come�ar� com a marca��o de "1" linha. Este valor ser�
		 * utilizado mais para frente para referencia do contador de linhas. N�O
		 * ALTERAR.
		 * */
		lines = new JTextArea("1");

		lines.setBackground(Color.LIGHT_GRAY);
		/**
		 * altera o tanao inicial do painel que mostra o numero de linhas. Assim
		 * n�o fica com um painel muito estreito logo de cara
		 */
		lines.setPreferredSize(new Dimension(35, lines.getHeight()));
		// como � area de textoo n�o pode deixar o usu�rio escrever nela
		lines.setEditable(false);

		jta.getDocument().addDocumentListener(new DocumentListener() {
			/**
			 * O gettext abaixo far� a "m�gica", para identificar a quantidade
			 * de linhas que dever�o ser apresentadas"
			 */
			public String getText() {
				int caretPosition = jta.getDocument().getLength();
				Element root = jta.getDocument().getDefaultRootElement();
				String text = "1" + System.getProperty("line.separator");
				for (int i = 2; i < root.getElementIndex(caretPosition) + 2; i++) {
					text += i + System.getProperty("line.separator");
				}
				return text;
			}

			@Override
			public void changedUpdate(DocumentEvent de) {
				lines.setText(getText());
			}

			@Override
			public void insertUpdate(DocumentEvent de) {
				lines.setText(getText());
				modify = true;
			}

			@Override
			public void removeUpdate(DocumentEvent de) {
				lines.setText(getText());
			}

		});
		this.getViewport().add(jta);
		this.setRowHeaderView(lines);
		this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	}


	public boolean isModify() {
		return modify;
	}

	public void setModify(boolean modify) {
		this.modify = modify;
	}


	public JTextArea getEditor(){
		return jta;
	}
}
