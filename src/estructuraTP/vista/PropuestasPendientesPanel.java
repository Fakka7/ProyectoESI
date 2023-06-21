package estructuraTP.vista;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import estructuraTP.dao.PropuestaDAO;
import estructuraTP.modelo.Propuesta;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class PropuestasPendientesPanel extends JPanel {

    /* 
     */
    private static final long serialVersionUID = 1L;
    private JTable table;
	private ArrayList<Propuesta> propuestas;
	private Propuesta propuesta;


    /* 
     * Create the panel.
     */
    public PropuestasPendientesPanel() {
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
		cargarTablaPendientes(dataModel);
		scrollPane.setViewportView(table);
		

        JButton botonVerDetallesPropuesta = new JButton("Ver Detalles");
        botonVerDetallesPropuesta.setBounds(62, 442, 125, 33);
        add(botonVerDetallesPropuesta);
        botonVerDetallesPropuesta.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent ae) {
        	
        		Propuesta e = obtenerPropuestaSeleccionada();
				JFrame marco = 
						(JFrame) SwingUtilities.getWindowAncestor((JComponent) ae.getSource());
				marco.setContentPane(new verDetallesPropuesta(e));
				marco.validate();
        		
        	}
        });
        
        
        

        JButton botonRechazarPropuesta = new JButton("Rechazar");
        botonRechazarPropuesta.setBounds(527, 442, 125, 33);
        add(botonRechazarPropuesta);
        botonRechazarPropuesta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                
            	Propuesta e = obtenerPropuestaSeleccionada();
            	PropuestaDAO eDao = new PropuestaDAO();
            	
				eDao.modificarEstado(e, "Rechazada");
            	
            	JFrame marco = 
                        (JFrame) SwingUtilities.getWindowAncestor((JComponent) ae.getSource());
                marco.setContentPane(new PropuestaRechazadaPanel(e));
                marco.validate();
            }
        });

        JButton botonAceptarPropuesta = new JButton("Aceptar");
        botonAceptarPropuesta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
            	
            	Propuesta e = obtenerPropuestaSeleccionada();
            	PropuestaDAO eDao = new PropuestaDAO();
            	
				eDao.modificarEstado(e, "Aceptada");
            	
            	JFrame marco = 
                        (JFrame) SwingUtilities.getWindowAncestor((JComponent) ae.getSource());
                marco.setContentPane(new PropuestaAceptadaPanel(e));
                marco.validate();
            }
        });
        botonAceptarPropuesta.setBounds(294, 442, 125, 33);
        add(botonAceptarPropuesta);

    }
    
    private void cargarTablaPendientes(DefaultTableModel dataModel) {
		
		dataModel.setRowCount(0);
		
		PropuestaDAO eDao = new PropuestaDAO();
		propuestas = eDao.traerTodasLasPendientes();
		for (Propuesta e : propuestas) {

			Object[] fila = new Object[] {e.getTitulo(), e.getOrigen(), e.getCategoria(), e.getAutor(), e.getFecha(), e.getDescripcion(), e.getMotivacion(), e.getEstado(), e.getMotivoRechazo()};
			dataModel.addRow(fila);
		}
	}

	private Propuesta obtenerPropuestaSeleccionada() {
		// TODO Mejorar (evitar relacionar el �ndice de la tabla con el �ndice del ArrayList)
		int filaSeleccionada = table.getSelectedRow();
		return propuestas.get(filaSeleccionada);
	}

	public Propuesta getPropuesta() {
		return propuesta;
	}

	public void setPropuesta(Propuesta propuesta) {
		this.propuesta = propuesta;
	}
	

}