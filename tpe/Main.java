package ProgramacionIII.tpe;

public class Main {

	public static void main(String args[]) {
		Servicios servicios = new Servicios("./datasets/Procesadores.csv", "./datasets/Tareas.csv");
		BackTacking back = new BackTacking(servicios.getTareas(),servicios.getProcesadores(),51);
		/*
		System.out.println(servicios.servicio1("T3"));
		System.out.println(servicios.servicio2(false));
		System.out.println(servicios.servicio3(30,71));
		*/
		System.out.print(back.asignarTareas());
		//plantear tareas nuevas para que no haya solucion, a ver si devuelve null

	}
}
