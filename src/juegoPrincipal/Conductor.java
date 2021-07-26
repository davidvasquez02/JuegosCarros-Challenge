/**
 * 
 */
package juegoPrincipal;

/**
 * @author DAVID --- Objeto conductor compuesto de un jugador y un carro.
 *
 */
public class Conductor {
	
	private Carro carro;
	private Jugador jugador;
	private int turnos=0;
	private int timesFirst =0;
	
	
	public Conductor(Carro carro, Jugador jugador) {
		
		this.carro = carro;
		this.jugador = jugador;
		
	}
	
	public Jugador getJugador() {
		return jugador;	
	}
	
	public Carro getCarro() {
		return carro;
	}
	
	public void increaseTurnos() {
		turnos++;
	}
	public int getTurnos() {
		return turnos;
	}
	public void resetTurnos() {
		turnos=0;
	}
	public void increaseTimesFirst(){
		timesFirst+=1;
	}
	public int getTimesFirst() {
		return timesFirst;
	}
	
	

}
