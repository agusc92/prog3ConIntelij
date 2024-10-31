package ProgramacionIII.tpe;

import java.util.LinkedList;

public class Procesador {
    private String id_procesador;
    private String codigo_procesador;
    private boolean esta_refrigerado;
    private Integer ano_funcionamiento;
    private LinkedList<Tarea> tareas;
    private int cantidadTareasCriticas;
    public Procesador(String id_procesador,String codigo_procesador,boolean esta_refrigerado,Integer ano_funcionamiento) {
        this.id_procesador = id_procesador;
        this.codigo_procesador = codigo_procesador;
        this.esta_refrigerado = esta_refrigerado;
        this.ano_funcionamiento = ano_funcionamiento;
        this.tareas = new LinkedList<>();
        this.cantidadTareasCriticas = 0;
    }

    public String getCodigo_procesador() {
        return codigo_procesador;
    }

    public boolean isEsta_refrigerado() {
        return esta_refrigerado;
    }

    public Integer getAno_funcionamiento() {
        return ano_funcionamiento;
    }

    public String getId_procesador() {
        return id_procesador;
    }

    public void agregarTarea(Tarea tarea){
        if(tarea.isCritica()){
            this.cantidadTareasCriticas ++;
        }
        this.tareas.addFirst(tarea);
    }

    public void quitarTarea(Tarea tarea){
        if(tarea.isCritica()){
            this.cantidadTareasCriticas --;
        }
        this.tareas.remove(tarea);

    }
    public Integer obtenerTiempo(){
        int tiempoTotal = 0;
        for (Tarea tarea : this.tareas){
            tiempoTotal+= tarea.getTiempo();
        }
        return tiempoTotal;
    }
    public void agregarTareas(LinkedList<Tarea>tareas){
        this.tareas = tareas;
    }
    public LinkedList<Tarea> obtenerTareas(){
        return this.tareas;
    }
    public int cantidadTareasCriticas(){
       return this.cantidadTareasCriticas;
    }
    @Override
    public String toString(){
        String respuesta="Procesador: "+this.id_procesador+" Tareas: [";
        for(Tarea t: this.tareas){
            respuesta += t.toString();
        }
        return respuesta+="] Tiempo: "+this.obtenerTiempo();

    }
}

