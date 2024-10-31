package ProgramacionIII.tpe;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

public class Estado {
    private LinkedList<Procesador> procesadores;
    private Integer tiempo;
    private LinkedList<Tarea> noAsignadas;
    private int cantidadMaximaCriticas;
    public Estado(LinkedList<Procesador> procesadores,LinkedList<Tarea> tareas) {
        this.procesadores = procesadores;
        this.noAsignadas = tareas;
        this.cantidadMaximaCriticas = 2;
    }

    public Iterator<Procesador> getProcesadores() {
        return this.procesadores.iterator();
    }

    public Tarea tareaNoAsignada(){
        return null;
    }

    public LinkedList<Procesador> getListaProcesadores(){
        return this.procesadores;
    }

    public Integer getTiempo(){

        int tiempoMaximo = 0;
        for(Procesador procesador :this.procesadores){
            if(procesador.obtenerTiempo()>tiempoMaximo){
                tiempoMaximo = procesador.obtenerTiempo();
            }
        }
        this.tiempo= tiempoMaximo;
        return this.tiempo;
    }

    public boolean esEstadoFinal(){
        return this.noAsignadas.isEmpty();
    }

    public Tarea obtenerTarea(){
        return this.noAsignadas.getFirst();
    }

    public void actualizar(Procesador procesador,Tarea tarea){
        procesador.agregarTarea(tarea);
        this.noAsignadas.remove(tarea);
    }

    public void deshacer(Procesador procesador,Tarea tarea){
        this.noAsignadas.addFirst(tarea);
        procesador.quitarTarea(tarea);
    }
    public boolean esFactible(Integer tiempoLimite){
        boolean factible = true;
        for(Procesador p : this.procesadores){
            if(!p.isEsta_refrigerado()){
                factible = p.obtenerTiempo()<tiempoLimite;
                if(!factible) {
                    return factible;
                }
            }
            factible = p.cantidadTareasCriticas() <= this.cantidadMaximaCriticas;
                if(! factible){
                    return factible;
                }

        }
        return factible;
    }

}
