package ProgramacionIII.tpe;

import java.util.*;

public class Greedy {

    private Solucion solucion;
    private ArrayList<Tarea> candidatos;
    private Integer tiempoLimite;
    private ArrayList<Procesador> procesadores;
    private int contadorHijos;
    private boolean primera;
    private boolean procesadorAvanza;
    public Greedy(ArrayList<Tarea> tareas, ArrayList<Procesador> procesadores, Integer tiempoLimite) {
        this.procesadores = procesadores;
        this.tiempoLimite = tiempoLimite;
        this.solucion = new Solucion();
        this.contadorHijos=0;
        this.primera = true;
        this.procesadorAvanza = true;
    }
    public Solucion asignarTareas(){
        Collections.sort(this.candidatos);

        this.procesadores = this.ordenarProcesadores(this.procesadores);
        int procesadorActual=0;
        int intentos=0;
        while (!this.candidatos.isEmpty()){
            Procesador procesador=this.procesadores.get(procesadorActual);
            Tarea t = this.candidatos.getFirst();
            if(t!=null){
                procesador.agregarTarea(t);
                this.candidatos.remove(t);
                intentos =0;
            }else {
                intentos ++;
            }


            //se controla si agarramos el primer o ultimo procesador
            if(this.procesadorAvanza){
                procesadorActual ++;

            }else {
                procesadorActual --;
            }
            //cambia el valor de para saber si tiene que incrementar o decrementar el indice del procesador
            if(procesadorActual==this.procesadores.size()){
                this.procesadorAvanza = !this.procesadorAvanza;
            }
            //si da una vuelta completa por los procesadores y no se asigna la tarea, no hay solucon, puede que haya que cambiarla
            //porque es del planteo anterior.
            if(intentos==this.procesadores.size()){
                return null;
            }
        }
        return this.solucion;

    }


    private ArrayList<Procesador> ordenarProcesadores(ArrayList<Procesador>procesadores){
        ArrayList<Procesador> result = new ArrayList<>();
        for (Procesador p : procesadores){
            if(p.isEsta_refrigerado()){
                result.addFirst(p);
            }else {
                result.addLast(p);
            }
        }
        return result;
    }

    public Tarea seleccionar(Procesador p){
        // while para seleccionar la primer tarea posible para este procesador
        Tarea tareaActual=null;
        Iterator<Tarea> tareas = this.candidatos.iterator();
        boolean factible=false;
        while(tareas.hasNext()&&!factible){
            tareaActual = tareas.next();
            factible = esFactible(p,tareaActual);

        }
        return tareaActual;
    }
    private boolean esFactible(Procesador p,Tarea t){
        //verifica que la tarea seleccionada pueda ser ingresada en el procesador actual
        boolean factible = false;
        if(!p.isEsta_refrigerado()){
            factible = noSuperaTiempo(p,t);
        }
         factible = (factible && noSuperaCriticas(p,t));
        return factible;
    }
    private boolean noSuperaTiempo(Procesador p,Tarea t){
        return ((p.obtenerTiempo()+t.getTiempo()<this.tiempoLimite));
    }
    private boolean noSuperaCriticas(Procesador p,Tarea t){
        return(t.isCritica()&&p.cantidadTareasCriticas()>=2);
    }

}
