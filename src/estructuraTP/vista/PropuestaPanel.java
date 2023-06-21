package estructuraTP.vista;

import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;

import estructuraTP.dao.PropuestaDAO;
import estructuraTP.modelo.Propuesta;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PropuestaPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private ArrayList<Propuesta> propuestas;

	/**
	 * Create the panel.
	 */
	public PropuestaPanel() {
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 42, 580, 159);
		add(scrollPane);
		
		table = new JTable();
		DefaultTableModel dataModel = new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"Titulo", "Origen", "Categoria", "Autor", "Fecha", "Descripcion", "Motivacion", "Estado", "MotivoRechazo"
			}
		);
		table.setModel(dataModel);
		scrollPane.setViewportView(table);
		
		JButton btnNuevo = new JButton("Nuevo");
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				JFrame marco = 
						(JFrame) SwingUtilities.getWindowAncestor((JComponent) ae.getSource());
				marco.setContentPane(new PropuestaAltaMod());
				marco.validate();
			}
		});
		btnNuevo.setBounds(101, 247, 89, 23);
		add(btnNuevo);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				
				Propuesta e = obtenerPropuestaSeleccionada();
				PropuestaDAO eDao = new PropuestaDAO();
				eDao.eliminar(e.getTitulo());
				cargarTabla(dataModel);
			}
		});
		btnEliminar.setBounds(416, 247, 89, 23);
		add(btnEliminar);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				Propuesta e = obtenerPropuestaSeleccionada();
				JFrame marco = 
						(JFrame) SwingUtilities.getWindowAncestor((JComponent) ae.getSource());
				marco.setContentPane(new PropuestaAltaMod(e));
				marco.validate();

			}
		});
		btnModificar.setBounds(260, 247, 89, 23);
		add(btnModificar);

		cargarTabla(dataModel);

		
	}

	private void cargarTabla(DefaultTableModel dataModel) {
		
		dataModel.setRowCount(0);
		
		PropuestaDAO eDao = new PropuestaDAO();
		propuestas = eDao.traerTodas();
		for (Propuesta e : propuestas) {

			Object[] fila = new Object[] {e.getTitulo(), e.getOrigen(), e.getCategoria(), e.getAutor(), e.getFecha(), e.getDescripcion(), e.getMotivacion(), e.getEstado(), e.getMotivoRechazo()};
			dataModel.addRow(fila);
		}
	}

	private Propuesta obtenerPropuestaSeleccionada() {
		// TODO Mejorar (evitar relacionar el indice de la tabla con el �ndice del ArrayList)
		int filaSeleccionada = table.getSelectedRow();
		return propuestas.get(filaSeleccionada);
	}
}