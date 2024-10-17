package ProgramacionIII.tp4;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;

public class GrafoDirigido<T> implements Grafo<T> {
	private Hashtable<Integer, LinkedList<Arco<T>>> vertices;

	public GrafoDirigido(){
		this.vertices = new Hashtable<>();
	}

	@Override
public void agregarVertice(int verticeId) {
		if(!this.vertices.contains(verticeId))
			this.vertices.put(verticeId, new LinkedList<Arco<T>>());
	}

	@Override
	public void borrarVertice(int verticeId) {
		//borro el vertice
		this.vertices.remove(verticeId);
		//obtengo los vertices uno a uno
		for(LinkedList<Arco<T>> arcos : this.vertices.values()){
			//creo un iterador para iterar sus arcos
			Iterator<Arco<T>> iteradorArcos = arcos.iterator();
			while(iteradorArcos.hasNext()){
				Arco<T> arcoActual = iteradorArcos.next();
				//si algun arco tiene como destino el vertice borrado, lo elimino
				if(arcoActual.getVerticeDestino() == verticeId){
					arcos.remove(arcoActual);
				}
			}
		}

	}

	@Override
	public void agregarArco(int verticeId1, int verticeId2, T etiqueta) {
		LinkedList<Arco<T>> arcos = this.vertices.get(verticeId1);
		Arco<T> nuevoArco = new Arco<>(verticeId1,verticeId2,etiqueta);

		if(this.vertices.containsKey(verticeId2))
			if(arcos != null &&!arcos.contains(nuevoArco))
				arcos.addFirst(nuevoArco);
	}

	@Override
	public void borrarArco(int verticeId1, int verticeId2) {
		//obtengo los arcos del verticeh
		LinkedList<Arco<T>> arcos = this.vertices.get(verticeId1);
		//creo un iterador para recorrerlos
		Iterator<Arco<T>> iteradorArcos = arcos.iterator();
		while(iteradorArcos.hasNext()){
			Arco<T> arco = iteradorArcos.next();
			//si algun arco tiene como destino el vertice, lo borro
			if(arco.getVerticeDestino()==verticeId2){
				arcos.remove(arco);
			}
		}

	}

	@Override
	public boolean contieneVertice(int verticeId) {
		return this.vertices.contains(verticeId);
	}

	@Override
	public boolean existeArco(int verticeId1, int verticeId2) {
		Iterator<Arco<T>> arcos= vertices.get(verticeId1).iterator();
		while(arcos.hasNext()){
			if(arcos.next().getVerticeDestino()==verticeId2){
				return true;
			}
		}
		return false;
	}

	@Override
	public Arco<T> obtenerArco(int verticeId1, int verticeId2) {
		Iterator<Arco<T>> arcos = this.vertices.get(verticeId1).iterator();
		while(arcos.hasNext()){
			Arco<T> arco= arcos.next();
			if(arco.getVerticeDestino()==verticeId2)
				return arco;
		}
		return null;
	}

	@Override
	public int cantidadVertices() {

		return this.vertices.size();
	}

	@Override
	public int cantidadArcos() {
		int cantArcos = 0;
		for(LinkedList<Arco<T>> arcos : this.vertices.values()){
			cantArcos += arcos.size();
		}
		return cantArcos;
	}

	@Override
	public Iterator<Integer> obtenerVertices() {
		LinkedList<Integer> iteradorVertices = new LinkedList<>();
		for(int arcos : this.vertices.keySet()){
			iteradorVertices.addFirst(arcos);
		}
		return iteradorVertices.iterator();
	}

	@Override
	public Iterator<Integer> obtenerAdyacentes(int verticeId) {
		Iterator<Arco<T>> iterador = this.vertices.get(verticeId).iterator();
		LinkedList<Integer> adyacentes = new LinkedList<Integer>();
		while(iterador.hasNext()){
			adyacentes.addFirst(iterador.next().getVerticeDestino());
		}
		return adyacentes.iterator();
	}

	@Override
	public Iterator<Arco<T>> obtenerArcos() {
		LinkedList<Arco<T>> iteradorArcos = new LinkedList<>();
		for(LinkedList<Arco<T>> arcos : this.vertices.values()){
			iteradorArcos.addAll(arcos);
		}
		return iteradorArcos.iterator();

	}

	@Override
	public Iterator<Arco<T>> obtenerArcos(int verticeId) {

		return this.vertices.get(verticeId).iterator();
	}

}
