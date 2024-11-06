package ProgramacionIII.tp4;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

public class BuscadorCaminos {
    private Grafo<?> grafo;
    private HashSet<Integer> visitados; //guardo los vertices que ya visite
    private LinkedList<LinkedList<Integer>> todosLosCaminos = new LinkedList<>();
    public BuscadorCaminos(Grafo<?> grafo){
        this.grafo = grafo;
        this.visitados = new HashSet<>();
    }
    public boolean existeCamino(Integer origen , Integer destino){
        this.visitados.add(origen); //guardo el origen, ya que lo estoy visitando en este momento
        if(origen.equals(destino) ){//si el origen es el destino, llegue
            return true;
        }
        Iterator<Integer> adyacentes = this.grafo.obtenerAdyacentes(origen);//obtengo los adyasentes al origen
        while(adyacentes.hasNext()){//recorro los adyasentes
            Integer siguiente = adyacentes.next();
            if(!this.visitados.contains(siguiente)){//si el adyasente siguiente no esta entre los visitados
               boolean respuesta = this.existeCamino(siguiente,destino);//busco camino a partir de el adyasente
               if(respuesta)
                   return respuesta;//en este punto retorno la respuesta solo si es verdadera.
            }

        }


        //si recorri todo el grafo y no encontre un camino, retorno false.
        return false;
    }
    public LinkedList<Integer> obtenterCaminoMasLargo(Integer origen, Integer destino){

        this.visitados.clear();//limpio el hash por si lo use co otro metodo
        return caminoMasLargo(origen, destino, new LinkedList<>(), new LinkedList<>());
    }

    private LinkedList<Integer> caminoMasLargo(Integer origen , Integer destino,
                                               LinkedList<Integer> actual,LinkedList<Integer> mejor){
        this.visitados.add(origen);
        actual.add(origen); //agrego el vertice visitado al arreglo "actual"
        if(origen.equals(destino)){

            if(actual.size()>mejor.size()){//si llego al destino y el arreglo actual es mas largo que el mejor
                mejor = new LinkedList<Integer>(actual);//el actual se vuelve una copia del mejor
            }
        }else{//si el actual no es el destino
                Iterator<Integer> adyasentes = this.grafo.obtenerAdyacentes(origen);
                while(adyasentes.hasNext()){//itero los adyasentes
                    Integer siguiente = adyasentes.next();
                    if(!this.visitados.contains(siguiente)){
                        mejor = caminoMasLargo(siguiente,destino,actual,mejor);//llamo recursivamente con el siguiente
                    }
                }
        }
        actual.removeLast();
        this.visitados.remove(origen);//si no llego al destino por este camino, deshago el avance actual
        return mejor;
    }
    public LinkedList<LinkedList<Integer>> todosLosCaminosHacia(Integer destino){
        this.visitados.clear();//limpio el hash por si lo use co otro metodo
        this.todosLosCaminos.clear();
        Iterator<Integer> vertices = this.grafo.obtenerVertices();
        while (vertices.hasNext()){

            encontrarTodosLosCaminosHacia(vertices.next(),destino, new LinkedList<>());
        }
        return  this.todosLosCaminos;
    }

    private void encontrarTodosLosCaminosHacia(Integer origen,Integer destino ,LinkedList<Integer>actual ){
        this.visitados.add(origen);
        actual.add(origen); //agrego el vertice visitado al arreglo "actual"
        if(origen.equals(destino)){
            this.todosLosCaminos.add(new LinkedList<Integer>(actual));

        }else{//si el actual no es el destino
            Iterator<Integer> adyasentes = this.grafo.obtenerAdyacentes(origen);
            while(adyasentes.hasNext()){//itero los adyasentes
                Integer siguiente = adyasentes.next();
                if(!this.visitados.contains(siguiente)){
                   encontrarTodosLosCaminosHacia(siguiente,destino,actual);//llamo recursivamente con el siguiente
                }
            }
        }
        actual.removeLast();
        this.visitados.remove(origen);//si no llego al destino por este camino, deshago el avance actual

    }

}

