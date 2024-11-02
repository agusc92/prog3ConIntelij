package ProgramacionIII.tpe;
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

    public LinkedList<Procesador> getListaProcesadores(){
        return this.procesadores;
    }

    public Integer getTiempo(){
        //se calcual y devuelve el tiempo mayor de procesado
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
            if(!p.isEsta_refrigerado()){//en caso de no ser refrijerado controla que no se exeda el tiempo limite
                factible = p.obtenerTiempo()<tiempoLimite;
                if(!factible) {
                    return factible;
                }
            }
            //en caso que la tarea sea critica se controla que el
            //procesador no contenga 2 tareas griticas ya ingresadas
            factible = p.cantidadTareasCriticas() <= this.cantidadMaximaCriticas;
                if(! factible){
                    return factible;
                }

        }
        return factible;
    }

}
