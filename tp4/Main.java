package ProgramacionIII.tp4;

import java.util.Iterator;
import java.util.LinkedList;

public class Main {

	public static void main(String[] args) {

		// Creo un grafo dirigdo donde las etiquetas de los arcos son valores Float
		GrafoDirigido<Float> grafito = new GrafoDirigido<>();
		ServicioDfs dfs = new ServicioDfs(grafito);
		BuscadorCaminos gps = new BuscadorCaminos(grafito);
		// Agrego los vertices 1 y 2
		grafito.agregarVertice(2);
		grafito.agregarVertice(1);
		grafito.agregarVertice(4);
		grafito.agregarVertice(3);
		grafito.agregarVertice(6);
		grafito.agregarVertice(7);
		grafito.agregarVertice(5);
		grafito.agregarVertice(8);

		 //Genero un arco desde 1 hasta 2 con el valor de etiqueta 3
		grafito.agregarArco(1, 5, 2F);
		grafito.agregarArco(1, 2, 1F);
		grafito.agregarArco(2, 3, 4F);
		grafito.agregarArco(1, 4, 3F);
		grafito.agregarArco(7, 4, 12F);
		grafito.agregarArco(2, 5, 5F);
		//grafito.agregarArco(3, 1, 6F);
		grafito.agregarArco(4, 5, 8F);
		grafito.agregarArco(8, 5, 15F);
		grafito.agregarArco(4, 6, 7F);
		grafito.agregarArco(6, 5, 11F);
		grafito.agregarArco(5, 3, 9F);
		grafito.agregarArco(7, 6, 13F);
		grafito.agregarArco(7, 8, 14F);


		// Obtengo el arco entre 1 y 2, y le pido la etiqueta
		//Float etiqueta = grafito.obtenerArco(6, 5).getEtiqueta();
		
		Iterator<Integer> verticesAd = grafito.obtenerAdyacentes(7);
	/*
		while(verticesAd.hasNext()){
			Integer vertice = verticesAd.next();
			System.out.println(vertice);
		}
		LinkedList<Integer> recorridoDfs = dfs.Dfs();
		verticesAd = recorridoDfs.iterator();
		while (verticesAd.hasNext()){
			Integer caminoActual = verticesAd.next();
			System.out.println(caminoActual);
		}
		*/
	Iterator<Integer> camino = gps.obtenterCaminoMasLargo(7,3).iterator();
	while (camino.hasNext()) {
		System.out.println(camino.next());
	}

	}

}
