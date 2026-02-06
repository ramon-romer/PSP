package ejercicioCarreras;

import java.util.Random;

public class Coche {

	private String nombre;
	private Integer velocidadPorSegundo;
	private Integer kmRecorridos;
	private Carrera carrera;
	private Random random;

	public Coche(String nombre, Integer velocidadPorSegundo, Carrera carrera) {
		super();
		this.nombre = nombre;
		this.velocidadPorSegundo = velocidadPorSegundo;
		this.carrera = carrera;
		kmRecorridos = 0;
		random = new Random();
	}

	public void correrCarrera() {
		do {
			pausar();
			avanzar();
			System.out.println(nombre + " >> va por el km " + kmRecorridos);
		} while (kmRecorridos < carrera.getKmTotales());
		System.out.println("Fin de carrera !!");
		carrera.subirAlPodio(nombre);
	}

	public void avanzar() {
		kmRecorridos = kmRecorridos + velocidadPorSegundo;
		if (kmRecorridos > carrera.getKmTotales()) {
			kmRecorridos = carrera.getKmTotales();
		}
	}

	public void pausar() {
		Integer tiempoEspera = random.nextInt(1, 4);
		try {
			Thread.sleep(tiempoEspera * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
