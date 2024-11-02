package ProgramacionIII.tpe;

import java.util.LinkedList;

public class SolucionGreedy {
    private LinkedList<Procesador> procesadores;
    private Integer tiempoDeProcesado;
    private int candidatosConsiderados;
    public SolucionGreedy() {
        this.procesadores = new LinkedList<>();
        this.tiempoDeProcesado = 0;
        this.candidatosConsiderados = 0;
    }
    public void aumentarCandidatos(){
        this.candidatosConsiderados ++;
    }
    public void agregarProcesador(Procesador p){
        this.procesadores.add(p);
        if(p.obtenerTiempo()>this.tiempoDeProcesado){
            tiempoDeProcesado=p.obtenerTiempo();
        }
    }
    public LinkedList<Procesador> getProcesadores() {
        return this.procesadores;
    }
    @Override
    public String toString(){
        String respuesta="Greedy: \n";
        respuesta += "Solucion obtenida: \n";
        for(Procesador p: this.procesadores){
            respuesta += (p.toString()+"\n");

        }
        respuesta +="Tiempo maximo de procesado: "+this.tiempoDeProcesado +"\n";

            respuesta += "Cantidad de candidatos considerados: "+this.candidatosConsiderados;
        return respuesta;

    }
}



