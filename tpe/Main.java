package ProgramacionIII.tpe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class Main {

	public static void main(String args[]) {
		Servicios servicios = new Servicios("./datasets/Procesadores.csv", "./datasets/Tareas.csv");
		LinkedList<Tarea> tareas = tranformarEnLista(servicios.getTareas());
		Greedy greedy = new Greedy(tareas,convertirEnArraylist(servicios.getProcesadores()),40);

		BackTacking back = new BackTacking(tranformarEnLista(servicios.getTareas()),servicios.getProcesadores(),40);
		/*
		System.out.println(servicios.servicio1("T3"));
		System.out.println(servicios.servicio2(false));
		System.out.println(servicios.servicio3(30,71));
		*/
		System.out.println(back.asignarTareas());
		System.out.print(greedy.asignarTareas());
		//plantear tareas nuevas para que no haya solucion, a ver si devuelve null


	}

	static LinkedList<Tarea> tranformarEnLista(HashMap<String,Tarea> tareas){
		//convertimos el hash de tareas en una lista ya que en Backtracking accederemos siempre a la primera tarea,
		//lo cual se resuelve en una complegidad de O(1) en una LinkedList. En el caso de Greedy accederemos a la
		//primera y la ultima alternativamente y en este caso el ArrayList y la LinkedList nos ofrecen complegidades
		//similares.
		return new LinkedList<>(tareas.values());
	}

	static ArrayList<Procesador> convertirEnArraylist(LinkedList<Procesador>procesadores){
		//convertimos la lista de procesadores en ArrayList para Greedy, ya que vamos a obtenerlos mediante un indice
		//y con un arraylist esto se resuelve con una complegidad de O(1).
		ArrayList<Procesador> result = new ArrayList<>();
		for(Procesador p :procesadores){
			Procesador aux = new Procesador(p.getId_procesador(),p.getCodigo_procesador(),p.isEsta_refrigerado(),p.getAno_funcionamiento());
			aux.agregarTareas(new LinkedList<>(p.obtenerTareas()));
			result.add(aux);
		}
		return result;
	}
	static void limpiarProcesadores(){

	}
}
