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

    public BackTacking(LinkedList<Tarea> tareas, LinkedList<Procesador> procesadores, Integer tiempoLimite) {
        this.procesadores = procesadores;
        this.tiempoLimite = tiempoLimite;
        this.solucion = new Solucion();
        this.tareas = tareas;
        this.contadorHijos=0;
    }

    public Solucion asignarTareas() {
        Estado e = new Estado(this.procesadores,this.tareas);
        Solucion s = this.asignarTareas(e);
        if(s.getTiempoDeProcesado()== 0){
            return null;
        }else{
            return s;
        }


    }

    private Solucion asignarTareas(Estado e){
        this.contadorHijos ++;
        if(e.esEstadoFinal()){

                this.solucion = new Solucion();
                this.solucion.copiarEstado(e,this.contadorHijos);

        }else{
            Iterator<Procesador> itProcesador = e.getProcesadores();

            while (itProcesador.hasNext()){
                Procesador procesadorActual = itProcesador.next();
                Tarea tareaActual = e.obtenerTarea();
                e.actualizar(procesadorActual,tareaActual);
                if(e.esFactible(this.tiempoLimite)){
                    //poda
                    if(this.solucion.getTiempoDeProcesado() ==0 ||e.getTiempo() < solucion.getTiempoDeProcesado()){
                        asignarTareas(e);
                    }

                }

                e.deshacer(procesadorActual,tareaActual);
            }
        }


        return this.solucion;
    }



}
