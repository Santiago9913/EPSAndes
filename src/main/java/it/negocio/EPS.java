package it.negocio;

public class EPS implements VOEPS {

	private long id;
	
	private String nombre;
	
	public EPS() {
		this.id = 0;
		this.nombre = "";
	}
	
	public EPS(long id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}

	@Override
	public long getId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getNombre() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "EPS [id=" + id + ", nombre=" + nombre + "]";
	}
}
