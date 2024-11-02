package ProgramacionIII.tpe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;

public class Main {

	public static void main(String args[]) {
		Servicios servicios = new Servicios("./datasets/Procesadores.csv", "./datasets/Tareas.csv");
		LinkedList<Tarea> tareas = tranformarEnLista(servicios.getTareas());
		Greedy2 greedy = new Greedy2(tareas,convertirEnArraylist(servicios.getProcesadores()),40);

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
		return new LinkedList<>(tareas.values());
	}
	static LinkedList<Procesador> ordenarProcesadores(LinkedList<Procesador>procesadores){
		LinkedList<Procesador> result = new LinkedList<>();
		for (Procesador p : procesadores){
			if(p.isEsta_refrigerado()){
				result.addFirst(p);
			}else {
				result.addLast(p);
			}
		}
		return result;
	}
	static ArrayList<Procesador> convertirEnArraylist(LinkedList<Procesador>procesadores){
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
