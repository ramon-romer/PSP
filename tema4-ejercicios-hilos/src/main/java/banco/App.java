package banco;

public class App {
	public static void main(String[] args) {
		Banco banco = new Banco();
		Tarjeta t1 = new Tarjeta("1111A", banco, true);
		Tarjeta t2 = new Tarjeta("2222B", banco, false);
	
		Thread thread1 = new Thread(() -> t1.generarMovimientos());
		Thread thread2 = new Thread(() -> t2.generarMovimientos());
		
		thread1.start();
		thread2.start();
		
		System.out.println("Fin hilo principal");
	}
}
