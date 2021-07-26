/**
 * 
 */
package juegoPrincipal;

import java.util.ArrayList;

/**
 * @author DAVID --- Almacena el podio de los 3 primeros lugares de cada recorrido en una pista
 *
 */
public class Podio {
	
	private ArrayList<Integer> firstPlace = new ArrayList<>();
	private ArrayList<Integer> secondPlace = new ArrayList<>(); 
	private ArrayList<Integer> thirdPlace = new ArrayList<>();
	
	public Podio() {
	}
	public void increaseFirstPlace(int id) {
	}
	
	public void setFirstPlace(int id){
		firstPlace.add(id);
	}
	public void setSecondPlace(int id){
		secondPlace.add(id);
	}
	public void setThirdPlace(int id){
		thirdPlace.add(id);
	}
	public void setGeneralPlace(int i) {
				
	}
	
	public String formatPodios(ArrayList<Conductor> conductores) {
		
		String formatPodios="\n";
				
		for(int i=0; i<firstPlace.size(); i++) {
			//suma veces que quedo en primer lugar a la general
			conductores.get(firstPlace.get(i)).increaseTimesFirst();
			//crea una string con los podios de todos los recorridos de la pista.
			formatPodios+=("-Resultados Circuito Nro. " + (i+1));
			formatPodios+=(":-\n\n Primer lugar para: " + conductores.get(firstPlace.get(i)).getJugador().getName());
			formatPodios+=(".\n Segundo lugar para: " + conductores.get(secondPlace.get(i)).getJugador().getName());
			formatPodios+=(".\n Tercer lugar para: " + conductores.get(thirdPlace.get(i)).getJugador().getName() + ".\n\n");
		}
		
		formatPodios+=formatPodiosGeneral(conductores);
		
		return formatPodios;
		
	}
	private String formatPodiosGeneral(ArrayList<Conductor> conductores) {
		
		String formatPodios="";
		
		formatPodios+="\nPodio General de Victorias: \n";
		
		for(int i=0; i<conductores.size(); i++) {
			formatPodios+="\n "+ conductores.get(i).getJugador().getName() + ": " + conductores.get(i).getTimesFirst(); 	
		}
		
		
		return formatPodios;
	}
	

}
