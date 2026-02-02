package banco;

import java.util.Random;

public class Tarjeta {
	private String numero;
	private Banco banco;
	private Boolean puedeGastar;
	
	private Random random;
	
	public Tarjeta(String numero, Banco banco, Boolean puedeGastar) {
		super();
		this.numero = numero;
		this.banco = banco;
		this.puedeGastar = puedeGastar;
		random = new Random();
	}
	
	public void generarMovimientos() {
		while(true) {
			Integer importe = random.nextInt(-200, 100);
			if (!puedeGastar) {
				importe = random.nextInt(0, 100);
			}
			banco.incrementarSaldo(numero, importe);
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
} 
