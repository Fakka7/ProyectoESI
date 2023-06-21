package estructuraTP.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import estructuraTP.modelo.Material;
import estructuraTP.modelo.MaterialPropuestas;
import estructuraTP.modelo.Propuesta;


public class MaterialDAO {

	/*----------------------------------------------------------------------------------------------------------------*/

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

	/*----------------------------------------------------------------------------------------------------------------*/

	public boolean guardar(Material e) {

		int filasAfectadas = 0;
		Connection c = null;
		try {
			
			c = conectar();
			String sql = "INSERT INTO `material`(`titulo_material`, `categoria_material`, `descripcion_material`, `fuente_material`, `enlace_material`, `tratamiento_material`, `procedencia_material`) VALUES (?, ?, ?, ?, ?, ?, ?);";
			
			PreparedStatement pStmt = c.prepareStatement(sql);
			
			pStmt.setString(1, e.getTitulo());
			
			CategoriaDAO cDao = new CategoriaDAO();			
			int categoriaId = cDao.buscarIdCategoria(e.getCategoria());		
			pStmt.setInt(2, categoriaId);
		
			pStmt.setString(3, e.getDescripcion());
			pStmt.setString(4, e.getFuente());
			pStmt.setString(5, e.getEnlace());
			pStmt.setString(6, e.getTratamiento());
			pStmt.setString(7, e.getProcedencia());
				
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
	
	/*----------------------------------------------------------------------------------------------------------------*/

	public boolean modificar(Material m, String titulo) {

		int filasAfectadas = 0;
		Connection c = null;
		try {
			c = conectar();
			String sql = "UPDATE `material` SET `titulo_material` =?, `categoria_material` = ?, `descripcion_material` = ?, `fuente_material` = ?, `enlace_material` = ?, `tratamiento_material` = ? WHERE `titulo_material` = ?;";
			
			PreparedStatement pStmt = c.prepareStatement(sql);
				
			pStmt.setString(1, m.getTitulo());
			
			CategoriaDAO cDao = new CategoriaDAO();			
			int categoriaId = cDao.buscarIdCategoria(m.getCategoria());					
			pStmt.setInt(2, categoriaId);
			
			pStmt.setString(3, m.getDescripcion());
			pStmt.setString(4, m.getFuente());
			pStmt.setString(5, m.getEnlace());
			pStmt.setString(6, m.getTratamiento());
			pStmt.setString(7, titulo);
			
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
	
	
	/*----------------------------------------------------------------------------------------------------------------*/
	
	public boolean eliminar(String titulo) {

		int filasAfectadas = 0;
		Connection c = null;
		try {
			c = conectar();
			String sql = "DELETE FROM `bdesi`.`material` WHERE `titulo_material` = ?;";
			
			PreparedStatement pStmt = c.prepareStatement(sql);
			
			pStmt.setString(1, titulo);

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
	
	/*----------------------------------------------------------------------------------------------------------------*/
	
	public ArrayList<Material> traerTodos() {
		ArrayList<Material> materiales = new ArrayList<Material>();
		Connection c = null;
		try {
			c = conectar();
			String sql = "SELECT `material`.`titulo_material`, `material`.`categoria_material`, `material`.`descripcion_material`, `material`.`fuente_material`, `material`.`enlace_material`, `material`.`tratamiento_material`, `material`.`procedencia_material` FROM `bdesi`.`material`;";
			Statement stmt = c.createStatement();

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
			
				String titulo = rs.getString("titulo_material");
				
				int categoriaId = rs.getInt("categoria_material");
				CategoriaDAO cDao = new CategoriaDAO();
				String categoriaNombre = cDao.buscarCategoria(categoriaId);

				String descripcion = rs.getString("descripcion_material");
				String fuente = rs.getString("fuente_material");
				String enlace = rs.getString("enlace_material");
				String procedencia = rs.getString("procedencia_material");
				
				String tratamiento = rs.getString("tratamiento_material");
				
				Material m = new MaterialPropuestas(titulo, categoriaNombre, descripcion, fuente, enlace, tratamiento, procedencia);
				
				materiales.add(m);
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
		return materiales;

	}
	
	/*----------------------------------------------------------------------------------------------------------------*/

	public ArrayList<MaterialPropuestas> traerTodosLosDePropuestas() {
		ArrayList<MaterialPropuestas> materiales = new ArrayList<>();
		Connection c = null;
		try {
			c = conectar();
			String sql = "SELECT `material`.`titulo_material`, `material`.`categoria_material`, `material`.`descripcion_material`, `material`.`fuente_material`, `material`.`enlace_material`, `material`.`tratamiento_material`, `material`.`procedencia_material` FROM `bdesi`.`material` WHERE procedencia_material = \" \" OR procedencia_material is null;";
			Statement stmt = c.createStatement();

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
			
				String titulo = rs.getString("titulo_material");
				
				int categoriaId = rs.getInt("categoria_material");
				CategoriaDAO cDao = new CategoriaDAO();
				String categoriaNombre = cDao.buscarCategoria(categoriaId);

				String descripcion = rs.getString("descripcion_material");
				String fuente = rs.getString("fuente_material");
				String enlace = rs.getString("enlace_material");
				String procedencia = rs.getString("procedencia_material");
				
				String tratamiento = rs.getString("tratamiento_material");
				
				MaterialPropuestas m = new MaterialPropuestas(titulo, categoriaNombre, descripcion, fuente, enlace, tratamiento, procedencia);
				
				materiales.add(m);
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
		return materiales;

	}

	/*----------------------------------------------------------------------------------------------------------------*/
	
	public ArrayList<Propuesta> traerTodasLasPropuestasDelMaterial(Material m) {
		
		ArrayList<Propuesta> propuestas = new ArrayList<>();
		Connection c = null;
		try {
			c = conectar();
			String sql = "SELECT `origen_propuesta`, `categoria`, `autor_propuesta`, `fecha_propuesta`, `titulo_propuesta`, `descripcion_propuesta`, `motivacion_propuesta`, `estado_propuesta`, `motivoRechazo_propuesta` FROM propuesta p, matxpropuesta mp WHERE id_material = ? AND p.id_propuesta = mp.id_propuesta;";
			PreparedStatement pStmt = c.prepareStatement(sql);

			int idMat = obtenerIDMaterial(m);
			pStmt.setInt(1, idMat);
			
			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
			
				String titulo = rs.getString("titulo_propuesta");
				String origen = rs.getString("origen_propuesta");
				
				int categoriaId = rs.getInt("categoria");
				CategoriaDAO cDao = new CategoriaDAO();
				String categoriaNombre = cDao.buscarCategoria(categoriaId);

				String autor = rs.getString("autor_propuesta");
				String fecha = rs.getString("fecha_propuesta");
				String descripcion = rs.getString("descripcion_propuesta");
				String motivacion = rs.getString("motivacion_propuesta");
				String estado = rs.getString("estado_propuesta");
				String motivoRechazo = rs.getString("motivoRechazo_propuesta");
				
				Propuesta p = new Propuesta (titulo, origen, categoriaNombre, autor, fecha,
						descripcion, motivacion, estado, motivoRechazo);
				
				propuestas.add(p);
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
		return propuestas;
		
	}
	
	/*----------------------------------------------------------------------------------------------------------------*/

	public int obtenerIDMaterial(Material m) {
		
		int idMat = 0;
		
		Connection c = null;

        try {
        c = conectar();
        String sql = "SELECT material.id_material FROM bdesi.material WHERE material.titulo_material = ? ;";
        PreparedStatement Pstmt = c.prepareStatement(sql);
        Pstmt.setString(1, m.getTitulo());
        ResultSet rs = Pstmt.executeQuery();

        while (rs.next()) {

        idMat = rs.getInt("id_material");

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

        return idMat;
		
		
	}
	
	/*----------------------------------------------------------------------------------------------------------------*/
	
	
	public ArrayList<MaterialPropuestas> traerTodosLosMaterialesPorCategoria(Propuesta e) {


		ArrayList<MaterialPropuestas> materiales = new ArrayList<MaterialPropuestas>();
		Connection c = null;
		try {
			c = conectar();
			String sql = "SELECT `material`.`titulo_material`, `material`.`categoria_material`, `material`.`descripcion_material`, `material`.`fuente_material`, `material`.`enlace_material`, `material`.`tratamiento_material`,`material`.`procedencia_material` FROM `bdesi`.`material` WHERE categoria_material = ?;";
			PreparedStatement pStmt = c.prepareStatement(sql);

			CategoriaDAO cDAO = new CategoriaDAO();
			int idCat = cDAO.buscarIdCategoria(e.getCategoria());
			
			pStmt.setInt(1, idCat);
			
			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
			
				String titulo = rs.getString("titulo_material");
				
				int categoriaId = rs.getInt("categoria_material");
				CategoriaDAO cDao = new CategoriaDAO();
				String categoriaNombre = cDao.buscarCategoria(categoriaId);

				String descripcion = rs.getString("descripcion_material");
				String fuente = rs.getString("fuente_material");
				String enlace = rs.getString("enlace_material");
				String procedencia = rs.getString("procedencia_material");
				
				String tratamiento = rs.getString("tratamiento_material");
				
				MaterialPropuestas m = new MaterialPropuestas (titulo, categoriaNombre, descripcion, fuente, enlace, tratamiento, procedencia);
					
				if (cDAO.buscarIdCategoria(e.getCategoria()) == cDAO.buscarIdCategoria(m.getCategoria())) {
					materiales.add(m);
				}
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
		
		return materiales;
		
	}
}

