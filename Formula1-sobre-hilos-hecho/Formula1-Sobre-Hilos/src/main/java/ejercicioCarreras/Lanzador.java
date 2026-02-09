package ejercicioCarreras;

public class Lanzador {
	public static void main(String[] args) {
		Carrera carrera = new Carrera(1000);
		Coche cocheAlonso = new Coche("Alonso", 100, carrera);

		// TODO: Los dos coches nuevos creados
		Coche cocheHamilton = new Coche("Hamilton", 101, carrera);
		Coche cocheSainz = new Coche("Sainz", 99, carrera);

		// TODO: Son los tres threads para que realicen la salida
		Thread thread1 = new Thread(() -> cocheAlonso.correrCarrera());
		Thread thread2 = new Thread(() -> cocheHamilton.correrCarrera());
		Thread thread3 = new Thread(() -> cocheSainz.correrCarrera());

		thread1.start();
		thread2.start();
		thread3.start();

		carrera.imprimirPodio();
		
		System.out.println("Carrera finalizada!!");
	}

}
