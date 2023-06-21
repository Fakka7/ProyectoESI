package estructuraTP.vista;

import java.awt.Choice;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import estructuraTP.modelo.Propuesta;

import java.awt.Color;


public class verDetallesPropuesta extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JTextField txtAutor;
	private JTextField txtTitulo;
	private JTextField txtOrigen;
	private JTextField txtFecha;
	private JTextField txtBrebeDescripcion;
	
	private Propuesta propuesta;
	private JTextField txtMotivacion;

	/**
	 * Create the panel.
	 */
	public verDetallesPropuesta() {

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
		
		Choice choiceCategoria = new Choice();
		choiceCategoria.setEnabled(false);
		choiceCategoria.setBounds(213, 327, 223, 35);
		add(choiceCategoria);
		choiceCategoria.add("Bullying");
		choiceCategoria.add("Salud");
		choiceCategoria.add("Violencia de Genero");
		
		txtAutor = new JTextField();
		txtAutor.setEditable(false);
		txtAutor.setBounds(213, 142, 497, 33);
		add(txtAutor);
		txtAutor.setColumns(10);
		
		txtTitulo = new JTextField();
		txtTitulo.setEditable(false);
		txtTitulo.setColumns(10);
		txtTitulo.setBounds(213, 85, 497, 33);
		add(txtTitulo);
		
		txtOrigen = new JTextField();
		txtOrigen.setEditable(false);
		txtOrigen.setColumns(10);
		txtOrigen.setBounds(213, 30, 497, 33);
		add(txtOrigen);
		
		JButton botonCrearPropuesta = new JButton("Volver");
		botonCrearPropuesta.setBounds(292, 489, 133, 50);
		add(botonCrearPropuesta);
		botonCrearPropuesta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				

				JFrame marco = 
						(JFrame) SwingUtilities.getWindowAncestor((JComponent) ae.getSource());
				marco.setContentPane(new PropuestasPendientesPanel());
				marco.validate();
				
				
			}
		});
		
		JLabel lblBreveDescripcion = new JLabel("Breve Descripcion:");
		lblBreveDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblBreveDescripcion.setBounds(10, 257, 159, 22);
		add(lblBreveDescripcion);
		
		txtFecha = new JTextField();
		txtFecha.setEditable(false);
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
		txtBrebeDescripcion.setEditable(false);
		txtBrebeDescripcion.setColumns(10);
		txtBrebeDescripcion.setBounds(213, 254, 497, 33);
		add(txtBrebeDescripcion);
		
		txtMotivacion = new JTextField();
		txtMotivacion.setEditable(false);
		txtMotivacion.setColumns(10);
		txtMotivacion.setBounds(213, 384, 497, 33);
		add(txtMotivacion);
		
		

	}
	
	public verDetallesPropuesta(Propuesta e) {
		// Ejecuto primero el constructor sin argumentos para que se contruya la pantalla.
		this();
		// Cargo los datos del objeto estudiante en los campos de texto.
		
		txtOrigen.setText("" + e.getOrigen());
		txtTitulo.setText("" + e.getTitulo());
		txtAutor.setText("" + e.getAutor());
		txtFecha.setText("" + e.getFecha());
		txtBrebeDescripcion.setText("" + e.getDescripcion());
		txtMotivacion.setText("" + e.getMotivacion());
		
		this.propuesta = e;
		
	}
	public boolean esEdicion() {
		return this.propuesta != null;
		
	}
}