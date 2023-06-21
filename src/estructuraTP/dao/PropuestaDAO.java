package estructuraTP.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import estructuraTP.modelo.Material;
import estructuraTP.modelo.Propuesta;



public class PropuestaDAO {

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

	public boolean guardar(Propuesta e) {

		int filasAfectadas = 0;
		Connection c = null;
		try {
			
			c = conectar();
			String sql = "INSERT INTO `Propuesta` (`origen_propuesta`, `titulo_propuesta`, `descripcion_propuesta`, `categoria`, `autor_propuesta`, `fecha_propuesta`, `motivacion_propuesta`, `estado_propuesta`, `motivoRechazo_propuesta`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
			
			PreparedStatement pStmt = c.prepareStatement(sql);
			
			pStmt.setString(1, e.getOrigen());
			pStmt.setString(2, e.getTitulo());
			pStmt.setString(3, e.getDescripcion());
			
			CategoriaDAO cDao = new CategoriaDAO();			
			int categoriaId = cDao.buscarIdCategoria(e.getCategoria());		
			pStmt.setInt(4, categoriaId);
			
			pStmt.setString(5, e.getAutor());
			pStmt.setString(6, e.getFecha());
			pStmt.setString(7, e.getMotivacion());
			pStmt.setString(8, e.getEstado());
			pStmt.setString(9, " ");
			

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

	public boolean modificar(Propuesta e, String titulo) {

		int filasAfectadas = 0;
		Connection c = null;
		try {
			c = conectar();
			String sql = "UPDATE `bdesi`.`propuesta` SET `origen_propuesta` = ?, `categoria` = ?, `autor_propuesta` = ?, `fecha_propuesta` = ?, `titulo_propuesta` = ?, `descripcion_propuesta` = ?, `motivacion_propuesta` = ?, `estado_propuesta` = ?, `motivoRechazo_propuesta` = ? WHERE `titulo_propuesta` = ?;";
			
			PreparedStatement pStmt = c.prepareStatement(sql);
				
			pStmt.setString(1, e.getOrigen());
			
			CategoriaDAO cDao = new CategoriaDAO();			
			int categoriaId = cDao.buscarIdCategoria(e.getCategoria());					
			pStmt.setInt(2, categoriaId);
			
			pStmt.setString(3, e.getAutor());
			pStmt.setString(4, e.getFecha());
			pStmt.setString(5, e.getTitulo());
			pStmt.setString(6, e.getDescripcion());
			pStmt.setString(7, e.getMotivacion());
			pStmt.setString(8, e.getEstado());
			pStmt.setString(9, " ");
			pStmt.setString(10, titulo);
			
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

	public boolean modificarEstado(Propuesta x, String nuevoEstado) {
        conectar();
        int filasAfectadas = 0;
        try {

            String sql = "UPDATE `bdesi`.`propuesta` SET estado_propuesta=? where titulo_propuesta = ?";
            PreparedStatement pStmt = conectar().prepareStatement(sql);
            
            pStmt.setString(1, nuevoEstado);
            pStmt.setString(2, x.getTitulo());
            
            filasAfectadas = pStmt.executeUpdate();
        } catch (SQLException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        } 
        finally 
        {
            try {
                if (conectar() != null) {
                    conectar().close();
                }
            } catch (SQLException ex) {
                // TODO Auto-generated catch block
                ex.printStackTrace();
            }
        }
        return filasAfectadas!=0;

    }
	
	/*----------------------------------------------------------------------------------------------------------------*/

	public boolean modificarMotivoRechazo(Propuesta e, String titulo, String nuevoMotivoRechazo) {
        
		int filasAfectadas = 0;
		Connection c = null;
		try {
			c = conectar();
			String sql = "UPDATE `bdesi`.`propuesta` SET `origen_propuesta` = ?, `categoria` = ?, `autor_propuesta` = ?, `fecha_propuesta` = ?, `titulo_propuesta` = ?, `descripcion_propuesta` = ?, `motivacion_propuesta` = ?, `estado_propuesta` = ?, `motivoRechazo_propuesta` = ? WHERE `titulo_propuesta` = ?;";
			
			PreparedStatement pStmt = c.prepareStatement(sql);
					
			pStmt.setString(1, e.getOrigen());
			
			CategoriaDAO cDao = new CategoriaDAO();
			
			int categoriaId = cDao.buscarIdCategoria(e.getCategoria());
				
			pStmt.setInt(2, categoriaId);
			pStmt.setString(3, e.getAutor());
			pStmt.setString(4, e.getFecha());
			pStmt.setString(5, e.getTitulo());
			pStmt.setString(6, e.getDescripcion());
			pStmt.setString(7, e.getMotivacion());
			pStmt.setString(8, e.getEstado());
			pStmt.setString(9, nuevoMotivoRechazo);
			pStmt.setString(10, titulo);
			
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

	public boolean eliminar(String clave) {

		int filasAfectadas = 0;
		Connection c = null;
		try {
			c = conectar();
			String sql = "DELETE FROM `bdesi`.`propuesta` WHERE `titulo_propuesta` = ?;";
			
			PreparedStatement pStmt = c.prepareStatement(sql);
			
			pStmt.setString(1, clave);

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

	public ArrayList<Propuesta> traerTodas() {
		ArrayList<Propuesta> propuestas = new ArrayList<Propuesta>();
		Connection c = null;
		try {
			c = conectar();
			String sql = "SELECT `propuesta`.`id_propuesta`, `propuesta`.`origen_propuesta`, `propuesta`.`categoria`, `propuesta`.`autor_propuesta`, `propuesta`.`fecha_propuesta`, `propuesta`.`titulo_propuesta`, `propuesta`.`descripcion_propuesta`, `propuesta`.`motivacion_propuesta`, `propuesta`.`estado_propuesta`, `propuesta`.`motivoRechazo_propuesta` FROM `bdesi`.`propuesta`;";
			Statement stmt = c.createStatement();

			ResultSet rs = stmt.executeQuery(sql);

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
				
				Propuesta e = new Propuesta (titulo, origen, categoriaNombre, autor, fecha,
				descripcion, motivacion, estado, motivoRechazo);
				
				propuestas.add(e);
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

	public ArrayList<Propuesta> traerTodasLasPendientes() {
		ArrayList<Propuesta> propuestas = new ArrayList<Propuesta>();
		Connection c = null;
		try {
			c = conectar();
			String sql = "SELECT `propuesta`.`id_propuesta`, `propuesta`.`origen_propuesta`, `propuesta`.`categoria`, `propuesta`.`autor_propuesta`, `propuesta`.`fecha_propuesta`, `propuesta`.`titulo_propuesta`, `propuesta`.`descripcion_propuesta`, `propuesta`.`motivacion_propuesta`, `propuesta`.`estado_propuesta`, `propuesta`.`motivoRechazo_propuesta` FROM `bdesi`.`propuesta` WHERE `propuesta`.`estado_propuesta` = 'Pendiente'";
			Statement stmt = c.createStatement();

			ResultSet rs = stmt.executeQuery(sql);

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
				
				Propuesta e = new Propuesta (titulo, origen, categoriaNombre, autor, fecha,
				descripcion, motivacion, estado, motivoRechazo);
				
				propuestas.add(e);
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

	public int obtenerIDPropuesta(Propuesta p) {
		
		int idProp = 0;
		
		Connection c = null;

        try {
        c = conectar();
        String sql = "SELECT propuesta.id_propuesta FROM bdesi.propuesta WHERE propuesta.titulo_propuesta = ? ;";
        PreparedStatement Pstmt = c.prepareStatement(sql);
        Pstmt.setString(1, p.getTitulo());
        ResultSet rs = Pstmt.executeQuery();

        while (rs.next()) {

        idProp = rs.getInt("id_propuesta");

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

        return idProp;
	}

	/*----------------------------------------------------------------------------------------------------------------*/

	public ArrayList<Propuesta> traerTodasLasAceptadasPorCategoria(Material m) {


		ArrayList<Propuesta> propuestas = new ArrayList<Propuesta>();
		Connection c = null;
		try {
			c = conectar();
			String sql = "SELECT  `propuesta`.`origen_propuesta`, `propuesta`.`categoria`, `propuesta`.`autor_propuesta`, `propuesta`.`fecha_propuesta`, `propuesta`.`titulo_propuesta`, `propuesta`.`descripcion_propuesta`, `propuesta`.`motivacion_propuesta`, `propuesta`.`estado_propuesta`, `propuesta`.`motivoRechazo_propuesta` FROM `bdesi`.`propuesta` WHERE `propuesta`.`estado_propuesta` = 'Aceptada';";
			Statement stmt = c.createStatement();

			ResultSet rs = stmt.executeQuery(sql);

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
				
				Propuesta e = new Propuesta (titulo, origen, categoriaNombre, autor, fecha,
				descripcion, motivacion, estado, motivoRechazo);
					
				CategoriaDAO cDAO = new CategoriaDAO();
				if (cDAO.buscarIdCategoria(e.getCategoria()) == cDAO.buscarIdCategoria(m.getCategoria())) {
					propuestas.add(e);
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
		
		return propuestas;
		
	}
	
	/*----------------------------------------------------------------------------------------------------------------*/

}