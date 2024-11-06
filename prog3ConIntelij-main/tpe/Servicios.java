package ProgramacionIII.tpe;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import ProgramacionIII.tpe.utils.CSVReader;

/**
 * NO modificar la interfaz de esta clase ni sus métodos públicos.
 * Sólo se podrá adaptar el nombre de la clase "Tarea" según sus decisiones
 * de implementación.
 */
public class Servicios {

	/*
     * Expresar la complejidad temporal del constructor.
     */
	private HashMap<String,Tarea> tareasMap;
	private LinkedList<Tarea> critica;
	private LinkedList<Tarea> noCritica;
	private LinkedList<Procesador> procesadores;

	// Complejidad temporal cosntructor: O(t + p) donde t son las tareas y p los procesadores
	public Servicios(String pathProcesadores, String pathTareas)
	{
		CSVReader reader = new CSVReader();
		this.tareasMap = new HashMap<String,Tarea>();
		this.procesadores = new LinkedList<>();
		reader.readProcessors(pathProcesadores,this.procesadores);
		reader.readTasks(pathTareas,this.tareasMap);
		this.critica = new LinkedList<Tarea>();
		this.noCritica = new LinkedList<Tarea>();


		for (Tarea tarea : tareasMap.values()) {
			if(tarea.isCritica()) {
				//se crean 2 listas ya que el servicio 2 solo tiene dos posibilidades
				this.critica.addFirst(tarea);
			}else {
				this.noCritica.addFirst(tarea);
			}
		}
		

        //crear 2 listas, una para criticas y otra para no criticas.
	}
	
	/*
     * Expresar la complejidad temporal del servicio 1.
     * Complejidad temporal servicio 1: O(1)
     */
	public Tarea servicio1(String ID) {	
		
		return this.tareasMap.get(ID);
				 
	}
    
    /*
     * Expresar la complejidad temporal del servicio 2.
     * Complejidad temporal servicio 2: O(1)
     */
	public List<Tarea> servicio2(boolean esCritica) {
		if(esCritica) {
			return critica;
		}else {
			return noCritica;
		}
		
	}

    /*
     * Expresar la complejidad temporal del servicio 3.
     * Complejidad temporal servicio 3: O(n)
     */
	public List<Tarea> servicio3(int prioridadInferior, int prioridadSuperior) {
		LinkedList<Tarea> tareaRango = new LinkedList<Tarea>();
		//lo desarrollamos en el metodo porque en cada consulta tenemos que recorrer todas las tareas
		for (Tarea tarea : this.critica) {
			if(prioridadInferior< tarea.getPrioridad() && prioridadSuperior >tarea.getPrioridad()) {
				tareaRango.addFirst(tarea);
			}
		}
		for (Tarea tarea : this.noCritica) {
			if(prioridadInferior< tarea.getPrioridad() && prioridadSuperior >tarea.getPrioridad()) {
				tareaRango.addFirst(tarea);
			}
		}
		return tareaRango;
	}
	public HashMap<String,Tarea> getTareas(){
		return this.tareasMap;
	}
	public LinkedList<Procesador> getProcesadores(){
		return this.procesadores;
	}
}
