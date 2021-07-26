/**
 * 
 */
package juegoPrincipal;

/**
 * @author DAVID --- Carril por donde ira cada carro y llevará el conteo de los Kms.
 *
 */
public class Carril {
	
	private Carro carro;
	private int distancia = 0; //almacena la distancia recorrida en mts.
	
	public Carril(Carro carro) {
		this.carro = carro;
	}
	
	public int getDistancia() {
		return distancia;
	}
	
	public void increaseDistancia(int incremento) {
		distancia+=(incremento*100);
	}
	
	public void resetDistancia() {
		distancia = 0;
	}
	
	public Carro getCarro() {
		return carro;
	}
	
	

}
