package minecraftskinandresourcesfixer;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class LogWindow extends JFrame implements SuperSimpleLogger {
	
	private JTextArea text;
	private JScrollPane scroll;
	
	public LogWindow() {
		super("Minecraft Skin And Resources Fixer " + SkinFixer2Main.VERSION);
		text = new JTextArea();
		text.setFont(new Font("Monospaced", 0, 12));
		text.setEditable(false);
		scroll = new JScrollPane(text, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		setLayout(new BorderLayout());
		add(scroll, BorderLayout.CENTER);
		setSize(600, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		revalidate();
		repaint();
	}

	@Override
	public void log(String s) {
		Calendar c = Calendar.getInstance();
		String timestamp = String.format("[%d-%02d-%02d %02d:%02d:%02d.%03d] ",
				c.get(Calendar.YEAR),
				c.get(Calendar.MONTH) + 1,
				c.get(Calendar.DAY_OF_MONTH),
				c.get(Calendar.HOUR_OF_DAY),
				c.get(Calendar.MINUTE),
				c.get(Calendar.SECOND),
				c.get(Calendar.MILLISECOND));
		System.out.println(timestamp + s);
		if(text.getText().length() == 0) {
			text.setText(timestamp + s);
		} else {
			text.setText(text.getText() + '\n' + timestamp + s);
		}
		scroll.getVerticalScrollBar().setValue(scroll.getVerticalScrollBar().getMaximum());
	}

}
