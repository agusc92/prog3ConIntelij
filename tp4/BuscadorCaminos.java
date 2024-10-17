package ProgramacionIII.tp4;

import java.util.HashSet;
import java.util.Iterator;

public class BuscadorCaminos {
    private Grafo<?> grafo;
    private HashSet<Integer> visitados; //guardo los vertices que ya visite
    public BuscadorCaminos(Grafo<?> grafo){
        this.grafo = grafo;
        this.visitados = new HashSet<>();
    }
    public boolean existeCamino(Integer origen , Integer destino){
        this.visitados.add(origen); //guardo el origen, ya que lo estoy visitando en este momento
        if(origen.equals(destino) ){//si el origen es el destino, llegue
            return true;
        }
        Iterator<Integer> adyacentes = this.grafo.obtenerAdyacentes(origen);//obtengo los adyasentes al destino
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
}
