package estructuraTP.vista;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import estructuraTP.dao.MatPropuestaDAO;
import estructuraTP.dao.PropuestaDAO;
import estructuraTP.modelo.MaterialPropuestas;
import estructuraTP.modelo.Propuesta;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class MaterialAsignarPropuestasPanel extends JPanel {

	/* 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private ArrayList<Propuesta> propuestas;

	/*
	 * Create the panel.
	 */
	public MaterialAsignarPropuestasPanel(MaterialPropuestas m) {

		setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 42, 580, 159);
		add(scrollPane);

		table = new JTable();
		DefaultTableModel dataModel = new DefaultTableModel(new Object[][] {}, new String[] { "Titulo", "Origen",
				"Categoria", "Autor", "Fecha", "Descripcion", "Motivacion", "Estado" });
		table.setModel(dataModel);
		scrollPane.setViewportView(table);
		cargarTablaAceptadasPorCategoria(dataModel, m);

		JButton botonAgregarPropuesta = new JButton("Agregar Propuesta");
		botonAgregarPropuesta.setBounds(62, 442, 125, 33);
		add(botonAgregarPropuesta);
		botonAgregarPropuesta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				
				Propuesta e = obtenerPropuestaSeleccionada();
				MatPropuestaDAO mpDAO = new MatPropuestaDAO();
				mpDAO.asignar(m, e);
			}
		});

		JButton botonGuardarMaterial = new JButton("Guardar");
		botonGuardarMaterial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {

				 JFrame marco = (JFrame) SwingUtilities.getWindowAncestor((JComponent)
				 ae.getSource()); marco.setContentPane(new MaterialPanel());
				 marco.validate();
			}
		});
		botonGuardarMaterial.setBounds(531, 442, 125, 33);
		add(botonGuardarMaterial);

	}

	private void cargarTablaAceptadasPorCategoria(DefaultTableModel dataModel, MaterialPropuestas m) {

		dataModel.setRowCount(0);

		PropuestaDAO eDao = new PropuestaDAO();
		propuestas = eDao.traerTodasLasAceptadasPorCategoria(m);
		for (Propuesta e : propuestas) {
			Object[] fila = new Object[] { e.getTitulo(), e.getOrigen(), e.getCategoria(), e.getAutor(), e.getFecha(),
					e.getDescripcion(), e.getMotivacion(), e.getEstado(), e.getMotivoRechazo() };
			dataModel.addRow(fila);

		}
	}
	private Propuesta obtenerPropuestaSeleccionada() {
		// TODO Mejorar (evitar relacionar el �ndice de la tabla con el �ndice del ArrayList)
		int filaSeleccionada = table.getSelectedRow();
		return propuestas.get(filaSeleccionada);
	}
}