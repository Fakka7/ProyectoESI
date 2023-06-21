package estructuraTP.modelo;

public class Propuesta {
	
	/*----------------------------------------------------------------------------------------------------------------*/
	//ATRIBUTOS
	private String titulo;
	private String origen;
	private String categoria;
	private String autor;
	private String fecha;
	private String descripcion;
	private String motivacion;
	private String estado = "Pendiente";
	private String motivoRechazo;
	
	public Propuesta(String titulo, String origen, String categoria, String autor, String fecha,
			String descripcion, String motivacion, String estado, String motivoRechazo) {
		super();
		this.titulo = titulo;
		this.origen = origen;
		this.categoria = categoria;
		this.autor = autor;
		this.fecha = fecha;
		this.descripcion = descripcion;
		this.motivacion = motivacion;
		this.estado = estado;
		this.motivoRechazo = motivoRechazo;
	}
	
	
	public String getMotivacion() {
		return motivacion;
	}
	public void setMotivacion(String motivacion) {
		this.motivacion = motivacion;
	}
	public String getOrigen() {
		return origen;
	}
	public void setOrigen(String origen) {
		this.origen = origen;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getMotivoRechazo() {
		return motivoRechazo;
	}
	public void setMotivoRechazo(String motivoRechazo) {	
		this.motivoRechazo = motivoRechazo;
	}
	
	/*----------------------------------------------------------------------------------------------------------------*/
		
}
