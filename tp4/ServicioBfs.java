package ProgramacionIII.tp4;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

public class ServicioBfs {

    public boolean existeCaminoBfs(Integer origen,Integer destino,Grafo g){
        LinkedList<Integer> cola = new LinkedList<>();
        cola.add(origen);
        HashSet<Integer> visitados = new HashSet<>();
        boolean existeCamino = false;

        while(!cola.isEmpty()&&!existeCamino){
            Integer actual = cola.poll();

            Iterator<Integer> itAdy = g.obtenerAdyacentes(actual);
            while(itAdy.hasNext()&&!existeCamino){
                Integer ady = itAdy.next();
                cola.add(ady);
                if(ady.equals(destino)){
                    existeCamino = true;
                }
            }
        }

        return existeCamino;
    }
}
