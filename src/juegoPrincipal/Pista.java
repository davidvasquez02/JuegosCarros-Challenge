/**
 * 
 */
package juegoPrincipal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;

/**
 * @author DAVID --- Pista en donde se ubicaran los carriles y los recorridos de la carrera 
 *
 */
public class Pista {
	
	private ArrayList<Carril> carriles = new ArrayList<Carril>();
	private ArrayList<Integer> recorridos = new ArrayList<Integer>();
	
	public Pista(ArrayList<Carril> carriles) {
		this.carriles = carriles;
	}
	
	public void setRecorridos(int cant) throws ParseException { 
		
		for(int i=0; i<cant; i++) {
			Integer distanciaRecorrido = formatNumber();
			recorridos.add(distanciaRecorrido);
		}
	}
	
	public String printRecorridos() {
		
		String recorridosFormat = "\n";
		
		for(int i=0; i<recorridos.size(); i++) {
			
			recorridosFormat+="Recorrido " + (i+1) + ": tiene " + ((double)recorridos.get(i)/1000) + "kms \n";
		}
		
		return recorridosFormat;
		
	}
	
	public int cantRecorridos() {
		return recorridos.size();
	}
	public int getRecorrido(int i) {
		return recorridos.get(i);
	}
	
	private Integer formatNumber() throws ParseException {
		
		DecimalFormat formateador = new DecimalFormat("###0.##");
		double formatMts = Math.random();
		formatMts = formateador.parse(formateador.format(formatMts)).doubleValue();
		
		return ((int)(formatMts*6000)+2000);
	}
	
	public int findCarril (int j) {
		
		for(int i=0; i< carriles.size(); i++) {
			if(carriles.get(i).getCarro().getID() == j) {
				return i;
			}
		}
		
		return -1;
		
	}
	
	public Carril getCarril(int index) {
		return carriles.get(index);
	}
	
}


