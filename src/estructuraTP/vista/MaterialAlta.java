package estructuraTP.vista;


import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import estructuraTP.dao.CategoriaDAO;
import estructuraTP.dao.MaterialDAO;
import estructuraTP.modelo.Material;
import estructuraTP.modelo.MaterialPropuestas;

public class MaterialAlta extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JTextField txtEnlace;
	private JTextField txtFuente;
	private JTextField txtTitulo;
	
	private Material material;
	private JTextField txtDescripcion;
	private JTextField txtProcedencia;
	private JButton botonGuardarMaterial;
	
	private JComboBox<String> choiceCategoria;

	/**
	 * Create the panel.
	 */
	public MaterialAlta() {
		
		setLayout(null);
		

		JLabel lblNewLabel = new JLabel("Titulo:");
		lblNewLabel.setToolTipText("");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(10, 33, 159, 22);
		add(lblNewLabel);

		JLabel lblDescripcion = new JLabel("Descripcion:");
		lblDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDescripcion.setBounds(10, 271, 159, 22);
		add(lblDescripcion);

		JLabel lblFuente = new JLabel("Fuente:");
		lblFuente.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblFuente.setBounds(10, 88, 159, 22);
		add(lblFuente);

		JLabel lblEnlaceAlDocumento = new JLabel("Enlace al documento:");
		lblEnlaceAlDocumento.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEnlaceAlDocumento.setBounds(10, 147, 159, 22);
		add(lblEnlaceAlDocumento);

		JLabel lblCa = new JLabel("Categoria:");
		lblCa.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCa.setBounds(10, 210, 159, 22);
		add(lblCa);

		choiceCategoria = new JComboBox<>();
		choiceCategoria.setEditable(false);
		choiceCategoria.setBounds(213, 206, 223, 35);
		add(choiceCategoria);
		
		CategoriaDAO cDao = new CategoriaDAO();
		for (String categoria : cDao.traerTodas()) {
			choiceCategoria.addItem(categoria);
		}

		txtEnlace = new JTextField();
		txtEnlace.setBounds(213, 144, 497, 33);
		add(txtEnlace);
		txtEnlace.setColumns(10);

		txtFuente = new JTextField();
		txtFuente.setColumns(10);
		txtFuente.setBounds(213, 85, 497, 33);
		add(txtFuente);

		txtTitulo = new JTextField();
		txtTitulo.setColumns(10);
		txtTitulo.setBounds(213, 30, 497, 33);
		add(txtTitulo);

		
		
		txtDescripcion = new JTextField();
		txtDescripcion.setColumns(10);
		txtDescripcion.setBounds(213, 274, 497, 33);
		add(txtDescripcion);
		
		txtProcedencia = new JTextField();
		txtProcedencia.setColumns(10);
		txtProcedencia.setBounds(213, 344, 497, 33);
		add(txtProcedencia);
		
		JLabel lblProcedencia = new JLabel("Procedencia:");
		lblProcedencia.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblProcedencia.setBounds(10, 344, 159, 22);
		add(lblProcedencia);
		
		JButton botonVolver = new JButton("Volver");
		botonVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				JFrame marco = 
						(JFrame) SwingUtilities.getWindowAncestor((JComponent) ae.getSource());
				marco.setContentPane(new MaterialPanel());
				marco.validate();
			}
		});
		botonVolver.setBounds(577, 487, 133, 50);
		add(botonVolver);
		
		botonGuardarMaterial = new JButton("Crear Material");
		botonGuardarMaterial.setBounds(111, 487, 133, 50);
		add(botonGuardarMaterial);
		
		botonGuardarMaterial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {			
		
				String titulo = txtTitulo.getText();
				String categoria = choiceCategoria.getSelectedItem().toString();
				String descripcion = txtDescripcion.getText();
				String fuente = txtFuente.getText();
				String enlace = txtEnlace.getText();
				String procedencia = txtProcedencia.getText();
				
				
				MaterialDAO eDao = new MaterialDAO();
				if (esEdicion()) {
					// Es una modificacion
					Material nuevo = new MaterialPropuestas(titulo, categoria, descripcion, fuente, enlace, "On Going", procedencia);
					eDao.modificar(nuevo, material.getTitulo());
				} else {
					// Es un alta.
					Material material = new MaterialPropuestas(titulo, categoria, descripcion, fuente, enlace, "No Prioritario", procedencia);
					
					eDao.guardar(material);
				}
				
				desactivarCampos();
			}
		});
		
		

	}
	
	public MaterialAlta(Material e) {
		// Ejecuto primero el constructor sin argumentos para que se contruya la pantalla.
		this();
		// Cargo los datos del objeto estudiante en los campos de texto.
		

		txtTitulo.setText("" + e.getTitulo());
		txtDescripcion.setText("" + e.getDescripcion());
		txtFuente.setText("" + e.getFuente());
		txtEnlace.setText("" + e.getEnlace());
		
		this.material = e;
		
	}
	public boolean esEdicion() {
		return this.material != null;
		
	}
	public void desactivarCampos() {
		
		txtTitulo.setEditable(false);
		txtTitulo.setEnabled(false);
		txtFuente.setEditable(false);
		txtFuente.setEnabled(false);
		txtEnlace.setEditable(false);
		txtEnlace.setEnabled(false);
		txtDescripcion.setEditable(false);
		txtDescripcion.setEnabled(false);
		txtProcedencia.setEditable(false);
		txtProcedencia.setEnabled(false);
		choiceCategoria.setEditable(false);
		choiceCategoria.setEnabled(false);
		botonGuardarMaterial.setEnabled(false);
		
	}
}