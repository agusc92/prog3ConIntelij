package ProgramacionIII.tpe;

import java.util.LinkedList;

public class SolucionBacktracking {
    private LinkedList<Procesador> procesadores;
    private Integer tiempoDeProcesado;
    private int cantidadHijos;
    public SolucionBacktracking() {
        this.procesadores = new LinkedList<>();
        this.tiempoDeProcesado = 0;
        this.cantidadHijos=0;

    }

    public void copiarEstado(Estado e,int cantidadHijos){
        //Obtenemos la solucion a partir de un estado, realizando una copia de los procesadores para no
        //manipular el mismo objeto desde diferentes estructuras
        for(Procesador p :e.getListaProcesadores()){
            Procesador aux = new Procesador(p.getId_procesador(),p.getCodigo_procesador(),p.isEsta_refrigerado(),p.getAno_funcionamiento());
            aux.agregarTareas(new LinkedList<>(p.obtenerTareas()));
            this.procesadores.add(aux);
        }
        this.tiempoDeProcesado = e.getTiempo();
        this.cantidadHijos = cantidadHijos;
    }

    public LinkedList<Procesador> getProcesadores() {
        return this.procesadores;
    }

    public Integer getTiempoDeProcesado() {
        return tiempoDeProcesado;
    }


    @Override
    public String toString(){
        //previamente definimos el metodo ToString en todos los elementos de la solucion y aqui realizamos
        //El ensamblado del string que sera mostrado en el main.
        String respuesta="Backtracking: \n";
        respuesta += "Solucion obtenida: \n";
        for(Procesador p: this.procesadores){
            respuesta += (p.toString()+"\n");

        }
        respuesta +="Tiempo maximo de procesado: "+this.tiempoDeProcesado +"\n";

            respuesta += "Cantidad de hijos generados: "+this.cantidadHijos;


        return respuesta;

    }

    /*
	Backtracking
	Solución obtenida: cada procesador con las tareas asignadas.
	Solución obtenida: tiempo máximo de ejecución.
	Métrica para analizar el costo de la solución (cantidad de estados generados)

	 */
}
