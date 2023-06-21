package estructuraTP.vista;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;

public class PantallaInicio extends JPanel {

	private static final long serialVersionUID = 1L;

	public PantallaInicio() {
		
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("JORNADA ESI");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblNewLabel.setBounds(145, 126, 173, 55);
		add(lblNewLabel);

	}
}
