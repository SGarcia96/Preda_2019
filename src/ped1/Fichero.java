package ped1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Clase que se encarga de leer un fichero con el nombre de los jugadores y devuelve un Array con el nombre de estos
 *
 */
public class Fichero {
	
	private ArrayList<String> nombresJugadores = new ArrayList<String>();
	
	////////////////////MÉTODOS///////////////////////
	
	/**
	 * método que devuelve la lista con los nombres de los jugadores
	 */
	public ArrayList<String> listaJugadores() {
		return nombresJugadores;
	}
	
	/**
	 * método que devuelve el número de nombres de la lista
	 */
	public int numeroJugadores() {
		return nombresJugadores.size();
	}
	
	/**
	 * método que imprime los nombres de los jugadores del fichero
	 */
	public void printNombresJugadores() {
		for (int i=0; i < nombresJugadores.size(); i++) {
			System.out.println(nombresJugadores.get(i));
		}
	}
	
	/**
	 * método que devuelve el número de nombres del fichero
	 * @return
	 */
	public int sizeNombresJugadores() {
		return nombresJugadores.size();
	}
	
	/**
	 * método que lee el fichero introducido por parámetro, que será la lista con los nombres de los jugadores del torneo
	 * y los almacena en una lista
	 * @param fichero
	 */
	public void leerFichero(String fichero) {
		try {
			BufferedReader entrada = new BufferedReader(new FileReader(fichero));
			try {
				String linea;
				while ((linea=entrada.readLine()) != null) {
					nombresJugadores.add(linea);
				}
			entrada.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			 System.out.println("No se encontro el archivo: " + fichero);
		}
	}
}
