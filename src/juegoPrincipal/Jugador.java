/**
 * 
 */
package juegoPrincipal;

/**
 * @author DAVID --- objeto jugador que participara en la carrera
 *
 */
public class Jugador {
	
	private String name;
	private int id;
	
	public Jugador(String name, int id){
		
		this.name = name;
		this.id = id;
		
	}
	
	public String getName() {
		return name;
	}
	
	public int getID(){
		return id;
	}

}
