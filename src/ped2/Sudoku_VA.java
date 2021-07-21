package ped2;
/**
 * Clase encargada de construir el sudoku mediante la estrategia de Vuelta atrás
 * @author Cervera
 *
 */
public class Sudoku_VA {
	
	private boolean t; //parámetro de traza
	
	/**
	 * método que construye el sudoku de 9x9 utilizando vuelta atrás
	 * @param solucion matriz parcial inicial del sudoku a resolver
	 * @param N tamaño del sudoku
	 * @return
	 */
	public boolean sudoku_VA(int[][] solucion, int N) {
		int fila = -1;
		int columna = -1;
		boolean celdaVacia = true;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (solucion[i][j] == 0) {
					fila = i;
					columna = j;
					celdaVacia = false;
					break;
				}
			}
			if (!celdaVacia) {
				break;
			}
		}

		//No quedan espacios vacíos
		if (celdaVacia) {
			return true;
		}

		//Rellenamos los espacios vacíos con los números correctos
		for (int num = 1; num <= N; num++) {
			if (esFactible(solucion, fila, columna, num)) {
				solucion[fila][columna] = num;
				if (sudoku_VA(solucion, N)) {	//si se ha llegado a la solución, procesa la solución (se imprime sudoku)
					// imprimir sudoku
					return true;
				}
				else{
					//sustituye la celda
					solucion[fila][columna] = 0;
				}
			}
		}
		return false;
	}
	
	/**
	 * metodo que comprueba la factibilidad de una solución parcial 
	 * (que una celda no repita su valor en la misma fila, columna, o subgrupo de 3x3
	 * @param solucion
	 * @param fila
	 * @param columna
	 * @param numero
	 * @return
	*/
	private boolean esFactible(int[][] solucion, int fila, int columna, int numero) {
		for (int col = 0; col < solucion.length; col++) {		//Se comprueban las filas
			if (solucion[fila][col] == numero) {
				return false;
			}
		}

		for (int fil = 0; fil < solucion.length; fil++) {	//Se comprueban las columnas
			if (solucion[fil][columna] == numero) {
				return false;
			}
		}

		//Se comprueban los cuadrantes 3x3
		int sqrt = (int)Math.sqrt(solucion.length);	//Almacenamos en sqrt la raiz cuadrada del tamaño del sudoku: sqrt(9)=3, la usamos para medir los cuadrantes 3x3
		int filaInicio = fila - fila % sqrt;
		int columnaInicio = columna - columna % sqrt;
		
		for (int fil = filaInicio; fil < filaInicio + sqrt; fil++) {
			for (int col = columnaInicio; col < columnaInicio + sqrt; col++) {
				if (solucion[fil][col] == numero) {
					return false;
				}
			}
		}
		
		if (getTraza()) {
			System.out.println("fila="+ fila + " columna=" + columna + " numero:" + numero);
		}
		//si no hay coincidencia del mismo número en una fila, columna, o subgrupo de 3x3, devuelve true
		return true;
	}
	
	/**
	 * metodo que activa o desactiva la impresión de la traza de los parámetros si se introduce -t o no
	 * @param t
	 */
	public void setTraza(int t){
		if (t==1) {
			this.t = true;
		}
		else if (t==0) {
			this.t = false;
		}
		else {
			System.err.println("-t: parámetro válido: 1 on, 0 off");
		}
	}
	
	/**
	 * metodo que devuelve el valor t para imprimir o no la traza de parámetros
	 * @return
	 */
	public boolean getTraza() {
		return this.t;
	}

	/**
	 * metodo que imprime el sudoku
	 * @param solucion
	 * @param N
	 */
	public void imprimirSolucion(int[][] solucion, int N) {
		for (int fila = 0; fila < N; fila++) {
			for (int columna = 0; columna < N; columna++) {
				System.out.print(solucion[fila][columna] + " ");
			}
			System.out.println();
			
			if ((fila + 1) % (int)Math.sqrt(N) == 0) {
				System.out.print("");
			}
		}
	}
}