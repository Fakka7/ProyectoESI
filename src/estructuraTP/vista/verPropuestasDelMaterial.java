package estructuraTP.vista;

import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import estructuraTP.dao.MaterialDAO;
import estructuraTP.modelo.Material;
import estructuraTP.modelo.Propuesta;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JLabel;

public class verPropuestasDelMaterial extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private ArrayList<Propuesta> propuestas;
	

	/**
	 * Create the panel.
	 */
	public verPropuestasDelMaterial(Material m) {
		
		
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 42, 580, 159);
		add(scrollPane);
		
		table = new JTable();
		DefaultTableModel dataModel = new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"Titulo", "Origen", "Categoria", "Autor", "Fecha", "Descripcion", "Motivacion", "Estado"
			}
		);
		table.setModel(dataModel);
		scrollPane.setViewportView(table);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				
				JFrame marco = 
						(JFrame) SwingUtilities.getWindowAncestor((JComponent) ae.getSource());
				marco.setContentPane(new MaterialPanel());
				marco.validate();
			}
		});
		btnVolver.setBounds(618, 118, 183, 23);
		add(btnVolver);
		
		JButton btnPropuestasAsignadas = new JButton("Filtrar: Materiales (Propuestas)");
		btnPropuestasAsignadas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				
				JFrame marco = 
						(JFrame) SwingUtilities.getWindowAncestor((JComponent) ae.getSource());
				marco.setContentPane(new MaterialesPropuestasPanel());
				marco.validate();
				
			}
		});
		btnPropuestasAsignadas.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnPropuestasAsignadas.setBounds(618, 84, 183, 23);
		add(btnPropuestasAsignadas);
		
		JLabel title = new JLabel("TODAS Las Propuestas Del Material");
		title.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 13));
		title.setBounds(10, 17, 183, 14);
		add(title);

		cargarTabla(dataModel, m);

		
	}

	private void cargarTabla(DefaultTableModel dataModel, Material m) {
		
		dataModel.setRowCount(0);
		
		MaterialDAO mDao = new MaterialDAO();
		propuestas = mDao.traerTodasLasPropuestasDelMaterial(m);
		for (Propuesta e : propuestas) {

			Object[] fila = new Object[] {e.getTitulo(), e.getOrigen(), e.getCategoria(), e.getAutor(), e.getFecha(), e.getDescripcion(), e.getMotivacion(), e.getEstado(), e.getMotivoRechazo()};
			dataModel.addRow(fila);
		}
	}


}