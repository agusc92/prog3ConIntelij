package ProgramacionIII.tpe;

import java.util.Iterator;
import java.util.LinkedList;

public class BackTacking {
    private SolucionBacktracking solucion;
    private LinkedList<Tarea> tareas;
    private Integer tiempoLimite;
    private LinkedList<Procesador> procesadores;
    private int contadorHijos;

    public BackTacking(LinkedList<Tarea> tareas, LinkedList<Procesador> procesadores, Integer tiempoLimite) {
        this.procesadores = procesadores;
        this.tiempoLimite = tiempoLimite;
        this.solucion = new SolucionBacktracking();
        this.tareas = tareas;
        this.contadorHijos=0;
    }

    public SolucionBacktracking asignarTareas() {
        Estado e = new Estado(this.procesadores,this.tareas);//seteo del estado inicial
        SolucionBacktracking s = this.asignarTareas(e);
        //una vez fuera de el back se verifica si se llego a una solucion
        if(s.getTiempoDeProcesado()== 0){
            return null;
        }else{
            return s;
        }
    }

    private SolucionBacktracking asignarTareas(Estado e){
        this.contadorHijos ++;
        if(e.esEstadoFinal()){
            //si llega hasta aqui significa que es la mejor solucion hasta el momento y se guarda.
                this.solucion = new SolucionBacktracking();
                this.solucion.copiarEstado(e,this.contadorHijos);
        }else{
            Iterator<Procesador> itProcesador = e.getProcesadores();

            while (itProcesador.hasNext()){
                Procesador procesadorActual = itProcesador.next();
                Tarea tareaActual = e.obtenerTarea();//devuelve la primer tarea de la lista.
                e.actualizar(procesadorActual,tareaActual);//actualiza la lista de tareas
                if(e.esFactible(this.tiempoLimite)){//verifica si la tarea agregada cumple con las restricciones.
                    //poda: si la tarea agregada provoca que el tiempo de procesado actual supere al tiempo
                    //de la mejor solucion hasta el momento, no se sigue generando hijos a partir de este.
                    if(this.solucion.getTiempoDeProcesado() ==0 ||e.getTiempo() < solucion.getTiempoDeProcesado()){
                        asignarTareas(e);
                    }
                }
                e.deshacer(procesadorActual,tareaActual);//revierte el ultimo paso.
            }
        }


        return this.solucion;
    }



}
