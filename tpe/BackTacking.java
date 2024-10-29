package ProgramacionIII.tpe;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class BackTacking {
    private Solucion solucion;
    private LinkedList<Tarea> tareas;
    private Integer tiempoLimite;
    private LinkedList<Procesador> procesadores;
    private Estado e;

    public BackTacking(HashMap<String, Tarea> tareas, LinkedList<Procesador> procesadores, Integer tiempoLimite) {

        this.procesadores = procesadores;
        this.tiempoLimite = tiempoLimite;
        this.solucion = null;
        this.tareas = this.tranformarEnLista(tareas);
        this.e = new Estado(procesadores,this.tareas);



    }
    public Solucion asignarTareas(){
        if(e.esEstadoFinal()){
            if(e.getTiempo() < this.solucion.getTiempoDeProcesado()||this.solucion.getTiempoDeProcesado() ==null){
                this.solucion.copiarEstado(this.e);
            }
        }else{
            Iterator<Procesador> itProcesador = e.getProcesadores();

            while (itProcesador.hasNext()){
                Procesador procesadorActual = itProcesador.next();
                Tarea tareaActual = this.e.obtenerTarea();
                this.e.actualizar(procesadorActual,tareaActual);
                asignarTareas();
                this.e.deshacer(procesadorActual,tareaActual);
            }
        }


        return this.solucion;
    }

    private LinkedList<Tarea> tranformarEnLista(HashMap<String,Tarea> tareas){
        LinkedList<Tarea> listaTareas = new LinkedList<>();
        for (Tarea tarea : tareas.values()) {
            listaTareas.add(tarea);
        }
        return listaTareas;
    }
}
