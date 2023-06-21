package estructuraTP.modelo;


public abstract class Material {
	
	/*----------------------------------------------------------------------------------------------------------------*/
	//ATRIBUTOS
	private String titulo;
	private String categoria;
	private String descripcion;
	private String fuente;
	private String enlace;
	private String tratamiento;
	private String procedencia;
	
	
	public Material(String titulo, String categoria, String descripcion, String fuente, String enlace, 
			String tratamiento, String procedencia) {
		super();
		this.titulo = titulo;
		this.categoria = categoria;
		this.descripcion = descripcion;
		this.fuente = fuente;
		this.enlace = enlace;
		this.tratamiento = tratamiento;
		this.procedencia = procedencia;
	}
	
	public Material(String titulo, String categoria, String descripcion, String fuente, String enlace) {
		super();
		this.titulo = titulo;
		this.categoria = categoria;
		this.descripcion = descripcion;
		this.fuente = fuente;
		this.enlace = enlace;
		
	}

	public String getProcedencia() {
		return procedencia;
	}

	public void setProcedencia(String procedencia) {
		this.procedencia = procedencia;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getFuente() {
		return fuente;
	}

	public void setFuente(String fuente) {
		this.fuente = fuente;
	}

	public String getEnlace() {
		return enlace;
	}

	public void setEnlace(String enlace) {
		this.enlace = enlace;
	}

	public String getTratamiento() {
		return tratamiento;
	}

	public void setTratamiento(String tratamiento) {
		this.tratamiento = tratamiento;
	}
}
