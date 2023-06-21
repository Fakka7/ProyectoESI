package estructuraTP.vista;

import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import estructuraTP.dao.MatPropuestaDAO;
import estructuraTP.dao.MaterialDAO;
import estructuraTP.modelo.Material;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JLabel;

public class MaterialPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private ArrayList<Material> materiales;

	/**
	 * Create the panel.
	 */
	public MaterialPanel() {
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 42, 580, 159);
		add(scrollPane);
		
		table = new JTable();
		DefaultTableModel dataModel = new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"Titulo", "Categoria", "Descripcion", "Fuente", "Enlace", "Tratamiento", "Procedencia"
			}
		);
		table.setModel(dataModel);
		scrollPane.setViewportView(table);
		
		JButton btnNuevo = new JButton("Nuevo Material");
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				JFrame marco = 
						(JFrame) SwingUtilities.getWindowAncestor((JComponent) ae.getSource());
				marco.setContentPane(new MaterialAlta());
				marco.validate();
			}
		});
		btnNuevo.setBounds(618, 90, 183, 23);
		add(btnNuevo);
		
		JButton btnEliminar = new JButton("Eliminar Material");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				
				Material m = obtenerMaterialSeleccionado();
				MaterialDAO mDao = new MaterialDAO();
				MatPropuestaDAO mpDao = new MatPropuestaDAO();
				mpDao.eliminar(m);
				mDao.eliminar(m.getTitulo());
				cargarTabla(dataModel);
			}
		});
		btnEliminar.setBounds(618, 158, 183, 23);
		add(btnEliminar);
		
		JButton btnModificar = new JButton("Modificar Material");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				Material e = obtenerMaterialSeleccionado();
				JFrame marco = 
						(JFrame) SwingUtilities.getWindowAncestor((JComponent) ae.getSource());
				marco.setContentPane(new MaterialMod(e));
				marco.validate();

			}
		});
		btnModificar.setBounds(618, 124, 183, 23);
		add(btnModificar);
		
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
		btnPropuestasAsignadas.setBounds(618, 56, 183, 23);
		add(btnPropuestasAsignadas);
		
		JLabel title = new JLabel("TODOS Los Materiales");
		title.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 13));
		title.setBounds(10, 17, 145, 14);
		add(title);

		cargarTabla(dataModel);

		
	}

	private void cargarTabla(DefaultTableModel dataModel) {
		
		dataModel.setRowCount(0);
		
		MaterialDAO mDao = new MaterialDAO();
		materiales = mDao.traerTodos();
		for (Material e : materiales) {

			Object[] fila = new Object[] {e.getTitulo(), e.getCategoria(), e.getDescripcion(), e.getFuente(), e.getEnlace(), e.getTratamiento(), e.getProcedencia()};
			dataModel.addRow(fila);
		}
	}

	private Material obtenerMaterialSeleccionado() {
		// TODO Mejorar (evitar relacionar el indice de la tabla con el �ndice del ArrayList)
		int filaSeleccionada = table.getSelectedRow();
		return materiales.get(filaSeleccionada);
	}
}