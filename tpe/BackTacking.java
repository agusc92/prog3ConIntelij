package ProgramacionIII.tpe;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class BackTacking {
    private Solucion solucion;
    private LinkedList<Tarea> tareas;
    private Integer tiempoLimite;
    private LinkedList<Procesador> procesadores;
    private int contadorHijos;

    public BackTacking(HashMap<String, Tarea> tareas, LinkedList<Procesador> procesadores, Integer tiempoLimite) {
        this.procesadores = procesadores;
        this.tiempoLimite = tiempoLimite;
        this.solucion = new Solucion();
        this.tareas = this.tranformarEnLista(tareas);
        this.contadorHijos=0;
    }

    public Solucion asignarTareas() {
        Estado e = new Estado(this.procesadores,this.tareas);
        Solucion s = this.asignarTareas(e);
        if(s.getTiempoDeProcesado()== null){
            return null;
        }else{
            return s;
        }


    }

    private Solucion asignarTareas(Estado e){
        this.contadorHijos ++;
        if(e.esEstadoFinal()){
            if(this.solucion.getTiempoDeProcesado() ==null ||e.getTiempo() < this.solucion.getTiempoDeProcesado()){
                this.solucion = new Solucion();
                this.solucion.copiarEstado(e,this.contadorHijos);
            }
        }else{
            Iterator<Procesador> itProcesador = e.getProcesadores();

            while (itProcesador.hasNext()){
                Procesador procesadorActual = itProcesador.next();
                Tarea tareaActual = e.obtenerTarea();
                e.actualizar(procesadorActual,tareaActual);
                if(e.esFactible(this.tiempoLimite)){
                    //realizar poda: si una tarea es asignada a un procesador y el tiempo de procesado de este exede el tiempo de la solucion
                    //no asignarla
                    asignarTareas(e);
                }

                e.deshacer(procesadorActual,tareaActual);
            }
        }


        return this.solucion;
    }

    private LinkedList<Tarea> tranformarEnLista(HashMap<String,Tarea> tareas){
        return new LinkedList<>(tareas.values());
    }
}
