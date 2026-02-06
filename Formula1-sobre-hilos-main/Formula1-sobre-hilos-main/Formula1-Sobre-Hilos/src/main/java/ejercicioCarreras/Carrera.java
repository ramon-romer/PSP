package ejercicioCarreras;

public class Carrera {

	private Integer kmTotales;
	private String[] podio;
	private Integer personasEnPodio;
	
	public Carrera(Integer kmTotales) {
		this.kmTotales = kmTotales;
		podio = new String[3];
		personasEnPodio = 0;
	}

	public Integer getKmTotales() {
		return kmTotales;
	}

	public void setKmTotales(Integer kmTotales) {
		this.kmTotales = kmTotales;
	}
	
	public synchronized void subirAlPodio(String nombreCoche) {
		if (personasEnPodio < podio.length) {
	        podio[personasEnPodio] = nombreCoche;
	        personasEnPodio++;
	        notifyAll();
	    }
	}
	
	public void  imprimirPodio() {
		System.out.println("Podio de la carrera: ");
		for (int i = 0; i < podio.length; i++) {
			System.out.println("\tLugar " + (i+1) + " >> " + podio[i]);
		}
	}
	
	
}
