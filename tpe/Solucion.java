package ProgramacionIII.tpe;

import java.util.LinkedList;

public class Solucion {
    private LinkedList<Procesador> procesadores;
    private Integer tiempoDeProcesado;

    public Solucion() {
        this.procesadores = null;
        this.tiempoDeProcesado = null;
    }

    public void copiarEstado(Estado e){
        this.procesadores = e.getListaProcesadores();
        this.tiempoDeProcesado = e.getTiempo();
    }

    public LinkedList<Procesador> getProcesadores() {
        return procesadores;
    }

    public Integer getTiempoDeProcesado() {
        return tiempoDeProcesado;
    }
}
