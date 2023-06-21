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
import estructuraTP.dao.MaterialDAO;
import estructuraTP.dao.PropuestaDAO;
import estructuraTP.modelo.MaterialPropuestas;
import estructuraTP.modelo.Propuesta;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;

public class PropuestaAceptadaPanel extends JPanel {

    /*
     * 
     */
    private static final long serialVersionUID = 1L;
    private JTable table;
    private Propuesta propuesta;
    
    public Propuesta getPropuesta() {
		return propuesta;
	}
	public void setPropuesta(Propuesta propuesta) {
	}

	private ArrayList<MaterialPropuestas> materiales = new ArrayList<>();

    /*
     * Create the panel.
     */
    

    
    public PropuestaAceptadaPanel(Propuesta propuesta) {
        
    	
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
		
		cargarTabla(dataModel, propuesta);


        JButton botonCrearMaterial = new JButton("Crear Material");
        botonCrearMaterial.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                
            	PropuestaDAO pDao = new PropuestaDAO();
            	pDao.modificarEstado(propuesta, "Pendiente");
            	
            	JFrame marco = 
                        (JFrame) SwingUtilities.getWindowAncestor((JComponent) ae.getSource());
                marco.setContentPane(new MaterialAlta());
                marco.validate();
            }
        });

        botonCrearMaterial.setBounds(45, 442, 125, 33);
        add(botonCrearMaterial);

        JButton botonAsignarPropuesta = new JButton("Asignar Propuesta");
        botonAsignarPropuesta.setBounds(531, 442, 125, 33);
        add(botonAsignarPropuesta);
        
        JLabel lblNewLabel = new JLabel("MAERIALES DE PROPUESTAS");
        lblNewLabel.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 16));
        lblNewLabel.setBounds(10, 11, 189, 14);
        add(lblNewLabel);
        botonAsignarPropuesta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                
            	
            	MaterialPropuestas m = obtenerMaterialSeleccionado();
            	MatPropuestaDAO mpDao  = new MatPropuestaDAO();
            	mpDao.asignar(m, propuesta);
            	
            	JFrame marco =  (JFrame) SwingUtilities.getWindowAncestor((JComponent) ae.getSource());
                marco.setContentPane(new PropuestaPanel());
                marco.validate();
            }
        });
    }
    


	private void cargarTabla(DefaultTableModel dataModel, Propuesta ef) {
		
		dataModel.setRowCount(0);
		
		MaterialDAO mDao = new MaterialDAO();
		materiales = mDao.traerTodosLosMaterialesPorCategoria(ef);
		for (MaterialPropuestas e : materiales) {

			Object[] fila = new Object[] {e.getTitulo(), e.getCategoria(), e.getDescripcion(), e.getFuente(), e.getEnlace(), e.getTratamiento(), e.getProcedencia()};
			dataModel.addRow(fila);
		}
	}
    
    private MaterialPropuestas obtenerMaterialSeleccionado() {
		// TODO Mejorar (evitar relacionar el �ndice de la tabla con el �ndice del ArrayList)
		int filaSeleccionada = table.getSelectedRow();
		return materiales.get(filaSeleccionada);
	}


	

    
}