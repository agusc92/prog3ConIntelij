package ProgramacionIII.tpe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class Greedy {

        private SolucionGreedy solucion;
        private LinkedList<Tarea> candidatos;
        private Integer tiempoLimite;
        private ArrayList<Procesador> procesadores;
        private int contadorHijos;
        public Greedy(LinkedList<Tarea> tareas, ArrayList<Procesador> procesadores, Integer tiempoLimite) {
            this.procesadores = procesadores;
            this.tiempoLimite = tiempoLimite;
            this.solucion = new SolucionGreedy();
            this.contadorHijos=0;
            this.candidatos = tareas;
        }

    public SolucionGreedy asignarTareas(){
        Collections.sort(this.candidatos);//ordena las tareas de mayor a menor duracion.
        int procesadorIndex = 0;//se utiliza para elegir a cual procesador acceder.
        int intentos = 0;//se utiliza para saber si una tarea ya se intento meter en todos los procesadores.
        int tareasAsignadas=0;//se utiliza para saber cuantas tareas fueron asignadas.
        boolean invertida=false;//se utiliza para saber de que extemo de la lista se obtendra la tarea.
        Tarea t = null;
            while(intentos<this.procesadores.size()&&!this.candidatos.isEmpty()){
                if(t==null){//verifica si la tarea elegida anteriormente pudo ser asignada
                    if(invertida){
                        t= this.candidatos.getLast();
                        this.candidatos.remove(t);
                    }else{
                        t = this.candidatos.getFirst();
                        this.candidatos.remove(t);
                    }
                    this.solucion.aumentarCandidatos();
                }

                Procesador pActual = this.procesadores.get(procesadorIndex);

                if (esFactible(pActual,t)){//verifica si la tarea seleccionada puede ser agregada al procesador actual.
                    pActual.agregarTarea(t);
                    intentos=0;//al asignar la tarea se reinicia el contador de intentos
                    tareasAsignadas ++;
                    t=null;//al ser asignada la tarea, se limpia la variable de tarea actual.
                    if(tareasAsignadas==this.procesadores.size()){
                        //al asignar una tarea en cada procesador, se cambia el extremo de la lista de donde
                        //se obtienen las tareas (lo mas eficiente posible).
                        invertida = !invertida;
                        tareasAsignadas = 0;
                    }
                }else {
                    intentos ++;
                }
                procesadorIndex ++;//se avanza al siguiente procesador.
                if(procesadorIndex==this.procesadores.size()){
                    //se reinicia la posicion del procesador actual para volver a asignar desde el principio.
                    procesadorIndex=0;
                }
            }
            if (intentos==this.procesadores.size()){
                //si una tarea se intento agregar a todos los procesadores y no se logro, no hay solucion.
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
            factible = noSuperaTiempo(p,t);//en caso de no ser refrijerado controla que no se exeda el tiempo limite
        }
        if(t.isCritica()){
            factible = (factible && noSuperaCriticas(p));//en caso que la tarea sea critica se controla que el
                                                        //procesador no contenga 2 tareas griticas ya ingresadas
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
