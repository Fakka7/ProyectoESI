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
import estructuraTP.dao.PropuestaDAO;
import estructuraTP.modelo.Propuesta;

import java.awt.Color;


public class PropuestaAltaMod extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private JTextField txtAutor;
	private JTextField txtTitulo;
	private JTextField txtOrigen;
	private JTextField txtFecha;
	private JTextField txtBrebeDescripcion;
	private JTextField txtMotivacion;
	private JButton botonCrearPropuesta;
	
	
	private Propuesta propuesta;
	JComboBox<String> choiceCategoria;
	

	/**
	 * Create the panel.
	 */
	public PropuestaAltaMod() {

		setLayout(null);
		
		JLabel lblOrigen = new JLabel("Origen:");
		lblOrigen.setToolTipText("");
		lblOrigen.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblOrigen.setBounds(10, 33, 159, 22);
		add(lblOrigen);
		
		JLabel lblMotivacion = new JLabel("Motivación:");
		lblMotivacion.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMotivacion.setBounds(10, 381, 159, 22);
		add(lblMotivacion);
		
		JLabel lblTitulo = new JLabel("Título:");
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTitulo.setBounds(10, 88, 159, 22);
		add(lblTitulo);
		
		JLabel lblFechaPropuesta = new JLabel("Fecha:");
		lblFechaPropuesta.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblFechaPropuesta.setBounds(10, 203, 159, 22);
		add(lblFechaPropuesta);
		
		JLabel lblCategoria = new JLabel("Categoría:");
		lblCategoria.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCategoria.setBounds(10, 325, 159, 22);
		add(lblCategoria);
		
		choiceCategoria = new JComboBox<>();
		choiceCategoria.setBounds(213, 327, 223, 35);
		add(choiceCategoria);
		
		CategoriaDAO cDao = new CategoriaDAO();
		for (String categoria : cDao.traerTodas()) {
			choiceCategoria.addItem(categoria);
		}
		
		txtAutor = new JTextField();
		txtAutor.setBounds(213, 142, 497, 33);
		add(txtAutor);
		txtAutor.setColumns(10);
		
		txtTitulo = new JTextField();
		txtTitulo.setColumns(10);
		txtTitulo.setBounds(213, 85, 497, 33);
		add(txtTitulo);
		
		txtOrigen = new JTextField();
		txtOrigen.setColumns(10);
		txtOrigen.setBounds(213, 30, 497, 33);
		add(txtOrigen);
		
		botonCrearPropuesta = new JButton("Guardar Propuesta");
		botonCrearPropuesta.setBounds(213, 491, 133, 50);
		add(botonCrearPropuesta);
		botonCrearPropuesta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
								
				
				String origen = txtOrigen.getText();
				String titulo = txtTitulo.getText();
				String autor = txtAutor.getText();
				String fecha = txtFecha.getText();
				String descripcion = txtBrebeDescripcion.getText();
				String categoria = choiceCategoria.getSelectedItem().toString();
				String motivacion = txtMotivacion.getText();
				
				PropuestaDAO eDao = new PropuestaDAO();
				
				if (esEdicion()) {
					// Es una modificacion
					Propuesta nuevo = new Propuesta(titulo, origen, categoria, autor, fecha, descripcion, motivacion, propuesta.getEstado(), "");
					eDao.modificar(nuevo, propuesta.getTitulo());
				} else {
					// Es un alta.
					Propuesta propuesta = new Propuesta(titulo, origen, categoria, autor, fecha, descripcion, motivacion, "Pendiente", "");
					
					eDao.guardar(propuesta);
				}
				
				desactivarCampos();
			}
		});
		
		JLabel lblBreveDescripcion = new JLabel("Breve Descripcion:");
		lblBreveDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblBreveDescripcion.setBounds(10, 257, 159, 22);
		add(lblBreveDescripcion);
		
		txtFecha = new JTextField();
		txtFecha.setForeground(new Color(192, 192, 192));
		txtFecha.setText("aaaa-mm-dd");
		txtFecha.setColumns(10);
		txtFecha.setBounds(213, 200, 497, 33);
		add(txtFecha);
		
		JLabel lblAutor = new JLabel("Autor (opcional):");
		lblAutor.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAutor.setBounds(10, 145, 159, 22);
		add(lblAutor);
		
		txtBrebeDescripcion = new JTextField();
		txtBrebeDescripcion.setColumns(10);
		txtBrebeDescripcion.setBounds(213, 254, 497, 33);
		add(txtBrebeDescripcion);
		
		txtMotivacion = new JTextField();
		txtMotivacion.setColumns(10);
		txtMotivacion.setBounds(213, 384, 497, 33);
		add(txtMotivacion);
		
		JButton botonVolver = new JButton("Volver");
		botonVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				JFrame marco = 
						(JFrame) SwingUtilities.getWindowAncestor((JComponent) ae.getSource());
				marco.setContentPane(new PropuestaPanel());
				marco.validate();
			}
		});
		botonVolver.setBounds(577, 491, 133, 50);
		add(botonVolver);
		
		

	}
	
	public PropuestaAltaMod(Propuesta e) {
		// Ejecuto primero el constructor sin argumentos para que se contruya la pantalla.
		this();
		// Cargo los datos del objeto estudiante en los campos de texto.
		
		txtOrigen.setText("" + e.getOrigen());
		txtTitulo.setText("" + e.getTitulo());
		
		
		txtAutor.setText("" + e.getAutor());
		txtFecha.setText("" + e.getFecha());
		txtBrebeDescripcion.setText("" + e.getDescripcion());
		txtMotivacion.setText("" + e.getMotivacion());
		choiceCategoria.setSelectedItem(e.getCategoria());
		
		
		this.propuesta = e;
		
	}
	public boolean esEdicion() {
		return this.propuesta != null;
		
	}
	
public void desactivarCampos() {
		
		txtTitulo.setEditable(false);
		txtTitulo.setEnabled(false);
		txtOrigen.setEditable(false);
		txtOrigen.setEnabled(false);
		txtAutor.setEnabled(false);
		txtAutor.setEditable(false);
		txtFecha.setEditable(false);
		txtFecha.setEnabled(false);
		txtBrebeDescripcion.setEditable(false);
		txtBrebeDescripcion.setEnabled(false);
		txtMotivacion.setEditable(false);
		txtMotivacion.setEnabled(false);
		choiceCategoria.setEditable(false);
		choiceCategoria.setEnabled(false);
		botonCrearPropuesta.setEnabled(false);
		
	}
	
}