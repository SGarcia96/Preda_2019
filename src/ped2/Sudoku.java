package ped2;

public class Sudoku {

	public static void main(String[] args) {
		
		Sudoku_VA sudoku = new Sudoku_VA();
		FicheroSudoku fichero = new FicheroSudoku();
		
		//cuadricula es la entrada estandar(inicial) del sudoku si no se introduce un fichero de entrada correcto
		int[][] cuadricula = { { 3, 0, 6, 5, 0, 8, 4, 0, 0 },
							   { 5, 2, 0, 0, 0, 0, 0, 0, 0 },
							   { 0, 8, 7, 0, 0, 0, 0, 3, 1 },
							   { 0, 0, 3, 0, 1, 0, 0, 8, 0 },
							   { 9, 0, 0, 8, 6, 3, 0, 0, 5 },
							   { 0, 5, 0, 0, 9, 0, 6, 0, 0 },
							   { 1, 3, 0, 0, 0, 0, 2, 5, 0 },
							   { 0, 0, 0, 0, 0, 0, 0, 7, 4 },
							   { 0, 0, 5, 2, 0, 6, 3, 0, 0 } };
		int N = cuadricula.length; //tamaño del sudoku
		
		
		//Tratamos los parámetros introducidos por consola
		switch(args.length) {
		case 0: //no hay argumentos
			System.err.println("Debe insertar parámetros: -h para obterner ayuda");
			System.exit(1);
			break;
		case 1: //el primer parámetro puede ser -h o fichero entrada
			if (args[0].equals("-h")) {
				imprimirAyuda(args[0]);
			}
			else if (args[0]!=""){
				//si el archivo no existe carga el sudoku estandar contenido en cuadricula, si no, introduce archivo
				fichero.leerFichero(args[0]);
				
				if (fichero.getFicheroNoEncontrado()) { //si el archivo no existe cargamos el sudoku predeterminado
					System.out.println("Sudoku inicial predeterminado: \n");
					printSudoku(cuadricula);
					System.out.println();
					System.out.println("Sudoku resultado: \n");
					if (sudoku.sudoku_VA(cuadricula, N)) {
						sudoku.imprimirSolucion(cuadricula, N);
					}
					else {
						System.out.println("No hay solución");
					}
					
				} else { //si el archivo existe cargamos el del fichero
					System.out.println("Sudoku inicial cargado del fichero: " + args[0]);
					System.out.println();
					fichero.printSudoku();
					System.out.println();
					System.out.println("Sudoku resultado: \n");
					if (sudoku.sudoku_VA(fichero.getSudoku(), N)) {
						sudoku.imprimirSolucion(fichero.getSudoku(), N);
					}
					else {
						System.out.println("No hay solución");
					}
				}
			}
			break;
		case 2: //los parámetros pueden ser -t y fichero_entrada
			if (args[0].equals("-t") && args[1]!="") {
				fichero.leerFichero(args[1]);
				sudoku.setTraza(1);
				
				if (fichero.getFicheroNoEncontrado()) { //si el archivo no existe cargamos el sudoku predeterminado
					System.out.println("Sudoku inicial predeterminado: \n");
					printSudoku(cuadricula);
					System.out.println();
					System.out.println("Sudoku resultado: \n");
					if (sudoku.sudoku_VA(cuadricula, N)) {
						sudoku.imprimirSolucion(cuadricula, N);
					}
					else {
						System.out.println("No hay solución");
					}
					
				} else { //si el archivo existe cargamos el del fichero
					System.out.println("Sudoku inicial cargado del fichero: " + args[1]);
					System.out.println();
					fichero.printSudoku();
					System.out.println();
					System.out.println("Sudoku resultado: \n");
					if (sudoku.sudoku_VA(fichero.getSudoku(), N)) {
						sudoku.imprimirSolucion(fichero.getSudoku(), N);
					}
					else {
						System.out.println("No hay solución");
					}
				}
			}
			break;
		default: 
			System.err.println("Debe introducir un número de parámetros correcto, [-h] [-t] [fichero_entrada] (máximo 2 parámetros)");
			System.exit(1);
		}
	}
	
	//Método para imprimir ayuda si se introduce -h como primer parámetro
	static void imprimirAyuda(String arg) {
		if (arg.equals("-h")) {
			System.out.println("SINTAXIS: sudoku [-t][-h] [fichero entrada]\n"
					+ "[-t]		Traza cada llamada recursiva y sus parámetros\n"
					+ "[-h]		Muestra esta ayuda\n"
					+ "[fichero entrada]	Tabla inicial sudoku\n");
		}
	}
	
	/**
	 * método que imprime el sudoku inicial
	 */
	static void printSudoku(int[][] sudoku) {
		for (int fila = 0; fila < 9; fila++) {
			for (int columna = 0; columna < 9; columna++) {
				System.out.print(sudoku[fila][columna] + " ");
			}
			System.out.println();
		}
	}

}
