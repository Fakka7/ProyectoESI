package estructuraTP.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CategoriaDAO {

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

	public ArrayList<String> traerTodas() {
		ArrayList<String> categorias = new ArrayList<String>();

		Connection c = null;
		try {
			c = conectar();
			String sql = "SELECT `categorias`.`nombre_categoria` FROM `bdesi`.`categorias`;";
			Statement stmt = c.createStatement();

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {

				String categoriaNombre = rs.getString("nombre_categoria");

				categorias.add(categoriaNombre);
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

		return categorias;
	}

	/*----------------------------------------------------------------------------------------------------------------*/

	public String buscarCategoria(int id) {

		String categoriaNombre = null;

		Connection c = null;
		try {
			c = conectar();
			String sql = "select nombre_categoria from categorias where id_Categorias = ?";
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				categoriaNombre = rs.getString("nombre_categoria");

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

		return categoriaNombre;

	}
	
	/*----------------------------------------------------------------------------------------------------------------*/

	public int buscarIdCategoria(String categoria) {

		int categoriaId = 0;

		Connection c = null;
		try {
			c = conectar();
			String sql = "select id_categorias from categorias where nombre_Categoria = ?";
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setString(1, categoria);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				categoriaId = rs.getInt("id_categorias");

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

		return categoriaId;

	}

	/*----------------------------------------------------------------------------------------------------------------*/

}