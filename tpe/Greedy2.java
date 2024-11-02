package ProgramacionIII.tpe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class Greedy2 {

        private SolucionGreedy solucion;
        private LinkedList<Tarea> candidatos;
        private Integer tiempoLimite;
        private ArrayList<Procesador> procesadores;
        private int contadorHijos;
        private boolean primera;
        private boolean procesadorAvanza;
        public Greedy2(LinkedList<Tarea> tareas, ArrayList<Procesador> procesadores, Integer tiempoLimite) {
            this.procesadores = procesadores;
            this.tiempoLimite = tiempoLimite;
            this.solucion = new SolucionGreedy();
            this.contadorHijos=0;
            this.primera = true;
            this.procesadorAvanza = true;
            this.candidatos = tareas;
        }

    public SolucionGreedy asignarTareas(){
        Collections.sort(this.candidatos);
        int procesadorIndex = 0;
        int intentos = 0;
        int tareasAsignadas=0;
        boolean invertida=false;
        int candidatosConsiderados = 0;
        Tarea t = null;





            while(intentos<this.procesadores.size()&&!this.candidatos.isEmpty()){
                if(t==null){
                    if(invertida){
                        t = this.candidatos.pop();

                    }else{
                        t = this.candidatos.poll();
                    }
                    this.solucion.aumentarCandidatos();
                }


                Procesador pActual = this.procesadores.get(procesadorIndex);


                if (esFactible(pActual,t)){
                    pActual.agregarTarea(t);
                    intentos=0;
                    tareasAsignadas ++;
                    t=null;
                    if(tareasAsignadas==this.procesadores.size()){
                        invertida = !invertida;
                        tareasAsignadas = 0;
                    }
                }else {
                    intentos ++;
                }
                procesadorIndex ++;
                if(procesadorIndex==this.procesadores.size()){
                    procesadorIndex=0;
                }
            }
            if (intentos==this.procesadores.size()){
                return null;
            }

        for (Procesador p :this.procesadores){
            solucion.agregarProcesador(p);
        }

        return this.solucion;
    }
    private boolean esFactible(Procesador p,Tarea t){
        //verifica que la tarea seleccionada pueda ser ingresada en el procesador actual
        boolean factible = true;
        if(!p.isEsta_refrigerado()){
            factible = noSuperaTiempo(p,t);
        }
        if(t.isCritica()){
            factible = (factible && noSuperaCriticas(p));
        }

        return factible;
    }
    private boolean noSuperaTiempo(Procesador p,Tarea t){
        return ((p.obtenerTiempo()+t.getTiempo()<this.tiempoLimite));
    }
    private boolean noSuperaCriticas(Procesador p){
        return(p.cantidadTareasCriticas()<2);
    }
}
