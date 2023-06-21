package estructuraTP.modelo;


import estructuraTP.dao.MatPropuestaDAO;

public class MaterialPropuestas extends Material {
	
	/*----------------------------------------------------------------------------------------------------------------*/
	

	public MaterialPropuestas(String titulo, String categoria, String descripcion, String fuente, String enlace,
			String tratamiento, String procedencia) {
		super(titulo, categoria, descripcion, fuente, enlace, tratamiento, procedencia);
		// TODO Auto-generated constructor stub
	}
	
	
	public String esPrioritario(MaterialPropuestas m) {
		
		MatPropuestaDAO mpDAO = new MatPropuestaDAO();
		
		if(mpDAO.siEsMaterialPrioritario(m)) {
			return "Es Prioritario";
		}
		
		return "No es Prioritario";
	
	}
	/*----------------------------------------------------------------------------------------------------------------*/

}
