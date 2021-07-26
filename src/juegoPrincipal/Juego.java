package juegoPrincipal;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;


public class Juego {

	// Programa principal del juego
	public static void main(String[] args) throws ParseException {
		
		boolean seguir = true;
		int contPartidas = 0;

		do {			
			contPartidas++;
			printStart(contPartidas);
			ArrayList<Jugador> Jugadores = createJugador();	
			ArrayList<Conductor> Conductores = createConductor(Jugadores);
			ArrayList<Carril> Carriles = setCarriles(Conductores);
			Pista pista = createPista(Carriles);
			//desarrolla la partida y devuelve un podio con los resultados
			Podio podio = startJuego(Conductores, pista);
			//imprime resultados
			String resultados = podio.formatPodios(Conductores);
			System.out.println(resultados);
			//guarda resultados
			savePodios(resultados, contPartidas);	
			
			seguir = askContinuar();
	
		}
		while(seguir);
		
	}
	
	//Pregunta si el jugador desea realizar otra partida desde cero.
	private static boolean askContinuar() {
		
		Scanner sc = new Scanner(System.in);
		String respuesta;

		do {
			System.out.println("Deseas jugar otra partida? Responde S/N");
			respuesta = sc.next();
		
			if(respuesta.equalsIgnoreCase("S")) {
				System.out.println("\n\n---Genial, vamos a jugar de nuevo.");
				return true;
			}
			else if(respuesta.equalsIgnoreCase("N")) {
				System.out.println("\n\n---Vale, hasta la proxima conductor.");
				return false;
			}else {
				System.out.println("Opcion invalida.");
			}
		}while(respuesta.equalsIgnoreCase("s")==false && respuesta.equalsIgnoreCase("n")==false);
		return false;
	}

	// imprime los mensajes iniciales del programa
	private static void printStart(int contPartidas) { 
		
		System.out.println("\n---JUEGO DE CARRERAS---\n");
		System.out.println("Partida No. " + contPartidas);
		System.out.println("En este juego podras medirte contra tus amigos para ver quien es el conductor mas veloz.\n");
		if(contPartidas==1) System.out.println("Las reglas son las siguientes:\n\nAl iniciar deberas elegir la cantidad de jugadores a los cuales se les asignará un carro, y competirán en una pista de carreras \ndonde sus movimientos serán dados de forma aleatoria, el primero en llegar a la distancia indicada por cada recorrido de la pista\nsera el ganador.\n");
        System.out.print("Indica la cantidad de jugadores para la carrera (El numero minimo de competidores es 3):\n\n ");
       
	}
	
	// crea la cantidad de jugadores dada por el usuario y los agrega en un arrayList
	private static ArrayList<Jugador> createJugador() {  
		
		Scanner sc = new Scanner(System.in);
		int cantJugadores = sc.nextInt();
		
		ArrayList<Jugador> Jugadores = new ArrayList<Jugador>();   

		System.out.print("\nOk, vamos a registrarlos");

		String name;
		
		//new java.util.Scanner(System.in).nextLine();
		
		for(int i=0; i<cantJugadores; i++) {
			
			System.out.println("\nEscriba el nombre del jugador Nro. "+ (i+1)+ "\n     ");
			name = sc.next();
			Jugador jugador = new Jugador(name, i+1);
			Jugadores.add(jugador);
		
		}
				
		return Jugadores;
	
	}

	// Crea los conductores, ubicandole a cada jugador un carro para la carrera.
	private static ArrayList<Conductor> createConductor( ArrayList<Jugador> Jugadores ) { 
		
		Scanner sc = new Scanner(System.in);
		int elegido;
		
		System.out.println("\nGenial, ahora vamos a elegir los vehiculos");
		
		ArrayList<Carro> Carros = createCarro(Jugadores.size());
		
		ArrayList<Conductor> Conductores = new ArrayList<Conductor>();
		
		
		for(int i=0; i<Jugadores.size(); i++) {
			
			System.out.println("\nQue numero de auto quieres para el jugador: " + Jugadores.get(i).getName());
			System.out.print("Carros disponibles: ");
			
			for(int j=0; j<Carros.size(); j++) {
				System.out.print(Carros.get(j).getID() + " ");
			}
			
			System.out.println();
			
			elegido = sc.nextInt();
			
			for(int j=0; j<Carros.size(); j++) {
				
				if(Carros.get(j).getID() == elegido) {
					
					Conductor conductor = new Conductor(Carros.get(j),Jugadores.get(i));
					Conductores.add(conductor);
					Carros.remove(j);
				}

			}
				
			System.out.println();
		}
		
		return Conductores;
	}
	
	//Crea los carros elegibles por los conductores
	private static ArrayList<Carro> createCarro(int cantJugadores) {
		
		ArrayList<Carro> Carros = new ArrayList<Carro>();
		
		for(int i=0;i<cantJugadores;i++) {
			Carro carro = new Carro(i+1);		
			Carros.add(carro);
		}
		
		return Carros;
	}
	
	//Crea carriles y los asigna a un carro sobre la pista
	private static ArrayList<Carril> setCarriles(ArrayList<Conductor> conductores) {
		
		ArrayList<Carril> carriles = new ArrayList<Carril>();
		
		for(int i=0; i<conductores.size(); i++) {
			Carril carril=new Carril(conductores.get(i).getCarro());
			carriles.add(carril);
		}
		
		System.out.println("Listo!, los carriles quedaron asignados de la siguiente manera:\n");
		
		for(int i=0; i<carriles.size(); i++) {
			System.out.println("Carril No " + (i+1) + ", con carro No " + carriles.get(i).getCarro().getID() + " - manejado por: "+conductores.get(i).getJugador().getName());
		}
		
		return carriles;
	}
	
	
	//Crea la pista y se ubican los carriles dentro, tambien se indican la cantidad de recorridos que tendra.
	private static Pista createPista(ArrayList<Carril> carriles) throws ParseException {
		
		Scanner sc = new Scanner(System.in);
		Pista pista = new Pista(carriles);
		int cantRecorridos;
		
		System.out.println("\nPara continuar elige cuantos recorridos diferentes quieres para la carrera: ");
		
		cantRecorridos = sc.nextInt();
		
		pista.setRecorridos(cantRecorridos);
		
		System.out.println(pista.printRecorridos());
		
		return pista;
		
	}
	
	
	// empieza el juego iterando en los recorridos de la pista.
	private static Podio startJuego(ArrayList<Conductor> conductores, Pista pista) {
		System.out.println("Todo esta listo para comenzar la carrera!\n ");
		boolean modo=chooseModo();
		
		Podio podio=new Podio();
		
		for(int i = 0; i<pista.cantRecorridos(); i++) {
			
			printNRecorrido(i);
			int terminaron=0;
			
			while(terminaron < conductores.size()) {
				for(int j = 0;j<conductores.size(); j++ ) {	
					
					double dado = Math.random()*6+1;					
					int carrilActual = pista.findCarril(conductores.get(j).getCarro().getID());
					int incremento = ((int)(dado));
					
					if(pista.getCarril(carrilActual).getDistancia()< pista.getRecorrido(i)) {
						
						pista.getCarril(carrilActual).increaseDistancia(incremento);
						conductores.get(j).increaseTurnos();
						
						System.out.println("<>Turno jugador: "+ conductores.get(j).getJugador().getName());
						if(modo) {
							System.out.println("\nPulsa enter para lanzar el dado");
							new java.util.Scanner(System.in).nextLine();
						}
						System.out.println("\nSacaste "+ ((int)dado)+ "\nTe moviste en el carril " + (carrilActual+1)+ ". Distancia recorrida actual: " + pista.getCarril(carrilActual).getDistancia()+"\n\n-----\n");
						
						if(pista.getCarril(carrilActual).getDistancia()>= pista.getRecorrido(i)) {
							
							terminaron++;
							System.out.println("Terminaste el recorrido en " + conductores.get(j).getTurnos() + " turnos" );
							conductores.get(j).resetTurnos();
							podio = setPodio(terminaron,j, podio);
								
						}
					}
				}
			}
			
			resetDistancias(conductores,pista);
		}
		System.out.println("\nPulsa enter para ver el podio de todos los recorridos");
		new java.util.Scanner(System.in).nextLine();
		return podio;
	}
	
	//imprime el numero del recorrido actual
	private static void printNRecorrido(int i) {
		if(i>0) {
			System.out.println("\nPulsa enter para empezar el recorrido No. " + (i+1));
			new java.util.Scanner(System.in).nextLine();
		}
		System.out.println("---Recorrido No. " + i + "---\n");
	}
	
	//Metodo para elegir si se desea jugar en moto automatico o en manual.
	private static boolean chooseModo() {
		
		Scanner sc = new Scanner(System.in);
		String respuesta;

		do {
			System.out.println("Quieres probar el modo manual o modo automatico?(M/A)\n ");
			respuesta = sc.next();
			if(respuesta.equalsIgnoreCase("m")){
				return true;
			}else if(respuesta.equalsIgnoreCase("a")) { 
				return false;
			}
		}while(respuesta.equalsIgnoreCase("m")==false && respuesta.equalsIgnoreCase("a")==false);
		
		return false;
	}

	//reinicia las distancias de los carriles al empezar un nuevo recorrido en la pista
	private static void resetDistancias(ArrayList<Conductor> conductores, Pista pista) {
		
		for(int j=0;j<conductores.size(); j++) {	
			pista.getCarril(j).resetDistancia();		
		}
	}
	
	
	//Asigno el podio a los 3 primeros e indica el numero de lugar de los demas competidores.
	private static Podio setPodio(int terminaron, int j, Podio podio) {
		
		 if(terminaron==1) {
			System.out.println("¡¡¡FELICIDADES!!! Ocupaste el primer lugar.\n");
			podio.setFirstPlace(j);			 
		 }else if(terminaron==2) {
			System.out.println("¡¡Bien Hecho!! Ocupaste el segundo lugar.\n");
			podio.setSecondPlace(j);			 
		 }else if(terminaron==3) {
			System.out.println("¡¡Muy bien!! Ocupaste el tercer lugar.\n");
			podio.setThirdPlace(j);	 
		 }else {
			System.out.println("Ocupaste la posicion Nro. " + terminaron);

		 }
		 return podio;

	}
	
	//Almacena los podios en un archivo .txt
	private static void savePodios(String resultados, int contPartidas) {
		 LocalDate now = LocalDate.now();
		 String frase = now + "\n" +resultados;
		 

		 try {
			FileWriter escritura = new FileWriter("Resultados\\ResultadosCarrera"+contPartidas+".txt");

		    for(int i = 0; i < frase.length(); i++) {
		    	 escritura.write(frase.charAt(i));
		    }
		    escritura.close();
		    
		 	} catch (IOException e) {
		        e.printStackTrace();
		    }
		    
		 System.out.println("\n\n Un archivo .txt fue creado con las estadisticas de cada recorrido de la partida\n\n");
	}
}
	