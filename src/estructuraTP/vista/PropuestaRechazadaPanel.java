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

public class PropuestaRechazadaPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JTextField txtAutor;
	private JTextField txtTitulo;
	private JTextField txtOrigen;
	private JTextField txtFecha;
	private JTextField txtBreveDescripcion;
	
	private Propuesta propuesta;
	private JTextField txtMotivoRechazo;
	private JTextField txtMotivacion;

	/**
	 * Create the panel.
	 */
	public PropuestaRechazadaPanel() {

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
		
		JComboBox<String> choiceCategoria = new JComboBox<>();
		choiceCategoria.setBounds(213, 327, 223, 35);
		add(choiceCategoria);
		CategoriaDAO cDao = new CategoriaDAO();
		for (String categoria : cDao.traerTodas()) {
			choiceCategoria.addItem(categoria);
		}
		
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
		
		JButton btnGuardarPropuesta = new JButton("Guardar Propuesta");
		btnGuardarPropuesta.setBounds(359, 547, 133, 50);
		add(btnGuardarPropuesta);
		btnGuardarPropuesta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				
				
				String origen = txtOrigen.getText();
				String titulo = txtTitulo.getText();
				String autor = txtAutor.getText();
				String fecha = txtFecha.getText();
				String descripcion = txtBreveDescripcion.getText();
				
				
				String categoria = choiceCategoria.getSelectedItem().toString();
				
				CategoriaDAO cDao = new CategoriaDAO();
				int categoriaId = cDao.buscarIdCategoria(categoria);
				
				categoria = cDao.buscarCategoria(categoriaId);
				
				
				String motivacion = txtMotivacion.getText();
				String motivoRechazo= txtMotivoRechazo.getText();
				
				Propuesta x = new Propuesta(titulo, origen, categoria, autor, fecha, descripcion, motivacion, "Rechazado" , motivoRechazo);
				PropuestaDAO eDao = new PropuestaDAO();
				
				eDao.modificarMotivoRechazo(x, titulo, txtMotivoRechazo.getText());
				
				JFrame marco = 
						(JFrame) SwingUtilities.getWindowAncestor((JComponent) ae.getSource());
				marco.setContentPane(new PropuestaPanel());
				marco.validate();
				
				
			}
		});
		
		JLabel lblBreveDescripcion = new JLabel("Breve Descripcion:");
		lblBreveDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblBreveDescripcion.setBounds(10, 257, 159, 22);
		add(lblBreveDescripcion);
		
		txtFecha = new JTextField();
		txtFecha.setEditable(false);
		txtFecha.setForeground(new Color(0, 0, 0));
		txtFecha.setText("aaaa-mm-dd");
		txtFecha.setColumns(10);
		txtFecha.setBounds(213, 200, 497, 33);
		add(txtFecha);
		
		JLabel lblAutor = new JLabel("Autor (opcional):");
		lblAutor.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAutor.setBounds(10, 145, 159, 22);
		add(lblAutor);
		
		txtBreveDescripcion = new JTextField();
		txtBreveDescripcion.setEditable(false);
		txtBreveDescripcion.setColumns(10);
		txtBreveDescripcion.setBounds(213, 254, 497, 33);
		add(txtBreveDescripcion);
		
		JLabel lblMotivoRechazo = new JLabel("Motivo rechazo:");
		lblMotivoRechazo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMotivoRechazo.setBounds(10, 466, 159, 14);
		add(lblMotivoRechazo);
		
		txtMotivoRechazo = new JTextField();
		txtMotivoRechazo.setBounds(213, 455, 497, 41);
		add(txtMotivoRechazo);
		txtMotivoRechazo.setColumns(10);
		
		txtMotivacion = new JTextField();
		txtMotivacion.setEditable(false);
		txtMotivacion.setBounds(213, 384, 497, 41);
		add(txtMotivacion);
		txtMotivacion.setColumns(10);
		
		

	}
	

	
	
	public PropuestaRechazadaPanel(Propuesta e) {
		// Ejecuto primero el constructor sin argumentos para que se contruya la pantalla.
		this();
		// Cargo los datos del objeto estudiante en los campos de texto.
		
		txtOrigen.setText("" + e.getOrigen());
		txtTitulo.setText("" + e.getTitulo());
		txtAutor.setText("" + e.getAutor());
		txtFecha.setText("" + e.getFecha());
		txtBreveDescripcion.setText("" + e.getDescripcion());
		txtMotivacion.setText("" + e.getMotivacion());
		this.setPropuesta(e);
		
	}




	public Propuesta getPropuesta() {
		return propuesta;
	}




	public void setPropuesta(Propuesta propuesta) {
		this.propuesta = propuesta;
	}

}