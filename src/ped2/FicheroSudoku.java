package ped2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Clase que se encarga de leer un fichero con un sudoku contenido y lo almacena en una matriz
 *
 */
public class FicheroSudoku {
	
	private int[][] sudoku = new int[9][9];
	boolean ficheroNoEncontrado = false;
	
////////////////////MÉTODOS///////////////////////
	
	/**
	 * método que devuelve la matriz con el sudoku leido
	 */
	public int[][] getSudoku() {
		return sudoku;
	}

	/**
	 * método que devuelve el tamaño del sudoku
	 */
	public int tamanyoSudoku() {
		return sudoku.length;
	}

	/**
	 * método que nos indica si se ha encontrado el fichero o no, si no se ha encontrado, 
	 * resolveremos el sudoku predeterminado de la clase Sudoku
	 */
	public boolean getFicheroNoEncontrado() {
		return ficheroNoEncontrado;
	}
	
	/**
	 * método que lee el fichero que contiene el sudoku a resolver introducido por parámetro 
	 * y lo almacena en una matriz que será el sudoku 
	 * @param fichero
	 */
	public void leerFichero(String fichero) {
		try {
			BufferedReader entrada = new BufferedReader(new FileReader(fichero));
			try {
				String linea = entrada.readLine();
				int fila = 0;
				while (linea != null) {
					String[] numeros = linea.split(" "); //dividimos los numeros separados por un espacio y los almacenamos en un array
					for (int i = 0; i < numeros.length; i++) {
						if(numeros[i].equals("-")) {
							numeros[i] = numeros[i].replace('-', '0'); //sustituimos los "-" por 0´s
						}
						sudoku[fila][i] = Integer.parseInt(numeros[i]);
					}
					fila++;
					linea = entrada.readLine(); //leemos la siguiente linea
				}
				entrada.close();
				ficheroNoEncontrado = false;
			} catch (IOException e) {
				e.printStackTrace();
			} catch (NumberFormatException e) {
				System.out.println("No se pudo convertir a entero");
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			System.out.println("No se encontro el archivo: " + fichero +"\n");
			ficheroNoEncontrado = true;
		}
	}
	
	/**
	 * método que imprime el sudoku leido
	 */
	public void printSudoku() {
		for (int fila = 0; fila < 9; fila++) {
			for (int columna = 0; columna < 9; columna++) {
				System.out.print(sudoku[fila][columna] + " ");
			}
			System.out.println();
		}
	}
}
