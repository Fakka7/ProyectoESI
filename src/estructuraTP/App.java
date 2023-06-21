package estructuraTP;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import estructuraTP.vista.MaterialPanel;
import estructuraTP.vista.PantallaInicio;
import estructuraTP.vista.PropuestaPanel;
import estructuraTP.vista.PropuestasPendientesPanel;




public class App {
	
	public static void main(String[] args) {
		
		JFrame marco = new JFrame();
		marco.setBounds(0, 0, 800, 600);
		marco.setVisible(true);
		marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		marco.setContentPane(new PantallaInicio());
		
		marco.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		
		
		JMenuBar menuBar = new JMenuBar();
		marco.setJMenuBar(menuBar);
		
		JMenu mnNewMenu_0 = new JMenu("Propuestas");
		menuBar.add(mnNewMenu_0);
		
		
		JMenuItem verPropuestas = new JMenuItem("Ver Propuestas");
		mnNewMenu_0.add(verPropuestas);
		verPropuestas.addActionListener(new ActionListener() {

			
			@Override
			public void actionPerformed(ActionEvent e) {
				marco.setContentPane(new PropuestaPanel());
				marco.validate();
			}
		});

		JMenuItem propuestasPendientes = new JMenuItem("Propuestas Pendientes");
		mnNewMenu_0.add(propuestasPendientes);

		propuestasPendientes.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				marco.setContentPane(new PropuestasPendientesPanel());
				marco.validate();
			}
		});
		

	
		JMenu mnNewMenu_1 = new JMenu("Material");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem verMateriales = new JMenuItem("Ver Materiales");
        mnNewMenu_1.add(verMateriales);

        verMateriales.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                marco.setContentPane(new MaterialPanel());
                marco.validate();
            }
        });
		
		
		JMenu mnNewMenu_1_1 = new JMenu("Jornadas");
		menuBar.add(mnNewMenu_1_1);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Crear Jornada");
		mnNewMenu_1_1.add(mntmNewMenuItem_4);
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Consultar Jornadas");
		mnNewMenu_1_1.add(mntmNewMenuItem_5);
		
		marco.validate();
	}

}