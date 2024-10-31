package ProgramacionIII.tpe;

public class Tarea {
	private String id;
	private String nombre;
	private Integer tiempo;
	private boolean critica;
	private Integer prioridad;
	
	public Tarea(String id, String nombre, Integer tiempo, boolean critica, Integer prioridad) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.tiempo = tiempo;
		this.critica = critica;
		this.prioridad = prioridad;
	}

	public String getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public Integer getTiempo() {
		return tiempo;
	}

	public boolean isCritica() {
		return critica;
	}

	public Integer getPrioridad() {
		return prioridad;
	}

	@Override
	public String toString(){
		return "[id:"+this.id+" critica: "+this.critica + " tiempo: "+this.tiempo+"]";

	}

}
