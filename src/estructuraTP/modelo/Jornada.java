package estructuraTP.modelo;

public class Jornada {
	
	/*----------------------------------------------------------------------------------------------------------------*/
	//ATRIBUTOS
	
	private String titulo;
	private String referenteInsitucional;
	private String objetivoPedagogico;
	private String categoria;
	
	public Jornada(int numJornada, String titulo, String referenteInsitucional, String objetivoPedagogico, String categoria) {
		super();
		
		this.referenteInsitucional = referenteInsitucional;
		this.objetivoPedagogico = objetivoPedagogico;
		this.titulo = titulo;
		this.categoria = categoria;
	}


	public String getReferenteInsitucional() {
		return referenteInsitucional;
	}

	public void setReferenteInsitucional(String referenteInsitucional) {
		this.referenteInsitucional = referenteInsitucional;
	}

	public String getObjetivoPedagogico() {
		return objetivoPedagogico;
	}

	public void setObjetivoPedagogico(String objetivoPedagogico) {
		this.objetivoPedagogico = objetivoPedagogico;
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
	/*----------------------------------------------------------------------------------------------------------------*/
}
