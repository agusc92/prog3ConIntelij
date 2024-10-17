package ProgramacionIII.tp4;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;

public class ServicioDfs {
    private Grafo<?> grafo;
    private Hashtable<Integer, String> vertices;//creo un hash para reflejar el estado de los vertices

    public ServicioDfs(Grafo<?> grafo){
        this.grafo = grafo;
    }

    public LinkedList<Integer> Dfs(){
        vertices = new Hashtable<>();
        LinkedList<Integer> resultado = new LinkedList<Integer>();
        Iterator<Integer> it = this.grafo.obtenerVertices();
        while(it.hasNext()){
            vertices.put(it.next(),"blanco");//inicializo la estructura auxiliar con todos los vertices en blanco
        }
        it = this.grafo.obtenerVertices();//reinicio el iterador
        while(it.hasNext()){
            Integer origen = it.next();
            if(vertices.get(origen).equals("blanco")){//visito el vertice solo si esta en blanco
                this.dfsVisit(origen,resultado);
            }
        }
        return resultado;
    }

    public void dfsVisit(Integer origen, LinkedList<Integer> resultado){
        this.vertices.put(origen,"amarillo");//marco inmediatamente el vertise visitado en amarillo
        resultado.add(origen);//lo adiero al resultado, ya que fue visitado
        Iterator<Integer> adyasentes = this.grafo.obtenerAdyacentes(origen);//
        while(adyasentes.hasNext()){
            Integer siguiente = adyasentes.next();
            if(this.vertices.get(siguiente).equals("blanco"))//si el siguiente es blanco
                this.dfsVisit(siguiente,resultado);//llamo recursivamente con el siguiente
        }
        this.vertices.put(origen,"negro");
    }
    public boolean tieneBucle(){
        this.vertices = new Hashtable<>();
        boolean resultado = false;
        Iterator<Integer> it = this.grafo.obtenerVertices();
        while(it.hasNext()){
            vertices.put(it.next(),"blanco");//inicializo la estructura auxiliar con todos los vertices en blanco
        }
        it = this.grafo.obtenerVertices();//reinicio el iterador
        while(it.hasNext()){
            Integer origen = it.next();
            if(vertices.get(origen).equals("blanco")){//visito el vertice solo si esta en blanco
               resultado = this.dfsBucle(origen);
            }
        }
        return resultado;
    }

    private boolean dfsBucle(Integer origen){

        this.vertices.put(origen,"amarillo");
        boolean  resultado = false;
        Iterator<Integer> it = this.grafo.obtenerAdyacentes(origen);
        while (it.hasNext()){
            Integer siguiente = it.next();
            if(this.vertices.get(siguiente).equals("blanco")){
                  resultado = this.dfsBucle(siguiente);
                if(resultado){
                    return resultado;}
            }
            if(this.vertices.get(siguiente).equals("amarillo")){
                return true;
            }

        }
        this.vertices.put(origen, "negro");
        return resultado;
    }
}
