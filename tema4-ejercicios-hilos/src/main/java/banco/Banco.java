package banco;

public class Banco {
	private Integer saldo;
	
	public Banco() {
		saldo = 0;
	}
	public synchronized void incrementarSaldo(String numTarjeta, Integer incremento) {
		
		saldo += incremento;
		System.out.println(">> Tarjeta " + numTarjeta + " >> incrementa " 
				+ incremento + " >> Saldo final " + saldo);
		
		try {
			if (saldo < 0 && incremento < 0) {
				wait();
			} else if (saldo > 0){
				notify();
			}
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
