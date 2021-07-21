package ped1;

import java.util.ArrayList;
/**
 * Clase principal que contiene el método main, del torneo de tenis
 * @author Cervera
 *
 */
public class Torneo {
	
	public static void main(String[] args) {
		
		Cuadrantes cuadrantes = new Cuadrantes();
		ArrayList<String> listaNombresJugadores = new ArrayList<String>(); 
		Fichero fichero = new Fichero();
		
		//Tratamos los parámetros introducidos por consola
		switch(args.length) {
		case 0: //no hay argumentos
			System.err.println("Debe insertar parámetros n o -h para obterner ayuda");
			System.exit(1);
			break;
		case 1: //el primer parámetro puede ser -h o n
			if (args[0].equals("-h")) {
				imprimirAyuda(args[0]);
			}
			else if (esNumero(args[0])){
				int n = parseN(args[0]); //n es el número de jugadores del torneo
				int nAux = n; //variable auxiliar para comprobar que n sea potencia de dos
				int dia = n - 1; //número de días del torneo
				int t = 0; //variable para activar la traza de cada invocación recursiva
				int tabla[][] = new int[n][dia]; //inicializamos la tabla del torneo
				if (potenciaDos(nAux) == 1 && n > 1) {
					cuadrantes.construirCuadrantes(n, tabla, t);
					imprimirTabla(n, tabla);
				}
				else {
					System.err.println("El número de jugadores del torneo debe ser potencia de 2 y mayor que 1");
					System.exit(1);
				}
			}
			break;
		case 2: //los parámetros pueden ser -t y n, o n y fichero_entrada
			if (args[0].equals("-t") && esNumero(args[1])) {
				int n = parseN(args[1]);
				int nAux = n;
				int dia = n - 1; 
				int t = 1; 
				int tabla[][] = new int[n][dia]; 
				if (potenciaDos(nAux) == 1 && n > 1) {
					cuadrantes.construirCuadrantes(n, tabla, t);
					imprimirTabla(n, tabla);
				}
				else {
					System.err.println("El número de jugadores del torneo debe ser potencia de 2 y mayor que 1");
					System.exit(1);
				}
			}
			else if (esNumero(args[0]) && args[1]!="") {
				fichero.leerFichero(args[1]);
				listaNombresJugadores = fichero.listaJugadores();
				int n = parseN(args[0]); //n es el número de jugadores del torneo
				
				//se comprueba si coincide el valor n jugadores con el numero de nombres del fichero de entrada
				if (n != fichero.numeroJugadores()) {
					System.err.println("El numero de jugadores n debe coincidir con el número de nombres contenidos en el fichero");
				} 
					
				int nAux = n; //variable auxiliar para comprobar que n sea potencia de dos
				int dia = n - 1; //número de días del torneo
				int t = 0; //variable para activar la traza de cada invocación recursiva
				int tabla[][] = new int[n][dia]; //inicializamos la tabla del torneo
				if (potenciaDos(nAux) == 1 && n > 1) {
					cuadrantes.construirCuadrantes(n, tabla, t);
					imprimirTablaConNombres(n, tabla, listaNombresJugadores);
				}
				else {
					System.err.println("El número de jugadores del torneo debe ser potencia de 2 y mayor que 1");
					System.exit(1);
				}
			}
			break;
		case 3: //los parámetros pueden ser -t n y fichero_entrada
			if (args[0].equals("-t") && esNumero(args[1]) && args[2]!="") {
				fichero.leerFichero(args[2]);
				listaNombresJugadores = fichero.listaJugadores();
				int n = parseN(args[1]);
				
				//se comprueba si coincide el valor n jugadores con el numero de nombres del fichero de entrada
				if (n != fichero.numeroJugadores()) {
					System.err.println("El numero de jugadores n debe coincidir con el número de nombres contenidos en el fichero");
				} 
				
				int nAux = n;
				int dia = n - 1; 
				int t = 1; 
				int tabla[][] = new int[n][dia]; 
				if (potenciaDos(nAux) == 1 && n > 1) {
					cuadrantes.construirCuadrantes(n, tabla, t);
					imprimirTablaConNombres(n, tabla, listaNombresJugadores);
				}
				else {
					System.err.println("El número de jugadores del torneo debe ser potencia de 2 y mayor que 1");
					System.exit(1);
				}
			}
			break;
		default: 
			System.err.println("Debe introducir un número de parámetros correcto, [-h] [-t] n [fichero_entrada] (máximo 3 parámetros)");
			System.exit(1);
		}
	}
	
	/**Método que se encarga de pasar String a Entero
	 * 
	 * @param arg es el argumento String que le pasamos al método main
	 * @return n es el argumento pasado a Entero
	 */
	static int parseN (String arg) {
		int n = Integer.parseInt(arg);
		return n;
	}
	
	/**Método para saber si un número es potencia de 2
	 * 
	 * @param n es el número que queremos verificar si es potencia de dos
	 * @return 1 si n es potencia de dos, o devuelve 0 si no lo es
	 */
	static int potenciaDos(int n) {
		int potencia = 2;
		while(potencia <= n) {
			if (n == potencia) {
				return 1;
			}
			else {
				potencia = potencia * 2;
			}
		}
		return 0;
	}
	
	/**
	 * Método para saber si una cadena es un número
	 * @param cadena cadena que se desea saber si es número
	 * @return true or false
	 */
	static boolean esNumero(String cadena){
		try {
			Integer.parseInt(cadena);
			return true;
		} catch (NumberFormatException nfe){
			return false;
		}
	}
	
	//Método que imprime la tabla del torneo
	static void imprimirTabla(int n, int tabla[][]) {
		int dias;
		dias = n-1;
		
		System.out.print("\n[ JUG ]");
		for (int i = 1; i <= dias; i++) {
			if (i < 10) {
				System.out.print("[ Dia " + i + " ]");
			}
			else {
				System.out.print("[Dia " + i + " ]");
			}
		}
		
		//Impresión de valores
		for(int i = 0; i <= dias; i++) {
			if(i < 10) {
				System.out.print("\n[ J " + (i+1) + " ]");
			}
			else {
				System.out.print("\n[ J " + (i+1) + "]");
			}

				for(int j=0; j < dias; j++) {
					if(tabla[i][j] < 10) {
						System.out.print("[  " + tabla[i][j] + "    ]");
					}
					else {
						System.out.print("[  " + tabla[i][j] + "   ]");
						}
					}
			}
	}
	
	//Método que imprime la tabla del torneo si se introduce un fichero de entrada
		static void imprimirTablaConNombres(int n, int tabla[][], ArrayList<String> listaNombres) {
			int dias;
			dias = n-1;
			
			System.out.print("\n[	 JUG	   ]");
			for (int i = 1; i <= dias; i++) {
				if (i < 10) {
					System.out.printf("[%15s", "      Dia " + i +  "      ]");
				}
				else {
					System.out.printf("[%15s", "      Dia " + i +  "     ]");
				}
			}
			
			//Impresión de valores
			for(int i = 0; i <= dias; i++) {
				System.out.printf("\n" + "[ J" + (i+1) + " " + listaNombres.get(i) + "%2s","]");

					for(int j=0; j < dias; j++) {
						System.out.print("[  " + listaNombres.get(tabla[i][j]-1) + "    ]");
					}
			}
		}
	
	//Método para imprimir ayuda si se introduce -h como primer parámetro
	static void imprimirAyuda(String arg) {
		if (arg.equals("-h")) {
			System.out.println("SINTAXIS: torneo [-t][-h] n [fichero entrada]\n"
					+ "[-t]		Traza la selección de clientes\n"
					+ "[-h]		Muestra esta ayuda\n"
					+ "n		Número de jugadores\n"
					+ "[fichero entrada]	Listado de los nombres de los jugadores del torneo\n");
		}
	}
	
}
