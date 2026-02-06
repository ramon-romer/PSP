package ejercicioCarreras;

public class Lanzador {
	public static void main(String[] args) {
		Carrera carrera = new Carrera(1000);
		Coche cocheAlonso = new Coche("Alonso", 100, carrera);
		cocheAlonso.correrCarrera();
		carrera.imprimirPodio();
	}
}
