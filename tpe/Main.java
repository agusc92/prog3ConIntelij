package ProgramacionIII.tpe;

public class Main {

	public static void main(String args[]) {
		Servicios servicios = new Servicios("tpe/datasets/Procesadores.csv", "tpe/datasets/Tareas.csv");
	
		System.out.println(servicios.servicio1("T3"));
		System.out.println(servicios.servicio2(false));
		System.out.println(servicios.servicio3(30,71));
		
	}
}
