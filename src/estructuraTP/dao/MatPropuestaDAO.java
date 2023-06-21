package estructuraTP.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import estructuraTP.modelo.Material;
import estructuraTP.modelo.MaterialPropuestas;
import estructuraTP.modelo.Propuesta;

public class MatPropuestaDAO {
	
	//private Material material;
	//private Propuesta propuesta;

	private Connection conectar() {
		
		String url = "jdbc:mysql://localhost:3306/bdesi";
		String usr = "root";
		String pass = "admin";
		
		Connection c = null;
		
		try {
			c = DriverManager.getConnection(url, usr, pass);
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		return c;

	}

	public boolean asignar(MaterialPropuestas m, Propuesta p) {

		int filasAfectadas = 0;
		Connection c = null;
		try {
			
			c = conectar();
			MaterialDAO mDao = new MaterialDAO();
			int idMat = mDao.obtenerIDMaterial(m);
			PropuestaDAO pDao = new PropuestaDAO();
			int idProp = pDao.obtenerIDPropuesta(p);
			
			
			String sql = "INSERT INTO `matxpropuesta` (`id_material`, `id_propuesta`) VALUES (?, ?);";
			
			PreparedStatement pStmt = c.prepareStatement(sql);
			
			pStmt.setInt(1, idMat);
			pStmt.setInt(2, idProp);
			
			filasAfectadas = pStmt.executeUpdate();
			
			if(siEsMaterialPrioritario(m)) {
				m.setTratamiento(m.esPrioritario(m));
				MaterialDAO mDAO = new MaterialDAO();
				mDAO.modificar(m, m.getTitulo());
			}
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
			
		} finally {
			try {
				if (c != null) {
					c.close();
				}
			} catch (SQLException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
		}
		return filasAfectadas != 0;
	}
	
	public boolean eliminar(Material m) {

		int filasAfectadas = 0;
		Connection c = null;
		try {
			c = conectar();
			String sql = "DELETE FROM `bdesi`.`matxpropuesta` WHERE `id_material` = ?;";
			
			PreparedStatement pStmt = c.prepareStatement(sql);
			
			MaterialDAO mDAO = new MaterialDAO();
			int idMat = mDAO.obtenerIDMaterial(m);
			
			pStmt.setInt(1, idMat);

			filasAfectadas = pStmt.executeUpdate();
			
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		} finally {
			try {
				if (c != null) {
					c.close();
				}
			} catch (SQLException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
		}
		return filasAfectadas != 0;

	}
	
	public boolean siEsMaterialPrioritario(MaterialPropuestas m) {
		ArrayList<Integer> idPropuestas = new ArrayList<>();
        Connection c = null;
        try {

            c = conectar();

            MaterialDAO mDAO = new MaterialDAO();
            int idMat = mDAO.obtenerIDMaterial(m);
            String sql = "SELECT id_propuesta FROM matxpropuesta WHERE id_material = ?;";
            PreparedStatement pStmt = c.prepareStatement(sql);
            pStmt.setInt(1, idMat);
            
            ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
			
				int idProp = rs.getInt("id_propuesta");
				idPropuestas.add(idProp);
			}


        } catch (SQLException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();

        } finally {
            try {
                if (c != null) {
                    c.close();
                }
            } catch (SQLException ex) {
                // TODO Auto-generated catch block
                ex.printStackTrace();
            }
        }
        return idPropuestas.size() > 3;
    }


	
}
